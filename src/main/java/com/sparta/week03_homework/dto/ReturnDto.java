package com.sparta.week03_homework.dto;

import com.sparta.week03_homework.entity.Timestamped;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReturnDto extends Timestamped {

    private Long id;
    private String title;
    private String content;
    private String author;
}
