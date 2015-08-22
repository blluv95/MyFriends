package com.example.baoadr01.myfriends.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.baoadr01.myfriends.R;
import com.example.baoadr01.myfriends.Utils.MyContact;
import com.example.baoadr01.myfriends.db.DatabaseFavorite;
import com.example.baoadr01.myfriends.db.DatabaseHandler;

public class EditInformationActivity extends ActionBarActivity implements View.OnClickListener {
    Button btn_edit_Save, btn_edit_Exit;
    EditText edittext_edit_name, edittext_edit_sdt;
    MyContact contact;
    DatabaseHandler databaseHandler;
    DatabaseFavorite databaseFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_information);
        init();
        getDataFromInformation();
    }

    public void init() {
        contact = new MyContact();
        databaseHandler = new DatabaseHandler(getBaseContext());
        databaseFavorite=new DatabaseFavorite(getApplicationContext());
        btn_edit_Exit = (Button) findViewById(R.id.btn_edit_exit);
        btn_edit_Save = (Button) findViewById(R.id.btn_edit_save);
        edittext_edit_name = (EditText) findViewById(R.id.editext_edit_name);
        edittext_edit_sdt = (EditText) findViewById(R.id.editext_edit_telephone);
        btn_edit_Save.setOnClickListener(this);
        btn_edit_Exit.setOnClickListener(this);
    }

    public void getDataFromInformation() {
        Bundle bundle = getIntent().getBundleExtra(InformationActivity.KEY_INFORMATION_INTENT);
        edittext_edit_name.setText(bundle.getString(InformationActivity.KEY_INFORMATION_NAME));
        edittext_edit_sdt.setText(bundle.getString(InformationActivity.KEY_INFORMATION_SDT));
        contact.setID(bundle.getInt(InformationActivity.KEY_INFORMATION_ID));
        contact.setCATEGORY(bundle.getString(InformationActivity.KEY_INFORMATION_CATEGORY));
        contact.setIMAGE(bundle.getByteArray(InformationActivity.KEY_INFORMATION_AVATAR));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_edit_save) {
            contact.setNAME(edittext_edit_name.getText().toString());
            contact.setSDT(edittext_edit_sdt.getText().toString());
            databaseHandler.updateData(contact);
            databaseFavorite.updateData(contact);
            System.exit(0);
        } else if (v.getId() == R.id.btn_edit_exit) {
            finish();
        }
    }
}
