package com.wpanf.recyclerview;

import java.io.Serializable;

/**
 * Created by wangpanfeng@nationsky.com on 18/9/28.
 */

public class DataBean implements Serializable {
    private int type;
    private String title;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
