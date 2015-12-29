/*
 * Copyright (c) 2015.
 *
 * 个人信息 版权所有
 *
 * LIANG JIAN WEI
 */

package com.liangjianwei.customproject.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;

import com.liangjianwei.customproject.Base.BaseActivity;
import com.liangjianwei.customproject.R;
import com.liangjianwei.customproject.fragment.ListViewFragment;
import com.liangjianwei.customproject.fragment.OtherFragment;

public class ViewPagerActivity extends BaseActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;

    private String[] mTabTitles = new String[]{"测试listView", "其他"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_view_pager);

        tabLayout = (TabLayout) findViewById(R.id.id_tab_layout);
        viewPager = (ViewPager) findViewById(R.id.id_tab_viewpager);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 1:
                        return new OtherFragment();
                }
                return new ListViewFragment();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTabTitles[position];
            }

            @Override
            public int getCount() {
                return mTabTitles.length;
            }
        });


        tabLayout.setupWithViewPager(viewPager);
    }

}
