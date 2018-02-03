package com.admin.luntan.dto;

import com.admin.luntan.base.BaseEntity;

/**
 * ueditor 上传图片返回值
 * Created by zhanghaichao on 2018/2/3.
 */
public class UeditorImgUploadResultDto extends BaseEntity {

    private static final long serialVersionUID = 8134559946096311151L;

    private String name;

    private String originalName;

    private String size;

    private String state;

    private String type;

    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
