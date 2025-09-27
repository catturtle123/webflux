package com.example.webflux.model.llmclient.jsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnswerListResponseDto {
    private List<String> answerList;

    @Override
    public String toString() {
        return String.join(",", answerList);
    }
}
