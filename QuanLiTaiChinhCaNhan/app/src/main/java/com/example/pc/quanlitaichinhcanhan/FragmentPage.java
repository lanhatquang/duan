package com.example.pc.quanlitaichinhcanhan;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class FragmentPage extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_page);
        viewPager = findViewById(R.id.viewPagerid);
        tabLayout = findViewById(R.id.tabLayoutid);

        //add icon cho tablayout
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_homepagedp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_timelinedp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_overviewdp));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //tao moi adapter
        AdapterFragment adapterFragment = new AdapterFragment(getSupportFragmentManager(),this,tabLayout.getTabCount());
        viewPager.setAdapter(adapterFragment);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
