package itp341.lee.restexample.api;

import java.util.List;

import itp341.lee.restexample.model.Country;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CountryApi {
    @GET("name/{countryCode}")
    Call<List<Country>> getCountries(@Path("countryCode") String country);

}
