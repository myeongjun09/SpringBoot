package com.mirim.board;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BoardApplication {

    public static void main(String[] args) {
        // 1. 필요한 객체들을 찾아서 만들어둔다.
        // 2. 창고에 저장한다. (창고 = 스프링 컨테이너)
        // 3. 내장 톰캣을 띄운다. 그에 맞는 포트(8080, 나는 8888)이 열린다.
        // 4. 요청을 받아서 요청이 오면 알맞는 코드로 넘겨준다.
        ApplicationContext context = SpringApplication.run(BoardApplication.class, args);

        Notifier notifier1 = context.getBean(Notifier.class);
        Notifier notifier2 = context.getBean(Notifier.class);
        System.out.println("같은 객체인가? " + (notifier1 == notifier2));// true 면 같은 곳 false 면 다른 곳

        Notifier direct1 = new EmailNotifier();
        Notifier direct2 = new EmailNotifier();
        System.out.println("직접 만들면 같은 객체인가? " + (direct1 == direct2)); //매번 new를 새로 해서 false가 나옴
//        spring 컨테이너에서 꺼내면 true 자신이 직접 객체를 만들면 false -> singleton 디자인 패턴
    }

}
