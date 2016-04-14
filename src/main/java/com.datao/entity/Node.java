package com.datao.entity;

import java.io.Serializable;

/**
 * Created by 海涛 on 2016/4/11.
 * 节点实体类
 */
public class Node implements Serializable {

    private static final long serialVersionUID = -5007420055887017884L;

    private Integer id;
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
