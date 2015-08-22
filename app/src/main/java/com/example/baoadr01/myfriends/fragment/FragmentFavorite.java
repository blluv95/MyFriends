package com.example.baoadr01.myfriends.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.baoadr01.myfriends.R;
import com.example.baoadr01.myfriends.Utils.MyContact;
import com.example.baoadr01.myfriends.activity.FavoriteActivity;
import com.example.baoadr01.myfriends.adapter.ShowListFavoriteAdapter;
import com.example.baoadr01.myfriends.db.DatabaseFavorite;

import java.util.List;

/**
 */
public class FragmentFavorite extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {
    ImageView imageView_add_favorite;
    ListView listView;
    List<MyContact> data;
    DatabaseFavorite db;
    ShowListFavoriteAdapter adapter;

    public FragmentFavorite() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_favorite, container, false);
         view.findViewById(R.id.image_add_contact_favorite).setOnClickListener(this);
        listView = (ListView) view.findViewById(R.id.listview_favorite);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        db = new DatabaseFavorite(getActivity());
        data = db.getAllData();
        adapter = new ShowListFavoriteAdapter(getActivity(), data);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listView.setOnItemClickListener(this);
        Log.i("onResume()---->","");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
        final String phoneNumber = data.get(position).getSDT();
        b.setTitle("Question");
        b.setMessage("Call And Message");
        b.setIcon(R.drawable.ic_action_personal);
        b.setPositiveButton("Call",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        // TODO Auto-generated method stub
                        Uri uri = Uri.parse("tel:" + phoneNumber);
                        Intent i = new Intent(Intent.ACTION_DIAL, uri);
                        startActivity(i);

                    }
                });

        b.setNegativeButton("Message",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        // TODO Auto-generatthied method stub
                        Intent sendIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:" + phoneNumber));
                        sendIntent.putExtra(Intent.EXTRA_TEXT,
                                "This is my text to send. !");
                        startActivity(Intent.createChooser(sendIntent,
                                "123"));
                    }
                });
        b.create().show();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), FavoriteActivity.class);
        startActivity(intent);
    }
}
