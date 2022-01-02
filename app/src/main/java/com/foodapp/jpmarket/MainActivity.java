package com.foodapp.jpmarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseUser firebaseUser;
    private Button btnLogout;

    RecyclerView recyclerView;

    String s1[], s2[];
    int images[]= {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

//    @SuppressLint("SetTextI18n");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogout = findViewById(R.id.btn_logout);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser.getDisplayName()!=null){
            Toast.makeText(getApplicationContext(), firebaseUser.getDisplayName(), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
        }

        btnLogout.setOnClickListener(v ->{
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        });

        recyclerView = findViewById(R.id.recyclerView);

        s1 = getResources().getStringArray(R.array.judul);
        s2 = getResources().getStringArray(R.array.deskripsi);

        PopRecAdapter myAdapter = new PopRecAdapter(this, s1, s2, images);

        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}