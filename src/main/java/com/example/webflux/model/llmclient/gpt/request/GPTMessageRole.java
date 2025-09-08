package com.example.webflux.model.llmclient.gpt.request;

import com.fasterxml.jackson.annotation.JsonValue;

public enum GPTMessageRole {
    SYSTEM,
    USER,
    ASSISTANT;

    @JsonValue
    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
