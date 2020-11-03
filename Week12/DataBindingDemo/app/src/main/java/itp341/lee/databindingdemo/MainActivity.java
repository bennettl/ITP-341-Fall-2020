package itp341.lee.databindingdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import itp341.lee.databindingdemo.databinding.ActivityMainBinding;
import itp341.lee.databindingdemo.model.User;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        final User newUser = new User();
        newUser.setFirstName("Gabe");
        binding.setUser(newUser);
        binding.setMainActivity(this);

    }

    public void onClickButton(View view){
        Toast.makeText(this, "You clicked me", Toast.LENGTH_LONG).show();
    }

    public void showToast(){
        Toast.makeText(this, "You clicked me", Toast.LENGTH_LONG).show();
    }

}