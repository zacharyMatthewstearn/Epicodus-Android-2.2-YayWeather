package com.example.guest.yayweather.services;

import com.example.guest.yayweather.Constants;
import com.example.guest.yayweather.models.Forecast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

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
        urlBuilder.addQueryParameter("units", "imperial");
        urlBuilder.addQueryParameter("cnt", "8");
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
                int temp = forecastJSON.getJSONObject("main").getInt("temp");
                String description = forecastJSON.getJSONArray("weather").getJSONObject(0).getString("description");
                int minTemp = forecastJSON.getJSONObject("main").getInt("temp_min");
                int maxTemp = forecastJSON.getJSONObject("main").getInt("temp_max");
                int humidity = forecastJSON.getJSONObject("main").getInt("humidity");

                forecast = new Forecast(placeName, id, temp, icon, description, humidity, minTemp, maxTemp);
            }
        }
        catch(IOException | JSONException e) {
            e.printStackTrace();
        }

        return forecast;
    }

    public ArrayList<Forecast> processFutureResults(Response response) {
        ArrayList<Forecast> forecasts = new ArrayList<Forecast>();

        try {
            String jsonData = response.body().string();
            if(response.isSuccessful()) {
                JSONObject forecastJSON = new JSONObject(jsonData);
                JSONArray forecastArray = forecastJSON.getJSONArray("list");
                for(int i = 0; i < forecastArray.length(); i++) {
                    JSONObject thisForecast = forecastArray.getJSONObject(i);
                    String placeName = forecastJSON.getJSONObject("city").getString("name");
                    String icon = thisForecast.getJSONArray("weather").getJSONObject(0).getString("icon");
                    String description = thisForecast.getJSONArray("weather").getJSONObject(0).getString("description");
                    int minTemp = thisForecast.getJSONObject("temp").getInt("min");
                    int maxTemp = thisForecast.getJSONObject("temp").getInt("max");
                    int humidity = thisForecast.getInt("humidity");
                    long date = thisForecast.getLong("dt");
                    Forecast newForecast = new Forecast(placeName, icon, description, humidity, minTemp, maxTemp, date);
                    forecasts.add(newForecast);
                }
            }
        }
        catch(IOException | JSONException e) {
            e.printStackTrace();
        }

        return forecasts;
    }
}
