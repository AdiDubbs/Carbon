package com.example.carbon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

public class Change3 extends AppCompatActivity {

    private double totalFootprint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change3);
        totalFootprint = Double.parseDouble(Objects.requireNonNull(getIntent().getExtras()).getString("Footprint2"));
        Button nextButton = findViewById(R.id.change3_btn);
        nextButton.setOnClickListener(view -> {
            calculateFootprint();
            Intent intent = new Intent(Change3.this, Change4.class);
            intent.putExtra("Footprint3", "" + totalFootprint);
            startActivity(intent);
        });
    }

    private void calculateFootprint() {

        double taxiValue = parseInput(findViewById(R.id.change3_cab_count));
        double busValue = parseInput(findViewById(R.id.change3_bus_count));
        double localTrainValue = parseInput(findViewById(R.id.change3_local_train_count));
        double longTrainValue = parseInput(findViewById(R.id.change3_intercity_train_count));

        double taxiImpact = (taxiValue * 0.00015);
        double busImpact = (busValue * 0.0001);
        double localTrainImpact = (localTrainValue * 0.000035);
        double longTrainImpact = (longTrainValue * 0.000004);

        double footprint3 = taxiImpact + busImpact + localTrainImpact + longTrainImpact;

        totalFootprint = totalFootprint + footprint3;
    }

    private double parseInput(EditText input) {
        String text = input.getText().toString().trim();
        return text.isEmpty() ? 0 : Double.parseDouble(text);
    }
}