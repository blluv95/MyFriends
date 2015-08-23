package com.example.baoadr01.myfriends.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

    public void setToolsBar() {
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
    }

    public void init() {
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
            final DatabaseHandler databaseHandler = new DatabaseHandler(getApplicationContext());
            final DatabaseFavorite databaseFavorite = new DatabaseFavorite(getApplicationContext());

            AlertDialog.Builder b = new AlertDialog.Builder(
                    getApplicationContext());
            b.setTitle("Question");
            b.setMessage("Do you want delete ?");
            b.setPositiveButton("Delete",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            databaseHandler.deleteData(id_);
                            databaseFavorite.deleteData(id_);
                            System.exit(0);
                        }
                    });

            b.setNegativeButton("Cannel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            System.exit(0);
                        }
                    });

            b.create().show();

//            databaseHandler.deleteData(id_);
//            databaseFavorite.deleteData(id_);
            Toast.makeText(getApplicationContext(), "Đã Xóa Xong ", Toast.LENGTH_LONG).show();
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


////--------------------
//package com.example.callandsend;
//
//        import java.util.ArrayList;
//
//        import Adapter.MyAdapter;
//        import DATA.MyContact;
//        import android.app.Activity;
//        import android.app.AlertDialog;
//        import android.app.ListActivity;
//        import android.content.DialogInterface;
//        import android.content.Intent;
//        import android.net.Uri;
//        import android.os.Bundle;
//        import android.view.Menu;
//        import android.view.MenuItem;
//        import android.view.View;
//        import android.view.View.OnClickListener;
//        import android.widget.AdapterView;
//        import android.widget.AdapterView.OnItemClickListener;
//        import android.widget.AdapterView.OnItemLongClickListener;
//        import android.widget.Button;
//        import android.widget.EditText;
//        import android.widget.ListView;
//        import android.widget.Toast;
//
//public class MainActivity extends Activity {
//
//    Button btSave;
//    EditText edSdt, edName;
//    ArrayList<MyContact> lst;
//    ListView listView;
//    MyAdapter adapter;
//    MyContact contact;
//    public static String name = "";
//    public static String phoneNumber = "";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        btSave = (Button) this.findViewById(R.id.btSave);
//        edSdt = (EditText) findViewById(R.id.edSdt);
//        edName = (EditText) findViewById(R.id.edName);
//        lst = new ArrayList<MyContact>();
//        listView = (ListView) findViewById(R.id.lst);
//
//        adapter = new MyAdapter(this, R.layout.view_row, lst);
//        listView.setAdapter(adapter);
//        btSave.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                contact = new MyContact();
//                String name = edName.getText().toString();
//                String sdt = edSdt.getText().toString();
//                contact.setName(name);
//                contact.setSdt(sdt);
//                lst.add(contact);
//                adapter.notifyDataSetChanged();
//                edName.setText("");
//                edSdt.setText("");
//            }
//        });
//        listView.setOnItemLongClickListener(new OnItemLongClickListener() {
//            //Lưu vết
//            @Override
//            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
//                                           int arg2, long arg3) {
//                // TODO Auto-generated method stub
//                contact = lst.get(arg2);
//                return false;
//            }
//        });
//
//        listView.setOnItemClickListener(new OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> lst1, View v, int position,
//                                    long arg3) {
//                // TODO Auto-generated method stub
//
//                MyContact mct = lst.get(position);
//                name = mct.getName();
//                phoneNumber = mct.getSdt();
//
//                AlertDialog.Builder b = new AlertDialog.Builder(
//                        MainActivity.this);
//                b.setTitle("Question");
//                b.setMessage("Call And Message");
//                b.setIcon(R.drawable.ic_launcher);
//                b.setPositiveButton("Call",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog,
//                                                int which) {
//                                // TODO Auto-generated method stub
//                                Uri uri = Uri.parse("tel:" + phoneNumber);
//                                Intent i = new Intent(Intent.ACTION_DIAL, uri);
//                                startActivity(i);
//
//                            }
//                        });
//
//                b.setNegativeButton("Message",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog,
//                                                int which) {
//                                // TODO Auto-generatthied method stub
//                                Intent sendIntent = new Intent(Intent.ACTION_SENDTO,Uri.parse("sms:"+phoneNumber));
//                                sendIntent.putExtra(Intent.EXTRA_TEXT,
//                                        "This is my text to send. !");
////								sendIntent.putExtra(
////										Intent.ACTION_SEND_MULTIPLE,
////										phoneNumber);
//                                // sendIntent.putExtra("sdt", phoneNumber);
////								sendIntent.setType("text/plain");
//                                startActivity(Intent.createChooser(sendIntent,
//                                        "123"));
//                            }
//                        });
//
//                b.create().show();
//            }
//        });
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//}

