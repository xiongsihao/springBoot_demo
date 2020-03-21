package com.xsh.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : xsh
 * @create : 2019-08-30 - 15:11
 * @describe:
 */
public class Msg {

    private int code;
    private String message;
    private Map<String, Object> extend = new HashMap<>();//返回数据

    public static Msg success(){
        Msg result=new Msg();
        result.setCode(100);
        result.setMessage("请求成功");
        return result;
    }

    public static Msg fail(){
        Msg result=new Msg();
        result.setCode(200);
        result.setMessage("请求失败");
        return result;
    }

    public Msg add(String key,Object value){
        this.getExtend().put(key,value);
        return this;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
