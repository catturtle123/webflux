package com.example.webflux.model.user.chat;

import com.example.webflux.exception.CommonError;
import com.example.webflux.model.llmclient.LlmChatResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserChatResponseDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -4328454630863705651L;

    private String response;
    private String title;
    private CommonError error;

    public UserChatResponseDto(LlmChatResponseDto llmChatResponseDto) {
        this.title = llmChatResponseDto.getTitle();
        this.response = llmChatResponseDto.getLlmResponse();
        this.error = llmChatResponseDto.getError();
    }

    public UserChatResponseDto(String title, String response) {
        this.response = response;
        this.title = title;
    }
}
