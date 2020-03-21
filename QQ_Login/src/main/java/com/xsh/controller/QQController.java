package com.xsh.controller;

import com.alibaba.fastjson.JSONObject;
import com.xsh.exception.StateErrorException;
import com.xsh.utils.QQHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * @author xsh
 * @create 2020-03-13 20:32
 * 发起QQ登录请求并回调信息
 */
@Controller
public class QQController {

    @Value("${qq.oauth.redirect_URI}")
    private String backUrl;
    @Value("${qq.appid}")
    private String APPID;
    @Value("${qq.appkey}")
    private String APPKEY;

    /**
     * 发起请求
     * @param session
     * @return
     */
    @GetMapping("/qq/oauth")
    public String qq(HttpSession session){

        //用于第三方应用防止CSRF攻击
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        session.setAttribute("state",uuid);

        //Step1：获取Authorization Code
        String url = "https://graph.qq.com/oauth2.0/authorize?response_type=code"+
                "&client_id=" + APPID +
                "&redirect_uri=" + URLEncoder.encode(backUrl) +
                "&state=" + uuid;

        return "redirect:" + url;
    }

    /**
     * QQ回调
     * @param request
     * @return
     */
    @GetMapping("/qq/callback")
    public String qqcallback(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        //qq返回的信息：http://graph.qq.com/demo/index.jsp?code=9A5F************************06AF&state=test
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        String uuid = (String) session.getAttribute("state");

        if(uuid != null){
            if(!uuid.equals(state)){
                throw new StateErrorException("QQ,state错误");
            }
        }


        //Step2：通过Authorization Code获取Access Token
        String url = "https://graph.qq.com/oauth2.0/token?grant_type=authorization_code"+
                "&client_id=" + APPID +
                "&client_secret=" + APPKEY +
                "&code=" + code +
                "&redirect_uri=" + backUrl;

        String access_token = QQHttpClient.getAccessToken(url);

        //Step3: 获取回调后的 openid 值
        url = "https://graph.qq.com/oauth2.0/me?access_token=" + access_token;
        String openid = QQHttpClient.getOpenID(url);

        //Step4：获取QQ用户信息
        url = "https://graph.qq.com/user/get_user_info?access_token=" + access_token +
                "&oauth_consumer_key="+APPID +
                "&openid=" + openid;

        JSONObject jsonObject = QQHttpClient.getUserInfo(url);

        //也可以放到Redis和mysql中,本例只做展示处理
        session.setAttribute("openid",openid);  //openid,用来唯一标识qq用户
        session.setAttribute("nickname",(String)jsonObject.get("nickname")); //QQ名
        session.setAttribute("figureurl_qq_2",(String)jsonObject.get("figureurl_qq_2")); //大小为100*100像素的QQ头像URL
        session.setAttribute("gender",(String)jsonObject.get("gender"));//性别
        session.setAttribute("year",(String)jsonObject.get("year"));//出生年
        System.out.println(jsonObject.toJSONString());
        for(String str:jsonObject.keySet()){
            System.out.println(str + ":" +jsonObject.get(str));
        }
        return "redirect:/home";
    }

}