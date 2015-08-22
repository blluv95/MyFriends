package com.example.baoadr01.myfriends.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baoadr01.myfriends.R;
import com.example.baoadr01.myfriends.db.DatabaseFavorite;
import com.example.baoadr01.myfriends.db.DatabaseHandler;
import com.example.baoadr01.myfriends.fragment.FragmentBoxOffice;

import java.io.ByteArrayInputStream;

public class InformationActivity extends ActionBarActivity implements View.OnClickListener {
    Toolbar toolbar;
    ImageView imageView_information_avatar;
    TextView textView_information_name, TextView_information_sdt, textView_category_telẹphone;
    Button btn_Send_sms, btn_call;
    int id_;
    byte[] bytes;
    public static final String KEY_INFORMATION_ID = "iid";
    public static final String KEY_INFORMATION_NAME = "iname";
    public static final String KEY_INFORMATION_SDT = "isdt";
    public static final String KEY_INFORMATION_AVATAR = "iavatar";
    public static final String KEY_INFORMATION_CATEGORY = "icategory";
    public static final String KEY_INFORMATION_INTENT = "iitent";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        init();
        setToolsBar();
        getData();
    }

    public void setToolsBar(){
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
    }

    public void init(){
        imageView_information_avatar = (ImageView) findViewById(R.id.image_information_avatar);
        textView_information_name = (TextView) findViewById(R.id.textview_name_from_infomation);
        TextView_information_sdt = (TextView) findViewById(R.id.textview_information_sdt);
        textView_category_telẹphone = (TextView) findViewById(R.id.textview_from_spiner);
        btn_Send_sms = (Button) findViewById(R.id.btn_send_sms);
        btn_call = (Button) findViewById(R.id.btn_call);
        btn_call.setOnClickListener(this);
        btn_Send_sms.setOnClickListener(this);
    }

    private void getData() {
        Bundle bundle = new Bundle();
        bundle = getIntent().getBundleExtra(FragmentBoxOffice.KEY_INTENT);
        TextView_information_sdt.setText(bundle.getString(FragmentBoxOffice.KEY_SDT));
        textView_information_name.setText(bundle.getString(FragmentBoxOffice.KEY_NAME));
        bytes = bundle.getByteArray(FragmentBoxOffice.KEY_AVATAR);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        imageView_information_avatar.setImageBitmap(bitmap);
        textView_category_telẹphone.setText(bundle.getString(FragmentBoxOffice.KEY_CATEGORY));
        id_ = bundle.getInt(FragmentBoxOffice.KEY_ID);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_information, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            DatabaseHandler databaseHandler = new DatabaseHandler(getApplicationContext());
            DatabaseFavorite databaseFavorite = new DatabaseFavorite(getApplicationContext());
            databaseHandler.deleteData(id_);
            databaseFavorite.deleteData(id_);
            Toast.makeText(getApplication(), "Đã Xóa Xong ", Toast.LENGTH_SHORT).show();
            System.exit(0);
            return true;
        }
        if (id == R.id.action_repair) {
            Intent intent = new Intent(getApplication(), EditInformationActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt(KEY_INFORMATION_ID, id_);
            Log.i("ID- Information--->", bundle.getInt(FragmentBoxOffice.KEY_ID) + "");

            bundle.putString(KEY_INFORMATION_NAME, textView_information_name.getText().toString());
            bundle.putString(KEY_INFORMATION_SDT, TextView_information_sdt.getText().toString());
            bundle.putByteArray(KEY_INFORMATION_AVATAR, bytes);
            bundle.putString(KEY_INFORMATION_CATEGORY, textView_category_telẹphone.getText().toString());
            intent.putExtra(KEY_INFORMATION_INTENT, bundle);
            startActivity(intent);
            System.exit(0);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_call) {
            Uri uri = Uri.parse("tel:" + Long.parseLong(TextView_information_sdt.getText().toString()));
            Intent i = new Intent(Intent.ACTION_DIAL, uri);
            startActivity(i);
        } else if (v.getId() == R.id.btn_send_sms) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            Intent sendIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:" + TextView_information_sdt.getText()));
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "This is my text to send. !");
            startActivity(Intent.createChooser(sendIntent,
                    "123"));
        }
    }
}

