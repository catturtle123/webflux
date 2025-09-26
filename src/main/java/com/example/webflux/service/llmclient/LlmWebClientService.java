package com.example.webflux.service.llmclient;

import com.example.webflux.exception.CommonError;
import com.example.webflux.exception.CustomErrorType;
import com.example.webflux.exception.ErrorTypeException;
import com.example.webflux.model.llmclient.LlmChatRequestDto;
import com.example.webflux.model.llmclient.LlmChatResponseDto;
import com.example.webflux.model.llmclient.LlmType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LlmWebClientService {
    Mono<LlmChatResponseDto> getChatCompletion(LlmChatRequestDto requestDto);

    LlmType getLlmType();

    Flux<LlmChatResponseDto> getChatCompletionStream(LlmChatRequestDto llmChatResponseDto);

    default Mono<LlmChatResponseDto> getChatCompletionWithCatchException(LlmChatRequestDto requestDto) {
        return getChatCompletion(requestDto)
                .onErrorResume(exception -> {
                    if (exception instanceof ErrorTypeException errorTypeException) {
                        CommonError commonError = new CommonError(errorTypeException.getErrorType().getCode(), errorTypeException.getMessage());
                        return Mono.just(new LlmChatResponseDto(commonError));
                    } else {
                        CommonError commonError = new CommonError(500, exception.getMessage());
                        return Mono.just(new LlmChatResponseDto(commonError));
                    }
                });
    }
}
