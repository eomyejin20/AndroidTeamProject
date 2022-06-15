package com.practice.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText id_tv, pw_tv;
    ImageButton login_btn, register_btn;
    String id, pw;
    String name;

    //SQLite 연결 관련 변수들
    SQLiteDatabase db;
    DatabaseHelper db_helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id_tv = findViewById(R.id.id_tv);
        pw_tv = findViewById(R.id.pw_tv);
        login_btn = findViewById(R.id.login_btn);
        register_btn = findViewById(R.id.register_btn);
        System.out.println("로그인 디비헬퍼 객체 생성 전");
        db_helper = new DatabaseHelper(this);       //DatabaseHelper 객체- OnCreate 자동 생성
        System.out.println("로그인 액티비티에서 디비헬퍼 객체 생성 후/getwritble 전");
        db = db_helper.getWritableDatabase();               //OnCreate 생성 안됨
        System.out.println("로그인 액티비티에서 db 객체에 넣고 나서");
        //user_info 테이블 생성(처음에 실행하고 주석처리)
        //db_helper.onCreate(db);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //db_helper.onCreate(db);
                id = id_tv.getText().toString();        //Edittext에서 id 문자열 가져옴
                pw = pw_tv.getText().toString();        //Edittext에서 비밀번호 문자열 가져옴

                //아이디와 비밀번호 문자열 DB로 전송하고 회원인지 아닌지 확인하는 코드
                name = db_helper.Login(db, id, pw);     //로그인 하고 사용자 이름 저장.
                if(name != "Undefined User"){
                    Toast.makeText(getApplicationContext(), name+"님 환영합니다", Toast.LENGTH_SHORT).show();       //이거 떴으니까 클릭은 된거임

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);   //(현재 액티비티, 옮길 액티비티)
                    intent.putExtra("name", name);        //문자열 전송
                    startActivity(intent);      //액티비티 이동, 문자열 인텐트 전송
                }
                else{
                    Toast.makeText(getApplicationContext(), "잘못된 회원정보입니다", Toast.LENGTH_SHORT).show();       //이거 떴으니까 클릭은 된거임
                }



            }
        });

        //회원가입 버튼 눌렀을 때
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);      //RegisterActivity로 이동
            }
        });
    }

}
