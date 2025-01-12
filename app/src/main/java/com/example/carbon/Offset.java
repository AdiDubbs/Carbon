package com.example.carbon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import io.paperdb.Paper;

public class Offset extends AppCompatActivity {
    private TextView tvAlready,tvFootprint;
    private EditText etTrees;
    private DatabaseReference userReference;
    String trees,emissions,emissions2,treetotal,setoff;
    private Button btnEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offset);
        setupUI();
        userReference = FirebaseDatabase.getInstance().getReference("User");
        Paper.init(this);
        String userEmail = Paper.book().read(Prevalent.userPhoneKey);
        if (!Objects.equals(userEmail, ""))
        {
            if (!TextUtils.isEmpty(userEmail))
            {
                findData(userEmail);
            }
        }
        btnEnter.setOnClickListener(view -> {
            calculateFootprint();
            updateDatabase(userEmail);
        });
        Button offsetButton = findViewById(R.id.offset_return_home_btn);
        offsetButton.setOnClickListener(view -> {
            startActivity(new Intent(Offset.this, Home.class));
        });

    }

    private void updateDatabase(String userPhoneKey)
    {
        userReference.child(userPhoneKey).child("userEmissions").setValue(emissions2);
        userReference.child(userPhoneKey).child("userSetoff").setValue(setoff);
        userReference.child(userPhoneKey).child("userTrees").setValue(treetotal);
    }


    private void calculateFootprint()
    {
        Double CARBONTREES,CARBONTREES2,EMISSIONS1,EMISSIONS2,TREES1,TREES2,TOTALTREES;
        EMISSIONS1=Double.parseDouble(emissions);
        TREES1=Double.parseDouble(etTrees.getText().toString());
        TREES2=Double.parseDouble(trees);
        TOTALTREES=TREES1+TREES2;
        treetotal=TOTALTREES.toString();
        CARBONTREES=(TOTALTREES*0.0218);
        CARBONTREES2=(TREES1*0.0218);
        setoff=CARBONTREES.toString();
        EMISSIONS2=EMISSIONS1-CARBONTREES2;
        emissions2=EMISSIONS2.toString();
        tvFootprint.setText(emissions2);
    }

    private void findData(String userKey)
    {
        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(userKey).exists()) {
                    User userData = snapshot.child(userKey).getValue(User.class);
                    trees = userData.getUserTrees();
                    setoff = userData.getUserSetoff();
                    emissions = userData.getUserEmissions();
                    tvAlready.setText(trees);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setupUI()
    {
        tvAlready=findViewById(R.id.offset_trees_count);
        tvFootprint=findViewById(R.id.offset_carbon_footprint_value);
        etTrees=findViewById(R.id.offset_additional_trees_input);
        btnEnter=findViewById(R.id.offset_enter_btn);
    }
}