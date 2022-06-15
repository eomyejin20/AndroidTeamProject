package com.practice.loginregister;
//사용자 버튼 동작
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;


public class UserActivity extends Activity {

    private Button backBtn, loginBtn;
    private EditText user_name;
    private String name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Back();
            }
        });

        loginBtn = findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        Intent getintent = getIntent();
        name = getintent.getStringExtra("name");    //현재 로그인한 사용자 이름 저장
        user_name = findViewById(R.id.user_name);
        user_name.setText(name);
        user_name.setEnabled(false);

        //로그인 하면 로그인버튼 숨기는 동작 처리..
//        System.out.println("유저 이름: " + user_name.getText().toString());
//        if(user_name.getText().toString() != "null"){
//            loginBtn.setVisibility(View.INVISIBLE);
//        }

    }

    public void Back (){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }







}
