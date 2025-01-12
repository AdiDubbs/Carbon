package com.example.carbon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Change1 extends AppCompatActivity{

    private double footprint1;
    private EditText inputPeople, inputElectricity, inputNaturalGas, inputLPG;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change1);
        setupUI();
        Button nextButton = findViewById(R.id.change1_btn);
        nextButton.setOnClickListener(view -> {
            calculateFootprint();
            Intent intent = new Intent(Change1.this, Change2.class);
            intent.putExtra("Footprint1", "" + footprint1);
            startActivity(intent);
        });
    }

    private void setupUI() {
        inputPeople = findViewById(R.id.change1_a1);
        inputElectricity = findViewById(R.id.change1_a2);
        inputNaturalGas = findViewById(R.id.change1_a3);
        inputLPG = findViewById(R.id.change1_a4);
    }

    private void calculateFootprint() {
        double countPeople = parseInput(inputPeople, 1);

        double countElectricity = parseInput(inputElectricity, 1000);
        double countNaturalGas = parseInput(inputNaturalGas, 0);
        double countLpg = parseInput(inputLPG, 0);

        double finalElectric = ((countElectricity * 0.708) / 1000) / countPeople;
        double finalNaturalGas = (countNaturalGas * 0.00208) / countPeople;
        double finalLPG = (countLpg * 0.00155) / countPeople;

        footprint1 = finalElectric + finalNaturalGas + finalLPG;
    }

    private double parseInput(EditText inputField, double defaultValue) {
        String inputText = inputField.getText().toString().trim();
        return inputText.isEmpty() ? defaultValue : Double.parseDouble(inputText);
    }
}