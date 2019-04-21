package com.example.lenovo.day3_geek.bean;

/**
 * @author xts
 *         Created by asus on 2019/4/19.
 */

public class V2exTabBean {
    public String link;
    public String tab;

    public V2exTabBean(String link, String tab) {
        this.link = link;
        this.tab = tab;
    }

    @Override
    public String toString() {
        return "V2exTabBean{" +
                "link='" + link + '\'' +
                ", tab='" + tab + '\'' +
                '}';
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }
}
