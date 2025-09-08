package com.example.webflux.chapter2;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class SchedulerTest {

    @Test
    public void testBasicFluxMono() {
        Mono.<Integer>just(2)
                .map(data -> data * 2)
                .publishOn(Schedulers.parallel())
                .filter(data -> {
                    System.out.println(Thread.currentThread().getName());
                    return data % 4 == 0;
                })
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(System.out::println);
    }
}
