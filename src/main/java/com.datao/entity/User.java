package com.datao.entity;

/**
 * Created by 海涛 on 2016/4/7.
 * User实体类
 */
public class User {
    public static String STATE_NORMAL = "正常";
    public static String STATE_PROHIBIT = "禁止";

    private Integer id;
    private String username;
    private String password;
    private String email;
    private String headimg;
    private String regtime;
    private String lastlogip;
    private String logtime;
    private String state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getRegtime() {
        return regtime;
    }

    public void setRegtime(String regtime) {
        this.regtime = regtime;
    }

    public String getLastlogip() {
        return lastlogip;
    }

    public void setLastlogip(String lastlogip) {
        this.lastlogip = lastlogip;
    }

    public String getLogtime() {
        return logtime;
    }

    public void setLogtime(String logtime) {
        this.logtime = logtime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
