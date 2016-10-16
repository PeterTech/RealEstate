package com.maxitech.realestate;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Avyukt on 10/16/2016.
 */
public class VenturesActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {
    private LinearLayout ll_Body;
    private Spinner sp_area;
    private String districtCode="",cityCode="";
    private RecyclerView recycler_view;
    private Firebase myFirebaseRef;
    private ArrayAdapter<AreaDO> arrayAreaAdapter;
    private ConsultantAdapter consultantAdapter;
    private ArrayList<AreaDO> arrAreas = new ArrayList<AreaDO>();
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

        myFirebaseRef.child("tblProperties").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    ArrayList<PropertyDO> arrProperties = new ArrayList<PropertyDO>();
                    PropertyDO propertyDO = postSnapshot.getValue(PropertyDO.class);
                    if(hmProperties.containsKey(propertyDO.getConsultantid())){
                        arrProperties= hmProperties.get(propertyDO.getConsultantid());
                    }
                    arrProperties.add(propertyDO);
                    hmProperties.put(propertyDO.getConsultantid(),arrProperties);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    private ArrayList<ConsultantDO> arrConsultants = new ArrayList<ConsultantDO>();
    private HashMap<String,ArrayList<PropertyDO>> hmProperties = new HashMap<String,ArrayList<PropertyDO>>();
    private  void initializeView(){
        sp_area= (Spinner) ll_Body.findViewById(R.id.sp_area);
        recycler_view= (RecyclerView) ll_Body.findViewById(R.id.recycler_view);
        consultantAdapter =new ConsultantAdapter(new ArrayList<ConsultantDO>());
        recycler_view.setAdapter(consultantAdapter);
        recycler_view.setLayoutManager(new LinearLayoutManager(VenturesActivity.this));
        recycler_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

        AreaDO areaDO =  arrAreas.get(position);
        String areaCode=areaDO.getAreacode();
        ArrayList<ConsultantDO> arrConsult = new ArrayList<ConsultantDO>();
        for(ConsultantDO consultantDO : arrConsultants){
            if(consultantDO.areacode.equalsIgnoreCase(areaCode))
                 arrConsult.add(consultantDO);
        }
        consultantAdapter.refresh(arrConsult);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public class ConsultantAdapter extends RecyclerView.Adapter<ConsultantAdapter.MyViewHolder> {

        private List<ConsultantDO> consultantDOList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView tv_ventureName,tv_count;
            public View parentView;
            public MyViewHolder(View view) {
                super(view);
                tv_count = (TextView) view.findViewById(R.id.tv_count);
                tv_ventureName = (TextView) view.findViewById(R.id.tv_ventureName);
                parentView = view;
            }
        }


        public  void refresh(List<ConsultantDO> consultantDOList){
            this.consultantDOList=consultantDOList;
            notifyDataSetChanged();
        }
        public ConsultantAdapter(List<ConsultantDO> propertyDOList) {
            this.consultantDOList = propertyDOList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.ventlurelist_cell, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            ConsultantDO consultantDO = consultantDOList.get(position);
            holder.tv_ventureName.setText(consultantDO.getName());
            if(hmProperties.containsKey(consultantDO.getCode())){
                holder.tv_count.setVisibility(View.VISIBLE);
                holder.tv_count.setText(hmProperties.get(consultantDO.getCode()).size()+"");
            }else{
                holder.tv_count.setVisibility(View.GONE);
            }

            holder.parentView.setTag(consultantDO.getCode());
            holder.parentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(holder.tv_count.getVisibility() == View.VISIBLE){
                        Intent intent = new Intent(VenturesActivity.this,VenturePropertiesActivity.class);
                        intent.putExtra("ventureDetails",hmProperties.get(v.getTag()));
                        startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return consultantDOList.size();
        }
    }

}
