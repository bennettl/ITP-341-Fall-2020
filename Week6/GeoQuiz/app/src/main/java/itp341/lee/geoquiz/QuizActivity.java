package itp341.lee.geoquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import itp341.lee.geoquiz.model.QuizQuestion;
import itp341.lee.geoquiz.model.Stats;

public class QuizActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_CHEAT_ACTIVITY = 100;

    private static final String PREF_CORRECT = "correct";

    private static final String PREF_INCORRECT = "incorrect";

    private static final String PREF_CHEATED = "cheated";

    private static final String PREF_FILE_NAME_STATS = "stats";

    private static final String BUNDLE_CURRENT_INDEX = "currentIndex";

    public static final String EXTRA_USER_CHEATED = "itp341.lee.geoquiz.userCheated";

    private TextView textViewQuestion;

    private TextView textViewCorrect;

    private TextView textViewIncorrect;

    private TextView textViewCheated;

    //Instance variables
    private boolean isCheater;		//flag which indicates if user cheated on current question

    private List<QuizQuestion> questions;

    private Stats stats;

    private int currentIndex;		//current question

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //initialize variables
        isCheater = false;
        currentIndex = 0;
        initializeQuestions();
        loadStats();

        if (savedInstanceState != null){
            currentIndex = savedInstanceState.getInt(BUNDLE_CURRENT_INDEX, 0);
        }

        //find view
        final Button buttonCheat = findViewById(R.id.button_cheat);
        final Button buttonNext = findViewById(R.id.button_next);
        final Button buttonTrue = findViewById(R.id.button_true);
        final Button buttonFalse = findViewById(R.id.button_false);
        textViewQuestion = findViewById(R.id.text_question);
        textViewCorrect = findViewById(R.id.textViewCorrect);
        textViewIncorrect = findViewById(R.id.textViewIncorrect);
        textViewCheated = findViewById(R.id.textViewCheated);

        displayStats();

        //set up listeners
        buttonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
                saveStats();
                displayStats();
            }
        });

        buttonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
                saveStats();
                displayStats();
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = (currentIndex + 1) % questions.size();
                isCheater = false;
                updateQuestion();

            }
        });

        buttonCheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              // Create an explicit intent, telling Android OS we want to start the CheatActivity
                final Intent intent = new Intent(QuizActivity.this, CheatActivity.class);
                final boolean answer = questions.get(currentIndex).getAnswer();
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

    // Intialize quiz questions array
    private void initializeQuestions(){
        String[] questionStrings = getResources().getStringArray(R.array.array_questions);
        int[] answers = getResources().getIntArray(R.array.array_answers);

        // Initialize an empty array
        questions = new ArrayList<>();

        for (int i = 0; i < questionStrings.length; i++){
            final QuizQuestion quizQuestion = new QuizQuestion(questionStrings[i], answers[i] == 1);
            questions.add(quizQuestion);
        }
    }

    // Save stats to shared preferences
    private void saveStats(){
        final SharedPreferences sharedPreferences = getSharedPreferences(PREF_FILE_NAME_STATS, MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PREF_CORRECT, stats.getCorrect());
        editor.putInt(PREF_INCORRECT, stats.getIncorrect());
        editor.putInt(PREF_CHEATED, stats.getCheated());
        editor.commit();
    }

    // Load stats from shared preferences
    private void loadStats(){
        final SharedPreferences sharedPreferences = getSharedPreferences(PREF_FILE_NAME_STATS, MODE_PRIVATE);
        final int cheated = sharedPreferences.getInt(PREF_CHEATED, 0);
        final int correct = sharedPreferences.getInt(PREF_CORRECT, 0);
        final int incorrect = sharedPreferences.getInt(PREF_INCORRECT, 0);
        stats = new Stats(correct, incorrect, cheated);
    }

    // Display stats into text views
    private void displayStats(){
        textViewCorrect.setText(getString(R.string.text_correct, stats.getCorrect()));
        textViewIncorrect.setText(getString(R.string.text_incorrect, stats.getIncorrect()));
        textViewCheated.setText(getString(R.string.text_cheated, stats.getCheated()));
    }

    /*
     * 	updateQuestion
     * 		Uses the current index to update the text view with the current question
     */
    private void updateQuestion() {
        final String currentQuestion = questions.get(currentIndex).getQuestion();
        textViewQuestion.setText(currentQuestion);;
    }


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
        final boolean answer = questions.get(currentIndex).getAnswer();

        int messageResourceId = 0;

        if (isCheater){

            stats.incrementCheated();

            if (userPressedTrue == answer){
                // Cheating is wrong
                messageResourceId = R.string.toast_correct_judgment;
            } else{
                // You do not cheat very well. Stick to guessing
                messageResourceId = R.string.toast_incorrect_judgment;
            }
        } else{
            if (userPressedTrue == answer){
                stats.incrementCorrect();
                // correct!
                messageResourceId = R.string.toast_correct;
            } else{
                stats.incrementIncorrect();
                // incorrect
                messageResourceId = R.string.toast_incorrect;
            }
        }

        Toast.makeText(this, messageResourceId, Toast.LENGTH_SHORT).show();
    }
}