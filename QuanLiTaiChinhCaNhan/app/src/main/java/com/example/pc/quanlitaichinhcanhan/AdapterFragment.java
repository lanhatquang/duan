package com.example.pc.quanlitaichinhcanhan;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class AdapterFragment extends FragmentPagerAdapter {

    private Context context;
    private int total;
    public AdapterFragment(FragmentManager fm, Context context, int total) {
        super(fm);
        this.context = context;
        this.total = total;
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 0:
                HomePage homePage = new HomePage();
                return homePage;
            case 1:
                TimelinePage timelinePage = new TimelinePage();
                return timelinePage;
            case 2:
                OverviewPage overviewPage = new OverviewPage();
                return overviewPage;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return total;
    }
}

