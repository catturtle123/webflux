package com.example.webflux.chapter2;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class BasicFluxMonoTest {

    @Test
    public void testBasicFluxMono() {
        Flux.<Integer>just(1,2,3,4,5) // 함수로 부터, 데이터로 부터 시작 가능
                .map(data -> data * 2)
                .filter(data -> data % 4 == 0)
                .subscribe(System.out::println);

        // 1. just 데이터로부터 흐름을 시작
        // 2. map과 filter같은 연산자로 데이터 가공
        // 3. subscribe하면서 데이터를 방출

        Mono.<Integer>just(2)
                .map(data -> data * 2)
                .filter(data -> data % 4 == 0)
                .subscribe(System.out::println);
    }

    @Test
    public void testBasicCollect() {
        Mono<List<Integer>> listMono = Flux.<Integer>just(1, 2, 3, 4, 5) // 함수로 부터, 데이터로 부터 시작 가능
                .map(data -> data * 2)
                .filter(data -> data % 4 == 0)
                .collectList();

        listMono.subscribe(System.out::println);
    }

}
