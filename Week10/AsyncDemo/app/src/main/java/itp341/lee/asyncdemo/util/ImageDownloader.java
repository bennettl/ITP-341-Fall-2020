package itp341.lee.asyncdemo.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import itp341.lee.asyncdemo.R;

public class ImageDownloader {

    public static Bitmap download(Context context){

        try {
            Thread.sleep(5000);
            return BitmapFactory.decodeResource(context.getResources(), R.drawable.painter);
        } catch(InterruptedException e){
            e.printStackTrace();
        }

        return null;
    }
}
