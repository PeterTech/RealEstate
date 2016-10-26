package com.maxitech.realestate;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class DashboardActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

    private Firebase myFirebaseRef;
    private LinearLayout ll_Body;
    private Spinner sp_district, sp_city;
    private Button btn_submit;
    private ArrayAdapter<DistrictDO> arrayDisAdapter;
    private ArrayAdapter<CityDO> arrayCityAdapter;
    @Override
    public void initial() {

        showLoader("");
        getSupportActionBar().hide();
        ll_Body=(LinearLayout)inflater.inflate(R.layout.dashboard_screen,null);
        llMiddle.addView(ll_Body, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        initializeView();
        Firebase.setAndroidContext(this);
        myFirebaseRef=new Firebase("https://jaaga-e23ea.firebaseio.com/");
        getList();
        sp_district.setOnItemSelectedListener(this);
        sp_city.setOnItemSelectedListener(this);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                showLoader("");
                if(!TextUtils.isEmpty(districtCode)&& !TextUtils.isEmpty(cityCode)){


                    new Handler().postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {

                            hideLoader();
                            Intent intent= new Intent(DashboardActivity.this,VenturePropertiesActivity.class);
//                            Intent intent= new Intent(DashboardActivity.this,PropertySearchActivity.class);
                            intent.putExtra("districtCode",districtCode);
                            intent.putExtra("cityCode",cityCode);
                            startActivity(intent);
//                            finish();
                            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                        }


                    },2000);

                }else{
                    hideLoader();
                    showCoustomDialog("Please select District and City");
                }
            }
        });
    }

    private  void initializeView(){
        sp_district= (Spinner) ll_Body.findViewById(R.id.sp_district);
        sp_city= (Spinner) ll_Body.findViewById(R.id.sp_city);
        btn_submit= (Button) ll_Body.findViewById(R.id.btn_submit);

       // arrayCityAdapter = new ArrayAdapter<CityDO>(DashboardActivity.this, android.R.layout.simple_spinner_item,arrCitiesTemp);
        arrayCityAdapter = new ArrayAdapter<CityDO>(DashboardActivity.this, R.layout.spinner_item_white,arrCitiesTemp);

        arrayCityAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        sp_city.setAdapter(arrayCityAdapter);
    }
    private ArrayList<DistrictDO> arrDistricts = new ArrayList<DistrictDO>();
    private ArrayList<CityDO> arrCities = new ArrayList<CityDO>();

    private void getList(){

        myFirebaseRef.child("tblDistricts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                arrDistricts =  new ArrayList<DistrictDO>();

                DistrictDO tempdestrict = new DistrictDO();
                tempdestrict.name="Select District";
                tempdestrict.code="Select District";
                arrDistricts.add(tempdestrict);

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    DistrictDO districtDO= postSnapshot.getValue(DistrictDO.class);
                    arrDistricts.add(districtDO);
                }
               // arrayDisAdapter = new ArrayAdapter<DistrictDO>(DashboardActivity.this, android.R.layout.simple_spinner_item,arrDistricts);
                arrayDisAdapter = new ArrayAdapter<DistrictDO>(DashboardActivity.this,R.layout.spinner_item_white,arrDistricts);

                arrayDisAdapter.setDropDownViewResource
                        (android.R.layout.simple_spinner_dropdown_item);

                sp_district.setAdapter(arrayDisAdapter);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        myFirebaseRef.child("tblCity").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    CityDO cityDO = postSnapshot.getValue(CityDO.class);
                    arrCities.add(cityDO);
                }

                hideLoader();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }
    private String districtCode="",cityCode="";
    ArrayList<CityDO> arrCitiesTemp = new ArrayList<CityDO>();
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        if(parent.getId() == R.id.sp_city){
            CityDO cityDO =  arrCitiesTemp.get(position);
            cityCode = cityDO.getCitycode();
            districtCode=cityDO.getDistrictcode();
        }else if(parent.getId() == R.id.sp_district) {
            DistrictDO districtDO = arrDistricts.get(position);
            arrCitiesTemp.clear();
            if (districtDO.name.equalsIgnoreCase("All")) {
                showCoustomDialog("Please Select District");

                cityCode = "";
                districtCode = "";
            } else {

                for (CityDO cityP : arrCities) {
                    if (cityP.districtcode.equalsIgnoreCase(districtDO.code)) {
                        arrCitiesTemp.add(cityP);
                    }
                }
            }
            arrayCityAdapter.notifyDataSetChanged();
            if (arrCitiesTemp.size() > 0) {
                CityDO cityDO =  arrCitiesTemp.get(0);
                cityCode = cityDO.getCitycode();
                districtCode=cityDO.getDistrictcode();
                sp_city.setSelection(0);
            }else{

                cityCode = "";
                districtCode = "";
            }

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
