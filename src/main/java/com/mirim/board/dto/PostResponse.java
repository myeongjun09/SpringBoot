package com.mirim.board.dto;

import com.mirim.board.Post;

import java.time.LocalDateTime;

public class PostResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime createAt;

    private PostResponse(Long id, String title, String content, LocalDateTime createAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createAt = createAt;
    }

    public static PostResponse from(Post post) {
        return new PostResponse (
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt()
        );
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

    public LocalDateTime getCreateAt() {
        return createAt;
    }
}
