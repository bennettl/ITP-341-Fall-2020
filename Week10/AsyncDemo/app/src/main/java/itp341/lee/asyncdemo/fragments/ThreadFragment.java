package itp341.lee.asyncdemo.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import itp341.lee.asyncdemo.R;
import itp341.lee.asyncdemo.util.ImageDownloader;

public class ThreadFragment extends Fragment {

    private ImageView imageView;

    public ThreadFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loading, container, false);

        imageView = view.findViewById(R.id.imageView);
        Button buttonLoad = view.findViewById(R.id.button_load);
        Button buttonOther = view.findViewById(R.id.button_toast);

        buttonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImageV3();
            }
        });
        buttonOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "I'm working (Thread)", Toast.LENGTH_LONG ).show();
            }
        });

        return view;
    }

    //original version that simulates network delay of 5 seconds
    //sometimes causes ANR after clicking other button
    private void loadImageV1(){
        Bitmap bitmap = ImageDownloader.download(getContext());
        imageView.setImageBitmap(bitmap);
    }

    //second version that eliminates ANR due to network delay
    //but causes crash because we are updating the UI on the background thread (image loading)
    private void loadImageV2(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = ImageDownloader.download(getContext());
                imageView.setImageBitmap(bitmap);
            }
        });
        thread.start();
    }

    private void loadImageV3(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = ImageDownloader.download(getContext());

                // go back to main / UI thread to update UI components
                imageView.post(new Runnable() {
                    @Override
                    public void run() {
                        // update the UI
                        imageView.setImageBitmap(bitmap);
                    }
                });
            }
        });
        thread.start();
    }
}
