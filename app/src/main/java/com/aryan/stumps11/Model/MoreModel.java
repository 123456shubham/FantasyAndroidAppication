package com.aryan.stumps11.Model;

public class MoreModel {

    String itemname;
    int image;

    public MoreModel(int image, String itemname) {
        this.image = image;
        this.itemname = itemname;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }
}
