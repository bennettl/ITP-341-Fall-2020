package itp341.lee.livedataviewmodeldemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import itp341.lee.livedataviewmodeldemo.model.StateService;
import itp341.lee.livedataviewmodeldemo.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;

    private TextView mainTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        mainActivityViewModel.setUsername("John");

        mainTextView = findViewById(R.id.textViewMain);

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mainTextView.setText(s);
            }
        };

        StateService.get().getCurrentState().observe(this, observer);

        Button mainButton = findViewById(R.id.buttonMain);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StateService.get().updateCurrentState();
            }
        });
    }
}