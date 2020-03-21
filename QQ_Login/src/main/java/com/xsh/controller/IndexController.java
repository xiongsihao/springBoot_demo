package com.xsh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * @author xsh
 * @create 2020-03-13 20:31

 */
@Controller
public class IndexController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model){
        String openid = (String) session.getAttribute("openid");
        String nickname = (String) session.getAttribute("nickname");
        String figureurl_qq_2 = (String) session.getAttribute("figureurl_qq_2");
        String gender = (String) session.getAttribute("gender");
        String year = (String) session.getAttribute("year");

        model.addAttribute("openid",openid);
        model.addAttribute("nickname",nickname);
        model.addAttribute("figureurl_qq_2",figureurl_qq_2);
        model.addAttribute("gender",gender);
        model.addAttribute("year",year);
        return "home";
    }
}
