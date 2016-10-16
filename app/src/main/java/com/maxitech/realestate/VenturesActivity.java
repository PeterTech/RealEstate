package com.maxitech.realestate;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Avyukt on 10/16/2016.
 */
public class VenturesActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {
    private LinearLayout ll_Body;
    private Spinner sp_area;
    private String districtCode="",cityCode="",areaCode="";
    private RecyclerView recycler_view;
    private Firebase myFirebaseRef;
    private ArrayAdapter<AreaDO> arrayAreaAdapter;
    private ArrayList<AreaDO> arrAreas = new ArrayList<AreaDO>();
    private ArrayList<ConsultantDO> arrConsultants = new ArrayList<ConsultantDO>();
    @Override
    public void initial() {
        ll_Body=(LinearLayout)inflater.inflate(R.layout.venture_list_screen,null);
        llMiddle.addView(ll_Body, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        Bundle bundle = getIntent().getExtras();
        districtCode=bundle.getString("districtCode");
        cityCode=bundle.getString("cityCode");
        initializeView();
        Firebase.setAndroidContext(this);
        myFirebaseRef=new Firebase("https://rlestate-e2700.firebaseio.com/");
        getList();
        sp_area.setOnItemSelectedListener(this);
    }
    private void getList(){

        myFirebaseRef.child("tblAreas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                arrAreas =  new ArrayList<AreaDO>();

                AreaDO tempArea = new AreaDO();
                tempArea.areaname="Select Area";
                tempArea.areacode="Select code";
                arrAreas.add(tempArea);

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    AreaDO areaDO= postSnapshot.getValue(AreaDO.class);
                    if(areaDO.districtcode.equalsIgnoreCase(districtCode)&& areaDO.citycode.equalsIgnoreCase(cityCode)) {
                        arrAreas.add(areaDO);
                    }
                }
                arrayAreaAdapter = new ArrayAdapter<AreaDO>
                        (VenturesActivity.this, android.R.layout.simple_spinner_item,arrAreas);

                arrayAreaAdapter.setDropDownViewResource
                        (android.R.layout.simple_spinner_dropdown_item);

                sp_area.setAdapter(arrayAreaAdapter);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        myFirebaseRef.child("tblConsultants").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    ConsultantDO consultantDO = postSnapshot.getValue(ConsultantDO.class);
                    arrConsultants.add(consultantDO);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }
    private  void initializeView(){
        sp_area= (Spinner) ll_Body.findViewById(R.id.sp_area);
        //recycler_view= (RecyclerView) ll_Body.findViewById(R.id.recycler_view);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

        AreaDO areaDO =  arrAreas.get(position);
        areaCode=areaDO.getAreacode();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
