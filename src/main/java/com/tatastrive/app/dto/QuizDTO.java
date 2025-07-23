package com.tatastrive.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuizDTO {
    private Long id;
    private String title;
    private String description;
    private Long creatorId;
}