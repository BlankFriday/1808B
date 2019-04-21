package com.example.lenovo.day3_geek.bean;

public class V2exBean {
    private String img;
    private String author;
    private String text;
    private String commentCount;
    private String tab;

    @Override
    public String toString() {
        return "V2exBean{" +
                "img='" + img + '\'' +
                ", author='" + author + '\'' +
                ", commentPeople='" + text + '\'' +
                ", commentCount='" + commentCount + '\'' +
                ", tab='" + tab + '\'' +
                ", topic='" + topic + '\'' +
                '}';
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCommentPeople() {
        return text;
    }

    public void setCommentPeople(String commentPeople) {
        this.text = commentPeople;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public V2exBean() {

    }

    public V2exBean(String img, String author, String text, String commentCount, String tab, String topic) {

        this.img = img;
        this.author = author;
        this.text = text;
        this.commentCount = commentCount;
        this.tab = tab;
        this.topic = topic;
    }

    private String topic;
}
