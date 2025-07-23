package com.tatastrive.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {
    private Long id;
    private String questionText;
    private int timeLimitSeconds;
    private Long quizId;
    private List<OptionDTO> options;
    private int correctOptionIndex;
}