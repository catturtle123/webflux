package com.example.webflux.model.llmclient.gemini.request;

import com.example.webflux.model.llmclient.gpt.request.GPTMessageRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class GeminiCompletionRequestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1612162569186439686L;

    private String content;

}
