package com.maxitech.realestate;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Avyukt on 10/16/2016.
 */
public class VenturePropertiesActivity extends BaseActivity {
    private CoordinatorLayout ll_Body;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private HashMap<String,ArrayList<PropertyDO>> hmProperties = new HashMap<String,ArrayList<PropertyDO>>();
    private ArrayList<PropertyDO> arrProperties = new ArrayList<PropertyDO>();
  //  private String districtCode="",cityCode="";

    @Override
    public void initial() {
        ll_Body=(CoordinatorLayout)inflater.inflate(R.layout.venture_property_screen,null);
        llMiddle.addView(ll_Body, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        viewPager = (ViewPager)ll_Body.findViewById(R.id.viewpager);

        Bundle bundle=getIntent().getExtras();
        arrProperties= (ArrayList<PropertyDO>) bundle.get("ventureDetails");
        tabLayout = (TabLayout) ll_Body.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        ArrayList<PropertyDO> arrDetails;
        for(PropertyDO propertyDO : arrProperties){
            if(hmProperties.containsKey(propertyDO.getPropertyType())){
                arrDetails = hmProperties.get(propertyDO.getPropertyType());
                arrDetails.add(propertyDO);
            }
            else {
                arrDetails= new ArrayList<PropertyDO>();
                arrDetails.add(propertyDO);
                hmProperties.put(propertyDO.getPropertyType(),arrDetails);
            }
        }

        setupViewPager (viewPager);
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PropertyFragment(hmProperties.get(1)), "Lands");
        adapter.addFragment(new PropertyFragment(hmProperties.get(2)), "Ventures");
        adapter.addFragment(new PropertyFragment(hmProperties.get(3)), "Houses");
        adapter.addFragment(new PropertyFragment(hmProperties.get(4)), "Rents");
        viewPager.setAdapter(adapter);
    }
}
