package com.example.webflux.model.llmclient.gpt.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GptChoice implements Serializable {
    @Serial
    private static final long serialVersionUID = -7902753705481923150L;

    private String finish_reason;
    private GptResponseMessageDto message;
    private GptResponseMessageDto delta;
}
