package com.example.quizapp;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    private TextView textView;
    private Button quit_button;

    private final String N_TAG = "IN_ONCLICK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int score = getIntent().getIntExtra("score", -1);

        textView = findViewById(R.id.finalscore_view);
        textView.setText(String.valueOf(score).toString());
        quit_button = findViewById(R.id.quit_button);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        quit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(N_TAG, "Clicked Quit");
                QuitApp(v);
            }
        });

    }

        public void QuitApp(View view){
            SummaryActivity.this.finish();
            System.exit(0);
        }

}