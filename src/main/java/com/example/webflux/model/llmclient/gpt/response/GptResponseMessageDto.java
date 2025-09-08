package com.example.webflux.model.llmclient.gpt.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GptResponseMessageDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -8049440189871211740L;

    private String content;
}
