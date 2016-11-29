package com.example.guest.yayweather.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.guest.yayweather.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ForecastActivity extends AppCompatActivity {
    @Bind(R.id.textView_zippy)
    TextView mTextViewZippy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        mTextViewZippy.setText(intent.getStringExtra("zippy"));
    }
}
