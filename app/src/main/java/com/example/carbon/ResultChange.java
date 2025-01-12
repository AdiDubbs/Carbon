package com.example.carbon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.paperdb.Paper;

public class ResultChange extends AppCompatActivity {
    private String CFChanged,CFEmissions,setoff;
    private TextView FootprintData;
    private Button btnCont;
    String userPhoneKey;
    DatabaseReference userReference,personalReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_change);
        CFChanged = getIntent().getExtras().getString("DataChange4");
        FootprintData=findViewById(R.id.result_change_footprint);
        FootprintData.setText(CFChanged);
        Paper.init(this);
        userPhoneKey=Paper.book().read(Prevalent.userPhoneKey);
        personalReference = FirebaseDatabase.getInstance().getReference("User").child(userPhoneKey);
        userReference = FirebaseDatabase.getInstance().getReference("User");
        btnCont=findViewById(R.id.result_change_btnCont);
        if (userPhoneKey != "") {
            if (!TextUtils.isEmpty(userPhoneKey)) {
                String id = personalReference.push().getKey();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String date = sdf.format(new Date());
                UserFootprint UFo = new UserFootprint(id, CFChanged, date);
                personalReference.child(id).setValue(UFo);
                userReference.child(userPhoneKey).child("userFootprint").setValue(CFChanged);
                userReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.child(userPhoneKey).exists()) {
                            User userData = snapshot.child(userPhoneKey).getValue(User.class);
                            setoff = userData.getUserSetoff();
                            double SETOFF,FOOTPRINT,EMISSIONS;
                            SETOFF=Double.parseDouble(setoff);
                            FOOTPRINT=Double.parseDouble(CFChanged);
                            EMISSIONS=FOOTPRINT-SETOFF;
                            CFEmissions=String.valueOf(EMISSIONS);
                            userReference.child(userPhoneKey).child("userEmissions").setValue(CFEmissions);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }
        btnCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultChange.this, Home.class);
                intent.putExtra("DataChange5", CFChanged);
                startActivity(intent);
            }
        });
    }


}