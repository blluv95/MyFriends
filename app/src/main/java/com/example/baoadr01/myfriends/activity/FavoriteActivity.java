package com.example.baoadr01.myfriends.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.baoadr01.myfriends.R;
import com.example.baoadr01.myfriends.Utils.MyContact;
import com.example.baoadr01.myfriends.adapter.FavoriteAdapter;
import com.example.baoadr01.myfriends.db.DatabaseFavorite;
import com.example.baoadr01.myfriends.db.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BaoADR01 on 8/21/2015.
 */
public class FavoriteActivity extends ActionBarActivity implements View.OnClickListener {
    DatabaseHandler db1;
    DatabaseFavorite db;
    Button btn_favorite_exit, btn_favorite_Save;
    ListView listView;
    List<MyContact> list;
    FavoriteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_all_contact);
        init();
        setAdapter();
    }
    public void setAdapter(){
        list = db1.getAllData();
        adapter = new FavoriteAdapter(getApplicationContext(), list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void init() {
        btn_favorite_exit = (Button) findViewById(R.id.btn_favorite_exit);
        btn_favorite_Save = (Button) findViewById(R.id.btn_favorite_save);
        listView = (ListView) findViewById(R.id.listview_show_all);
        list = new ArrayList<MyContact>();
        btn_favorite_exit.setOnClickListener(this);
        btn_favorite_Save.setOnClickListener(this);
        db1=new DatabaseHandler(getApplicationContext());
        db=new DatabaseFavorite(getApplicationContext());
    }



    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_favorite_save) {
            for (int i = 0; i < list.size(); i++) {
                MyContact contact = list.get(i);
                if (contact.getID() == contact.getID_CHECKBOX()) {
                    Log.i("List Name---->", contact.getNAME() + "");
                    db.AddData(contact);
                }
            }
            System.exit(0);
        } else if (v.getId() == R.id.btn_favorite_exit) {
            finish();
        }

    }
}

