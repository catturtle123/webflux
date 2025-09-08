package com.example.webflux.model.user.chat;

import com.example.webflux.model.llmclient.LlmModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserChatRequestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -4328454630863705651L;

    private String request;
    private LlmModel llmModel;
}
