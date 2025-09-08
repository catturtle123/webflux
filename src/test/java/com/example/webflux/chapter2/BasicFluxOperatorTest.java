package com.example.webflux.chapter2;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.util.context.Context;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BasicFluxOperatorTest {

    /**
     * 데이터: just, empty, from ~시리즈
     * 함수: defer, create
     */
    @Test
    public void testFluxFromDate() {
        Flux.just(1,2)
                .subscribe(System.out::println);

        List<Integer> basicList = List.of(1,2);
        Flux.fromIterable(basicList)
                .subscribe(System.out::println);
    }

    /**
     * Flux defer -> 안에서 Flux 객체를 반환해줘야 합니다.
     */
    @Test
    public void testFluxFromFunction() {
        Flux.defer(() -> Flux.just(1,2)).subscribe(System.out::println);

        Flux.create(sink ->{
            sink.next(1);
            sink.next(2);
            sink.complete();
        }).subscribe(System.out::println);
    }

    @Test
    public void testSinkDetail() {
        Flux.<String>create(sink ->{
            AtomicInteger atomicInteger = new AtomicInteger(0);
            recursiveFunction(sink);
        })
                .contextWrite(Context.of("counter", new AtomicInteger(0)))
                .subscribe(System.out::println);
    }

    public void recursiveFunction(FluxSink<String> sink) {
        AtomicInteger counter = sink.contextView().get("counter");
        if (counter.incrementAndGet() < 10) {
            sink.next(""+counter);
            recursiveFunction(sink);
        } else {
            sink.complete();
        }
    }

    //ThreadLocal -> context
}
