package com.example.seoulsharespaceproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.seoulsharespaceproject.Home.HomeActivity;
import com.example.seoulsharespaceproject.Home.HomeFrament;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText emailText,passwordText;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();    //이메일 비밀번호 로그인 모듈 변수
    FirebaseUser user=mAuth.getCurrentUser();   //현재 로그인 된 유저 정보를 담을 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);

        findViewById(R.id.loginButton).setOnClickListener(this);
        findViewById(R.id.registerButton).setOnClickListener(this);
        findViewById(R.id.autologin).setOnClickListener(this);
    }

    //로그아웃을 하지 않으면 자동으로 메인페이지로 넘어갑니다.
    @Override
    protected void onStart(){
        super.onStart();
            if (user != null) {
                Toast.makeText(LoginActivity.this, "로그아웃을 하지않아 자동로그인으로 로그인합니다.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginButton:
                mAuth.signInWithEmailAndPassword(emailText.getText().toString(), passwordText.getText().toString())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                } else {

                                    Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                break;
            case R.id.registerButton:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
            case R.id.autologin:
                break;

        }
    }

}
