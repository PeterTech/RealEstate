package com.maxitech.realestate;

import android.app.ActionBar;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Spinner;

/**
 * Created by Avyukt on 10/16/2016.
 */
public class VenturesActivity extends BaseActivity {
    private LinearLayout ll_Body;
    private Spinner sp_area;
    private String districtCode="",cityCode="";
    @Override
    public void initial() {
        ll_Body=(LinearLayout)inflater.inflate(R.layout.venture_list_screen,null);
        llMiddle.addView(ll_Body, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        Bundle bundle = getIntent().getExtras();
        districtCode=bundle.getString("districtCode");
        cityCode=bundle.getString("cityCode");

    }
}
