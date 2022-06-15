package com.practice.loginregister;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    public SQLiteOpenHelper openHelper;
    public SQLiteDatabase db;
    public static DatabaseAccess instance;
    Cursor c = null;

    public DatabaseAccess(Context context) { //데이터 베이스 생성자
        this.openHelper = new DatabaseOpenHelper(context);

    }

    public static DatabaseAccess getInstance(Context context) { // 데이터 베이스 객체 생성 함수
        if (instance == null) {
            instance = new DatabaseAccess(context);

        }
        return instance;
    }

    public void open() {//데이터베이스 읽을 수 있는 권한 부여 함수
        this.db = openHelper.getWritableDatabase();

    }

    public void close() {//데이터 베이스 다 읽고 닫아주는 함수
        if (db != null) {
            this.db.close();
        }
    }

    public ArrayList<String> GetMenu(ArrayList name) { //재료를 name 매개변수로 받으면 query문을 통해 menu를 반환해주는 함수

        List query_name=new ArrayList<String>();
        for(int i=0; i<name.size();i++) {
            query_name.add("%" + name.get(i).toString() + "%");
            if (i != name.size() - 1) {
                query_name.add("%");
            }
        }
            String joinQuery= "'" + String.join("",query_name) + "'";

        System.out.println(joinQuery);

        /*System.out.println(joinQuery);*/
        c = db.rawQuery("SELECT menu from recipe_version2 Where ingredients like"+joinQuery, new String[]{});
        ArrayList<String>search_menu = new ArrayList<>();
        while (c.moveToNext()) {
            String menu = c.getString(0);
            search_menu.add(c.getString(0));;
        }
        return search_menu;
    }

    public ArrayList<String> viewmenu() { // query문을 통해 전체 menu를 반환해주는  함수
        int i=0;
        c = db.rawQuery("select * from recipe_version2" , new String[]{});
        ArrayList<String>menu = new ArrayList<>();
        while (c.moveToNext()) {
            menu.add(c.getString(0));
        }
        return menu;
    }
    public ArrayList<String> GetLink(String name) { //메뉴에 해당하는 링크를 반환하는 함수
        int i=0;
        c = db.rawQuery("select link from recipe_version2 where menu ='" + name + "'" , new String[]{});
        ArrayList<String>link = new ArrayList<>();
        while (c.moveToNext()) {
            link.add(c.getString(0));
        }
        return link;
    }

}
