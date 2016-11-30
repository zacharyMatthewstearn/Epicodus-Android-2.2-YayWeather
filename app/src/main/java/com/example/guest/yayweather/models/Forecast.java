package com.example.guest.yayweather.models;

/**
 * Created by Guest on 11/29/16.
 */
public class Forecast {
    private String mPlaceName;
    private int mId;
    private double mTemp;
    private String mIcon;
    private String mDescription;
    private int mHumidity;
    private double mMinTemp;
    private double mMaxTemp;

    public Forecast(String placeName, int id, double temp, String icon, String description, int humidity, double minTemp, double maxTemp) {
        mPlaceName = placeName;
        mId = id;
        mTemp = temp;
        mIcon = icon;
        mDescription = description;
        mHumidity = humidity;
        mMinTemp = minTemp;
        mMaxTemp = maxTemp;
    }

    public Forecast(String placeName, String icon, String description, int humidity, double minTemp, double maxTemp) {
        mIcon = icon;
        mPlaceName = placeName;
        mDescription = description;
        mHumidity = humidity;
        mMinTemp = minTemp;
        mMaxTemp = maxTemp;
    }

    public String getPlaceName() {
        return mPlaceName;
    }

    public void setPlaceName(String placeName) {
        mPlaceName = placeName;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public double getTemp() {
        return mTemp;
    }

    public void setTemp(double temp) {
        mTemp = temp;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public int getHumidity() {
        return mHumidity;
    }

    public void setHumidity(int humidity) {
        mHumidity = humidity;
    }

    public double getMinTemp() {
        return mMinTemp;
    }

    public void setMinTemp(double minTemp) {
        mMinTemp = minTemp;
    }

    public double getMaxTemp() {
        return mMaxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        mMaxTemp = maxTemp;
    }
}
