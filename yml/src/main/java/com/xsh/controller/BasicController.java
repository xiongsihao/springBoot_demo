package com.xsh.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : xsh
 * @describe: 使用yml配置文件的值
 */
@Controller
public class BasicController {

    @Value("${name}")
    private String name;

    @Value("${person.age}")
    private int age;

    @Value("${city[1]}")
    private String city;

    @Value("${student[1].score}")
    private int score;
    @ResponseBody
    @RequestMapping("hello1")
    public String test1(){
        return "hello,"+name+","+age+","+city+","+score;
    }
}
