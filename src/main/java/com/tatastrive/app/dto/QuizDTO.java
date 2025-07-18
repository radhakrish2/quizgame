package com.tatastrive.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuizDTO {
    private Long id;
    private String title;
    private String description;
    private Long creatorId;
}