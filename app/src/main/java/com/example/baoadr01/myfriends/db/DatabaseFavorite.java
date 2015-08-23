package com.example.baoadr01.myfriends.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.baoadr01.myfriends.Utils.MyContact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by BaoADR01 on 8/22/2015.
 */


public class DatabaseFavorite extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "MyFavorite";
    private static int DATABASE_VERSION = 1 ;
    public static String TABLE_NAME = "ListFavorite";
    public static String KEY_ID = "id_f";
    public static String KEY_URI_IMAGE = "image_f";
    public static String KEY_SDT = "sdt_f";
    public static String KEY_NAME = "name_f";
    public static String KEY_CATEGORY="category_f";

    Context context;

    public DatabaseFavorite(Context context) {
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
        values.put(KEY_CATEGORY, v.getCATEGORY());

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
            v.setCATEGORY(cursor.getString(4));
            list.add(v);
            cursor.moveToNext();
        }
        for(int i=0;i<list.size();i++) {
            Collections.sort(list, new Comparator<MyContact>() {
                @Override
                public int compare(MyContact sv1, MyContact sv2) {
                    return (sv1.getNAME().compareToIgnoreCase(sv2.getNAME()));
                }
            });
        }
        return list;
    }


    public MyContact getDataTheoPos(int pos) {
        SQLiteDatabase sDatabase = getReadableDatabase();
        ArrayList<MyContact> lst = new ArrayList<MyContact>();
        String command = "select * from " + TABLE_NAME;
        Cursor cursor = sDatabase.rawQuery(command, null);
        cursor.moveToFirst(); // cursor như 1 biến con trỏ . CHo trỏ lên thằng
        // đầu tiên ds.
        // nếu không phải thằng cuối cùng của ds thì sẽ lấy ds ra
        while (!cursor.isAfterLast()) {
            MyContact v = new MyContact();
            v.setID(cursor.getInt(0));
            v.setNAME(cursor.getString(1));
            v.setSDT(cursor.getString(2));
            v.setIMAGE(cursor.getBlob(3));
            v.setCATEGORY(cursor.getString(4));
            lst.add(v);
            // sau Khi lấy ra con trỏ cursor sẽ trỏ đến thằng tiếp theo để lấy
            // dữ liệu
            cursor.moveToNext();
        }
        return lst.get(pos);
    }


    public void updateData(MyContact v){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, v.getNAME());
        values.put(KEY_SDT,v.getSDT());
        db.update(TABLE_NAME, values, KEY_ID + " =? ", new String[]{String.valueOf(v.getID())});
        Log.i("updateData-->", "this");
        db.close();
    }

    public void deleteData(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID +" =?", new String[]{String.valueOf(id)});
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String taoBangLienHe = "create table " + TABLE_NAME + " ( " + KEY_ID +" Integer primary key, " + KEY_NAME + " text, "
                + KEY_SDT + " text, "+KEY_URI_IMAGE +" BLOB, "+KEY_CATEGORY+ " text"+")";
        db.execSQL(taoBangLienHe);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }
}



