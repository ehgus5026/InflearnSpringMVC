package com.example.SpringMVC.basic.request;


import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

//        log.info("messageBody={}", messageBody);

        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

//        log.info("messageBody={}", messageBody);

        responseWriter.write("ok");
    }

    /**
     * 요청 파라미터 : Get 방식으로 query stream, param 오는 것, post 방식으로 html 폼 데이터 전송하는 방식(content-type application.. url encoded)
     *  -> 이 두 경우에만 @RequstParam, @ModelAttribute 사용
     *  메시지 바디를 직접 조회하는 기능은 요청 파라미터를 조회하는 저 위 두 어노테이션과 관계가 없음.
     *  HttpEntity : Http header, body 정보를 편리하게 조회(메시지 바디 정보 조회)
     *  HttpEntity의 자료형을 보고 HttpBody에 있는 것을 문자로 바꿔서 넣어 주는 역할을 함(httpMessageConverter가 동작)
     *
     */
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {

        String messageBody = httpEntity.getBody();
//        log.info("messageBody={}", messageBody);


        return new HttpEntity<>("ok");
    }

    // RequestEntity<>, ResponseEntity<> 둘 다 모두 HttpEntity<>를 상속받음
    @PostMapping("/request-body-string-v4")
    public HttpEntity<String> requestBodyStringV4(RequestEntity<String> requestEntity) throws IOException {

        String messageBody = requestEntity.getBody();
//        log.info("messageBody={}", messageBody);

        return new ResponseEntity<>("ok", HttpStatus.CREATED);
    }

    @ResponseBody   // 응답 결과를 http MessageBody에 직접 담아서 전달, 객체 -> HTTP 메시지 컨버터 -> JSON 응답
    @PostMapping("/request-body-string-v5")
        public String requestBodyStringV5(@RequestBody String messageBody) {    // @RequestBody : Http 메세지 바디를 직접 조회, JSON 요청 -> HTTP 메시지 컨버터 -> 객체

        log.info("messageBody={}", messageBody);

        return "ok";
    }

}
