package com.example.webflux.chapter2;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class OperatorFlatMapTest {

    /**
     * Mono 구조에서 Mono를 빼줌 ex) Flux<Mono<T>>
     */

    @Test
    public void monoToFlux() {
        Mono<Integer> one = Mono.just(1);
        Mono<Flux<Integer>> map = one.map(data -> {
            return Flux.just(data, data + 1, data + 2);
        });

        map.subscribe(System.out::println);
    }

    @Test
    public void testWebClientFlatMap() {
        Flux.just(
                callWebClient("1단계 문제 이해하기", 1500),
            callWebClient("2단계", 1000),
            callWebClient("3단계", 1500)
        );

        Flux<String> stringFlux = Flux.<Mono<String>>create(sink -> {
                    sink.next(callWebClient("1단계 문제 이해하기", 1500));
                    sink.next(callWebClient("2단계 문제 이해하기", 500));
                    sink.next(callWebClient("3단계 문제 이해하기", 1000));
                    sink.complete();
                }
        ).flatMapSequential(stringMono -> {
            return stringMono;
        });

        stringFlux.subscribe(System.out::println);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Mono<String> callWebClient(String request, long delay) {
        return Mono.defer(() -> {
            try {
                Thread.sleep(delay);
                return Mono.just(request + " -> 딜레이 : " + delay);
            } catch (InterruptedException e) {
                return Mono.empty();
            }

        }).subscribeOn(Schedulers.boundedElastic());
    }
}
