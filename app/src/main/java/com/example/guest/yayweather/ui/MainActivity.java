package com.example.guest.yayweather.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.guest.yayweather.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.button_getWeather) Button mButtonGetWeather;
    @Bind(R.id.editText_inputZip) EditText mEditTextInputZip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        mButtonGetWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mEditTextInputZip.getText().toString().length() > 0){
                    Intent intent = new Intent(MainActivity.this, ForecastActivity.class);
                    intent.putExtra("zippy", mEditTextInputZip.getText().toString());
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Y U no entar zippy?!?!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
