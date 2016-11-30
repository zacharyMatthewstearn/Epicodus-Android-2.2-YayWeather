package com.example.guest.yayweather.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.guest.yayweather.R;
import com.example.guest.yayweather.services.WeatherService;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DailyForecastActivity extends AppCompatActivity {

    private static final String TAG = DailyForecastActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);

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
                String jsonData = response.body().string();
                Log.i(TAG, jsonData);
            }
        });
    }
}
