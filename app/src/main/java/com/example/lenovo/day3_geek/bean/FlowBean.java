package com.example.lenovo.day3_geek.bean;

public class FlowBean {
    private String title;
    private String content;

    @Override
    public String toString() {
        return "FlowBean{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public FlowBean() {

    }

    public FlowBean(String title, String content) {

        this.title = title;
        this.content = content;
    }
}
