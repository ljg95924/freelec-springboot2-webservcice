package com.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킴
// 여기서는 SpringRuner라는 스프링 실행자를 사용
// 즉 스프링부트테스트와 JUnit 사이에 연결자 역할을 함
@RunWith(SpringRunner.class)
// Web(Spring MVC)에 집중할 수 있는 어노테이션
// 선언할 경우 @Controller, @ControllorAdvice 등을 사용할 수 있음
// 단 @Service, @Component, @Repository 등은 사용할 수 없음
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    //스프링이 관리하는 빈을 주입받는다.
    @Autowired
    // 웹API를 테스트할때 사용한다.
    // 스프링 MVC 테스트의 시작점
    // 이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트를 할 수 있음
    private MockMvc mvc;

    @Test
    public void hello_가_리턴된다() throws Exception {
        String hello = "hello";

        //MockMvc를 통해 /hello 주소로 HTTP GET 요청
        mvc.perform(get("/hello"))
                // mvc.perform의 결과를 검증
                // HTTP Header의 Status를 검증, 여기선 OK 즉, 200인지 아닌지를 검증
                .andExpect(status().isOk())
                // 응답본문의 내용을 검증, Controllor에서 'hello'를 리턴하기 때문에 이값이 맞는지 검증
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        // 1. param
        // API 테스트할때 사용될 요청 파라미터, 단 값은 String만 허용
        // 2. jsonPath
        // JSON 응답값을 필드별로 검증할 수 있는 메소드, $를 기준으로 필드명을 명시
        mvc.perform(get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
