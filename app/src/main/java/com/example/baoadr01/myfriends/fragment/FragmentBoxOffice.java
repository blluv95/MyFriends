package com.example.baoadr01.myfriends.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.baoadr01.myfriends.R;
import com.example.baoadr01.myfriends.Utils.MyContact;
import com.example.baoadr01.myfriends.adapter.MyContactAdapter;
import com.example.baoadr01.myfriends.db.DatabaseHandler;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentBoxOffice#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentBoxOffice extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    DatabaseHandler db;

    Spinner spnCategoy, spnLevel, spnNumber;
    Button btnReady;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public FragmentBoxOffice() {
    }


    public static FragmentBoxOffice newInstance(String param1, String param2) {
        FragmentBoxOffice fragment = new FragmentBoxOffice();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public static MyContactAdapter adapter;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_mycontact, container, false);
        listView = (ListView) view.findViewById(R.id.listview_contact);
        db = new DatabaseHandler(view.getContext());
        final List<MyContact> lst = db.getAllData();
        adapter = new MyContactAdapter(view.getContext(), lst);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
//                Toast.makeText(getActivity(), lst.get(position).getID() + "", Toast.LENGTH_SHORT).show();
//                 db.deleteData(lst.get(position).getID());

                AlertDialog.Builder b = new AlertDialog.Builder(view.getContext());
                b.setTitle("Question");
                b.setMessage("Are you sure you want to delete?");
                b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.deleteData(lst.get(position).getID());
                       readAdapter();
                    }
                });
                b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }

                });
                b.create().show();


                return false;
            }
        });





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
}
