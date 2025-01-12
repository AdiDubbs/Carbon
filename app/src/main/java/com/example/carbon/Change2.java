package com.example.carbon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

public class Change2 extends AppCompatActivity {
    private double totalFootprint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change2);
        totalFootprint = Double.parseDouble(Objects.requireNonNull(getIntent().getExtras()).getString("Footprint1"));
        Button nextButton = findViewById(R.id.change2_btn);
        nextButton.setOnClickListener(view -> {
            calculateFootprint();
            Intent intent = new Intent(Change2.this, Change3.class);
            intent.putExtra("Footprint2", "" + totalFootprint);
            startActivity(intent);
        });
    }

    private void calculateFootprint() {
        double petrolMileage = parseInput(findViewById(R.id.change2_a6_petrol_mileage));
        double petrolDistance = parseInput(findViewById(R.id.change2_a6_petrol_distance));
        double dieselMileage = parseInput(findViewById(R.id.change2_a6_diesel_mileage));
        double dieselDistance = parseInput(findViewById(R.id.change2_a6_diesel_distance));
        double flightDistance = parseInput(findViewById(R.id.change2_a7));

        double petrolImpact = (petrolMileage == 0 || petrolDistance == 0) ? 0 : (petrolDistance / petrolMileage) * 0.0022;
        double dieselImpact = (dieselMileage == 0 || dieselDistance == 0) ? 0 : (dieselDistance / dieselMileage) * 0.0026;
        double flightImpact = flightDistance * 0.00028;

        double footprint2 = petrolImpact + dieselImpact + flightImpact + flightImpact;
        totalFootprint = totalFootprint + footprint2;
    }

    private double parseInput(EditText inputField) {
        String inputText = inputField.getText().toString().trim();
        return inputText.isEmpty() ? (double) 0 : Double.parseDouble(inputText);
    }
}