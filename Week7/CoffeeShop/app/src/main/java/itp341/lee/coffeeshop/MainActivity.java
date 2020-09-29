package itp341.lee.coffeeshop;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import itp341.lee.coffeeshop.model.CoffeeShop;
import itp341.lee.coffeeshop.model.CoffeeShopSingleton;

public class MainActivity extends AppCompatActivity {

    private Button buttonAdd;

    private ListView listView;

    private ArrayAdapter<CoffeeShop> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAdd = findViewById(R.id.button_add);

        listView = findViewById(R.id.listView);

        List<CoffeeShop> coffeeShops = CoffeeShopSingleton.get(this).getCoffeeShops();

        // Access coffee shop list and load it in the list
        arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                coffeeShops
        );
        listView.setAdapter(arrayAdapter);

        // Handle when user clicks add button
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        // Handle when user clicks on list view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_POSITION, position);
                startActivityForResult(intent, 0);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        arrayAdapter.notifyDataSetChanged();
    }
}