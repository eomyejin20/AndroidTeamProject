package com.practice.loginregister;
//나의 냉장고 동작
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class MyRefrigeratorActivity extends Activity {

    private EditText ref_name;  //현재 로그인한 사용자 이름 담을 Edittext
    private Button refAdd;
    private Button refDel;
    private ListView refList;
    private String name;        //현재 로그인한 사용자 이름 담을 변수
    private ImageButton iv_ice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrefrigerator);

        //SQLite 연결 관련 변수들
        SQLiteDatabase db;
        DatabaseHelper db_helper;       //디비 동작 관련 함수 있는 객체
        db_helper = new DatabaseHelper(this);
        db = db_helper.getWritableDatabase();               //OnCreate 생성 안됨

        Intent getintent = getIntent();
        name = getintent.getStringExtra("name");        //현재 로그인한 사용자 이름 저장

        ref_name = findViewById(R.id.ref_name);
        ref_name.setText(name + "의 냉장고");

        //재료추가 버튼 이동
        refAdd = findViewById(R.id.ref_add);
        refAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Add();
            }
        });

        //DB에 있는 추가한 재료 string으로 가져와서 listview에 배열로 넣기
        String[][] my_refrigerator_arr = new String[50][2];        //재료와 유통기한 넣을 2차원 배열
        my_refrigerator_arr =  db_helper.MyRefrigerator(db, "정재민");
        List<String> data = new ArrayList<>();

        for(int i=0; i<my_refrigerator_arr.length; i++){            //행의 수만큼 반복
            if(my_refrigerator_arr[i][0]!=null){        //null이 아니면 리스트뷰 배열에 추가
                data.add(my_refrigerator_arr[i][0] + "/" + my_refrigerator_arr[i][1]);      // 재료/소비기한 형식으로 데이터 추가
                System.out.println("myref: " + data.get(i));;

            }
       }

        System.out.println(data);

        refList = (ListView) findViewById(R.id.ref_listview);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);   //리스트뷰와 연결할 어댑터
        refList.setAdapter(adapter); //adapter를 list에 set. >> ListView와 Adapter 연결

        Button refDel = (Button) findViewById(R.id.ref_del);

        refDel.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count, checked ;
                count = adapter.getCount() ;

                if (count > 0) {
                    // 현재 선택된 아이템의 position 획득.
                    checked = refList.getCheckedItemPosition();

                    if (checked > -1 && checked < count) {
                        // 아이템 삭제
                        String remove_ingredient = data.get(checked).split("/")[0];   //지울 재료 이름
                        System.out.println(remove_ingredient);      //임의출력
                        db_helper.RemoveIngredient(db, name, remove_ingredient);    //디비 사용자별 냉장고 테이블에서 삭제
                        data.remove(checked) ;

                        // listview 선택 초기화.
                        refList.clearChoices();

                        // listview 갱신.
                        adapter.notifyDataSetChanged();
                    }
                }

            }
        });
    }

    //재료추가 버튼 이동
    public void Add() {
        Intent intent = new Intent(this, IngredientSelectActivity.class);
        intent.putExtra("name", name);      //이름 인텐트로 넘김
        startActivity(intent);
    }



}




