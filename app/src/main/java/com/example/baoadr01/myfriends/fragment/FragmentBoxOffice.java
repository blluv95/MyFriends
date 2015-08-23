package com.example.baoadr01.myfriends.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.baoadr01.myfriends.R;
import com.example.baoadr01.myfriends.Utils.MyContact;
import com.example.baoadr01.myfriends.activity.AddFriendsActivity;
import com.example.baoadr01.myfriends.activity.InformationActivity;
import com.example.baoadr01.myfriends.adapter.MyContactAdapter;
import com.example.baoadr01.myfriends.db.DatabaseFavorite;
import com.example.baoadr01.myfriends.db.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;


public class FragmentBoxOffice extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener, Filterable {

    public static final String KEY_ID = "id_";
    public static final String KEY_NAME = "name";
    public static final String KEY_SDT = "sdt";
    public static final String KEY_AVATAR = "avatar";
    public static final String KEY_INTENT = "intent";
    public static final String KEY_CATEGORY = "categoy_";

    EditText edit_search;
    ImageView imageView_add_contact;
    DatabaseHandler db;
    DatabaseFavorite db1;
    MyContactAdapter adapter;
    ListView listView;
    List<MyContact> lst;

    public FragmentBoxOffice() {
    }

    public static FragmentBoxOffice newInstance() {
        FragmentBoxOffice fragment = new FragmentBoxOffice();
        return fragment;
    }


    public void searchContact() {


        edit_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("CharSequence s--->",s.toString());
                Log.i("start--->",start+"");
                Log.i("before s--->",before+"");
                Log.i("count s--->",count+"");

            }

            @Override
            public void afterTextChanged(Editable s) {
                getFilter().filter(s.toString());
                Log.i("afterTextChanged--->", s.toString());

            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_mycontact, container, false);
        listView = (ListView) view.findViewById(R.id.listview_contact);
        edit_search = (EditText) view.findViewById(R.id.edittext_result_search);
        imageView_add_contact = (ImageView) view.findViewById(R.id.image_add_contact);
        imageView_add_contact.setOnClickListener(this);
        searchContact();
        db = new DatabaseHandler(view.getContext());
        db1 = new DatabaseFavorite(view.getContext());
        lst = db.getAllData();
        adapter = new MyContactAdapter(view.getContext(), lst);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        listView.setOnItemClickListener(this);
        return view;
    }


    public void readAdapter() {
        List<MyContact> lst = db.getAllData();
        adapter = new MyContactAdapter(getActivity(), lst);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        readAdapter();
        Log.i("onResume()", "--->");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), InformationActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_ID, lst.get(position).getID());
        bundle.putString(KEY_NAME, lst.get(position).getNAME());
        bundle.putString(KEY_SDT, lst.get(position).getSDT());
        bundle.putByteArray(KEY_AVATAR, lst.get(position).getIMAGE());
        bundle.putString(KEY_CATEGORY, lst.get(position).getCATEGORY());
        intent.putExtra(KEY_INTENT, bundle);
        startActivity(intent);
        Log.i("onItemClick--->", position + "");
        readAdapter();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), AddFriendsActivity.class);
        startActivity(intent);
    }


    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<MyContact> resultData = new ArrayList<MyContact>();
            for (int i = 0; i < lst.size(); i++) {
                if (lst.get(i).getNAME().toString().contains(constraint)) {
                    resultData.add(lst.get(i));
                }
            }
            FilterResults results = new FilterResults();
            results.values = resultData;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filterData = (List<MyContact>) results.values;
            adapter = new MyContactAdapter(getActivity(), filterData);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            Log.i("publishResults()--->", filterData.size() + "");
        }
    };
    List<MyContact> filterData=new ArrayList<MyContact>();
}
