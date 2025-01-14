package com.putrimaharani.gameedukasi.data;

public class MenuItem {
    private final String title;
    private final String description;
    private final int iconResId;

    public MenuItem(String title, String description, int iconResId) {
        this.title = title;
        this.description = description;
        this.iconResId = iconResId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getIconResId() {
        return iconResId;
    }
}