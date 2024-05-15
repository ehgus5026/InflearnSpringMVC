package com.example.SpringMVC.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello");

        return mav;
    }

    // 표준
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello!");

        return "response/hello";
    }

    // 권장 x
    @RequestMapping("/response/hello")  // @RequestMapping 경로와 View의 논리 경로가 일치하고 아무것도 반환하지 않으면, @RequestMapping 경로에서 /가 떼 uri가 뷰로 인식됨
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hi");
    }

}
