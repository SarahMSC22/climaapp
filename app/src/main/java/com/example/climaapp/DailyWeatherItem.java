package com.example.climaapp;

public class DailyWeatherItem {
    private String mDescription;
    private int mImage;
    private String mMax;
    private String mMin;

    public DailyWeatherItem(String description, int imgId, String maximum, String minimum) {
        this.mDescription = description;
        this.mImage = imgId;
        this.mMax = maximum;
        this.mMin = minimum;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public int getImgId() {
        return mImage;
    }

    public void setImgId(int imgId) {
        this.mImage = imgId;
    }

    public String getMax() {
        return mMax;
    }

    public void setMax(String mMax) {
        this.mMax = mMax;
    }

    public String getMin() {
        return mMin;
    }

    public void setMin(String mMin) {
        this.mMin = mMin;
    }
}