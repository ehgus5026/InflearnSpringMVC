package com.example.SpringMVC.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String headers(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpMethod httpMethod,
                          Locale locale,    // location 언어 정보
                          @RequestHeader MultiValueMap<String, String> headerMap,   // 헤더 한 번에 다 받기 , MulitValueMap : Map과 유사한데 하나의 키에 여러 값을 받을 수 있다.
                          @RequestHeader("host") String host,   // 헤더 하나만 받기(host는 필수 헤더)
                          @CookieValue(value = "myCookie", required = false) String cookie  // value : 쿠키 이름
                          ) {

        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);

        return "ok";
    }

}
