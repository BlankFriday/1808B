package com.example.lenovo.day3_geek.bean;

import java.io.Serializable;

public class GoldShowBean implements Serializable {
    public String title;
    public boolean isChecked;

    public GoldShowBean(String title, boolean isChecked) {
        this.title = title;
        this.isChecked = isChecked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
