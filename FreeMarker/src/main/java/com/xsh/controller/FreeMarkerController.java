package com.xsh.controller;

import com.xsh.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * @author : xsh
 * @create : 2020-06-23 - 22:33
 * @describe:
 */
@Controller
public class FreeMarkerController {

    @RequestMapping("/test1")
    public String test1(Map<String,Object> map){
        //map作为形参，里面的数据最终会作为request域响应给用户；
        //freemarker会渲染map内的数据
        map.put("name","xsh");


        Student stu1 = new Student();
        stu1.setName("小明");
        stu1.setAge(18);
        stu1.setMondy(1000.86f);
        stu1.setBirthday(new Date());
        Student stu2 = new Student();
        stu2.setName("小红");
        stu2.setMondy(200.1f);
        stu2.setAge(19);
        List<Student> friends = new ArrayList<>();
        friends.add(stu1);
        stu2.setFriends(friends);
        stu2.setBestFriend(stu1);
        List<Student> stus = new ArrayList<>();
        stus.add(stu1);
        stus.add(stu2);
        //向数据模型放数据
        map.put("stus",stus);

        //准备map数据
        HashMap<String,Student> stuMap = new HashMap<>();
        stuMap.put("stu1",stu1);
        stuMap.put("stu2",stu2);

        map.put("stu1",stu1);

        map.put("stuMap",stuMap);
        map.put("point",102920122);

        //返回freemarket模板的位置，基于resources/templates路径内
        return "test1";
    }
}
