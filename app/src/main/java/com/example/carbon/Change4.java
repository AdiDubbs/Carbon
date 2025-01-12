package com.example.carbon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Objects;

public class Change4 extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private double totalFootprint;
    String dietUser;

    String[] dietTypes = {
            "Vegetarian",
            "Vegan",
            "Light Non-Vegetarian",
            "Moderate Non-Vegetarian",
            "Heavy Non-Vegetarian"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change4);
        handleSpinner();
        totalFootprint = Double.parseDouble(Objects.requireNonNull(getIntent().getExtras()).getString("Footprint3"));
        Button nextButton = findViewById(R.id.change4_btn);
        nextButton.setOnClickListener(view -> {
            calculateFootprint();
            Intent intent = new Intent(Change4.this, Result.class);
            intent.putExtra("Footprint4", "" + totalFootprint);
            startActivity(intent);
        });
    }

    private void handleSpinner() {
        Spinner dietType = findViewById(R.id.change4_diet_ans);
        dietType.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dietTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dietType.setAdapter(adapter);
        dietUser = dietType.getSelectedItem().toString();
    }

    private void calculateFootprint() {
        double foodValue = parseInput(findViewById(R.id.change4_food_ans));
        double medicineValue = parseInput(findViewById(R.id.change4_medicine_ans));
        double clothesValue = parseInput(findViewById(R.id.change4_clothes_ans));
        double booksValue = parseInput(findViewById(R.id.change4_books_ans));
        double techValue = parseInput(findViewById(R.id.change4_tech_ans));
        double gadgetValue = parseInput(findViewById(R.id.change4_gadget_ans));
        double vehicleValue = parseInput(findViewById(R.id.change4_vehicle_ans));
        double furnitureValue = parseInput(findViewById(R.id.change4_furniture_ans));
        double leisureValue = parseInput(findViewById(R.id.change4_leisure_ans));
        double billsValue = parseInput(findViewById(R.id.change4_bills_ans));
        double educationValue = parseInput(findViewById(R.id.change4_education_ans));
        double entertainValue = parseInput(findViewById(R.id.change4_entertain_ans));

        double foodFactor = switch (dietUser) {
            case "Vegan" -> 0.000041;
            case "Light Non-Vegetarian" -> 0.000066;
            case "Moderate Non-Vegetarian" -> 0.00008;
            case "Heavy Non-Vegetarian" -> 0.000102;
            default -> 0.000054;
        };

        double foodImpact = foodValue * foodFactor;
        double medicineImpact = medicineValue * 0.0003;
        double clothesImpact = clothesValue * 0.000047;
        double booksImpact = booksValue * 0.000032;
        double techImpact = techValue * 0.000132;
        double gadgetImpact = gadgetValue * 0.000132;
        double vehicleImpact = vehicleValue * 0.000034;
        double furnitureImpact = furnitureValue * 0.000036;
        double leisureImpact = leisureValue * 0.000043;
        double billsImpact = billsValue * 0.000028;
        double educationImpact = educationValue * 0.000029;
        double entertainImpact = entertainValue * 0.000037;

        double footprint4 = foodImpact + medicineImpact + clothesImpact + booksImpact + techImpact
                + gadgetImpact + vehicleImpact + furnitureImpact + leisureImpact + billsImpact
                + educationImpact + entertainImpact;

        totalFootprint = totalFootprint + footprint4;
    }

    private double parseInput(EditText input) {
        String text = input.getText().toString().trim();
        return text.isEmpty() ? 0 : Double.parseDouble(text);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        if (position >= 0 && position < dietTypes.length) {
            String selectedDiet = dietTypes[position];
            Toast.makeText(Change4.this, "You selected: " + selectedDiet, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Change4.this, "Invalid selection", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(Change4.this, "No selection made", Toast.LENGTH_SHORT).show();
    }
}