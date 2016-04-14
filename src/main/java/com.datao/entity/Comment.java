package com.datao.entity;

import java.io.Serializable;

/**
 * Created by 海涛 on 2016/4/12.
 * 回复实体类
 */
public class Comment implements Serializable {

    private static final long serialVersionUID = 1586229351974949441L;

    private Integer id;
    private String createtime;
    private String texts;
    private Integer userid;
    private Integer topicid;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getTexts() {
        return texts;
    }

    public void setTexts(String texts) {
        this.texts = texts;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getTopicid() {
        return topicid;
    }

    public void setTopicid(Integer topicid) {
        this.topicid = topicid;
    }
}
