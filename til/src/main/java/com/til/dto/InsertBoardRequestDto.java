package com.til.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InsertBoardRequestDto {

    private String title;
    private String content;
}
