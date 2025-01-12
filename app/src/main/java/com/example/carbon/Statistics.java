package com.example.carbon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Statistics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        Button returnButton = findViewById(R.id.statistics_return_btn);
        returnButton.setOnClickListener(view -> {
            Intent intent = new Intent(Statistics.this, Home.class);
            startActivity(intent);
        });
    }
}