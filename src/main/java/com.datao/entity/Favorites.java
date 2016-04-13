package com.datao.entity;

/**
 * Created by 海涛 on 2016/4/13.
 * 收藏实体类
 */
public class Favorites {
    private Integer id;
    private Integer userid;
    private Integer topicid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
