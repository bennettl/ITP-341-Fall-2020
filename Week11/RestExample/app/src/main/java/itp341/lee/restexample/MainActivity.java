package itp341.lee.restexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import itp341.lee.restexample.model.Country;
import itp341.lee.restexample.service.CountryService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listViewMain);
        final ArrayAdapter<Country> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<Country>());
        listView.setAdapter(arrayAdapter);

        CountryService.get().getCountries("us", new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {

                arrayAdapter.addAll(response.body());
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {

            }
        });

    }
}