package com.wyc.employ;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static final String DataBase_name="employ.db";
	private static final int Version=1;
	
	public DBHelper(Context context) {
		super(context, DataBase_name, null, Version);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		/*private int id;
	private String name;
	private int age;
	private int sex;
	private int por;
	private double money;
	private double ywze;//当月业务总额;
	private int time; //当月工作时间*/
		db.execSQL("CREATE TABLE IF NOT EXISTS employ" +  
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " name VARCHAR, " +
                "age INTEGER, " +
                "sex INTEGER," +
                "por INTEGER," +
                "money DOUBLE," +
                "ywze DOUBLE," +
                "time INTEGER)");  
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		
		
	}

}
