package itp341.lee.mvvmdemo.model;

import java.util.List;

public class WeatherResponse {
    private List<Weather> weather;

    private WeatherMain main;

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public WeatherMain getMain() {
        return main;
    }

    public void setMain(WeatherMain main) {
        this.main = main;
    }
}
