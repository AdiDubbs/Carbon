package com.example.carbon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import io.paperdb.Paper;

public class Login extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        String totalFootprint = Objects.requireNonNull(getIntent().getExtras()).getString("TotalFootprint");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User");
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        Paper.init(this);
        Button loginButton = findViewById(R.id.login_btn);
        Button registerButton = findViewById(R.id.login_btn2);
        registerButton.setOnClickListener(view -> {
            Intent registerIntent = new Intent(Login.this, Register.class);
            registerIntent.putExtra("TotalFootprint", totalFootprint);
            startActivity(registerIntent);
        });
        loginButton.setOnClickListener(view -> {
            String emailVal = parseInput(findViewById(R.id.login_email));
            String passwordVal = parseInput(findViewById(R.id.login_password));
            validateUser(emailVal, passwordVal);
            Paper.book().write(Prevalent.userPhoneKey, emailVal);
            Paper.book().write(Prevalent.userPasswordKey, passwordVal);
        });
    }

    private String parseInput(EditText input) {
        return input.getText().toString().trim();
    }

    private void validateUser(String emailInput, String passwordInput) {
        progressDialog.setTitle("Signing in ...");
        progressDialog.setMessage("Please wait while we verify your account details");
        if (emailInput.isEmpty() || passwordInput.isEmpty()) {
            Toast.makeText(Login.this,"Error: Either the email id or the password field is still empty. Please fill both fields.",Toast.LENGTH_SHORT).show();
        } else {
            progressDialog.show();
            firebaseAuth.signInWithEmailAndPassword(emailInput, passwordInput).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        Toast.makeText(Login.this,"Login successful. Please proceed.",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this,Home.class));
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(Login.this,"Invalid Email ID or Password. Please try again.",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}