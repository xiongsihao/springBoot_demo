package com.xsh.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : xsh
 * @describe: 绑定map类型,省略前缀
 */
@Controller
@ConfigurationProperties(prefix="person")
public class MapController {


    private String name;

    private Integer age;

    @ResponseBody
    @RequestMapping("hello2")
    public String test1(){
        return "hello,"+name+","+age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
