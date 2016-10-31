package com.maxitech.realestate;

import android.app.ActionBar;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by Avyukt on 10/16/2016.
 */
public class VenturePropertiesActivity extends BaseActivity {
    private CoordinatorLayout ll_Body;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private TextView tvAreas;
    private ArrayList<PropertyDO> arrProperties = new ArrayList<PropertyDO>();
    private Firebase myFirebaseRef;
    private ArrayList<AreaDO> arrAreas = new ArrayList<AreaDO>();
    private String districtCode = "", cityCode = "";
    private int selectedPos = 0;
    private ViewPagerAdapter adapter;
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    private Criteria locationCritera;

    @Override
    public void initial() {
        ll_Body = (CoordinatorLayout) inflater.inflate(R.layout.venture_property_screen, null);
        llMiddle.addView(ll_Body, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        viewPager = (ViewPager) ll_Body.findViewById(R.id.viewpager);
        tvAreas = (TextView) ll_Body.findViewById(R.id.tvAreas);
        tabLayout = (TabLayout) ll_Body.findViewById(R.id.tabs);
        tvAreas.setTextAppearance(getApplicationContext(), R.style.customtextstyle);
        Bundle bundle = getIntent().getExtras();
        districtCode = bundle.getString("districtCode");
        cityCode = bundle.getString("cityCode");
        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://jaaga-e23ea.firebaseio.com/");

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);


        getList();
        tvAreas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAreas();
            }
        });

        locationListener = new MyLocationListener();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        locationCritera = new Criteria();
        locationCritera.setAccuracy(Criteria.ACCURACY_FINE);
        locationCritera.setAltitudeRequired(false);
        locationCritera.setBearingRequired(false);
        locationCritera.setCostAllowed(true);
        locationCritera.setPowerRequirement(Criteria.NO_REQUIREMENT);


        String providerName = locationManager.getBestProvider(locationCritera, true);
        locationManager.requestLocationUpdates(providerName, 0, 0, locationListener);
        Location location = locationManager.getLastKnownLocation(providerName);

        if(location!=null){
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }

    }

    private void getList() {
        showLoader("");
        myFirebaseRef.child("tblAreas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                arrAreas = new ArrayList<AreaDO>();
                ArrayList<String> arrstrAreas = new ArrayList<String>();
                int position = 0;
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    AreaDO areaDO = postSnapshot.getValue(AreaDO.class);
                    if (areaDO.districtcode.equalsIgnoreCase(districtCode) && areaDO.citycode.equalsIgnoreCase(cityCode)) {
                        arrAreas.add(areaDO);
                        arrstrAreas.add(areaDO.getAreaname());
                        position++;
                    }
                }
                areasList = arrstrAreas.toArray(new String[0]);
                if (arrAreas != null && arrAreas.size() > 0) {
                    getProperties();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    private void getProperties() {

        myFirebaseRef.child("tblProperties").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    ArrayList<PropertyDO> arrProperties = new ArrayList<PropertyDO>();
                    PropertyDO propertyDO = postSnapshot.getValue(PropertyDO.class);
                    if (hmProperties.containsKey(propertyDO.getPropertyType())) {
                        arrProperties = hmProperties.get(propertyDO.getPropertyType());
                    } else {
                        arrProperties = new ArrayList<PropertyDO>();
                    }

                    arrProperties.add(propertyDO);
                    hmProperties.put(propertyDO.getPropertyType(), arrProperties);
                }

                filterProperties();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

    }

    private void turnGPSOn() {
        String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

        String providerName = locationManager.getBestProvider(locationCritera, true);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(providerName, 0, 0, locationListener);
    }

    private void turnGPSOff(){
        String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

        if (ActivityCompat.checkSelfPermission(VenturePropertiesActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(VenturePropertiesActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.removeUpdates(locationListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        turnGPSOn();
    }

    @Override
    protected void onStop() {
        super.onStop();
        turnGPSOff();
    }

    private void filterProperties() {

        if (arrAreas != null && arrAreas.size() > 0) {
            AreaDO areaDO = arrAreas.get(selectedPos);
            tvAreas.setText(areaDO.getAreaname());
            ArrayList<PropertyDO> arrProperties = new ArrayList<PropertyDO>();
            if (hmProperties != null && hmProperties.size() > 0) {
                Set<String> keySet = hmProperties.keySet();
                hmFilteredProperties = new HashMap<String, ArrayList<PropertyDO>>();
                for (String propertyKey : keySet) {
                    ArrayList<PropertyDO> arrTempProperties = hmProperties.get(propertyKey);
                    for (PropertyDO propertyDO : arrTempProperties) {

                        if (propertyDO.getAreacode().equalsIgnoreCase(areaDO.areacode)) {

                            if (hmFilteredProperties.containsKey(propertyDO.getPropertyType())) {
                                arrProperties = hmFilteredProperties.get(propertyDO.getPropertyType());
                            } else {
                                arrProperties = new ArrayList<PropertyDO>();
                            }

                            arrProperties.add(propertyDO);
                            hmFilteredProperties.put(propertyDO.getPropertyType(), arrProperties);
                        }

                    }
                }
            }

        }

        adapter.clearFragments();
        adapter.addFragment(new PropertyFragment(hmFilteredProperties.get("1")), "Lands");
        adapter.addFragment(new PropertyFragment(hmFilteredProperties.get("2")), "Ventures");
        adapter.addFragment(new PropertyFragment(hmFilteredProperties.get("3")), "Houses");
        adapter.addFragment(new PropertyFragment(hmFilteredProperties.get("4")), "Rents");
        adapter.addFragment(new PropertyFragment(hmFilteredProperties.get("5")), "Others");
        adapter.notifyDataSetChanged();
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        hideLoader();

    }


    private String areasList[];

    private void showAreas() {
        AlertDialog.Builder builder = new AlertDialog.Builder(VenturePropertiesActivity.this);
        builder.setTitle("Select Area");
        String positiveText = getString(android.R.string.ok);
        builder.setCancelable(false);
        builder.setSingleChoiceItems(areasList, selectedPos, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedPos = which;
            }
        });

        builder.setPositiveButton("Choose", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                filterProperties();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private HashMap<String, ArrayList<PropertyDO>> hmProperties = new HashMap<String, ArrayList<PropertyDO>>();
    private HashMap<String, ArrayList<PropertyDO>> hmFilteredProperties = new HashMap<String, ArrayList<PropertyDO>>();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
    }

    String TAG = "MyLocationListener";
    public double latitude, longitude;

    private class MyLocationListener implements LocationListener {

        public void onLocationChanged(Location location) {
            if(location!=null){

                latitude = location.getLatitude();
                longitude = location.getLongitude();
            }

            Log.v(TAG, "IN ON LOCATION CHANGE");


            if (ActivityCompat.checkSelfPermission(VenturePropertiesActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(VenturePropertiesActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.removeUpdates(this);
        }

        public void onStatusChanged(String s, int i, Bundle bundle) {
            Log.v(TAG, "Status changed: " + s);
        }

        public void onProviderEnabled(String s) {
            Log.e(TAG, "PROVIDER DISABLED: " + s);
        }

        public void onProviderDisabled(String s) {
            Log.e(TAG, "PROVIDER DISABLED: " + s);
        }
    }
}
