package itp341.lee.coffeeshop;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import itp341.lee.coffeeshop.model.CoffeeShop;
import itp341.lee.coffeeshop.model.CoffeeShopSingleton;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_ID = DetailActivity.class.getPackage().getName() + "id";

    private long id = -1;

    private EditText editName;

    private EditText editAddress;

    private EditText editCity;

    private Spinner spinnerState;

    private EditText editZip;

    private EditText editPhone;

    private EditText editWebsite;

    private Button buttonSaveListing;

    private Button buttonDeleteListing;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        editName = findViewById(R.id.edit_name);
        editAddress = findViewById(R.id.edit_address);
        editCity = findViewById(R.id.edit_city);
        spinnerState = findViewById(R.id.spinner_state); // update
        editZip = findViewById(R.id.edit_zip);
        editPhone = findViewById(R.id.edit_phone);
        editWebsite = findViewById(R.id.edit_website);
        buttonSaveListing = findViewById(R.id.button_save_listing);
        buttonDeleteListing = findViewById(R.id.button_delete_listing);

        // -1 indiciates to us that there is no coffeeshop past to this activity, which means we are creating a new one
        Intent intent = getIntent();
        if (intent != null){
            id = intent.getIntExtra(EXTRA_ID, -1);

            if (id > -1){
                // Grab the coffeeshop at a given index
                CoffeeShop coffeeShop = CoffeeShopSingleton.get(this).getCoffeeShop(id);
                loadData(coffeeShop);
            }

        }

        buttonSaveListing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAndClose();
            }
        });

        buttonDeleteListing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAndClose();
            }
        });

    }

    // Load data existing coffee shop object into EditText
    private void loadData(CoffeeShop coffeeShop){
        editName.setText(coffeeShop.getName());
        editCity.setText(coffeeShop.getCity());

        // More fields to fill out...
    }

    private void saveAndClose(){
        CoffeeShop coffeeShop = new CoffeeShop();
        coffeeShop.setName(editName.getText().toString());
        coffeeShop.setCity(editCity.getText().toString());


        // More fields to fill out...

        // Add cofeeshop if position is invalid (-1)
        if (id == -1){
            CoffeeShopSingleton.get(this).addCoffeeShop(coffeeShop);
        }  else{
            CoffeeShopSingleton.get(this).updateCoffeeShop(coffeeShop);
        }

        finish();

    }

    private void deleteAndClose(){

        if (id != -1){
            final CoffeeShop coffeeShop = CoffeeShopSingleton.get(this).getCoffeeShop(id);
            CoffeeShopSingleton.get(this).removeCoffeeShop(coffeeShop);
        }

        finish();
    }
}
