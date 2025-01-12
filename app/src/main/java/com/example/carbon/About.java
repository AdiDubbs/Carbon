package com.example.carbon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.Button;
import android.os.Bundle;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Button homeButton = findViewById(R.id.version_btn);
        homeButton.setOnClickListener(view -> {
            startActivity(new Intent(About.this, Home.class));
        });
    }
}