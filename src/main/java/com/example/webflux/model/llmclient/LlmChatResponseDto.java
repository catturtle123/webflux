package com.example.webflux.model.llmclient;

import com.example.webflux.exception.CommonError;
import com.example.webflux.model.llmclient.gemini.response.GeminiChatResponseDto;
import com.example.webflux.model.llmclient.gpt.response.GptChatResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Slf4j
public class LlmChatResponseDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -6090498709200577316L;

    private String llmResponse;
    private CommonError error;
    private String title;

    public boolean isValid() {
        return Optional.ofNullable(error).isEmpty();
    }

    public LlmChatResponseDto(String title, String llmResponse) {
        this.title = title;
        this.llmResponse = llmResponse;
    }

    public LlmChatResponseDto(CommonError error) {
        log.error("[LlmResponseError] LlmResponseError: {}", error);
        this.error = error;
    }

    public LlmChatResponseDto(CommonError error, Throwable ex) {
        log.error("[LlmResponseError] LlmResponseError: {}", error, ex);
        this.error = error;
    }

    public LlmChatResponseDto(String llmResponse) {
        this.llmResponse = llmResponse;
    }

    public LlmChatResponseDto(GptChatResponseDto gptChatResponseDto) {
        this.llmResponse = gptChatResponseDto.getSingleChoice().getMessage().getContent();
    }

    public static LlmChatResponseDto getLlmChatResponseFromStream(GptChatResponseDto gptChatResponseDto) {
        return new LlmChatResponseDto(gptChatResponseDto.getSingleChoice().getDelta().getContent());
    }

    public LlmChatResponseDto(GeminiChatResponseDto geminiChatResponseDto) {
        this.llmResponse = geminiChatResponseDto.getSingleText();
    }
}
