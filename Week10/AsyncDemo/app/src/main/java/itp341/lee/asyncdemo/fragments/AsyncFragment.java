package itp341.lee.asyncdemo.fragments;

import android.graphics.Bitmap;
import android.os.AsyncTask;
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

public class AsyncFragment extends Fragment {

    private ImageView imageView;
    public AsyncFragment(){}

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
                DownloadImageTask downloadImageTask = new DownloadImageTask();
                downloadImageTask.execute("https://www.google.com/images/cute-puppy.png");
            }
        });
        buttonOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "I'm working (Async)", Toast.LENGTH_LONG ).show();
            }
        });

        return view;
    }

    class DownloadImageTask extends AsyncTask<String, Void, Bitmap>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Set loading spinner visibility to true
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
//            String imageUrl = strings[0];
            Bitmap bitmap = ImageDownloader.download(getContext());
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            // Set loading spinner visibility to false
            imageView.setImageBitmap(bitmap);
        }

    }
}
