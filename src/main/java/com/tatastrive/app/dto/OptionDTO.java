package com.tatastrive.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OptionDTO {
    private Long id;
    private String text;
    private int index;
}