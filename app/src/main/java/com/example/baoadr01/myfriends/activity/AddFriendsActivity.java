package com.example.baoadr01.myfriends.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.baoadr01.myfriends.R;
import com.example.baoadr01.myfriends.Utils.MyContact;
import com.example.baoadr01.myfriends.adapter.MyContactAdapter;
import com.example.baoadr01.myfriends.db.DatabaseHandler;
import com.example.baoadr01.myfriends.fragment.FragmentBoxOffice;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class AddFriendsActivity extends AppCompatActivity implements View.OnClickListener {
    Spinner spinner;
    EditText editName, edSdt;
    ImageButton imageAvatar;
    Button btnSave, btnExit;
    DatabaseHandler db;
    byte imageInByte[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends);
        db = new DatabaseHandler(this);
        spinner = (Spinner) findViewById(R.id.spinner);
        String[] data = {"Di động", "Nhà", "Cơ quan", "Fax cơ quan", "Máy nhắn tin"};
        spinner.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, data));

        editName = (EditText) findViewById(R.id.editext_name);
        edSdt = (EditText) findViewById(R.id.edittext_sdt);
        imageAvatar = (ImageButton) findViewById(R.id.img_button);
        btnSave = (Button) findViewById(R.id.btn_add_save);
        btnExit = (Button) findViewById(R.id.btn_add_exit);
        btnSave.setOnClickListener(this);
        btnExit.setOnClickListener(this);


        Bitmap image1 = BitmapFactory.decodeResource(getResources(),
                R.drawable.avatar);
        imageAvatar.setImageBitmap(image1);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image1.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        image = stream.toByteArray();


        imageAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent2, 1);
            }
        });
    }

    Uri uri = null;
    byte[] image;

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK)
                uri = data.getData();
            try {
                image = readBytes(uri);
            } catch (IOException e) {
                e.printStackTrace();
            }


            ByteArrayInputStream imageStream = new ByteArrayInputStream(image);
            Bitmap theImage = BitmapFactory.decodeStream(imageStream);
            imageAvatar.setImageBitmap(theImage);

        }
    }
    public byte[] readBytes(Uri uri) throws IOException {
        // this dynamically extends to take the bytes you read
        InputStream inputStream = getContentResolver().openInputStream(uri);
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        // this is storage overwritten on each iteration with bytes
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        // we need to know how may bytes were read to write them to the byteBuffer
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        // and then we can return your byte array.
        return byteBuffer.toByteArray();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    Random random=new Random();
    int id;
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_add_save) {
            id=random.nextInt(1000000);
            MyContact myContact = new MyContact();
            myContact.setID(id);
            myContact.setNAME(editName.getText().toString());
            myContact.setSDT(edSdt.getText().toString());
            myContact.setIMAGE(image);
            db.AddData(myContact);
            Log.i("ID----->", id + "");
            Log.i("FragmentBoxOffice.coun----->", MyContactAdapter.vt + "");
            FragmentBoxOffice.adapter.notifyDataSetChanged();
            finish();
        }
    }
}
