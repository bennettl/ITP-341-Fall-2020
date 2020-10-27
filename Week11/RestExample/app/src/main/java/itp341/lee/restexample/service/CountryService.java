package itp341.lee.restexample.service;

import java.util.List;

import itp341.lee.restexample.api.CountryApi;
import itp341.lee.restexample.model.Country;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// Singleton that configures Retrofit client and has a method to get countries
public class CountryService {

    private static final String BASE_URL = "https://restcountries.eu/rest/v2/";

    private static CountryService countryService;

    public static CountryService get(){
        if (countryService == null){
            countryService = new CountryService();
        }

        return countryService;
    }

    private CountryApi countryApi;

    private CountryService(){

        // Configure Retrofit REST client
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        countryApi = retrofit.create(CountryApi.class);

    }

    public void getCountries(String countryCode, Callback<List<Country>> callback){
        countryApi.getCountries(countryCode).enqueue(callback);
    }
}
