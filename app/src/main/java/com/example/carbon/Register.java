package com.example.carbon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import io.paperdb.Paper;

public class Register extends AppCompatActivity {

    private String totalFootprint;
    private AlertDialog progressDialog;
    private DatabaseReference databaseUser;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        totalFootprint = Objects.requireNonNull(getIntent().getExtras()).getString("TotalFootprint");
        databaseUser = FirebaseDatabase.getInstance().getReference("User");
        firebaseAuth = FirebaseAuth.getInstance();
        Paper.init(this);
        Button registerButton = findViewById(R.id.register_btn);
        Button loginButton = findViewById(R.id.register_btn2);
        loginButton.setOnClickListener(view -> {
            Intent loginIntent = new Intent(Register.this, Login.class);
            loginIntent.putExtra("TotalFootprint", totalFootprint);
            startActivity(loginIntent);
        });
        registerButton.setOnClickListener(view -> {
            String nameValue = parseInput(findViewById(R.id.register_name));
            String phoneValue = parseInput(findViewById(R.id.register_phone));
            String emailValue = parseInput(findViewById(R.id.register_email));
            String passwordValue = parseInput(findViewById(R.id.register_password));
            String confirmValue = parseInput(findViewById(R.id.register_password2));
            boolean isValid = validate(nameValue, phoneValue, emailValue, passwordValue, confirmValue);
            if (isValid) {
                registerMe(emailValue, passwordValue);
                addUser(nameValue, phoneValue, emailValue, totalFootprint);
                Paper.book().write(Prevalent.userPhoneKey, emailValue);
                Paper.book().write(Prevalent.userPasswordKey, passwordValue);
                DatabaseReference personalReference = FirebaseDatabase.getInstance().getReference("User").child(phoneValue);
                String id = personalReference.push().getKey();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String date = sdf.format(new Date());
                UserFootprint footprintUser = new UserFootprint(id, totalFootprint, date);
                personalReference.child(id).setValue(footprintUser);
            }
        });
    }

    private String parseInput(EditText input) {
        return input.getText().toString().trim();
    }

    private void addUser(String nameVal, String emailVal, String phoneVal, String footprintVal) {
        String offsetVal, treeVal;
        offsetVal = Integer.toString(0);
        treeVal = Integer.toString(0);
        if (!TextUtils.isEmpty(nameVal)) {
            User newUser = new User(nameVal, phoneVal, emailVal, footprintVal, footprintVal, offsetVal, treeVal);
            databaseUser.child(phoneVal).setValue(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Register.this, "Record added successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        String errorMessage = Objects.requireNonNull(task.getException()).toString();
                        Toast.makeText(Register.this, "Network error ..." + errorMessage, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }}

    private boolean validate(String nameInput, String phoneInput, String emailInput,
                             String passwordInput, String confirmInput) {
        boolean check = false;
        if (nameInput.isEmpty() || phoneInput.isEmpty() || emailInput.isEmpty() || passwordInput.isEmpty() || confirmInput.isEmpty()) {
            Toast.makeText(Register.this,"Error: A mandatory field is still input. Please input all details so as to register your account.", Toast.LENGTH_LONG).show();
        } else if (passwordInput != confirmInput) {
            Toast.makeText(Register.this, "Error: Your passwords do not match. Please enter the same password in both fields to continue.", Toast.LENGTH_LONG).show();
        } else {
            check = true;
        }
        return check;
    }

    private void registerMe(String emailVal, String passwordVal) {
        progressDialog.setTitle("Signing up ...");
        progressDialog.setMessage("Please wait while we register your account");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(emailVal, passwordVal).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(Register.this,"Registration Successful.",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register.this,Home.class));
                } else {
                    String msg= Objects.requireNonNull(task.getException()).toString();
                    Toast.makeText(Register.this,"Registration Failed."+msg,Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });
    }
}