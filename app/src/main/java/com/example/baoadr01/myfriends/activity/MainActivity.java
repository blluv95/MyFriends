package com.example.baoadr01.myfriends.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.baoadr01.myfriends.R;
import com.example.baoadr01.myfriends.fragment.FragmentBoxOffice;
import com.example.baoadr01.myfriends.fragment.FragmentVocabulary;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class MainActivity extends AppCompatActivity implements MaterialTabListener {

    Toolbar toolbar;
    private ViewPager mViewPager;
    private MaterialTabHost tabHost;
    EditText editText;

    public static final int TAB_HITS = 0;
    public static final int TAB_UPCOMING = 1;
    public static final int TAB_COUNT = 2;
    EditText editText_search_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=(Toolbar) findViewById(R.id.app_bar);
        mViewPager = (ViewPager) findViewById(R.id.paper);
        setSupportActionBar(toolbar);
        getSupportActionBar().setCustomView(R.layout.custom_actionbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        MyPaperAdapter adapter = new MyPaperAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(adapter);
        tabHost = (MaterialTabHost) findViewById(R.id.materialTabHost);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                tabHost.setSelectedNavigationItem(position);

            }
        });

        for (int i = 0; i < adapter.getCount(); i++) {
            tabHost.addTab(tabHost.newTab()
                    .setText(adapter.getPageTitle(i))
//                    .setIcon(adapter.getIcon(i))
                    .setTabListener(this));
        }


         editText = (EditText) findViewById(R.id.edittext_result_search);
//        editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//                FragmentBoxOffice.adapter.getFilter().filter(s.toString());
//            }
//        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id==R.id.action_add){
            Intent intent=new Intent(getApplication(),AddFriendsActivity.class);
            startActivity(intent);
            return true;
        }if(id==R.id.edittext_result_search){

        }




        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(MaterialTab materialTab) {
        mViewPager.setCurrentItem(materialTab.getPosition());

    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }


    class MyPaperAdapter extends FragmentStatePagerAdapter {
        int icons[] = {R.drawable.ic_action_home, R.drawable.ic_action_personal};
        String[] tabs = getResources().getStringArray(R.array.tabs);

        public MyPaperAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int num) {
            Fragment fragment = null;
//            L.m("getItem called for " + num);
            switch (num) {
                case TAB_HITS:
                    fragment = FragmentBoxOffice.newInstance("", "");
                    break;
                case TAB_UPCOMING:
                    fragment = new FragmentVocabulary();
                    break;
            }
            return fragment;
        }

            @Override
        public CharSequence getPageTitle(int position) {
            return getResources().getStringArray(R.array.tabs)[position];
        }
        public Drawable getIcon(int possition){
            return getResources().getDrawable(icons[possition]);

        }
        @Override
        public int getCount() {
            return TAB_COUNT;
        }
    }





}
