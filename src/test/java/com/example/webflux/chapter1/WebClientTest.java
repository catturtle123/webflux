package com.example.webflux.chapter1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@SpringBootTest
public class WebClientTest {

    private WebClient webClient = WebClient.builder().build();

    @Test
    void testWebClient() {
        Flux<Integer> flux = webClient.get()
                        .uri("http://localhost:8080/reactive/onenine/flux")
                        .retrieve()
                        .bodyToFlux(Integer.class);

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
