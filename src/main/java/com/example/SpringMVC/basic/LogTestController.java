package com.example.SpringMVC.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {

//    private final Logger log = LoggerFactory.getLogger(getClass()); -> @Slf4j 어노테이션을 사용하면 알아서 lombok이 넣어줌

    @GetMapping
    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        // 로그의 레벨을 직접 정해줄 수 있음
        // 기본이 info 레벨
        // 로그를 콘솔에만 남기는 것 뿐만 아니라 파일에도 넣어서 저장할 수도 있음
        log.trace("trace log={}", name);    // 파라미터로 넘김
        log.debug("debug log={}", name);
        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }
}
