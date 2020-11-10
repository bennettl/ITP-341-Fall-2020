package itp341.lee.mvvmdemo.respository;

import itp341.lee.mvvmdemo.api.WeatherApi;
import itp341.lee.mvvmdemo.model.WeatherResponse;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherRepository {
    private static WeatherRepository shared;

    public static WeatherRepository get(){
        if (shared == null){
            shared = new WeatherRepository();
        }

        return shared;
    }

    private final String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    private final String APP_ID = "2fdb5894a9de0566060b6f8ee38c1c84";

    private WeatherApi weatherApi;

    private WeatherRepository(){
        // Configure the Retrofit REST client
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        weatherApi = retrofit.create(WeatherApi.class);
    }

    public void getCurrentWeather(String cityName, Callback<WeatherResponse> callback){
        weatherApi.getCurrentWeather(cityName, APP_ID).enqueue(callback);
    }
}
