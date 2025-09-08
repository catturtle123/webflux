package com.example.webflux.model.llmclient.gemini.request;

import com.fasterxml.jackson.annotation.JsonValue;

public enum GeminiMessageRole {
    MODEL,
    USER;

    @JsonValue
    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
