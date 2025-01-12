package com.example.carbon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;

public class Question3 extends AppCompatActivity {
    private String CFResult;
    private double CFQuestion3A,CFQuestion3B;
    private EditText Taxis,LocalTrain,LongTrain,Bus,Subway;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question3);
        setupUI();
        CFResult = getIntent().getExtras().getString("Data2");
        btnNext = findViewById(R.id.activity_question3_btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateQuestion3();
                Intent intent = new Intent(Question3.this, Question4.class);
                intent.putExtra("Data3", "" + CFQuestion3B);
                startActivity(intent);
            }
        });
    }

    private void setupUI()
    {
        Taxis = findViewById(R.id.activity_question3_A8_Taxis_A);
        LocalTrain = findViewById(R.id.activity_question3_A9_LocalTrain_A);
        LongTrain = findViewById(R.id.activity_question3_A10_LongTrain_A);
        Bus = findViewById(R.id.activity_question3_A11_Bus_A);
        Subway = findViewById(R.id.activity_question3_A12_Subways_A);
    }

    private void calculateQuestion3()
    {
        double TAXI,LOCAL,LONG,BUS,SUB,ResultTaxi,ResultLocal,ResultLong,ResultBus,ResultSubway,ResultCF3;
        String taxi,localtrain,longtrain,buses,subway;
        taxi=Taxis.getText().toString().trim();
        localtrain=LocalTrain.getText().toString().trim();
        longtrain=LongTrain.getText().toString().trim();
        buses=Bus.getText().toString().trim();
        subway=Subway.getText().toString().trim();
        if (taxi.isEmpty())
        {TAXI=0;}
        else
        {TAXI = Double.parseDouble(Taxis.getText().toString());}
        if (localtrain.isEmpty())
        {LOCAL=0;}
        else
        {LOCAL = Double.parseDouble(LocalTrain.getText().toString());}
        if (longtrain.isEmpty())
        {LONG=0;}
        else
        {LONG = Double.parseDouble(LongTrain.getText().toString());}
        if (buses.isEmpty())
        {BUS=0;}
        else
        {BUS = Double.parseDouble(Bus.getText().toString());}
        if (subway.isEmpty())
        {SUB=0;}
        else
        {SUB = Double.parseDouble(Subway.getText().toString());}
        ResultTaxi = (TAXI*0.00015);
        ResultLocal = (LOCAL*0.000035);
        ResultLong = (LONG*0.000004);
        ResultBus = (BUS*0.0001);
        ResultSubway = (SUB*0.000028);
        ResultCF3 = ResultTaxi+ResultLocal+ResultLong+ResultBus+ResultSubway;
        CFQuestion3A=Double.parseDouble(CFResult);
        CFQuestion3B=CFQuestion3A+ResultCF3;
    }
}
