package com.maxitech.realestate;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Avyukt on 10/22/2016.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Tab1 one = new Tab1();
                return one;
            case 1:
                Tab1 tab2 = new Tab1();
                return tab2;
            case 2:
                Tab1 tab3 = new Tab1();
                return tab3;

        }
        return null;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
