package bennett.lee.mochademo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView imageView = findViewById(R.id.main_image);
        final String imageUrl = "https://fox28spokane.com/wp-content/uploads/2020/04/AprilShoe-720x399.jpg";

        Picasso.get().load(imageUrl).into(imageView);

    }
}