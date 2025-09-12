package com.example.webflux.service.llmclient;

import com.example.webflux.model.llmclient.LlmChatRequestDto;
import com.example.webflux.model.llmclient.LlmChatResponseDto;
import com.example.webflux.model.llmclient.LlmType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LlmWebClientService {
    Mono<LlmChatResponseDto> getChatCompletion(LlmChatRequestDto requestDto);

    LlmType getLlmType();

    Flux<LlmChatResponseDto> getChatCompletionStream(LlmChatRequestDto llmChatResponseDto);
}
