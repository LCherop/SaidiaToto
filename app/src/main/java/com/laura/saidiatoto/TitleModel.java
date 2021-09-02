package com.laura.saidiatoto;

public class TitleModel {
    String title;

    public TitleModel(String title) {
        this.title = title;
    }
    //private String k ="";
    public TitleModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "TitleModel{" +
                "title='" + title + '\'' +
                '}';
    }
}
