package com.mirim.board;

import com.mirim.board.service.PostService;
import com.sun.net.httpserver.HttpServer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;

    }

    @GetMapping()
    public ResponseEntity<?> getPosts(@RequestParam(required = false) String keyword) {
        System.out.println("이 요청을 처리하는 PostController : " + System.identityHashCode(this));
        if(keyword != null) {
            List<Map<String, Object>> posts = postService.searchPosts(keyword);
            return ResponseEntity.ok(posts);
        }

        List<Map<String, Object>> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/count")
    public String getPostCount() {
        long postCount = postService.getPostCount();
        return "게시글 개수 : " + postCount + "개";
    }

    @GetMapping("/{id}") //주소에서 long 타입 이외의 값이 들어오면 에러가 남(400번대 클라이언트 에러)
    public ResponseEntity<?> getPost(@PathVariable Long id) {
//        게시글 번호가 10번보다 크면 게시글이 없는 거임
        if (id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("게시글 번호는 1 이상이여야 합니다.");
        }
        Map<String, Object> post = postService.getPost(id);
        if(post == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 게시물입니다");
        }
        return ResponseEntity.ok(post);
//        return ResponseEntity.status(HttpStatus.OK).body(id + "번 게시글입니다.");
//        return id + "번 게시글입니다.";
    }

    @PostMapping // RequestMapping에서 posts를 붙였기에 여기서 안 써도 됨
    public ResponseEntity<?> createPost(@RequestBody Map<String, Object> request) {
        String title =  (String)request.get("title");
        String content = (String) request.get("content");

        Map<String, Object> response = postService.createPost(title, content);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//        return "[" + title + "] 게시글이 등록되었습니다. : " + content;
    } // 브라우저에서 요청하는 것은 GET 밖에 안됨

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        // 게시글 번호가 0보다 작거나 같으면 잘못된 입력임
        if (id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("게시글 번호는 1 이상이어야 합니다.");
        }
        if(id > 10) {
            // 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 게시글입니다");
        }

        String title = (String)request.get("title");
        String content = (String)request.get("content");

//        db에다가 id로 조회해서 title, content 내용을 수정한다고 치고~
        Map<String, Object> response = postService.updatePost(id, title, content);
        if(response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 게시물입니다");
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        // 게시글 번호가 0보다 작거나 같으면 잘못된 입력임
        if (id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("게시글 번호는 1 이상이어야 합니다.");
        }
        if(id > 10) {
            // 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 게시글입니다");
        }

        // db에서 삭제한다고 치고
        boolean deleted = postService.deletePost(id);
        if(!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 게시글입니다");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

//    public class Main {
//        public static void main(String[] args) throws IOException {
//            HttpServer httpServer = HttpServer.create(new InetSocketAddress(9090), 0);
//
//            httpServer.createContext("/hello", exchange -> {
//                String response = "hello";
//                exchange.sendResponseHeaders(200, response.getBytes().length);
//                OutputStream os  = exchange.getResponseBody();
//                os.write((response.getBytes()));
//                os.close();
//            });
//
//            httpServer.setExecutor(null);
//            httpServer.start();
//            System.out.println("http://localhost:9090/hello");
//        }
//    }
}
