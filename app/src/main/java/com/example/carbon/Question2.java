package com.example.carbon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Question2 extends AppCompatActivity {
    private String CFResult;
    private double CFQuestion2A,CFQuestion2B;
    private EditText PetrolMileage, PetrolKM, DieselMileage, DieselKM, CNGMileage, CNGKM, Flight1, Flight2, Flight3, Flight4, Flight5;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2);
        setupUI();
        CFResult = getIntent().getExtras().getString("Data1");
        btnNext = findViewById(R.id.activity_question2_btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateQuestion2();
                Intent intent = new Intent(Question2.this, Question3.class);
                intent.putExtra("Data2", "" + CFQuestion2B);
                startActivity(intent);
            }
        });
    }

    private void setupUI() {
        PetrolMileage = findViewById(R.id.activity_question2_A6_Petrol_Mileage1);
        PetrolKM = findViewById(R.id.activity_question2_A6_Petrol_km1);
        DieselMileage = findViewById(R.id.activity_question2_A6_Diesel_Mileage1);
        DieselKM = findViewById(R.id.activity_question2_A6_Diesel_km1);
        CNGMileage = findViewById(R.id.activity_question2_A6_CNG_Mileage1);
        CNGKM = findViewById(R.id.activity_question2_A6_CNG_km1);
        Flight1 = findViewById(R.id.activity_question2_A7_Flight1_A);
        Flight2 = findViewById(R.id.activity_question2_A7_Flight2_A);
        Flight3 = findViewById(R.id.activity_question2_A7_Flight3_A);
        Flight4 = findViewById(R.id.activity_question2_A7_Flight4_A);
        Flight5 = findViewById(R.id.activity_question2_A7_Flight5_A);
    }

    private void calculateQuestion2() {
        double PM, PK, DM, DK, CM, CK, F1, F2, F3, F4, F5, ResultPetrol, ResultDiesel, ResultCNG, ResultFlight1, ResultFlight2, ResultFlight3, ResultFlight4, ResultFlight5, ResultCF2;
        String petrolmileage, petrolkm, dieselmileage, dieselkm, cngmileage, cngkm, flight1, flight2, flight3, flight4, flight5;
        petrolmileage = PetrolMileage.getText().toString().trim();
        petrolkm = PetrolKM.getText().toString().trim();
        dieselmileage = DieselMileage.getText().toString().trim();
        dieselkm = DieselKM.getText().toString().trim();
        cngmileage = CNGMileage.getText().toString().trim();
        cngkm = CNGKM.getText().toString().trim();
        flight1 = Flight1.getText().toString().trim();
        flight2 = Flight2.getText().toString().trim();
        flight3 = Flight3.getText().toString().trim();
        flight4 = Flight4.getText().toString().trim();
        flight5 = Flight5.getText().toString().trim();
        if (petrolmileage.isEmpty())
        {PM=0;}
        else
        {PM=Double.parseDouble(PetrolMileage.getText().toString());}
        if (petrolkm.isEmpty())
        {PK=0;}
        else
        {PK = Double.parseDouble(PetrolKM.getText().toString());}
        if (dieselmileage.isEmpty())
        {DM=0;}
        else
        {DM = Double.parseDouble(DieselMileage.getText().toString());}
        if (dieselkm.isEmpty())
        {DK=0;}
        else
        {DK = Double.parseDouble(DieselKM.getText().toString());}
        if (cngmileage.isEmpty())
        {CM=0;}
        else
        {CM = Double.parseDouble(CNGMileage.getText().toString());}
        if (cngkm.isEmpty())
        {CK=0;}
        else
        {CK = Double.parseDouble(CNGKM.getText().toString());}
        if (flight1.isEmpty())
        {F1=0;}
        else
        {F1 = Double.parseDouble(Flight1.getText().toString());}
        if (flight2.isEmpty())
        {F2=0;}
        else
        {F2 = Double.parseDouble(Flight2.getText().toString());}
        if (flight3.isEmpty())
        {F3=0;}
        else
        {F3 = Double.parseDouble(Flight3.getText().toString());}
        if (flight4.isEmpty())
        {F4=0;}
        else
        {F4 = Double.parseDouble(Flight4.getText().toString());}
        if (flight5.isEmpty())
        {F5=0;}
        else
        {F5 = Double.parseDouble(Flight5.getText().toString());}
        if (PM==0 && PK==0)
        {
            ResultPetrol=0;
        }
        else
        {
            ResultPetrol=(PK/PM)*0.0022;
        }
        if (DM==0 && DK==0)
        {
            ResultDiesel=0;
        }
        else
        {
            ResultDiesel=(DK/DM)*0.0026;
        }
        if (CM==0 && CK==0)
        {
            ResultCNG=0;
        }
        else
        {
            ResultCNG=(CK/CM)*0.004;
        }
        ResultFlight1= F1*0.00028;
        ResultFlight2 = F2*0.00028;
        ResultFlight3 = F3*0.00028;
        ResultFlight4 = F4*0.00028;
        ResultFlight5 = F5*0.00028;
        ResultCF2 = ResultPetrol+ResultDiesel+ResultCNG+ResultFlight1+ResultFlight2+ResultFlight3+ResultFlight4+ResultFlight5;
        CFQuestion2A=Double.parseDouble(CFResult);
        CFQuestion2B=CFQuestion2A+ResultCF2;
    }
}