package com.example.guest.yayweather.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.yayweather.R;
import com.example.guest.yayweather.models.Forecast;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DailyForecastDetailsFragment extends Fragment {
    @Bind(R.id.iconImageView) ImageView mImageViewIcon;
    @Bind(R.id.dayTextView)
    TextView mTextViewDay;
    @Bind(R.id.dateTextView) TextView mTextViewDate;
    @Bind(R.id.tempTextView) TextView mTextViewMinMax;
    @Bind(R.id.humidityTextView) TextView mTextViewHumidity;
    @Bind(R.id.descriptionTextView) TextView mTextViewDescription;

    private Forecast mForecast;

    public static DailyForecastDetailsFragment newInstance(Forecast forecast) {
        DailyForecastDetailsFragment forecastDetailFragment = new DailyForecastDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable("forecast", Parcels.wrap(forecast));
        forecastDetailFragment.setArguments(args);
        return forecastDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mForecast = Parcels.unwrap(getArguments().getParcelable("forecast"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily_forecast_details, container, false);
        ButterKnife.bind(this, view);

        String imageUrl = "http://openweathermap.org/img/w/" + mForecast.mIcon+".png";
        Picasso.with(view.getContext()).load(imageUrl).resize(200, 200).error(R.drawable.angel).into(mImageViewIcon);
        mTextViewDay.setText(mForecast.formatDate("EEE"));
        mTextViewDate.setText(mForecast.formatDate("MMM d"));
        mTextViewMinMax.setText(mForecast.mMinTemp + "°/" + mForecast.mMaxTemp + "°");
        mTextViewHumidity.setText("humidity: " + mForecast.mHumidity + "%");
        mTextViewDescription.setText(mForecast.mDescription);

        return view;
    }

}
