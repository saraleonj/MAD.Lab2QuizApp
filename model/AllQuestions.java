package com.example.quizapp.model;

import com.example.quizapp.R;

public class AllQuestions {

    private int questionIndex;

    public Question[] allQuestions = {
            new Question(R.string.start_q, true),
            new Question(R.string.continent_q, true),
            new Question(R.string.oceans_q, false),
            new Question(R.string.add3_4_q, true),
            new Question(R.string.add5_4_q, false)

    };

    public AllQuestions() { questionIndex = 0; }

    public Question getQuestion(int index) {
        index = index % allQuestions.length;
        return allQuestions[index];
    }




}
