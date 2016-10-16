package com.maxitech.realestate;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

/**
 * Created by Avyukt on 10/16/2016.
 */
public class VenturePropertiesActivity extends BaseActivity {
    private CoordinatorLayout ll_Body;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;
  //  private String districtCode="",cityCode="";

    @Override
    public void initial() {
        ll_Body=(CoordinatorLayout)inflater.inflate(R.layout.venture_screen,null);
        llMiddle.addView(ll_Body, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
       /* Bundle bundle = getIntent().getExtras();
        districtCode=bundle.getString("districtCode");
        cityCode=bundle.getString("cityCode");
*/
        viewPager = (ViewPager)ll_Body.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) ll_Body.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentOne(), "ONE");
        adapter.addFragment(new FragmentOne(), "TWO");
        adapter.addFragment(new FragmentOne(), "THREE");
        adapter.addFragment(new FragmentOne(), "FOUR");
        viewPager.setAdapter(adapter);
    }
}
