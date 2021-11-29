package com.example.teamapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class activity_join extends AppCompatActivity {
    Button button7;
    EditText fName, fId, fPw;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        Button button6 = findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return;
            }
        });
        mDatabase = FirebaseDatabase.getInstance().getReference();
        button7 = findViewById(R.id.button7);
        fName = findViewById(R.id.signup_name);
        fId = findViewById(R.id.signup_id);
        fPw = findViewById(R.id.signup_pw);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getName = fName.getText().toString().trim();
                String getId = fId.getText().toString().trim();
                String getPw = fPw.getText().toString().trim();

                HashMap result = new HashMap<>();
                result.put("name", getName);
                result.put("id", getId);
                result.put("pw", getPw);

                if(fName.length() == 0 || fId.length() == 0 || fPw.length() == 0) {
                    Toast toast = Toast.makeText(activity_join.this, "빈칸을 전부 기입해주세요.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                writeNewUser(getId,getName,getPw);

            }
        });
    }
    private void writeNewUser(String userName, String userPw, String pw) {
        User user = new User(userName, userPw,pw);
        mDatabase.child("users").child(userName).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Write was successful!
                        Toast.makeText(activity_join.this, "save success", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Write failed
                        Toast.makeText(activity_join.this,  "save faulure", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}