package com.example.baoadr01.myfriends.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baoadr01.myfriends.R;

/**
 * Created by BaoADR01 on 8/16/2015.
 */
public class FragmentVocabulary extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.fragment_demo2, container, false);
        return view;
    }

}
