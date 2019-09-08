package com.example.seoulsharespaceproject.Search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toolbar;

import com.example.seoulsharespaceproject.R;
import com.google.android.material.tabs.TabLayout;

public class SearchInfoActivity extends AppCompatActivity {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("자세히"));
        tabLayout.addTab(tabLayout.newTab().setText("가는법"));
        tabLayout.addTab(tabLayout.newTab().setText("후기"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager =(ViewPager) findViewById(R.id.pager);
        SearchInfoPageAdapter pageAdapter = new SearchInfoPageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
