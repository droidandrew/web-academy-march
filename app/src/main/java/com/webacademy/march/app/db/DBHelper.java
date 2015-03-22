package com.webacademy.march.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *
 */
public class DBHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "db_name";
    private static int version = 1;
    private String SQL = "create table " + UserTable.TABLE + "(" +
            " _id integer primary key autoincrement," +
            " " + UserTable.NAME + " text, " +
            " " + UserTable.AGE + " integer);";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // nothing
    }

    public static class UserTable {
        public static String TABLE = "user";
        public static String NAME = "name";
        public static String AGE = "age";
    }
}
