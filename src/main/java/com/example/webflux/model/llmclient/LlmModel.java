package com.example.webflux.model.llmclient;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.example.webflux.model.llmclient.LlmType.GEMINI;
import static com.example.webflux.model.llmclient.LlmType.GPT;

@AllArgsConstructor
@Getter
public enum LlmModel {
    GPT_4O("gpt-4o", GPT),
    GEMINI_2_0_FLASH("gemini-2.0-flash", GEMINI),
    GPT_5_NANO("gpt-5-nano", GPT);

    private final String code;
    private final LlmType llmType;

    @JsonValue
    @Override
    public String toString() {
        return  code;
    }
}
