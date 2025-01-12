package com.example.carbon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class FootprintHistory extends AppCompatActivity {
    DatabaseReference personalDatabase;
    private ListView userList;
    private List<UserFootprint> historyList;
    String userPhoneKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_footprint_history);
        Paper.init(this);
        userPhoneKey = Paper.book().read(Prevalent.userPhoneKey);
        userList = findViewById(R.id.history_list);
        personalDatabase = FirebaseDatabase.getInstance().getReference("User").child(userPhoneKey);
        historyList = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        personalDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                historyList.clear();
                for (DataSnapshot userSnapshot : snapshot.getChildren())
                {
                    UserFootprint user = userSnapshot.getValue(UserFootprint.class);
                    historyList.add(user);
                }
                History adapter = new History(FootprintHistory.this,historyList);
                userList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}