package com.maxitech.realestate;

import android.content.Intent;
import android.net.Uri;
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
    private TextView tvConsultantName;
    private boolean isLike=false;

    public PropertyFragment(ArrayList<PropertyDO> propertyDOs) {
        this.arrProperties = propertyDOs;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        llContent = (LinearLayout) inflater.inflate(R.layout.property_fragment, container, false);
        initializeView();
        return llContent;

    }
    private  void initializeView(){
        tvConsultantName = (TextView)llContent.findViewById(R.id.tvConsultantName);
        tvConsultantName.setVisibility(View.GONE);
        recycler_view= (RecyclerView)llContent.findViewById(R.id.recycler_view);
        propertyAdapter =new PropertyAdapter(new ArrayList<PropertyDO>());
        propertyAdapter.refresh(arrProperties);
        recycler_view.setAdapter(propertyAdapter);
        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.MyViewHolder> {

        private List<PropertyDO> propertyDOList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView tvVentureName,tvAddress,tvPhoneNumber;
            public ImageView ivVentureImage,ivMapIcon,ivLike;
            public View parentView;
            public MyViewHolder(View view) {
                super(view);
                tvAddress = (TextView) view.findViewById(R.id.tvAddress);
                tvVentureName = (TextView) view.findViewById(R.id.tvVentureName);
                tvPhoneNumber = (TextView) view.findViewById(R.id.tvPhoneNumber);
                ivVentureImage = (ImageView) view.findViewById(R.id.ivVentureImage);
                ivMapIcon = (ImageView) view.findViewById(R.id.ivMapIcon);
                ivLike = (ImageView) view.findViewById(R.id.ivLike);
                parentView=view;
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
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            PropertyDO propertyDO = propertyDOList.get(position);
            holder.tvVentureName.setText(propertyDO.getName());
            holder.tvAddress.setText(propertyDO.getAddress());
            holder.tvPhoneNumber.setText("Phone:"+propertyDO.getPhoneNumber());

            if(position == 0)
                 holder.ivVentureImage.setImageResource(R.drawable.home1);

            else if(position == 1)
                holder.ivVentureImage.setImageResource(R.drawable.home2);

            else if(position == 2)
                holder.ivVentureImage.setImageResource(R.drawable.home3);

            holder.ivLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isLike=!isLike;
                    if(isLike)
                        holder.ivLike.setImageResource(R.drawable.like);
                    else
                        holder.ivLike.setImageResource(R.drawable.unlike);
                }
            });
            holder.parentView.setTag(propertyDO);
            holder.parentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<PropertyDO> tempPropertyList=new ArrayList<PropertyDO>();
                    PropertyDO property = (PropertyDO) v.getTag();
                    for(PropertyDO propertydo:arrProperties){
                        if(property.getPropertyid()!=propertydo.getPropertyid()){
                            tempPropertyList.add(propertydo);
                        }
                    }
                        Intent intent = new Intent(getActivity(),PropertyDetailsActivity.class);
                        intent.putExtra("propertyDetails",property);
                        intent.putExtra("consultantName",property.getName());
                        intent.putExtra("propertyList",tempPropertyList);
                        startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                }
            });

            holder.ivMapIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    double latitude1=17.4707515,longitude1=78.3366296,latitude2= 17.4477532,longitude2=78.3677646;
                    String uri = "http://maps.google.com/maps?f=d&hl=en&saddr="+((VenturePropertiesActivity)getActivity()).latitude
                            +","+((VenturePropertiesActivity)getActivity()).longitude+"&daddr="+latitude2+","+longitude2;
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
                    startActivity(Intent.createChooser(intent, "Select an application"));

                }
            });
        }

        @Override
        public int getItemCount() {
            if(propertyDOList!=null && propertyDOList.size()>0)
             return propertyDOList.size();

            return  0;
        }
    }
}