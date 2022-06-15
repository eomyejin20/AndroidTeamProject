package com.practice.loginregister;
import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseOpenHelper extends SQLiteAssetHelper{
    private static final String DATABASE_NAME="naengsiljang.sqlite3";
    private static final int DATABASE_VERSION=1;
    private static final String factory = "" ;

    public DatabaseOpenHelper(Context context){
        super(context,DATABASE_NAME,  null, DATABASE_VERSION);
    }
}