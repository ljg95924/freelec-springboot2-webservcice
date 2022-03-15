package com.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
// @MappedSuperclass: JPA Entity 클래스들이 BaseTimeEntity을 상속할 경우 
// 필드들(createdDate, modifiedDate)도 칼럼으로 인식하도록함
@MappedSuperclass
// @EntityListeners(AuditingEntityListener.class): BaseTimeEntity 클래스에 Auditing 기능을 포함시킴
@EntityListeners(AuditingEntityListener.class)
// 모든 Entity의 상위 클래스이며 Entity들의 Date를 자동으로 관리하는 역할
public class BaseTimeEntity {

    @CreatedDate // Entity가 생성되어 저장될 때 시간이 자동 저장됨
    private LocalDateTime createdDate;

    @LastModifiedDate // 조회한 Entity의 값을 변경할 때 시간이 자동 저장됨
    private LocalDateTime modifiedDate;
}
