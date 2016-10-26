package com.maxitech.realestate;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

/**
 * Created by Anil.Jain on 10/26/2016.
 */

public class RealEstateApplication extends MultiDexApplication {


    @Override
    public void onCreate() {
        super.onCreate();
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
