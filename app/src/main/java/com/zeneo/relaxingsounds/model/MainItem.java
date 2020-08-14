package com.zeneo.relaxingsounds.model;

public class MainItem {
    private String title;
    private int image;
    private int raw;

    public MainItem(String title, int image, int raw) {
        this.title = title;
        this.image = image;
        this.raw = raw;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getRaw() {
        return raw;
    }

    public void setRaw(int raw) {
        this.raw = raw;
    }
}