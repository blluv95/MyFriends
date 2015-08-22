package com.example.baoadr01.myfriends.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.baoadr01.myfriends.R;
import com.example.baoadr01.myfriends.fragment.FragmentBoxOffice;
import com.example.baoadr01.myfriends.fragment.FragmentFavorite;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class MainActivity extends AppCompatActivity implements MaterialTabListener {

    private ViewPager mViewPager;
    private MaterialTabHost tabHost;
    MyPaperAdapter adapter;
    public static final int TAB_HITS = 0;
    public static final int TAB_UPCOMING = 1;
    public static final int TAB_COUNT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setConfigTabs();
    }



    public void setConfigTabs() {
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tabHost.setSelectedNavigationItem(position);
                showSnack(adapter.getPageTitle(position).toString());

            }
        });
        for (int i = 0; i < adapter.getCount(); i++) {
            tabHost.addTab(tabHost.newTab()
                    .setText(adapter.getPageTitle(i))
                    .setTabListener(this));
        }

    }
    public void init() {
        mViewPager = (ViewPager) findViewById(R.id.paper);
        adapter = new MyPaperAdapter(getSupportFragmentManager());
        tabHost = (MaterialTabHost) findViewById(R.id.materialTabHost);
        mViewPager.setAdapter(adapter);

    }
    public void showSnack(String string) {
        Snackbar.make(findViewById(R.id.paper), string, Snackbar.LENGTH_SHORT).setAction("My Action", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        }).show();
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
            switch (num) {
                case TAB_HITS:
                    fragment = FragmentBoxOffice.newInstance();
                    break;
                case TAB_UPCOMING:
                    fragment = new FragmentFavorite();
                    break;
            }
            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getResources().getStringArray(R.array.tabs)[position];
        }

        public Drawable getIcon(int possition) {
            return getResources().getDrawable(icons[possition]);

        }

        @Override
        public int getCount() {
            return TAB_COUNT;
        }
    }


}
