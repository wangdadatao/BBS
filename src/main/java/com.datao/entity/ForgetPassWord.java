package com.datao.entity;

import java.io.Serializable;

/**
 * Created by 海涛 on 2016/4/8.
 * 忘记密码实体类
 */
public class ForgetPassword implements Serializable {

    private static final long serialVersionUID = -5462846247127867558L;

    private Integer id;
    private Integer uid;
    private String token;
    private String createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
