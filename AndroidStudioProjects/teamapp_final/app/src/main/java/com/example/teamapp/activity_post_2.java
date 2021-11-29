package com.example.teamapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class activity_post_2 extends AppCompatActivity {
    EditText ftitle, fcontent;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_2);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        ftitle = findViewById(R.id.title);
        fcontent = findViewById(R.id.content);
        Button postbtn = findViewById(R.id.postbtn);
        postbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getTitle = ftitle.getText().toString();
                String getContent = fcontent.getText().toString();

                HashMap result = new HashMap<>();
                result.put("title", getTitle);
                result.put("content", getContent);

                writeNewBoard(getTitle,getContent);

            }
        });
    }
    private void writeNewBoard(String title, String content) {
        Board_2 board = new Board_2(title, content);
        mDatabase.child("boards_2").push().setValue(board)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Write was successful!
                        Toast.makeText(activity_post_2.this, "게시물이 작성 되었습니다", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Write failed
                        Toast.makeText(activity_post_2.this,  "save faulure", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
