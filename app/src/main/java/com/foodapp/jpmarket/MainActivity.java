package com.foodapp.jpmarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import models.Product;
import network.GetDataService;
import network.JPmerketClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private FirebaseUser firebaseUser;
    private Button btnLogout;

    private PopRecAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;

//    RecyclerView recyclerView;
//    BottomNavigationView bottomNavigationView;

    String s1[], s2[];
    int images[]= {R.mipmap.klepon, R.mipmap.kue_putu, R.mipmap.lemperr, R.mipmap.lumpiaa, R.mipmap.apem, R.mipmap.nagasari, R.mipmap.getuk_lindri};

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

//        recyclerView = findViewById(R.id.recyclerView);
//
//        s1 = getResources().getStringArray(R.array.judul);
//        s2 = getResources().getStringArray(R.array.deskripsi);
//
//        PopRecAdapter myAdapter = new PopRecAdapter(this, s1, s2, images);
//
//        recyclerView.setAdapter(myAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = JPmerketClientInstance.getJPmarketInstance().create(GetDataService.class);

        Call<List<Product>> call = service.getProducts();
        call.enqueue(new Callback<List<Product>>() {

            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                progressDoalog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("retrofit1", t.toString());
                progressDoalog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<Product> photoList) {
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new PopRecAdapter(this,photoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}