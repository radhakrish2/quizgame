package com.tatastrive.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerAnswerDTO {
    private Long id;
    private Long playerId;
    private Long gameQuestionId;
    private int selectedOptionIndex;
    private boolean isCorrect;
    private int score;
}