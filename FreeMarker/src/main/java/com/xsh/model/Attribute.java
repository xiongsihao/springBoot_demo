package com.xsh.model;

/**
 * @author : xsh
 * @create : 2020-06-25 - 15:11
 * @describe: 用于存放从数据库获取的字段类型，字段名字，字段备注
 */
public class Attribute {
    private String type;//类型
    private String name;//名字
    private String remarks;//备注

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Attribute() {
    }

    public Attribute(String type, String name, String remarks) {
        this.type = type;
        this.name = name;
        this.remarks = remarks;
    }
}
