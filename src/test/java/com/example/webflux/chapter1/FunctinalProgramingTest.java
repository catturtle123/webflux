package com.example.webflux.chapter1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.IntStream;

@SpringBootTest
public class FunctinalProgramingTest {

    @Test
    void produceOneToNine() {
        List<Integer> sink = new ArrayList<>();
        for (int i = 1; i <= 9; i++){
            sink.add(i);
        }

        // *2를 해주고 싶다
        sink = map(sink, (data) -> data * 4);
        // 4의 배수들만 남겨두고 싶다
        sink = filter(sink, (data) -> data % 4 == 0);
        forEach(sink, (data) -> System.out.println(data));
    }

    @Test
    void produceOneToNineStream() {
        IntStream.range(1, 9).boxed()
                .map((data) -> data * 4)
                .filter((data) -> data % 4 == 0)
                .forEach((data) -> System.out.println(data));
    }

    @Test
    void produceOneToNineFlux() {
        Flux<Integer> flux = Flux.create(sink -> {
            for (int i = 1; i <= 9; i++) {
                sink.next(i);
            }
            sink.complete();
        });

        flux.subscribe(data -> System.out.println("WebFlux가 구독 중!!: " + data));
        System.out.println("Netty 이벤트 루프로 스레드 복귀!");
    }

    @Test
    void produceOneToNineFluxOperator() {
        Flux.fromIterable(IntStream.rangeClosed(1,9).boxed().toList())
                .map((data) -> data * 4)
                .filter((data) -> data % 4 == 0)
                .subscribe((data) -> System.out.println(data));
    }

    private void forEach(List<Integer> sink, Consumer<Integer> consumer) {
        for (int i = 0; i < sink.size(); i++) {
            consumer.accept(sink.get(i));
        }
    }

    private List<Integer> filter(List<Integer> sink, Function<Integer, Boolean> predicate) {
        List<Integer> newSink2 = new ArrayList<>();
        for (int i = 0; i <= 8; i++){
            if (predicate.apply(sink.get(i))) {
                newSink2.add(sink.get(i));
            }
        }
        sink = newSink2;
        return sink;
    }

    private List<Integer> map(List<Integer> sink, Function<Integer, Integer> mapper) {
        List<Integer> newSink1 = new ArrayList<>();
        for (int i = 0; i <= 8; i++){
            newSink1.add(mapper.apply(sink.get(i)));
        }
        sink = newSink1;
        return sink;
    }
}
