package com.example.guest.yayweather.services;

import com.example.guest.yayweather.Constants;
import com.example.guest.yayweather.models.Forecast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherService {

    public static void getCurrentWeather(String zip, Callback callback){
        OkHttpClient client = new OkHttpClient.Builder().build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.API_CURRENT_WEATHER_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.ZIP_QUERY_PARAMETER, zip);
        urlBuilder.addQueryParameter(Constants.API_KEY_QUERY_PARAMETER, Constants.API_KEY);
        urlBuilder.addQueryParameter("units", "imperial");
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public static void getFutureWeather(String id, Callback callback){
        OkHttpClient client = new OkHttpClient.Builder().build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.API_FORECAST_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.ID_QUERY_PARAMTER, id);
        urlBuilder.addQueryParameter(Constants.API_KEY_QUERY_PARAMETER, Constants.API_KEY);
        urlBuilder.addQueryParameter("cnt", "7");
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public Forecast processCurrentResults(Response response) {
        Forecast forecast = null;

        try {
            String jsonData = response.body().string();
            if(response.isSuccessful()) {
                JSONObject forecastJSON = new JSONObject(jsonData);

                String placeName = forecastJSON.getString("name");
                int id = forecastJSON.getInt("id");
                String icon = forecastJSON.getJSONArray("weather").getJSONObject(0).getString("icon");
                double temp = forecastJSON.getJSONObject("main").getDouble("temp");
                String description = forecastJSON.getJSONArray("weather").getJSONObject(0).getString("description");
                double minTemp = forecastJSON.getJSONObject("main").getDouble("temp_min");
                double maxTemp = forecastJSON.getJSONObject("main").getDouble("temp_max");
                int humidity = forecastJSON.getJSONObject("main").getInt("humidity");

                forecast = new Forecast(placeName, id, temp, icon, description, humidity, minTemp, maxTemp);
            }
        }
        catch(IOException | JSONException e) {
            e.printStackTrace();
        }

        return forecast;
    }

}
