package com.foodapp.jpmarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
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
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends AppCompatActivity {
    private EditText editName, editEmail, editPassword, editPassword_Conf;
    private Button btnRegister, btnLogin;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editName = findViewById(R.id.name);
        editEmail = findViewById(R.id.email_reg);
        editPassword = findViewById(R.id.password_reg);
        editPassword_Conf = findViewById(R.id.password_conf);
        btnRegister = findViewById(R.id.reg_btn);
//        btnLogin = findViewById(R.id.sign_in);


        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);

//        btnLogin.setOnClickListener(v ->{
//            finish();
//        });

        btnRegister.setOnClickListener(v -> {
            if(editName.getText().length() > 0 && editEmail.getText().length() > 0 && editPassword.getText().length() > 0 && editPassword_Conf.getText().length() > 0) {
                if (editPassword.getText().toString().equals(editPassword_Conf.getText().toString())) {
                    register(editName.getText().toString(), editEmail.getText().toString(), editPassword.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "Passwords are not the same", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Please fill in all data!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void register(String name, String email, String password) {
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful() && task.getResult() != null) {
                    FirebaseUser firebaseUser = task.getResult().getUser();
                    if (firebaseUser != null) {
                        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder().setDisplayName(name).build();
                        firebaseUser.updateProfile(request).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                signUp();
                            }
                        });
                    } else {
                        Toast.makeText(getApplicationContext(), "Registration process failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void reload() {
    }

    public void signUp() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void signIn(View view) {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accrodingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            signUp();
        }
    }
}