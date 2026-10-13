package com.mirim.board;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 200) // 컬럼 어노테이션에는 이름을 따로 지정해주거나 notnull updatable를 줘서 쓸 수 있다.
    private String title;
    private String content;
    @Column(name = "created_at", nullable = false, updatable = false)
    // createdAt은 작성 되고 수정 될 이유가 없기 때문에 nullable false를 줬다.
    private LocalDateTime createdAt;
    @Column(name="view_count", nullable = false)
    private Long viewCount;

    public Post() {
    }
//    ORM으로 채워넣기 전에 객체를 빈 상태로 만들어 준다
//    JPA를 사용하기 위해선 기본 생성자가 무조건 필요함

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
