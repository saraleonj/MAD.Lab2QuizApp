package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;

import com.example.quizapp.controller.NextQuestion;
import com.example.quizapp.controller.Score;
import com.example.quizapp.model.AllQuestions;
import com.example.quizapp.model.Question;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_INDEX = "GAME_MAIN_ACTIVITY";

    private TextView question_view;
    private TextView score_view;
    private Button true_button;
    private Button false_button;
    private Button next_button;
    private Button done_button;


    AllQuestions allQuestions = new AllQuestions();
    NextQuestion nextQuestion = new NextQuestion();
    Score score = new Score();

    private final String N_TAG = "IN_ONCLICK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        question_view = findViewById(R.id.question_view);
        score_view = findViewById(R.id.score_view);

        question_view.setText(R.string.start_q);
        score_view.setText(R.string.initial_score);


        true_button = findViewById(R.id.t_button);
        false_button = findViewById(R.id.f_button);
        next_button = findViewById(R.id.next_button);
        done_button = findViewById(R.id.done_button);



        true_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(N_TAG, "Clicked True");
                tfButtons(v,true);
            }
        });

        false_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(N_TAG, "Clicked False");
                tfButtons(v, false);
            }
        });

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(N_TAG, "Clicked Next");
                int index = nextQuestion.getCurrentQuestion();
                Question question = null;
                try {
                    question = allQuestions.getQuestion(index);
                } catch (Exception e) {
                    Log.d(TAG_INDEX, "index out of bounds");
                }

                score.skipQuestion();
                index = nextQuestion.getNextQuestionIndex();
                score_view.setText(String.valueOf(score.getScore()));

                question_view.setText(allQuestions.getQuestion(index).getQuestionID());

            }
        });

        done_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(N_TAG, "Clicked Done");
                SummaryActivity(v);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void SummaryActivity (View v){
        Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
        intent.putExtra("score", score.getScore());
        startActivity(intent);
        MainActivity.this.finish();
    }

    public void tfButtons (View v, Boolean flag){
        int index = nextQuestion.getCurrentQuestion();
        Question question = null;
        try {
            question = allQuestions.getQuestion(index);
        } catch (Exception e) {
            Log.d(TAG_INDEX, "index out of bounds");
        }

        if (flag == false) {
            if (!question.isQuestionTrue()) {
                score.correctAnswer();
                score_view.setText(String.valueOf(score.getScore()));
            }
        }

        if (flag == true) {
            if (question.isQuestionTrue()) {
                score.correctAnswer();
                score_view.setText(String.valueOf(score.getScore()));
            }
        }


        index = nextQuestion.getNextQuestionIndex();
        question_view.setText(allQuestions.getQuestion(index).getQuestionID());
    }

}