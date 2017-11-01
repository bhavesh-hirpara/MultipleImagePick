package com.admision.objects;

public class MenuItem {
    public MenuItem(String ID, int icon, String name) {
        this.ID = ID;
        this.icon = icon;
        this.name = name;
    }

    public MenuItem(int icon, String name) {
        this.icon = icon;
        this.name = name;
    }

    public String ID;
    public int icon;
    public String name;

    public boolean isSelected;

    public MenuItem setSelected(boolean isSelected) {
        this.isSelected = isSelected;
        return this;
    }
}
