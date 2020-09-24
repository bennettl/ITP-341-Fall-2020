package itp341.lee.preferencesdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a settings fragment and inject it into the FrameLayout
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frameLayoutSettings, new SettingsFragment())
                .commit();

        // Functionally equivalent, but arguably uglier
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.frameLayoutSettings, new SettingsFragment());
//        fragmentTransaction.commit();
    }
}