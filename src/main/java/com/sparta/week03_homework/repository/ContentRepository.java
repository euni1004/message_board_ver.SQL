package com.sparta.week03_homework.repository;

import com.sparta.week03_homework.dto.ReturnDto;
import com.sparta.week03_homework.entity.Content;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContentRepository {

    public void createContent(Content content) throws SQLException {
        // DB 연결
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:week03homeworkdb", "sa", "");

        // DB Query 작성
        PreparedStatement ps = connection.prepareStatement("select max(id) as id from content");
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            // product id 설정 = product 테이블의 마지막 id + 1
            content.setId(rs.getLong("id") + 1);
        } else {
            throw new SQLException("content 테이블의 마지막 id 값을 찾아오지 못했습니다.");
        }
        ps = connection.prepareStatement("insert into content(id,title,content,author,password) values(?, ?, ?, ?, ?)");
        ps.setLong(1, content.getId());
        ps.setString(2, content.getTitle());
        ps.setString(3, content.getContent());
        ps.setString(4, content.getAuthor());
        ps.setString(5, content.getPassword());


        // DB Query 실행
        ps.executeUpdate();

        // DB 연결 해제
        ps.close();
        connection.close();
    }

    public List<ReturnDto> getContents() throws SQLException {
        List<ReturnDto> contents = new ArrayList<>();

        // DB 연결
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:week03homeworkdb", "sa", "");

        // DB Query 작성 및 실행
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * from content");

        // DB Query 결과를 상품 객체 리스트로 변환
        while (rs.next()) {
            Content content = new Content();
            content.setId(rs.getLong("id"));
            content.setTitle(rs.getString("title"));
            content.setContent(rs.getString("content"));
            content.setAuthor(rs.getString("author"));
            contents.add(content.Dto());
        }

        // DB 연결 해제
        rs.close();
        connection.close();

        return contents;
    }

    public void updateContent(Long id, String title, String content, String author, String password) throws SQLException {
        // DB 연결
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:week03homeworkdb", "sa", "");

        // DB Query 작성
        PreparedStatement ps = connection.prepareStatement("update content set title=?,content=?,author=?,password=? where id = ?");
        ps.setString(1,title);
        ps.setString(2,content);
        ps.setString(3,author);
        ps.setString(4,password);
        ps.setLong(5, id);

        // DB Query 실행
        ps.executeUpdate();

        // DB 연결 해제
        ps.close();
        connection.close();
    }

    public void delContent(Long id) throws SQLException{
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:week03homeworkdb", "sa", "");

        PreparedStatement ps = connection.prepareStatement("delete content where id =?");
        ps.setLong(1,id);

        ps.executeUpdate();
        ps.close();
        connection.close();
    }

    public Content getContent(Long id) throws SQLException {
        Content content = new Content();

        // DB 연결
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:week03homeworkdb", "sa", "");

        // DB Query 작성
        PreparedStatement ps = connection.prepareStatement("select * from content where id = ?");
        ps.setLong(1, id);

        // DB Query 실행
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            content.setId(rs.getLong("id"));
            content.setTitle(rs.getString("title"));
            content.setContent(rs.getString("content"));
            content.setAuthor(rs.getString("author"));
        }

        // DB 연결 해제
        rs.close();
        ps.close();
        connection.close();

        return content;
    }

    public String checkPw(Long id) throws SQLException{
        String result = "";
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:week03homeworkdb", "sa", "");

        // DB Query 작성
        PreparedStatement ps = connection.prepareStatement("select password from content where id = ?");
        ps.setLong(1, id);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            result=rs.getString("password");
        }

        rs.close();
        ps.close();
        connection.close();
        return result;

    }

}
