package itp341.lee.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CheatActivity extends AppCompatActivity {

    public static final String EXTRA_ANSWER = "itp341.lee.geoquiz.answer";

    boolean answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        Intent intent = getIntent();
        if (intent != null){
            answer = intent.getBooleanExtra(EXTRA_ANSWER, false);
        }

        // Find the views
        final TextView textViewAnswer = findViewById(R.id.text_answer);
        final Button buttonShowAnswer = findViewById(R.id.button_show_answer);
        buttonShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dataIntent = new Intent();
                dataIntent.putExtra(QuizActivity.EXTRA_USER_CHEATED, true);
                setResult(RESULT_OK, dataIntent);

                if (answer){
                    // set text view to be true
                    textViewAnswer.setText(R.string.label_true);
                } else{
                    // set text view to be false
                    textViewAnswer.setText(R.string.label_false);
                }
            }
        });

    }
}