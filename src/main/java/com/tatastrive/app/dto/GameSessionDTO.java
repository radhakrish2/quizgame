package com.tatastrive.app.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class GameSessionDTO {
    private Long id;
    private String sessionCode;
    private boolean isActive;
    private LocalDateTime startedAt;
    private Long hostId;
    private Long quizId;
}