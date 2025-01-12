package com.example.carbon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class QuizInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_info);
        Button btnBegin = findViewById(R.id.quiz_info_btn);
        btnBegin.setOnClickListener(view ->
                startActivity(new Intent(QuizInfo.this, Change1.class)));
    }
}