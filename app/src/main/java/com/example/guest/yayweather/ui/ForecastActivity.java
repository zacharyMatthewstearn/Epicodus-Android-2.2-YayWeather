package com.example.guest.yayweather.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.yayweather.R;
import com.example.guest.yayweather.models.Forecast;
import com.example.guest.yayweather.services.WeatherService;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ForecastActivity extends AppCompatActivity {
    private static final String TAG =ForecastActivity.class.getSimpleName();
    @Bind(R.id.textView_heading) TextView mTextViewHeading;
    @Bind(R.id.textView_zippy) TextView mTextViewZippy;
    @Bind(R.id.imageView_icon) ImageView mImageViewIcon;
    @Bind(R.id.textView_temp) TextView mTextViewTemp;
    @Bind(R.id.textView_minmax) TextView mTextViewMinMax;
    @Bind(R.id.textView_humidity) TextView mTextViewHumidity;
    @Bind(R.id.textView_description) TextView mTextViewDescription;
    @Bind(R.id.button_daily) Button mButtonDaily;
    private Forecast mCurrentForecast;
    private int mPlaceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String zippy = intent.getStringExtra("zippy");
        mTextViewZippy.setText(intent.getStringExtra("zippy"));
        getForecast(zippy);
        mButtonDaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(ForecastActivity.this, DailyForecastActivity.class);
                newIntent.putExtra("placeId", mPlaceId);
                startActivity(newIntent);
            }
        });
    }

    private void getForecast(String zippy){
        final WeatherService weatherService = new WeatherService();
        weatherService.getCurrentWeather(zippy, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mCurrentForecast =  weatherService.processCurrentResults(response);
                ForecastActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mPlaceId = mCurrentForecast.mId;
                        mTextViewHeading.setText(mCurrentForecast.mPlaceName);
                        String imageUrl = "http://openweathermap.org/img/w/" + mCurrentForecast.mIcon+".png";
                        Picasso.with(ForecastActivity.this).load(imageUrl).resize(200, 200).error(R.drawable.angel).into(mImageViewIcon);
                        mTextViewTemp.setText(mCurrentForecast.mTemp + "°");
                        mTextViewMinMax.setText(mCurrentForecast.mMinTemp + "°/" + mCurrentForecast.mMaxTemp + "°");
                        mTextViewHumidity.setText("humidity: " + mCurrentForecast.mHumidity + "%");
                        mTextViewDescription.setText(mCurrentForecast.mDescription);
                    }
                });
            }
        });
    }
}
