package com.foodapp.jpmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;

public class LoginActivity extends AppCompatActivity {
    private static final String LOG_Tag = LoginActivity.class.getSimpleName();

    Button signIn;
    EditText email,password;
    TextView signUp;
    private Object Intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //signIn = findViewById(R.id.login_btn);
        //email = findViewById(R.id.email_login);//
        //password = findViewById(R.id.password_login);//
        //signUp = findViewById(R.id.sign_up);//

    }

    public void daftar(View view) {
        Log.d(LOG_Tag, "daftar onclick");
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}