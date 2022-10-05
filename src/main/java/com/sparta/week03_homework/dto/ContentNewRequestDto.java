package com.sparta.week03_homework.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ContentNewRequestDto {

    private String title;
    private String content;
    private String author;
    private String password;
}
