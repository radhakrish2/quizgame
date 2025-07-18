package com.tatastrive.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerDTO {
    private Long id;
    private String nickname;
    private Long gameSessionId;
    private int totalScore;
}