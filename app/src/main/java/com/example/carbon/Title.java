package com.example.carbon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;

public class Title extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth=FirebaseAuth.getInstance();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_title);
        getSupportActionBar().hide();
        LogoLauncher ll=new LogoLauncher();
        ll.start();
    }
    private class LogoLauncher extends Thread {
        public void run() {
            try {
                sleep(5000);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
            if (firebaseAuth.getCurrentUser()!=null) {
                startActivity(new Intent(Title.this,Home.class));
            } else {
                startActivity(new Intent(Title.this,MainActivity.class));
            }
            Title.this.finish();
        }
    }
}