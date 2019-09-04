package com.tieshan.disintegrate.pojo;

import lombok.Data;

/**
 * @author huxuanhua
 * @projectName tms-gps
 * @description: TODO
 * @date 2019/7/2211:54
 */
public class User {
    private long id;
    private String username;
    private String head;
    private int sex;
    private String phone;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
