package com.tatastrive.app.dto;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class GameQuestionDTO {
    private Long id;
    private Long gameSessionId;
    private Long questionId;
    private int orderIndex;
    private LocalDateTime askedAt;
}