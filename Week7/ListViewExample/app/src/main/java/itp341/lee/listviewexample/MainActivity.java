package itp341.lee.listviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView = findViewById(R.id.listViewMain);
        final TextView textView = findViewById(R.id.text_no_companies);

        // Prepare the data (model)
//        final List<String> companies = Arrays.asList("Facebook", "Google", "Amazon", "Netflix", "Apple");

        final List<String> companies = new ArrayList<>();

        // Create the adapter
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, companies);

        // Link the ListView (subclass of AdapterView) to adapter
        listView.setAdapter(arrayAdapter);

        listView.setEmptyView(textView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

    }

}