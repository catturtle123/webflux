package com.example.webflux.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reactive")
@Slf4j
public class ReactiveProgrammingExampleController {

    // 1~9까지 출력하는 api
    @GetMapping("/onenine/list")
    public Mono<List<Integer>> produceOneToNine() {
        return Mono.fromCallable(()-> {
            List<Integer> sink = new ArrayList<>();
            for (int i = 1; i<=9;i++){
                Thread.sleep(500);
                sink.add(i);
            }
            return sink;
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @GetMapping("/onenine/flux")
    public Flux<Integer> produceOneToNineFlux() {
        return Flux.<Integer>create(sink -> {
            for (int i = 1; i<=9;i++){
                try {
                    log.info("현재 처리하고 있는 쓰레드 이름: {}", Thread.currentThread().getName());
                    Thread.sleep(500);
                } catch (InterruptedException e) {

                }
                sink.next(i);
            }
            sink.complete();
        }).subscribeOn(Schedulers.boundedElastic());
    }

}
