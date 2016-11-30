package com.example.guest.yayweather.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.yayweather.R;
import com.example.guest.yayweather.models.Forecast;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ForecastListAdapter extends RecyclerView.Adapter<ForecastListAdapter.ForecastViewHolder>{
    private ArrayList<Forecast> mForecasts = new ArrayList<>();
    private Context mContext;

    public ForecastListAdapter(Context context, ArrayList<Forecast> forecasts) {
        mContext = context;
        mForecasts = forecasts;
    }

    @Override
    public ForecastListAdapter.ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_daily_list_item, parent, false);
        ForecastViewHolder viewHolder = new ForecastViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ForecastViewHolder holder, int position) {
        holder.bindForecast(mForecasts.get(position));
    }

    @Override
    public int getItemCount() {
        return mForecasts.size();
    }

    public class ForecastViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.iconImageView) ImageView mIconImageView;
        @Bind(R.id.descriptionTextView) TextView mDescriptionTextView;
        @Bind(R.id.tempTextView) TextView mTempTextView;
        @Bind(R.id.dateTextView) TextView mTextViewDate;
        @Bind(R.id.dayTextView) TextView mTextViewDay;
        @Bind(R.id.humidityTextView) TextView mTextViewHumidity;
        private Context mContext;

        public ForecastViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindForecast(Forecast forecast) {
            String imageUrl = "http://openweathermap.org/img/w/" + forecast.getIcon()+".png";
            Picasso.with(mContext).load(imageUrl).resize(128, 128).into(mIconImageView);
            mTextViewDate.setText(forecast.formatDate("MMM d"));
            mTextViewDay.setText(forecast.formatDate("EEE"));
            mDescriptionTextView.setText(forecast.getDescription());
            mTempTextView.setText(forecast.getMinTemp() + "°/" + forecast.getMaxTemp() + "°");
            mTextViewHumidity.setText("Humidity: " + forecast.getHumidity() + "%");
        }
    }
}
