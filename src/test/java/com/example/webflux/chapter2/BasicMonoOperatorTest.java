package com.example.webflux.chapter2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@SpringBootTest
public class BasicMonoOperatorTest {

    // just, empty
    @Test
    public void startMonoFromDate() {
        Mono.just(1).subscribe(data -> System.out.println("data = " + data));

        Mono.empty().subscribe(data -> System.out.println("data = " + data));
    }

    //fromCallable, defer
    /**
     * fromCallable -> 동기적인 객체를 Mono로 반환할 때 사용, JPA, RestTemplate 사용할 때 많이 사용
     * defer -> Mono를 반환하고 싶을 때 사용, 그냥 Mono.just는 도달 시점에서 차이가 있음
     */
    @Test
    public void startMonoFromFunction() {
        Mono<String> monoFromCallable = Mono.fromCallable(() -> {
            // 우리 로직을 실행하고
            return "안녕";
        });

        Mono<String> monoFromDefer = Mono.defer(() -> {
            return Mono.just("안녕!");
        });
    }

    @Test
    public void testDeferNecessity() {
        Mono.defer(() ->{
            String a = "안녕";
            String b = "하세요";

            return Mono.just(a + b + "request");
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Test
    public void monoToFlux() {
        Mono<Integer> one = Mono.just(1);
        one.flatMapMany(data->{
            return Flux.just(data, data + 1, data + 2);
        });
    }
}
