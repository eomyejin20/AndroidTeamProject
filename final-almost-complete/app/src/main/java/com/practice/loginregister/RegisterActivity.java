package com.practice.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText name_et, id_et, pw_et, pw_check_et;
    Button register_btn;
    String name, id, pw, pw_check;

    //SQLite 연결 관련 변수들
    SQLiteDatabase db;
    DatabaseHelper db_helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name_et = findViewById(R.id.name_et);
        id_et = findViewById(R.id.id_et);
        pw_et = findViewById(R.id.pw_et);
        pw_check_et = findViewById(R.id.pw_check_et);
        register_btn = findViewById(R.id.register_btn);
        System.out.println("회원가입 액티비티에서 디비헬퍼 객체 생성 전");
        db_helper = new DatabaseHelper(this);       //DatabaseHelper 객체
        System.out.println("회원가입 액티비티에서 디비헬퍼 객체 생성 후/getWritable 전");
        db = db_helper.getWritableDatabase();

        //db_helper.onCreate(db);     //user_info 테이블 생성
        System.out.println("회원가입 함수에서 온크리에이트 다음");

        //회원가입 버튼 눌렀을 때
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean is_register_complete = false;

                name = name_et.getText().toString();
                id = id_et.getText().toString();
                pw = pw_et.getText().toString();
                pw_check = pw_check_et.getText().toString();

                //중복확인 넣을까 말까....아 귀찮;;;;
                if(pw.equals(pw_check)){        //비밀번호와 비밀번호 확인 일치하면
                    is_register_complete = db_helper.Register(db, name, id, pw);    //회원가입
                    if(is_register_complete){
                        Toast.makeText(getApplicationContext(), "회원가입 성공", Toast.LENGTH_SHORT).show();
                    }
                    //로그인 페이지로 이동
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);      //RegisterActivity로 이동
                }
                else{       //비밀번호와 비밀번호 확인 일치하지 않으면
                    Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
