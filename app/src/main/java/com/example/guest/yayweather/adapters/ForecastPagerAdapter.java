package com.example.guest.yayweather.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.guest.yayweather.models.Forecast;
import com.example.guest.yayweather.ui.DailyForecastDetailsFragment;

import java.util.ArrayList;

public class ForecastPagerAdapter extends FragmentPagerAdapter{
    private ArrayList<Forecast> mForecasts;

    public ForecastPagerAdapter(FragmentManager fm, ArrayList<Forecast> forecasts) {
        super(fm);
        mForecasts = forecasts;
    }

    @Override
    public Fragment getItem(int position) {
        return DailyForecastDetailsFragment.newInstance(mForecasts.get(position));
    }

    @Override
    public int getCount() {
        return mForecasts.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mForecasts.get(position).formatDate("EEEE");
    }
}
