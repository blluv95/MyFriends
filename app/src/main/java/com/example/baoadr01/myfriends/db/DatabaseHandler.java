package com.example.baoadr01.myfriends.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.baoadr01.myfriends.Utils.MyContact;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper{
	private static String DATABASE_NAME = "MyContact";
	private static int DATABASE_VERSION = 1 ;
	public static String TABLE_NAME = "FriendsList";
	public static String KEY_ID = "id";
	public static String KEY_URI_IMAGE = "image";
	public static String KEY_SDT = "sdt";
	public static String KEY_NAME = "name";

	Context context;
	
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context=context;
	}
	
	public void AddData(MyContact v){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_ID,v.getID());
		values.put(KEY_NAME, v.getNAME());
		values.put(KEY_SDT, v.getSDT());
		values.put(KEY_URI_IMAGE,v.getIMAGE());
		
		if(db.insert(TABLE_NAME, null, values)!= -1){
			Toast.makeText(context, "Thêm thành công!", Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(context, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
		}
		db.close();
	}
	
	public List<MyContact> getAllData(){
		List<MyContact> list = new ArrayList<MyContact>();
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "select * from " + TABLE_NAME;
		
		Cursor cursor = db.rawQuery(sql, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			MyContact v = new MyContact();
			v.setID(cursor.getInt(0));
			v.setNAME(cursor.getString(1));
			v.setSDT(cursor.getString(2));
			v.setIMAGE(cursor.getBlob(3));
			list.add(v);
			cursor.moveToNext();
		}
		return list;
	}
	
//	public void updateData(MyContact v){
//		SQLiteDatabase db = this.getWritableDatabase();
//		ContentValues values = new ContentValues();
//		values.put(KEY_WORD, v.getVOCABULARY());
//		values.put(KEY_DEFINITION,v.getDEFINITION());
//		db.update(TABLE_NAME, values, KEY_ID + " =? ", new String[]{String.valueOf(v.getID())});
//		db.close();
//	}
//
	public void deleteData(int id){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_NAME, KEY_ID +" =?", new String[]{String.valueOf(id)});
		db.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String taoBangLienHe = "create table " + TABLE_NAME + " ( " + KEY_ID +" Integer primary key, " + KEY_NAME + " text, "
				+ KEY_SDT + " text, "+KEY_URI_IMAGE +" BLOB"+ ")";
		db.execSQL(taoBangLienHe);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists " + TABLE_NAME);
		onCreate(db);
	}




}
