package com.example.guest.yayweather.models;

import org.parceler.Parcel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Parcel
public class Forecast {
    public String mPlaceName;
    public int mId;
    public int mTemp;
    public String mIcon;
    public String mDescription;
    public int mHumidity;
    public int mMinTemp;
    public int mMaxTemp;
    public long mDate;

    public Forecast() {

    }

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

    public String formatDate(String format) {
        DateFormat df = new SimpleDateFormat(format, Locale.getDefault());
        Date newDate = new Date(mDate * 1000); // MAYBE THIS IS SECONDS AND IT NEEDS MILLIS?!?!?!?!??!!??!?!?!
        return df.format(newDate);
    }
}
