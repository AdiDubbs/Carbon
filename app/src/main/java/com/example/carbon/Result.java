package com.example.carbon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        String totalFootprint = Objects.requireNonNull(getIntent().getExtras()).getString("Footprint4");
        TextView footprintView = findViewById(R.id.result_footprint);
        footprintView.setText(totalFootprint);
        Button continueButton = findViewById(R.id.result_btn);
        continueButton.setOnClickListener(view -> {
            Intent intent = new Intent(Result.this, Register.class);
            intent.putExtra("TotalFootprint", totalFootprint);
            startActivity(intent);
        });
    }
}