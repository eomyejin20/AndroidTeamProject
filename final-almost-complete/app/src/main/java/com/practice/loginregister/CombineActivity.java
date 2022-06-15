package com.practice.loginregister;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class CombineActivity extends AppCompatActivity {

    private EditText ref_name;  //현재 로그인한 사용자 이름 담을 Edittext
    private Button comBtn;
    boolean[] selectIngredient;
    private ListView list2; // DB 에 있는 나의 냉장고 데이터
    private String name;    //현재 로그인한 사용자 이름 저장할 변수
    public ArrayList<String> mDataList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combine);

        //SQLite 연결 관련 변수들
        SQLiteDatabase db;
        DatabaseHelper db_helper;       //디비 동작 관련 함수 있는 객체
        db_helper = new DatabaseHelper(this);
        db = db_helper.getWritableDatabase();               //OnCreate 생성 안됨

        Intent getintent = getIntent();
        name = getintent.getStringExtra("name");        //현재 로그인한 사용자 이름 저장

        ref_name = findViewById(R.id.ref_name);
        ref_name.setText(name + "의 냉장고");

        String[][] my_refrigerator_arr = new String[50][2];             //재료와 유통기한 넣을 2차원 배열
        my_refrigerator_arr =  db_helper.MyRefrigerator(db, name);

        //DB에 있는 나의 냉장고 데이터를 string으로 가져와서 listview에 배열로 넣기
        list2 = (ListView) findViewById(R.id.combine_listview);
        List<String> data = new ArrayList<>();          //나의 냉장고에서 띠울 배열

        for(int i=0; i<my_refrigerator_arr.length; i++){            //행의 수만큼 반복
            if(my_refrigerator_arr[i][0]!=null){        //null이 아니면 리스트뷰 배열에 추가
                data.add(my_refrigerator_arr[i][0] + "/" + my_refrigerator_arr[i][1]);      // 재료/소비기한 형식으로 데이터 추가가
                System.out.println(data.get(i));
            }

        }

        // 리스트를 여러개 선택할 수 있는 MUTIPLE_CHOICE 사용
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, data);
        list2.setAdapter(adapter); //adapter를 list에 set. >> ListView와 Adapter 연결

        mDataList = new ArrayList<String>(); // 선택되어져있는것만 조합하기 위해 선택되어져있는 리스트들의 TEXT 를 저장하는 배열


        Button combine = (Button) findViewById(R.id.combine);

        combine.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count, checked;

                //체크박스로 체크한 셀의 정보를 담고 있는 배열 얻어오기
                SparseBooleanArray checkedItems = list2.getCheckedItemPositions();
                count = adapter.getCount(); // data 몇개있는지 카운트
                if (checkedItems.size() != 0) // 만약 하나라도 체크 되어있다면
                {
                    for (int i = 0; i < count; i++) {
                        if (count > 0) {
                            // 현재 선택된 아이템의 position 획득.
                            if (checkedItems.get(i)) // 만약 i번째 리스트가 체크 되어져있다면
                            {
                                String ingredient;
                                ingredient = data.get(i);
                                //여기에 선택된 데이터를 mDataList에 집어넣고 조합
                                // 재민이형 이거 부탁해 선택된 데이터 string 으로 받아서 mdatalist 에 add 하는거 구현을 못했어
                                mDataList.add(ingredient.split("/")[0]); // /기준 슬라이싱 앞쪽 문자열
                            }
                        }
                    }
                }

                // listview 선택 초기화.
                list2.clearChoices();

                // listview 갱신.
                adapter.notifyDataSetChanged();

                Intent intent = new Intent(getApplicationContext(), CombinefinalActivity.class); // 조합된 레시피를 보여주는 xml로 넘어간다.
                intent.putExtra("data", mDataList); // 배열에 저장되어있는 string 들을 FINAL COMBINE에 넘겨준다.
                startActivity(intent);
                mDataList.clear(); //intent 넘어갔다가 다시 돌아올때 mDataList 초기화 해줌
            }

        });
    }
}



