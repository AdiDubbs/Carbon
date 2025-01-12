package com.example.carbon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Locale;

public class Question4 extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private Spinner sDiet;
    private double CFQuestion4A,CFQuestion4B;
    private String CFResult;
    private Button btnNext;
    ProgressDialog progressDialog;
    private EditText Food,Pharma,Clothes,Books,ITE,Devices,Vehicles,Furniture,Hotels,Mobile,Education,Recreation;
    String[] Diet = {"Vegetarian","Vegan","Light meat-eater","Medium meat-eater","Heavy meat-eater"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question4);
        progressDialog=new ProgressDialog(this);
        sDiet=findViewById(R.id.activity_question4_A13_Food_A);
        sDiet.setOnItemSelectedListener(this);
        ArrayAdapter aAdapter=new ArrayAdapter(this, R.layout.activity_question4,Diet);
        aAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sDiet.setAdapter(aAdapter);
        setupUI();
        CFResult = getIntent().getExtras().getString("Data3");
        btnNext = findViewById(R.id.activity_question4_btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateQuestion4();
                Intent intent = new Intent(Question4.this, Result.class);
                intent.putExtra("Data4", "" + CFQuestion4B);
                startActivity(intent);
            }
        });
    }

    private void setupUI()
    {
        Food=findViewById(R.id.activity_question4_A13_Food_B_2);
        Pharma=findViewById(R.id.activity_question4_A14_Pharma_B);
        Clothes=findViewById(R.id.activity_question4_A15_Clothes_B);
        Books=findViewById(R.id.activity_question4_A16_Books_B);
        ITE=findViewById(R.id.activity_question4_A17_IT_B);
        Devices=findViewById(R.id.activity_question4_A18_Devices_B);
        Vehicles=findViewById(R.id.activity_question4_A19_Vehicles_B);
        Furniture=findViewById(R.id.activity_question4_A20_Furniture_B);
        Hotels=findViewById(R.id.activity_question4_A21_Hotels_B);
        Mobile=findViewById(R.id.activity_question4_A22_Mobile_B);
        Education=findViewById(R.id.activity_question4_A23_Education_B);
        Recreation=findViewById(R.id.activity_question4_A24_Recreation_B);
    }

    private void calculateQuestion4()
    {
        String FoodDiet=sDiet.getSelectedItem().toString();
        double FD,PH,CL,BO,IT,DE,VE,FU,HO,MO,ED,RE,ResultFood,ResultPharma,ResultClothes,ResultBooks,ResultIT,ResultDevices,ResultVehicles,ResultFurniture,ResultHotels,ResultMobile,ResultEducation,ResultRecreation,ResultCF4;
        progressDialog.setTitle("Calculating ...");
        progressDialog.setMessage("Please wait while we calculate your carbon footprint");
        progressDialog.show();
        String food,pharma,clothes,books,ite,devices,vehicles,furniture,hotels,mobile,education,recreation;
        food=Food.getText().toString().trim();
        pharma=Pharma.getText().toString().trim();
        clothes=Clothes.getText().toString().trim();
        books=Books.getText().toString().trim();
        ite=ITE.getText().toString().trim();
        devices=Devices.getText().toString().trim();
        vehicles=Vehicles.getText().toString().trim();
        furniture=Furniture.getText().toString().trim();
        hotels=Hotels.getText().toString().trim();
        mobile=Mobile.getText().toString().trim();
        education=Education.getText().toString().trim();
        recreation=Recreation.getText().toString().trim();
        if (food.isEmpty())
        {FD=0;}
        else
        {FD=Double.parseDouble(Food.getText().toString());}
        if (pharma.isEmpty())
        {PH=0;}
        else
        {PH=Double.parseDouble(Pharma.getText().toString());}
        if (clothes.isEmpty())
        {CL=0;}
        else
        {CL=Double.parseDouble(Clothes.getText().toString());}
        if (books.isEmpty())
        {BO=0;}
        else
        {BO=Double.parseDouble(Books.getText().toString());}
        if (ite.isEmpty())
        {IT=0;}
        else
        {IT=Double.parseDouble(ITE.getText().toString());}
        if (devices.isEmpty())
        {DE=0;}
        else
        {DE=Double.parseDouble(Devices.getText().toString());}
        if (vehicles.isEmpty())
        {VE=0;}
        else
        {VE=Double.parseDouble(Vehicles.getText().toString());}
        if (furniture.isEmpty())
        {FU=0;}
        else
        {FU=Double.parseDouble(Furniture.getText().toString());}
        if (hotels.isEmpty())
        {HO=0;}
        else
        {HO=Double.parseDouble(Hotels.getText().toString());}
        if (mobile.isEmpty())
        {MO=0;}
        else
        {MO=Double.parseDouble(Mobile.getText().toString());}
        if (education.isEmpty())
        {ED=0;}
        else
        {ED=Double.parseDouble(Education.getText().toString());}
        if (recreation.isEmpty())
        {RE=0;}
        else
        {RE=Double.parseDouble(Recreation.getText().toString());}
        ResultFood=0.0;
        if (FoodDiet.equals("Vegetarian"))
        {
            ResultFood=FD*0.000054;
        }
        else if (FoodDiet.equals("Vegan"))
        {
            ResultFood=FD*0.000041;
        }
        else if (FoodDiet.equals("Light meat-eater"))
        {
            ResultFood=FD*0.000066;
        }
        else if (FoodDiet.equals("Medium meat-eater"))
        {
            ResultFood=FD*0.00008;
        }
        else if (FoodDiet.equals("Heavy meat-eater"))
        {
            ResultFood=FD*0.000102;
        }
        ResultPharma=PH*0.0003;
        ResultClothes=CL*0.000047;
        ResultBooks=BO*0.000032;
        ResultIT=IT*0.000132;
        ResultDevices=DE*0.000132;
        ResultVehicles=VE*0.000034;
        ResultFurniture=FU*0.000036;
        ResultHotels=HO*0.000043;
        ResultMobile=MO*0.000028;
        ResultEducation=ED*0.000029;
        ResultRecreation=RE*0.000037;
        ResultCF4=ResultFood+ResultPharma+ResultClothes+ResultBooks+ResultIT+ResultDevices+ResultVehicles+ResultFurniture+ResultHotels+ResultMobile+ResultEducation+ResultRecreation;
        CFQuestion4A=Double.parseDouble(CFResult);
        CFQuestion4B=CFQuestion4A+ResultCF4;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(Question4.this,"You have selected "+Diet[i],Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}