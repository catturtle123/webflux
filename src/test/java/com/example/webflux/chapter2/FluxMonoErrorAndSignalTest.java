package com.example.webflux.chapter2;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxMonoErrorAndSignalTest {

    @Test
    public void testBasicSignal() {

        // 각 시그널에 따라 출력됨.

        Flux.just(1,2,3,4)
                .doOnNext(System.out::println)
                .doOnComplete(() -> System.out.println("Completed"))
                .doOnError(ex -> System.out.println("Error"))
                .subscribe(data-> System.out.println("data = " + data));
    }

    @Test
    public void testBasicError() {

        // 각 시그널에 따라 출력됨.

        Flux.just(1,2,3,4)
                .map(data ->{
                            if (data == 3) {
                                throw new RuntimeException("Error");
                            }
                    return data * 2;
                })
//                .doOnError(ex -> System.out.println("Error"))
                .subscribe(data-> System.out.println("data = " + data));
    }

    @Test
    public void testFluxMonoDotError() {
        Flux.just(1,2,3,4)
                .flatMap(data->{
                    if (data != 3) {
                        return Mono.just(data);
                    } else {
                        return Mono.error(new RuntimeException("Error"));
                    }
                }).subscribe(data-> System.out.println("data = " + data));
    }
}
