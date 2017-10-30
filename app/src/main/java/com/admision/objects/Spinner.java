package com.admision.objects;

public class Spinner {
    public Spinner(String ID, String title) {
        this.ID = ID;
        this.title = title;
    }

    public Spinner(String ID, String title, long duration, double price) {
        this.ID = ID;
        this.title = title;
        this.duration = duration;
        this.price = price;
    }

    public Spinner(String title) {
        this.title = title;
    }

    public String ID;
    public String title;
    public long duration;
    public double price;

    public boolean isSelected;

    public Spinner setSelected(boolean isSelected) {
        this.isSelected = isSelected;
        return this;
    }
}
