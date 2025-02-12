package com.example.carbon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button continueButton = findViewById(R.id.main_btn);
        continueButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, QuizInfo.class)));
    }
}
