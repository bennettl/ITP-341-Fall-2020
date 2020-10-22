package itp341.lee.asyncdemo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import itp341.lee.asyncdemo.R;

public class MainFragment extends Fragment {

    public MainFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        Button buttonThread = view.findViewById(R.id.button_thread);
        Button buttonAsync = view.findViewById(R.id.button_async);

        buttonThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Fragment fragment = getFragmentManager().findFragmentById(R.id.fragment_container);
//
//                if (fragment != null){
//                    fragment =;
//                }

                setFragment(new ThreadFragment());
            }
        });

        buttonAsync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Fragment fragment = getFragmentManager().findFragmentById(R.id.fragment_container);
//
//                if (fragment != null){
//                    fragment =;
//                }

                setFragment(new AsyncFragment());
            }
        });

        return view;
    }

    private void setFragment(Fragment fragment){
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}