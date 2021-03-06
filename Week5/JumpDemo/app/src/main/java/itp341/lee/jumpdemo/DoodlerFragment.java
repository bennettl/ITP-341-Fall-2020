package itp341.lee.jumpdemo;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DoodlerFragment extends Fragment {

    public DoodlerFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View linearLayout = inflater.inflate(R.layout.fragment_doodler, container, false);

        final Button buttonJump = linearLayout.findViewById(R.id.buttonDoodlerJump);
        final ImageView imageView = linearLayout.findViewById(R.id.imageViewDoodler);

        buttonJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumpAnimation(imageView);
            }
        });

        return linearLayout;
    }

    public void jumpAnimation(Object o){
        ObjectAnimator heightAnimator = ObjectAnimator.ofFloat(o,"y", 0 , 50).setDuration(500);
        //Animator places object at 0 and takes it 50 from the top view
        //Pressing quickly creates jumping illusion
        //Adding more int values such as 0, 50, 0 will animate from 0, 50, to 0.
        heightAnimator.start();
    }
}
