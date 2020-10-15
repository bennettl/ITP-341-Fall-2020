package itp341.lee.coffeeshop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import itp341.lee.coffeeshop.model.CoffeeShop;
import itp341.lee.coffeeshop.model.CoffeeShopSingleton;

public class MainActivity extends AppCompatActivity {

    private Button buttonAdd;

    private ListView listView;

//    private ArrayAdapter<CoffeeShop> arrayAdapter;

    private CoffeeAdapter coffeeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAdd = findViewById(R.id.button_add);

        listView = findViewById(R.id.listView);

        final List<CoffeeShop> coffeeShops = CoffeeShopSingleton.get(this).getCoffeeShops();

        // Access coffee shop list and load it in the list
//        arrayAdapter = new ArrayAdapter<>(
//                this,
//                android.R.layout.simple_list_item_1,
//                coffeeShops
//        );
//        listView.setAdapter(arrayAdapter);

        coffeeAdapter = new CoffeeAdapter(this, coffeeShops);
        listView.setAdapter(coffeeAdapter);

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
                intent.putExtra(DetailActivity.EXTRA_ID, id);
                startActivityForResult(intent, 0);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

                final CoffeeShop originalCoffeeShop = CoffeeShopSingleton.get(MainActivity.this).getCoffeeShop(id);

                // Ask our singleton to delete a coffeshop and refresh the list view
                CoffeeShopSingleton.get(MainActivity.this).removeCoffeeShop(originalCoffeeShop);
                refreshList();

                // Create and show a snackbar to let the user undo the delete operation

                Snackbar.make(
                        findViewById(R.id.layoutMain),
                        "Coffeeshop Gone!!!",
                        Snackbar.LENGTH_LONG
                ).setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CoffeeShopSingleton.get(MainActivity.this).addCoffeeShop(originalCoffeeShop);
                        refreshList();
                    }
                }).show();

                return false;
            }
        });

    }

    private void refreshList(){
        // Make sure our CoffeeAdapater has the latest CoffeeShops
        final List<CoffeeShop> coffeeShops = CoffeeShopSingleton.get(this).getCoffeeShops();
        coffeeAdapter.clear();
        coffeeAdapter.addAll(coffeeShops);
        coffeeAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        refreshList();
    }


    // Custom adapter used for custom layouts
    private class CoffeeAdapter extends ArrayAdapter<CoffeeShop>{
        private List<CoffeeShop> coffeeShops;

        public CoffeeAdapter(Context context, List<CoffeeShop> coffeeShops){
            super(context, R.layout.coffeeshop_row, coffeeShops);
            this.coffeeShops = coffeeShops;
        }

        @Override
        public long getItemId(int position) {
            final CoffeeShop coffeeShop = coffeeShops.get(position);
            return coffeeShop.getId();
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            // 1. We have a convertview we can work with
            if (convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.coffeeshop_row, parent, false);
            }

            // 2. Get the data for given position
            final CoffeeShop coffeeShop = coffeeShops.get(position);

            // 3. Update our convert view with data from model
            final TextView textViewTitle = convertView.findViewById(R.id.textViewTitle);
            final TextView textViewSubtitle = convertView.findViewById(R.id.textViewSubtitle);

            textViewTitle.setText(coffeeShop.getName());
            textViewSubtitle.setText(coffeeShop.getCity());

            // 4. Return the end result
            return convertView;
        }
    }
}