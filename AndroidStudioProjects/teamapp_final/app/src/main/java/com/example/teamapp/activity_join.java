package com.example.teamapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.String;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        mDatabase.child("users").child(userName).child("userName").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);

                if(value!=null){
                    Toast.makeText(getApplicationContext(),"이미 존재하는 아이디 입니다.",Toast.LENGTH_SHORT).show();//토스메세지 출력
                }
                else{
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
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // 디비를 가져오던중 에러 발생 시
                //Log.e("MainActivity", String.valueOf(databaseError.toException())); // 에러문 출력
            }
        });

    }
}
