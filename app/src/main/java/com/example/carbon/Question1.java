package com.example.carbon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Question1 extends AppCompatActivity{
    private double CFQuestion1;
    private EditText People,Electricity,NaturalGas,LPGas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);
        setupUI();
        Button btnNext = findViewById(R.id.activity_question1_btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateQuestion1();
                Intent intent=new Intent(Question1.this,Question2.class);
                intent.putExtra("Data1",""+CFQuestion1);
                startActivity(intent);
            }
        });
    }

    private void setupUI()
    {
        People=findViewById(R.id.activity_question1_A1_People);
        Electricity=findViewById(R.id.activity_question1_A2_Electricity_A);
        NaturalGas=findViewById(R.id.activity_question1_A3_NaturalGas_A);
        LPGas=findViewById(R.id.activity_question1_A4_LPG_A);
    }

    private void calculateQuestion1() {
        double NOP, EC, NG, LPG, ResultEC, ResultNG, ResultLPG;
        String people, electricity, nautralgas, lpgas;
        people = People.getText().toString().trim();
        electricity = Electricity.getText().toString().trim();
        nautralgas = NaturalGas.getText().toString().trim();
        lpgas = LPGas.getText().toString().trim();
        if (people.isEmpty()) {
            NOP = 1;
        } else {
            NOP = Double.parseDouble(People.getText().toString());
        }
        if (electricity.isEmpty()) {
            EC = 1000;
        } else {
            EC = Double.parseDouble(Electricity.getText().toString());
        }
        if (nautralgas.isEmpty()) {
            NG = 0;
        } else {
            NG = Double.parseDouble(NaturalGas.getText().toString());
        }
        if (lpgas.isEmpty()) {
            LPG = 0;
        } else {
            LPG = Double.parseDouble(LPGas.getText().toString());
        }
        ResultEC = ((EC * 0.708) / 1000) / NOP;
        ResultNG = (NG * 0.00208) / NOP;
        ResultLPG = (LPG * 0.00155) / NOP;
        CFQuestion1 = ResultEC + ResultNG + ResultLPG;
    }
}

