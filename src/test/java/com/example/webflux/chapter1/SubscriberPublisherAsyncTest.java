package com.example.webflux.chapter1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@SpringBootTest
public class SubscriberPublisherAsyncTest {

    @Test
    void produceOneToNineFlux() {
        Flux<Integer> flux = Flux.<Integer>create(sink -> {
            for (int i = 1; i <= 9; i++) {

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                sink.next(i);
            }

            sink.complete();
        }).subscribeOn(Schedulers.boundedElastic());

        flux.subscribe(data -> {
            System.out.println("처리 되고 있는 쓰레드의 이름: " + Thread.currentThread().getName());
            System.out.println("WebFlux가 구독 중!!: " + data);
        });
        System.out.println("Netty 이벤트 루프로 스레드 복귀!");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
