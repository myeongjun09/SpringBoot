package com.mirim.board;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController // 어노테이션

public class HelloController {
    // 브라우저 -> 내장 톰캣 -> () -> HelloController.hello()
    // 교통정리 담당 = DispatcherServlet
    // DispatcherServlet이 하는 일
    // - 주소를 보고 어느 메서드로 보낼지 고른다.
    // - 목적지가 없다면 404를 응답한다.

    // CRUD : Create / Read(Get) / Update / Delete
    // 브라우저에서 주소창으로 직접 요청할 때는 GET 이외의 메서드는 보낼 수 없다.
    // 1. 게시글 작성하는 어떻게 테스트 할까
    // 2. RestController, GetMapping 뭐하는 애들일까?

    @Value("${my.message}")
    private String message;

    @GetMapping("/")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello")
    public String hello2() {
        return message;
//        throw new RuntimeException("일부러 에러를 냈습니다");
    }

    @GetMapping("/hello-map")
    public Map<String, Object> helloMap() { // view의 이름이 String 이 아니라 Map이라 500번대 에러가 뜸 -> 어노테이션이 Controller에서
        return Map.of("name", "김미림", "grade", 2);
    }
}
