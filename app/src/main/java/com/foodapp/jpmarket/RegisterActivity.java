package com.foodapp.jpmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;

public class RegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }

    public void signUp(View view) {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    public void signIn(View view) {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}