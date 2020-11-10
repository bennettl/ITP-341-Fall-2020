package itp341.lee.mvvmdemo.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import itp341.lee.mvvmdemo.model.WeatherResponse;
import itp341.lee.mvvmdemo.respository.WeatherRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<WeatherResponse> currentWeather;

    private MutableLiveData<String> currentCity;

    public MainActivityViewModel(){
        currentWeather = new MutableLiveData<>();
        currentCity = new MutableLiveData<>();
    }

    public LiveData<String> getCurrentCity(){
        return currentCity;
    }

    public LiveData<WeatherResponse> getCurrentWeather(){
        return currentWeather;
    }

    public void updateCurrentCity(String cityName){
        currentCity.setValue(cityName);

        WeatherRepository.get().getCurrentWeather(cityName, new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                currentWeather.setValue(response.body());
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    public String convertToFahrenheit(double degreesKelvin)
    {
        return String.format("%.2f",(((degreesKelvin - 273) * 9.0/5.0) + 32.0));
    }
}
