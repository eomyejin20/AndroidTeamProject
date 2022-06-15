package com.practice.loginregister;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CombinefinalActivity extends AppCompatActivity {

    private ListView FinalList; //리스트뷰 객체
    private ArrayList<String> combineData; //Intent로 부터 받은 재료 데이터
    public ArrayList<String> menulist; //메뉴를 추천받으면 넣을 리스트
    private ArrayList<String> menulink;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitiy_combine_final);
        //선택된 재료 데이터 가져오기
        combineData = new ArrayList<>();
        Intent intent = getIntent();
        combineData=intent.getExtras().getStringArrayList("data"); //Intent로 넘겨받은 데이터 재료 데이터에 넣기
        /*String joinIngredient=String.join("/",combineData); //배열 안에 있는 재료들을 감자/김치 형태로 join하기*/


        //DB에 있는 추천된 레시피 가져와서 listview에 배열로 넣기
        //리스트 속 item 연결
        // 리스트를 생성한다.
        menulist = new ArrayList<String>();
        FindMenu(combineData); //재료 값을 입력 받아 메뉴를 출력해주는 함수
        FinalList = (ListView) findViewById(R.id.final_listview);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, menulist);
        System.out.println(menulist);
        FinalList.setAdapter(adapter); //adapter를 list에 set. >> ListView와 Adapter 연결
        adapter.notifyDataSetChanged();
        combineData.clear();
       /* System.out.println(combineData);*/

        FinalList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //기능 구현
                //한줄 클릭하면 작동하는데, 웹뷰로 url넘겨서 할거임.
                //형은 url에 저장만 하면 됨.
                //변수 link에 저장된 url을  string으로 입력받아서 웹뷰로 이동
                String menu_name =menulist.get(position); //클릭한 리스트뷰에 해당하는 메뉴를 string으로 대입
                FindLink(menu_name); //링크를 반환하는 함수 출력
                String link_url = menulink.get(0); //반환받은 링크를 String으로 대입
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link_url)); //해당 url로 이동
                startActivity(intent);
            }
        });
    }
    public void FindMenu(ArrayList menu) //재료를 입력받으면 메뉴를 추천해주는 함수
    {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        menulist = databaseAccess.GetMenu(menu);
    }
    public void FindLink(String menu) //메뉴를 입력받으면 그에 해당하는 재료법 link를 반환해주는 함수
    {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        menulink = databaseAccess.GetLink(menu);
    }
}

