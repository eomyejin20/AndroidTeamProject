package com.practice.loginregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private ViewPager2Adapter ViewPager2Adapter;
    private String name;        //현재 로그인한 사용자 이름
    //광고 배너 자동 무한 슬라이드
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 2000;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 2000; // time in milliseconds between successive task executions.

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent getintent = getIntent();
        name = getintent.getStringExtra("name");    //현재 로그인한 사용자 이름 저장

        //Fragment 1~10로 각각 10개의 배너 화면에 표시
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(Fragment1.newInstance(0));
        fragments.add(Fragment2.newInstance(1));
        fragments.add(Fragment3.newInstance(2));
        fragments.add(Fragment4.newInstance(3));
        fragments.add(Fragment5.newInstance(4));
        fragments.add(Fragment6.newInstance(5));
        fragments.add(Fragment7.newInstance(6));
        fragments.add(Fragment8.newInstance(7));
        fragments.add(Fragment9.newInstance(8));
        fragments.add(Fragment10.newInstance(9));

        viewPager2 = (ViewPager2) findViewById(R.id.viewPager_banner);

        //viewpager2 연결해주는 adapter
        ViewPager2Adapter viewPager2Adapter = new ViewPager2Adapter(this, fragments);
        viewPager2.setAdapter(viewPager2Adapter);


        //배너 자동 슬라이드 기능 구현
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            @Override
            public void run() {
                if (currentPage == 9) { //배너가 끝까지 이동하면 다시 처음으로 이동
                    currentPage = 0;
                }
                viewPager2.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

        //종 모양 버튼 클릭시 액티비티 전환
        //Layout Resource File로 bell_btn_activity.xml과 연결할 Bell_Btn.java클래스 생성 필요
        ImageButton bell_btn = (ImageButton) findViewById(R.id.bell_btn);
        bell_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //버튼 클릭시 도움말(종 모양 버튼) 화면으로 전환하는 동작
                //Intent intent = new Intent(getApplicationContext(), Bell_Btn.class);
                //(intent);
            }
        });

        //유저 버튼 클릭시 액티비티 전환
        //Layout Resource File로 user_btn_activity.xml과 연결할 User_Btn.java클래스 생성 필요
        ImageButton user_btn = (ImageButton) findViewById(R.id.user_btn);
        user_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///버튼 클릭시 사용자 프로필 화면으로 전환하는 동작
                Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                intent.putExtra("name", name);      //이름 인텐트로 넘김
                startActivity(intent);
            }
        });

        //나의 냉장고 버튼 클릭시 액티비티 전환
        //Layout Resource File로 refrigerator_activity.xml과 연결할 Refrigerator.java클래스 생성 필요
        Button refrigerator = (Button) findViewById(R.id.my_btn);
        refrigerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //버튼 클릭시 나의 냉장고 화면으로 전환하는 동작
                Intent intent = new Intent(getApplicationContext(), MyRefrigeratorActivity.class);
                intent.putExtra("name", name);      //이름 인텐트로 넘김
                startActivity(intent);
            }
        });

        //냉장고를 부탁해 버튼 클릭시 액티비티 전환
        //Layout Resource File로 refrigerator_plz_activity.xml과 연결할 Refrigerator_Plz.java클래스 생성 필요

        Button comBtn = (Button) findViewById(R.id.com_btn);

        comBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                //버튼 클릭시 냉장고를 부탁해 화면으로 전환하는 동작
                Intent intent = new Intent(getApplicationContext(), CombineActivity.class);
                intent.putExtra("name", name);      //이름 인텐트로 넘김
                startActivity(intent);
            }
        });

        //레시피 버튼 클릭시 액티비티 전환
        //Layout Resource File로 recipe_activity.xml과 연결할 Recipe.java클래스 생성 필요

        Button recipe = (Button) findViewById(R.id.recipe_btn);

        recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //버튼 클릭시 레시피 화면으로 전환하는 동작
                Intent intent = new Intent(getApplicationContext(), AllRecipeActivity.class);
                startActivity(intent);

            }
        });


    }
}