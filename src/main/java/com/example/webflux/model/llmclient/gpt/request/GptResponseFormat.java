package com.example.webflux.model.llmclient.gpt.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GptResponseFormat implements Serializable {

    @Serial
    private static final long serialVersionUID = -6472910294018165732L;

    private String type;
}
