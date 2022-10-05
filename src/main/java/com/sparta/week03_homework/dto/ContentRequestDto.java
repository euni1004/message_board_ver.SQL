package com.sparta.week03_homework.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ContentRequestDto {

    private String title;
    private String content;
    private String author;
    private String password;
}
