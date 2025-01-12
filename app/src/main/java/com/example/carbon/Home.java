package com.example.carbon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import io.paperdb.Paper;

public class Home extends AppCompatActivity {
    private TextView viewFootPrint, viewOffset, viewTrees, viewEmissions;
    private DatabaseReference userReference;

    private FirebaseAuth firebaseAuth;
    String userFootPrint, userPhoneKey, userEmissions, userOffset, userTrees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupUI();
        firebaseAuth = FirebaseAuth.getInstance();
        Paper.init(this);
        userPhoneKey = Paper.book().read(Prevalent.userPhoneKey);
        userReference = FirebaseDatabase.getInstance().getReference("User");
        if (!Objects.equals(userPhoneKey, "")) {
            if (!TextUtils.isEmpty(userPhoneKey)) {
                showData(userPhoneKey);
            }
        }
    }

    private void setupUI() {
        viewFootPrint = findViewById(R.id.home_footprint2);
        viewOffset = findViewById(R.id.home_setoff2);
        viewTrees = findViewById(R.id.home_planted2);
        viewEmissions = findViewById(R.id.home_net2);
    }

    private void showData(String userKey) {
        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(userKey).exists()) {
                    User userData = snapshot.child(userKey).getValue(User.class);
                    boolean checkValidity = userData != null;
                    userFootPrint = checkValidity ? userData.getUserFootprint() : "0";
                    userEmissions = checkValidity ? userData.getUserEmissions() : "0";
                    userOffset = checkValidity ? userData.getUserSetoff() : "0";
                    userTrees = checkValidity ? userData.getUserTrees() : "0";
                    viewFootPrint.setText(userFootPrint);
                    viewEmissions.setText(userEmissions);
                    viewOffset.setText(userOffset);
                    viewTrees.setText(userTrees);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnu_stats) {
            startActivity(new Intent(Home.this, Statistics.class));
            finish();
        }
        else if (item.getItemId() == R.id.mnu_offset) {
            startActivity(new Intent(Home.this, Offset.class));
            finish();
        }
        else if (item.getItemId() == R.id.mnu_change) {
            startActivity(new Intent(Home.this, Change1.class));
            finish();
        }
        else if (item.getItemId() == R.id.mnu_about_app) {
            startActivity(new Intent(Home.this, About.class));
            finish();
        }
        else if (item.getItemId() == R.id.mnu_logout) {
            firebaseAuth.signOut();
            startActivity(new Intent(Home.this, MainActivity.class));
            finish();
        }
        else {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }

}
