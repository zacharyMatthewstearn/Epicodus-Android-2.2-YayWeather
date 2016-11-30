package com.example.guest.yayweather.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Forecast {
    private String mPlaceName;
    private int mId;
    private int mTemp;
    private String mIcon;
    private String mDescription;
    private int mHumidity;
    private int mMinTemp;
    private int mMaxTemp;
    private long mDate;

    public Forecast(String placeName, int id, int temp, String icon, String description, int humidity, int minTemp, int maxTemp) {
        mPlaceName = placeName;
        mId = id;
        mTemp = temp;
        mIcon = icon;
        mDescription = description;
        mHumidity = humidity;
        mMinTemp = minTemp;
        mMaxTemp = maxTemp;
    }

    public Forecast(String placeName, String icon, String description, int humidity, int minTemp, int maxTemp, long date) {
        mIcon = icon;
        mPlaceName = placeName;
        mDescription = description;
        mHumidity = humidity;
        mMinTemp = minTemp;
        mMaxTemp = maxTemp;
        mDate = date;
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

    public int getTemp() {
        return mTemp;
    }

    public void setTemp(int temp) {
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

    public int getMinTemp() {
        return mMinTemp;
    }

    public void setMinTemp(int minTemp) {
        mMinTemp = minTemp;
    }

    public int getMaxTemp() {
        return mMaxTemp;
    }

    public void setMaxTemp(int maxTemp) {
        mMaxTemp = maxTemp;
    }


    public long getDate() {
        return mDate;
    }

    public void setDate(int mDate) {
        this.mDate = mDate;
    }

    public String formatDate(String format) {
        DateFormat df = new SimpleDateFormat(format, Locale.getDefault());
        Date newDate = new Date(mDate * 1000); // MAYBE THIS IS SECONDS AND IT NEEDS MILLIS?!?!?!?!??!!??!?!?!
        return df.format(newDate);
    }
}
