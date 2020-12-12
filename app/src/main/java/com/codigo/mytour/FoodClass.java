package com.codigo.mytour;

public class FoodClass {
    String latitude;
    String longitude;
    String name;
    String tag;
    int image;

    public FoodClass(String name, int image,String tag, String latitude, String longitude) {
        this.name = name;
        this.image = image;
        this.tag = tag;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
