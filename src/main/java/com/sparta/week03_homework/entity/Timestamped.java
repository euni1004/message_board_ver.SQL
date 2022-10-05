package com.sparta.week03_homework.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 상속했을 때, 컬럼으로 인식하게 합니다.
@EntityListeners(AuditingEntityListener.class) // 생성/수정 시간을 자동으로 반영하도록 설정
public class Timestamped {

    @CreatedDate // 생성일자임을 나타냅니다.
    @Column(name="created_At",updatable = false)
    private LocalDateTime created_At;

    @LastModifiedDate // 마지막 수정일자임을 나타냅니다.
    @Column(name="modified_At")
    private LocalDateTime modified_At;
}