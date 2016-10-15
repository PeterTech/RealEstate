package com.maxitech.realestate;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class SplashScreenActivity extends BaseActivity {
    private LinearLayout ll_Body;
    @Override
    public void initial() {
        ll_Body=(LinearLayout)inflater.inflate(R.layout.splash_screen,null);
        llHeader.setVisibility(View.GONE);
        llMiddle.addView(ll_Body, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                finish();
                Intent intent		=	new Intent(SplashScreenActivity.this, LoginActivity.class);//CompetitorsListActivity
                startActivity(intent);
            }


        },2500);


    }
}
