package com.helloarron.storagesample.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by arron on 16/6/5.
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    public DBOpenHelper(Context context, String name) {
        super(context, name, null, 1);
    }

    /**
     * 首次调用数据库的时候触发，一般可以把创建库、表等操作放在这部分
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists user(_id integer primary key autoincrement, name text not null, pwd text not null)");
        db.execSQL("insert into user(name, pwd) values('admin', '123456')");
    }

    /**
     * 数据库版本发生改变的时候自动调用
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
