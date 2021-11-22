package com.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xiangnan
 * @date 2021年11月22日 2:48 下午
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public String test(HttpServletRequest request){
        System.out.println(request.getRemoteAddr());
        System.out.println(request.getHeader("x-forwarded-for"));
        return "ok";
    }
}
