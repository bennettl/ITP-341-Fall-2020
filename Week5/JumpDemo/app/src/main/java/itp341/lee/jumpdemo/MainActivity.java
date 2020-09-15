package itp341.lee.jumpdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // If loadNyan is true, then load the NyanFragment
    private boolean loadNyan;

    private static final String LOAD_NYAN = "loadNyan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check if we have saved data on savedInstanceState
        if (savedInstanceState != null){
            loadNyan = savedInstanceState.getBoolean(LOAD_NYAN);
        }

        // Add Nyan cat fragment into the FrameLayout
        final FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.frameLayoutFragment);

        // If there are no fragment, we need to create one ourselves
        if (fragment == null){
            fragment = new NyanFragment();
            // Use the FragmentTransaction object to add a fragment to the FrameLayout
            final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frameLayoutFragment, fragment);
            fragmentTransaction.commit();
            loadNyan = false;
        }

        Button button = findViewById(R.id.buttonSwap);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swap();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(LOAD_NYAN, loadNyan);
    }

    // Switch the fragments
    private void swap(){
        Fragment fragment;

        if (loadNyan){
            fragment = new NyanFragment();
            loadNyan = false;
        } else{
            fragment = new DoodlerFragment();
            loadNyan = true;
        }

        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutFragment, fragment);
        fragmentTransaction.commit();
    }
}