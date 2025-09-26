package com.example.webflux.model.llmclient.gpt.response;

import com.example.webflux.exception.CustomErrorType;
import com.example.webflux.exception.ErrorTypeException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GptChatResponseDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -682529784853121165L;

    private List<GptChoice> choices;

    public GptChoice getSingleChoice() {
        return choices.stream().findFirst().orElseThrow(() -> {
            return new ErrorTypeException("[GPTResponse] There is no choices.", CustomErrorType.GPT_RESPONSE_ERROR);
        });
    }
}
