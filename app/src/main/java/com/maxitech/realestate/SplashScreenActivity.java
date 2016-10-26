package com.maxitech.realestate;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Handler;
import android.widget.LinearLayout;

public class SplashScreenActivity extends BaseActivity {
    private LinearLayout ll_Body;
    @Override
    public void initial() {
        getSupportActionBar().hide();
        ll_Body=(LinearLayout)inflater.inflate(R.layout.splash_screen,null);
        llMiddle.addView(ll_Body, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent intent		=	new Intent(SplashScreenActivity.this, DashboardActivity.class);//CompetitorsListActivity
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
            }


        },2500);


    }
}
