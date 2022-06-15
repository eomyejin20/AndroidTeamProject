package com.practice.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoadingActivity extends AppCompatActivity {

    Button login_btn, register_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        login_btn = findViewById(R.id.login_btn);           //로그인 버튼
        register_btn = findViewById(R.id.register_btn);     //회원가입 버튼

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoadingActivity.this, LoginActivity.class);
                startActivity(intent);      //LoginActivity로 이동
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoadingActivity.this, RegisterActivity.class);
                startActivity(intent);      //RegisterActivity로 이동
            }
        });

    }
}

