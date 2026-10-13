package com.mirim.board.repository;


import com.mirim.board.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    //오타주의
    // select * from posts where title = ''
    // List<Post> findByTitle(String keyword);
    // select * from posts where title like '%%'
    List<Post> findByTitleContaining(String keyword);

//    List<Post> findByTitleContentContaining(String keyword);
    // 미리 쿼리를 만들어 두기 때문에 오타가 나게 된다면 서버가 올라가지 않음
}
