package com.foodapp.jpmarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText editEmail, editPassword;
    private Button btnLogin, btnRegister;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmail = findViewById(R.id.email_login);
        editPassword = findViewById(R.id.password_login);
        btnLogin = findViewById(R.id.login_btn);
//        btnRegister = findViewById(R.id.btn_reg);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please Wait!");
        progressDialog.setCancelable(false);

        btnLogin.setOnClickListener(v ->{
            if(editEmail.getText().length()>0 && editPassword.getText().length()>0){
                login(editEmail.getText().toString(), editPassword.getText().toString());
            }else {
                Toast.makeText(getApplicationContext(), "Please fill in all data!", Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void login(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful() && task.getResult()!=null){
                    if (task.getResult().getUser()!=null){
                        signIn();
                    }else{
                        Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void signUp(View view) {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }

    public void signIn() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            signIn();
        }
    }
}