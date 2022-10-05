package com.sparta.week03_homework.controller;

import com.sparta.week03_homework.dto.ContentNewRequestDto;
import com.sparta.week03_homework.dto.ContentRequestDto;
import com.sparta.week03_homework.dto.NewPasswordDto;
import com.sparta.week03_homework.dto.ReturnDto;
import com.sparta.week03_homework.entity.Content;
import com.sparta.week03_homework.service.ContentService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ContentController {
    private final ContentService contentService;

    @PostMapping("/api/contents")
    public Content createContent(@RequestBody ContentRequestDto requestDto) throws SQLException {
        Content content = contentService.createContent(requestDto);
        return content;
    }

    @PutMapping("/api/contents/{id}")
    public ContentNewRequestDto updateProduct(@PathVariable Long id, @RequestBody ContentNewRequestDto requestDto) throws SQLException {
        ContentNewRequestDto content = contentService.updateContent(id, requestDto);

        // 응답 보내기 (업데이트된 상품 id)
        return content;
    }

    @PostMapping("/api/contents/{id}")
    public boolean checkPw(@PathVariable Long id, @RequestBody NewPasswordDto requestDto) throws SQLException{
        boolean bool = contentService.checkPw(id, requestDto);
        return bool;
    }

    @GetMapping("/api/contents")
    public List<ReturnDto> getContents() throws SQLException {
        List<ReturnDto> contents = contentService.getContents();
        return contents;
    }

    @GetMapping("/api/contents/{id}")
    public Content getContent(@PathVariable Long id) throws SQLException {
        Content returnDto = contentService.getContent(id);
        return returnDto;
    }

    @DeleteMapping("/api/contents/{id}")
    public Long delContent(@PathVariable Long id) throws SQLException{
        Long bool = contentService.delContent(id);
        return bool;
    }
}
