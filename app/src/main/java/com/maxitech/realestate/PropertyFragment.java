package com.maxitech.realestate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Avyukt on 10/16/2016.
 */
public class PropertyFragment extends Fragment {
    private RecyclerView recycler_view;
    private TextView tv_areaName;
    private PropertyAdapter propertyAdapter;
    private LinearLayout llContent;
    private ArrayList<PropertyDO> arrProperties;
    public PropertyFragment() {
        // Required empty public constructor
    }
    public PropertyFragment(ArrayList<PropertyDO> arrProperties) {
        this.arrProperties = arrProperties;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        llContent = (LinearLayout) inflater.inflate(R.layout.property_fragment, container, false);
        return llContent;

    }
    private  void initializeView(){
        recycler_view= (RecyclerView)llContent.findViewById(R.id.recycler_view);
        propertyAdapter =new PropertyAdapter(new ArrayList<PropertyDO>());
        recycler_view.setAdapter(propertyAdapter);
        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.MyViewHolder> {

        private List<PropertyDO> propertyDOList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView tvVentureName,tvAddress,tvPhoneNumber;
            public ImageView ivVentureImage;

            public MyViewHolder(View view) {
                super(view);
                tvAddress = (TextView) view.findViewById(R.id.tvAddress);
                tvVentureName = (TextView) view.findViewById(R.id.tvVentureName);
                tvPhoneNumber = (TextView) view.findViewById(R.id.tvPhoneNumber);
                ivVentureImage = (ImageView) view.findViewById(R.id.ivVentureImage);
            }
        }


        public  void refresh(List<PropertyDO> propertyDOList){
            this.propertyDOList=propertyDOList;
            notifyDataSetChanged();
        }
        public PropertyAdapter(List<PropertyDO> propertyDOList) {
            this.propertyDOList = propertyDOList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.property_cell, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            PropertyDO propertyDO = propertyDOList.get(position);
            holder.tvVentureName.setText(propertyDO.getName());
            holder.tvAddress.setText(propertyDO.getAddress());
            holder.tvPhoneNumber.setText(propertyDO.getPhoneNumber());
           // holder.ivVentureImage.setText("2");
        }

        @Override
        public int getItemCount() {
            return propertyDOList.size();
        }
    }
}