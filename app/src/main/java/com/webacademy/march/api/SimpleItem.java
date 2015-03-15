package com.webacademy.march.api;

import org.apache.http.entity.SerializableEntity;

import java.io.Serializable;

/**
 * Created by student on 3/15/15.
 */
public class SimpleItem implements Serializable {

    private String title;
    private String content;
    private long data;

    public SimpleItem(String title, String content, long data) {
        this.title = title;
        this.content = content;
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public long getData() {
        return data;
    }


}
