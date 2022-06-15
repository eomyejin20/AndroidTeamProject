package com.practice.loginregister;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class IngredientSelectActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    // 여러개 버튼 배열로 처리하기 위해선언
    private Button[] mButton = new Button[144];
    private String[] btn_period = new String[144];          //버튼당 유통기한 적을 배열
    // 버튼에 텍스트를 가져오기 위한 리스트
    private ArrayList<String>ingredients;
    private ArrayList<String>periods;
    private String period;                                  //데이트피커에서 날짜 선택했을 때, 잠시 저장할 변수
    private String name;

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
//        String date_str = DateFormat.getDateInstance(DateFormat.).format(calendar.getTime());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        period = format.format(calendar.getTime());
        periods.add(period);     //해당 버튼에 매칭되는 인덱스에 저장된 유통기한을 periods에 삽입
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_select);

        ingredients = new ArrayList<String>();
        periods = new ArrayList<String>();

        Intent getintent = getIntent();
        name = getintent.getStringExtra("name");    //현재 로그인한 사용자 이름 저장

        mButton[0] = (Button) findViewById(R.id.meat_button1);
        mButton[1] = (Button) findViewById(R.id.meat_button2);
        mButton[2] = (Button) findViewById(R.id.meat_button3);
        mButton[3] = (Button) findViewById(R.id.meat_button4);
        mButton[4] = (Button) findViewById(R.id.meat_button5);
        mButton[5] = (Button) findViewById(R.id.meat_button6);
        mButton[6] = (Button) findViewById(R.id.meat_button7);
        mButton[7] = (Button) findViewById(R.id.meat_button8);
        mButton[8] = (Button) findViewById(R.id.meat_button9);
        mButton[9] = (Button) findViewById(R.id.meat_button10);
        mButton[10] = (Button) findViewById(R.id.meat_button11);
        mButton[11] = (Button) findViewById(R.id.meat_button12);
        mButton[12] = (Button) findViewById(R.id.meat_button13);
        mButton[13] = (Button) findViewById(R.id.meat_button14);
        mButton[14] = (Button) findViewById(R.id.meat_button15);
        mButton[15] = (Button) findViewById(R.id.meat_button16);
        mButton[16] = (Button) findViewById(R.id.ve_button1);
        mButton[17] = (Button) findViewById(R.id.ve_button2);
        mButton[18] = (Button) findViewById(R.id.ve_button3);
        mButton[19] = (Button) findViewById(R.id.ve_button4);
        mButton[20] = (Button) findViewById(R.id.ve_button5);
        mButton[21] = (Button) findViewById(R.id.ve_button6);
        mButton[22] = (Button) findViewById(R.id.ve_button7);
        mButton[23] = (Button) findViewById(R.id.ve_button8);
        mButton[24] = (Button) findViewById(R.id.ve_button9);
        mButton[25] = (Button) findViewById(R.id.ve_button10);
        mButton[26] = (Button) findViewById(R.id.ve_button11);
        mButton[27] = (Button) findViewById(R.id.ve_button12);
        mButton[28] = (Button) findViewById(R.id.ve_button13);
        mButton[29] = (Button) findViewById(R.id.ve_button14);
        mButton[30] = (Button) findViewById(R.id.ve_button15);
        mButton[31] = (Button) findViewById(R.id.ve_button16);
        mButton[32] = (Button) findViewById(R.id.ve_button17);
        mButton[33] = (Button) findViewById(R.id.ve_button18);
        mButton[34] = (Button) findViewById(R.id.ve_button19);
        mButton[35] = (Button) findViewById(R.id.ve_button20);
        mButton[36] = (Button) findViewById(R.id.ve_button21);
        mButton[37] = (Button) findViewById(R.id.ve_button22);
        mButton[38] = (Button) findViewById(R.id.ve_button23);
        mButton[39] = (Button) findViewById(R.id.ve_button24);
        mButton[40] = (Button) findViewById(R.id.ve_button25);
        mButton[41] = (Button) findViewById(R.id.ve_button26);
        mButton[42] = (Button) findViewById(R.id.ve_button27);
        mButton[43] = (Button) findViewById(R.id.ve_button28);
        mButton[44] = (Button) findViewById(R.id.ve_button29);
        mButton[45] = (Button) findViewById(R.id.ve_button30);
        mButton[46] = (Button) findViewById(R.id.ve_button31);
        mButton[47] = (Button) findViewById(R.id.ve_button32);
        mButton[48] = (Button) findViewById(R.id.ve_button33);
        mButton[49] = (Button) findViewById(R.id.ve_button34);
        mButton[50] = (Button) findViewById(R.id.ve_button35);
        mButton[51] = (Button) findViewById(R.id.ve_button36);
        mButton[52] = (Button) findViewById(R.id.ve_button37);
        mButton[53] = (Button) findViewById(R.id.ve_button38);
        mButton[54] = (Button) findViewById(R.id.ve_button39);
        mButton[55] = (Button) findViewById(R.id.ve_button40);
        mButton[56] = (Button) findViewById(R.id.ve_button41);
        mButton[57] = (Button) findViewById(R.id.ve_button42);
        mButton[58] = (Button) findViewById(R.id.ve_button43);
        mButton[59] = (Button) findViewById(R.id.ve_button44);
        mButton[60] = (Button) findViewById(R.id.seoson_button1);
        mButton[61] = (Button) findViewById(R.id.seoson_button2);
        mButton[62] = (Button) findViewById(R.id.seoson_button3);
        mButton[63] = (Button) findViewById(R.id.seoson_button4);
        mButton[64] = (Button) findViewById(R.id.seoson_button5);
        mButton[65] = (Button) findViewById(R.id.seoson_button6);
        mButton[66] = (Button) findViewById(R.id.seoson_button7);
        mButton[67] = (Button) findViewById(R.id.seoson_button8);
        mButton[68] = (Button) findViewById(R.id.seoson_button9);
        mButton[69] = (Button) findViewById(R.id.seoson_button10);
        mButton[70] = (Button) findViewById(R.id.seoson_button11);
        mButton[71] = (Button) findViewById(R.id.seoson_button12);
        mButton[72] = (Button) findViewById(R.id.seoson_button13);
        mButton[73] = (Button) findViewById(R.id.seoson_button14);
        mButton[74] = (Button) findViewById(R.id.seoson_button15);
        mButton[75] = (Button) findViewById(R.id.seoson_button16);
        mButton[76] = (Button) findViewById(R.id.seoson_button17);
        mButton[77] = (Button) findViewById(R.id.seoson_button18);
        mButton[78] = (Button) findViewById(R.id.seoson_button19);
        mButton[79] = (Button) findViewById(R.id.seoson_button20);
        mButton[80] = (Button) findViewById(R.id.seoson_button21);
        mButton[81] = (Button) findViewById(R.id.seoson_button22);
        mButton[82] = (Button) findViewById(R.id.seoson_button23);
        mButton[83] = (Button) findViewById(R.id.seoson_button24);
        mButton[84] = (Button) findViewById(R.id.seoson_button25);
        mButton[85] = (Button) findViewById(R.id.seoson_button26);
        mButton[86] = (Button) findViewById(R.id.seoson_button27);
        mButton[87] = (Button) findViewById(R.id.seoson_button28);
        mButton[88] = (Button) findViewById(R.id.seoson_button29);
        mButton[89] = (Button) findViewById(R.id.seoson_button30);
        mButton[90] = (Button) findViewById(R.id.seoson_button31);
        mButton[91] = (Button) findViewById(R.id.seoson_button32);
        mButton[92] = (Button) findViewById(R.id.seoson_button33);
        mButton[93] = (Button) findViewById(R.id.seoson_button34);
        mButton[94] = (Button) findViewById(R.id.seoson_button35);
        mButton[95] = (Button) findViewById(R.id.seoson_button36);
        mButton[96] = (Button) findViewById(R.id.seoson_button37);
        mButton[97] = (Button) findViewById(R.id.seoson_button38);
        mButton[98] = (Button) findViewById(R.id.seoson_button39);
        mButton[99] = (Button) findViewById(R.id.seoson_button40);
        mButton[100] = (Button) findViewById(R.id.seoson_button41);
        mButton[101] = (Button) findViewById(R.id.seoson_button42);
        mButton[102] = (Button) findViewById(R.id.seoson_button43);
        mButton[103] = (Button) findViewById(R.id.seoson_button44);
        mButton[104] = (Button) findViewById(R.id.fish_button1);
        mButton[105] = (Button) findViewById(R.id.fish_button2);
        mButton[106] = (Button) findViewById(R.id.fish_button3);
        mButton[107] = (Button) findViewById(R.id.fish_button4);
        mButton[108] = (Button) findViewById(R.id.fish_button5);
        mButton[109] = (Button) findViewById(R.id.fish_button6);
        mButton[110] = (Button) findViewById(R.id.fish_button7);
        mButton[111] = (Button) findViewById(R.id.fish_button8);
        mButton[112] = (Button) findViewById(R.id.fish_button9);
        mButton[113] = (Button) findViewById(R.id.fish_button10);
        mButton[114] = (Button) findViewById(R.id.fish_button11);
        mButton[115] = (Button) findViewById(R.id.fish_button12);
        mButton[116] = (Button) findViewById(R.id.fish_button13);
        mButton[117] = (Button) findViewById(R.id.fish_button14);
        mButton[118] = (Button) findViewById(R.id.fish_button15);
        mButton[119] = (Button) findViewById(R.id.fish_button16);
        mButton[120] = (Button) findViewById(R.id.fish_button17);
        mButton[121] = (Button) findViewById(R.id.fish_button18);
        mButton[122] = (Button) findViewById(R.id.fish_button19);
        mButton[123] = (Button) findViewById(R.id.fish_button20);
        mButton[124] = (Button) findViewById(R.id.instant_button1);
        mButton[125] = (Button) findViewById(R.id.instant_button2);
        mButton[126] = (Button) findViewById(R.id.instant_button3);
        mButton[127] = (Button) findViewById(R.id.instant_button4);
        mButton[128] = (Button) findViewById(R.id.instant_button5);
        mButton[129] = (Button) findViewById(R.id.instant_button6);
        mButton[130] = (Button) findViewById(R.id.instant_button7);
        mButton[131] = (Button) findViewById(R.id.instant_button8);
        mButton[132] = (Button) findViewById(R.id.other_button1);
        mButton[133] = (Button) findViewById(R.id.other_button2);
        mButton[134] = (Button) findViewById(R.id.other_button3);
        mButton[135] = (Button) findViewById(R.id.other_button4);
        mButton[136] = (Button) findViewById(R.id.other_button5);
        mButton[137] = (Button) findViewById(R.id.other_button6);
        mButton[138] = (Button) findViewById(R.id.other_button7);
        mButton[139] = (Button) findViewById(R.id.other_button8);
        mButton[140] = (Button) findViewById(R.id.other_button9);
        mButton[141] = (Button) findViewById(R.id.other_button10);
        mButton[142] = (Button) findViewById(R.id.other_button11);
        mButton[143] = (Button) findViewById(R.id.other_button12);
        //add 버튼 정의


        //버튼 클릭시 이벤트 정의

        for (int i = 0; i < 144; i++) {
            final int INDEX;
            INDEX=i;

            mButton[INDEX].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //만약 버튼이 클릭되어 있는 상태라면
                    if(mButton[INDEX].isSelected())
                    {
                        mButton[INDEX].setSelected(false);
                        mButton[INDEX].setBackgroundColor(Color.WHITE);
                    }

                    //버튼이 클릭되어 있지 않은 상태라면
                    else {
                        //데이트피커 열고 날짜 선택한 후 btn_period에 문자열 삽입
                        DialogFragment datepicker = new DatePickerFragment();
                        datepicker.show(getSupportFragmentManager(), "date picker");
//                        btn_period[INDEX] = period;

                        mButton[INDEX].setSelected(true);
                        mButton[INDEX].setBackgroundColor(Color.parseColor("#87CEFA"));     //색깔 스카이블루로 바꾸고

                    }

                }
            });
        }
        //추가 버튼 눌렀을 시 클릭 이벤트 정의
        ImageButton addButton = (ImageButton) findViewById(R.id.ingredient_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i = 0; i < 144; i++) {
                    final int INDEX;
                    INDEX = i;
                    // 재료 버튼이 선택되어져 있는 것만 찾아 데이터를 저장한다.
                    if (mButton[INDEX].isSelected()) {
                        String ingredient = mButton[INDEX].getText().toString();
                        ingredients.add(ingredient);               //재료 버튼에 있는 재료 문자열을 ingredients에 삽입
//                        periods.add(btn_period[INDEX]);     //해당 버튼에 매칭되는 인덱스에 저장된 유통기한을 periods에 삽입
                    }
                }
                for(int i=0; i<ingredients.size(); i++){
                    System.out.println(ingredients.get(i));
                    System.out.println(periods.get(i));
                }

                Intent intent = new Intent(getApplicationContext(), PeriodInsertActivity.class);
                intent.putExtra("ingredients", ingredients);
                intent.putExtra("periods", periods);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });

        //메인 페이지로 이동
        ImageButton go_main_btn = (ImageButton) findViewById(R.id.go_main_btn);
        go_main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


    }



}

