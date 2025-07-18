package com.tatastrive.app.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class QuestionDTO {
    private Long id;
    private String questionText;
    private int timeLimitSeconds;
    private Long quizId;
    private List<OptionDTO> options;
    private int correctOptionIndex;
}