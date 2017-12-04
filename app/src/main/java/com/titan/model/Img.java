package com.titan.model;

import java.io.Serializable;

/**
 * Created by whs on 2017/11/28
 * 图片实体类
 */

public class Img implements Serializable {
    private String name;
    private String path;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
