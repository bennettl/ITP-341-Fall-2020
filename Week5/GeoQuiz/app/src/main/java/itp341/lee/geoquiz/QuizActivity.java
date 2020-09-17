package itp341.lee.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_CHEAT_ACTIVITY = 100;

    private static final String BUNDLE_CURRENT_INDEX = "currentIndex";

    public static final String EXTRA_USER_CHEATED = "itp341.lee.geoquiz.userCheated";

    private TextView textViewQuestion;

    //Instance variables
    private boolean isCheater;		//flag which indicates if user cheated on current question

    private String[] questions;		//array of questions

    private int[] answers;			//array of answers (0 for false, 1 for true)

    private int currentIndex;		//current question

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //initialize variables
        isCheater = false;
        questions = getResources().getStringArray(R.array.array_questions);
        answers = getResources().getIntArray(R.array.array_answers);
        currentIndex = 0;

        if (savedInstanceState != null){
            currentIndex = savedInstanceState.getInt(BUNDLE_CURRENT_INDEX, 0);
        }

        //find view
        final Button buttonCheat = findViewById(R.id.button_cheat);
        final Button buttonNext = findViewById(R.id.button_next);
        final Button buttonTrue = findViewById(R.id.button_true);
        final Button buttonFalse = findViewById(R.id.button_false);
        textViewQuestion = findViewById(R.id.text_question);

        //set up listeners
        buttonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });

        buttonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = (currentIndex + 1) % questions.length ;
                isCheater = false;
                updateQuestion();
            }
        });

        buttonCheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              // Create an explicit intent, telling Android OS we want to start the CheatActivity
                final Intent intent = new Intent(QuizActivity.this, CheatActivity.class);
                final boolean answer = (answers[currentIndex] == 1);
                intent.putExtra(CheatActivity.EXTRA_ANSWER, answer);
                startActivityForResult(intent, REQUEST_CODE_CHEAT_ACTIVITY);
            }
        });

        // updateQuestion
        updateQuestion();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(BUNDLE_CURRENT_INDEX, currentIndex);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CHEAT_ACTIVITY){
            if (data != null){
                isCheater = data.getBooleanExtra(EXTRA_USER_CHEATED, false);
            } else{
                isCheater = false;
            }
        }
    }

    //TODO
    /*
     * 	updateQuestion
     * 		Uses the current index to update the text view with the current question
     */
    private void updateQuestion() {
        final String currentQuestion = questions[currentIndex];
        textViewQuestion.setText(currentQuestion);;
    }

    //TODO
    /*
     * 	checkAnswer
     * 		input: boolean - indicates if user pressed true or false
     * 		side-effect: displays a Toast based on 1) user's answer and 2) whether they cheated or not
     *
     * 		scenarios --> Toast message
     * 			User didn't cheat and answered incorrectly 	--> incorrect
     * 			User didn't cheat and answered correctly 	--> correct
     * 			User did cheat and answered incorrectly 	--> incorrect_judgment
     * 			User did cheat and answered correctly 		--> correct_judgment
     */
    private void checkAnswer(boolean userPressedTrue) {
        final boolean answer = (answers[currentIndex] == 1);

        int messageResourceId = 0;

        if (isCheater){
            if (userPressedTrue == answer){
                // Cheating is wrong
                messageResourceId = R.string.toast_correct_judgment;
            } else{
                // You do not cheat very well. Stick to guessing
                messageResourceId = R.string.toast_incorrect_judgment;
            }
        } else{
            if (userPressedTrue == answer){
                // correct!
                messageResourceId = R.string.toast_correct;
            } else{
                // incorrect
                messageResourceId = R.string.toast_incorrect;
            }
        }

        Toast.makeText(this, messageResourceId, Toast.LENGTH_SHORT).show();
    }
}