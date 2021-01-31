package com.xsh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : xsh
 * @create : 2021-01-31 - 22:59
 * @describe:
 */
@RestController
public class testController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello world";
    }
}
