package com.example.SpringMVC.basic.response;


import com.example.SpringMVC.basic.HelloData;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
//@Controller
//@ResponseBody
@RestController
public class ResponseBodyController {

    /**
     * @RequestBody, HttpEntity(RequestEntity) -> 각각 두 개에 따라서 처리하는 ArgumentResolver가 따로 존재함
     * 개발자가 작성한 파라미터를 보고 httpMessageConverter를 거쳐 ArgumentResolver가 필요한 객체를 만들어줌
     */

    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    /**
     * @ResponseBody, HttpEntity(ResponseEntity)
     * 응답의 경우 ReturnValueHandler가 httpMessageConverter를 호출해 응답 결과를 만들어줌
     */
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

//    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {
        return "ok";
    }

    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUserName("userA");
        helloData.setAge(20);

        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)  // 어노테이션은 상황 별 동적 설정이 안되기 때문에, 동적인 설정이 필요한 경우 ResponseEntity<> 사용
//    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUserName("userA");
        helloData.setAge(20);

        return helloData;
    }


}
