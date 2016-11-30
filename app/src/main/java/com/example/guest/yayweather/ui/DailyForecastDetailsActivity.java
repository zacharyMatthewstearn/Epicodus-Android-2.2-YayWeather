package com.example.guest.yayweather.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.guest.yayweather.R;
import com.example.guest.yayweather.adapters.ForecastPagerAdapter;
import com.example.guest.yayweather.models.Forecast;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DailyForecastDetailsActivity extends AppCompatActivity {

    @Bind(R.id.viewPager) ViewPager mViewPager;
    private ForecastPagerAdapter adapterViewPager;
    ArrayList<Forecast> mForecasts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast_details);
        ButterKnife.bind(this);

        mForecasts = Parcels.unwrap(getIntent().getParcelableExtra("forecasts"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new ForecastPagerAdapter(getSupportFragmentManager(), mForecasts);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
