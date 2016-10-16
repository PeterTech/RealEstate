package com.maxitech.realestate;

import android.app.ActionBar;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Avyukt on 10/16/2016.
 */
public class PropertiesListActivity extends BaseActivity {
    private LinearLayout ll_Body;
    private Spinner sp_area;
    private String districtCode = "", cityCode = "";

    @Override
    public void initial() {
        ll_Body = (LinearLayout) inflater.inflate(R.layout.venture_list_screen, null);
        llMiddle.addView(ll_Body, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        Bundle bundle = getIntent().getExtras();
        districtCode = bundle.getString("districtCode");
        cityCode = bundle.getString("cityCode");
    }

    public class VenturesAdapter extends RecyclerView.Adapter<VenturesAdapter.MyViewHolder> {

        private List<PropertyDO> propertyDOList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView tvVentureName, tvAddress, tvPhoneNumber;
            public ImageView ivVentureImage;

            public MyViewHolder(View view) {
                super(view);
                ivVentureImage = (ImageView) view.findViewById(R.id.ivVentureImage);
                tvVentureName = (TextView) view.findViewById(R.id.tvVentureName);
                tvAddress = (TextView) view.findViewById(R.id.tvAddress);
                tvPhoneNumber = (TextView) view.findViewById(R.id.tvPhoneNumber);
            }
        }


        public VenturesAdapter(List<PropertyDO> propertyDOList) {
            this.propertyDOList = propertyDOList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.address_cell, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            PropertyDO propertyDO = propertyDOList.get(position);
        }

        @Override
        public int getItemCount() {
            return propertyDOList.size();
        }
    }
}
