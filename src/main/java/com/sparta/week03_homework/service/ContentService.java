package com.sparta.week03_homework.service;

import com.sparta.week03_homework.dto.ContentNewRequestDto;
import com.sparta.week03_homework.dto.ContentRequestDto;
import com.sparta.week03_homework.dto.NewPasswordDto;
import com.sparta.week03_homework.dto.ReturnDto;
import com.sparta.week03_homework.entity.Content;
import com.sparta.week03_homework.repository.ContentRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ContentService {

    private final ContentRepository contentRepository;

    public ContentService() {
        ContentRepository contentRepository = new ContentRepository();
        this.contentRepository = contentRepository;
    }

    public Content createContent(ContentRequestDto requestDto) throws SQLException {
        Content content = new Content(requestDto);
        contentRepository.createContent(content);
        return content;
    }

    public List<ReturnDto> getContents() throws SQLException {
        List<ReturnDto> contents = contentRepository.getContents();
        return contents;
    }

    public Content getContent(Long id) throws SQLException {
        Content contents = contentRepository.getContent(id);
        return contents;
    }

    public boolean checkPw(Long id, NewPasswordDto requestDto) throws SQLException {
        String pw=contentRepository.checkPw(id);
        if (requestDto.getPassword().equals(pw)) {
            return true;
        }
        return false;
    }
    public ContentNewRequestDto updateContent(Long id, ContentNewRequestDto requestDto) throws SQLException {
        Content product = contentRepository.getContent(id);
        if (product == null) {
            throw new NullPointerException("해당 아이디가 존재하지 않습니다.");
        }

        String title = requestDto.getTitle();
        String content = requestDto.getContent();
        String author = requestDto.getAuthor();
        String password = requestDto.getPassword();

        contentRepository.updateContent(id, title, content, author, password);

        return requestDto;
    }

    public Long delContent(Long id) throws SQLException {
        Content content = contentRepository.getContent(id);
        if(content == null){
            throw new NullPointerException("해당 아이디가 존재하지 않습니다.");
        }

        contentRepository.delContent(id);
        return id;
    }
}
