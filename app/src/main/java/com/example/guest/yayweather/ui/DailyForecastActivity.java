package com.example.guest.yayweather.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.guest.yayweather.R;
import com.example.guest.yayweather.adapters.ForecastListAdapter;
import com.example.guest.yayweather.models.Forecast;
import com.example.guest.yayweather.services.WeatherService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DailyForecastActivity extends AppCompatActivity {
    private static final String TAG = DailyForecastActivity.class.getSimpleName();
    public ArrayList<Forecast> mForecasts = new ArrayList<Forecast>();
    @Bind(R.id.locationTextView) TextView mTextViewLocation;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerViewItsTheOnlyOne;
    private ForecastListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        int placeId = intent.getIntExtra("placeId", 0);
        getDailyForecast(placeId);
    }

    private void getDailyForecast(int id){
        final WeatherService weatherService = new WeatherService();
        weatherService.getFutureWeather(id + "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mForecasts = weatherService.processFutureResults(response);
                DailyForecastActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTextViewLocation.setText(mForecasts.get(0).getPlaceName());
                        mAdapter = new ForecastListAdapter(getApplicationContext(),mForecasts);
                        mRecyclerViewItsTheOnlyOne.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(DailyForecastActivity.this, 2);
                        mRecyclerViewItsTheOnlyOne.setLayoutManager(layoutManager);
                        mRecyclerViewItsTheOnlyOne.setHasFixedSize(true);
                    }
                });

            }
        });
    }
}
