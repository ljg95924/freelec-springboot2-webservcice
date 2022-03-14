package com.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


// @Getter: 클래스 내 모든 필드의 Getter 메소드 자동생성
// @Entity: 테이블과 링큳죌 클래스임을 나타낸다.
// 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름을 매칭
// Entity 클래스에서는 절대 Setter 메소드를 만들지 않는다.
@Getter
@NoArgsConstructor
@Entity
public class Posts {
    @Id // 해당 테이블의 PK 필드를 나타냄
    // @GeneratedValue: PK의 생성 규칙을 나타냄
    // 스프링부트 2.0에서는 GenerationType.IDENTITY 옵션을 추가해야만 auto_increment 가 됨
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Column : 테이블의 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 됨
    // 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    // Builder: 해당 클래스의 빌더 패턴 클래스 생성
    // 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
