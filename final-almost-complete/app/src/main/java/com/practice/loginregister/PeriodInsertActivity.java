package com.practice.loginregister;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PeriodInsertActivity extends AppCompatActivity {

    private ListView listview;
    private Button complete_btn;
    private ArrayList<String> ingredients;          //재료 문자열 인텐트로 가져와 담을 배열
    private ArrayList<String> periods;
    private ArrayList<String> ingredient_period;    // 재료/유통기한 형식의 문자열 담을 배열
    private ArrayList<String>[] insert_arr;           //디비에 저장할 2차원 배열
    private String name;
    //SQLite 연결 관련 변수들
    SQLiteDatabase db;
    DatabaseHelper db_helper;       //디비 동작 관련 함수 있는 객체


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_period_insert);

        db_helper = new DatabaseHelper(this);       //DatabaseHelper 객체- OnCreate 자동 생성
        db = db_helper.getWritableDatabase();               //OnCreate 생성 안됨

        listview = findViewById(R.id.listview);     // 재료/유통기한 형식의 문자열을 띄울 리스트뷰
        complete_btn = findViewById(R.id.date_complete_btn);    //완료 버튼

        ingredients = new ArrayList<String>();
        periods = new ArrayList<String>();
        ingredient_period = new ArrayList<String>();

        Intent get_ingredient_intent = getIntent();
        name = get_ingredient_intent.getStringExtra("name");    //이름
        ingredients = get_ingredient_intent.getStringArrayListExtra("ingredients");    //현재 선택한 재료 배열에 담음
        periods = get_ingredient_intent.getStringArrayListExtra("periods");   //현재 선택한 재료의 유통기한 배열에 담음
        //ingredients + "/" + periods  <- 이 문자열을 합쳐서 ingredient_period에 넣을 것임


        for(int i=0; i<ingredients.size(); i++){
            System.out.println("재료명: " + ingredients.get(i));
            System.out.println("유통기한: " + periods.get(i));
            ingredient_period.add(ingredients.get(i) + "/" + periods.get(i));       // 재료/유통기한 <- 이 형식으로 배열에 삽입
            System.out.println(ingredient_period.get(i));
        }

        //리스트뷰에 ingredient_period를 담기 위한 어댑터. 리스트뷰와 배열을 연결해주는 객체임
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ingredient_period);
        listview.setAdapter(adapter);       //리스트뷰에 값을 세팅

        //완료버튼 누르면 디비에 저장하고 나의냉장고로 화면 이동
        complete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0; i<listview.getCount(); i++){        //리스트뷰 개수만큼 반복문 돌면서 데이터 삽입
                    db_helper.InsertToMyRefrigerator(db, "정재민", ingredients.get(i), periods.get(i));
                    System.out.println(ingredients.get(i));
                    System.out.println(periods.get(i));

                }

                Intent intent = new Intent(PeriodInsertActivity.this, MyRefrigeratorActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });

    }
}
