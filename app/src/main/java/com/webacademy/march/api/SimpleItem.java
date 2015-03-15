package com.webacademy.march.api;

/**
 * Created by student on 3/15/15.
 */
public class SimpleItem {

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
