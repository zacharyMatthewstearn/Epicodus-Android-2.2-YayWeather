package com.example.guest.yayweather.ui;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.guest.yayweather.R;
import com.example.guest.yayweather.adapters.ForecastPagerAdapter;
import com.example.guest.yayweather.models.Forecast;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DailyForecastDetailsActivity extends AppCompatActivity {

    @Bind(R.id.viewPager) ViewPager mViewPager;
    @Bind(R.id.pagerHeader)
    PagerTabStrip mPagerHeader;
    private ForecastPagerAdapter adapterViewPager;
    ArrayList<Forecast> mForecasts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast_details);
        ButterKnife.bind(this);

        Typeface xmas = Typeface.createFromAsset(getAssets(),
                "fonts/PWChristmasfont.ttf");

        for(int i = 0; i < mPagerHeader.getChildCount(); i++){
            View nextChild = mPagerHeader.getChildAt(i);
            if(nextChild instanceof TextView){
                ((TextView) nextChild).setTypeface(xmas, Typeface.BOLD);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    ((TextView) nextChild).setTextColor(getColor(R.color.colorPrimary));
                } else {
                    ((TextView) nextChild).setTextColor(getResources().getColor(R.color.colorPrimary));
                }
            }
        }
        mPagerHeader.setDrawFullUnderline(true);
        mPagerHeader.setTabIndicatorColorResource(R.color.colorPrimaryLight);


        mForecasts = Parcels.unwrap(getIntent().getParcelableExtra("forecasts"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new ForecastPagerAdapter(getSupportFragmentManager(), mForecasts);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
