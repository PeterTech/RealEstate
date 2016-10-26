package com.maxitech.realestate;

import android.app.ActionBar;
import android.os.Bundle;
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
 * Created by Avyukt on 10/17/2016.
 */
public class PropertyDetailsActivity extends BaseActivity {
    private LinearLayout ll_Body;
    private PropertyDO propertyDO;
    private TextView tvVentureName, tv_phoneNumber, tv_location, tv_propertyType, tv_direction, tv_description;
    private ImageView img_plot, img_layout;
    private String consultantName = "";
    private ArrayList<PropertyDO> propertyDOList;
    private RecyclerView recycler_view;
    private PropertyAdapter propertyAdapter;

    @Override
    public void initial() {
        ll_Body = (LinearLayout) inflater.inflate(R.layout.details_screen, null);
        llMiddle.addView(ll_Body, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        Bundle bundle = getIntent().getExtras();
        propertyDO = (PropertyDO) bundle.get("propertyDetails");
        consultantName = (String) bundle.get("consultantName");
        propertyDOList = (ArrayList<PropertyDO>) bundle.get("propertyList");
        tvScreenTitle.setText(consultantName + "");
        initializeView();
        loadDetails();
    }

    private void loadDetails() {
        tvVentureName.setText(propertyDO.getName());
        tv_phoneNumber.setText("Phone:" + propertyDO.getPhoneNumber());
        tv_location.setText("Mancherial");
        tv_propertyType.setText(propertyDO.getPropertyType());
        tv_direction.setText("North-west");
        tv_description.setText("Owning a house is a matter of immense pride and satisfaction.A home is a canvas where we paint our dreams, let our imagination fly and experience the true meaning of independence. Twincity Group offers you an opportunity to own your 3 Bedroom Duplex Villas on beautifully landscaped residential site Uttampur.Enjoy an unforgettable suburban living in these beautifully constructed independent homes with profuse greenery,scenic locale and all the luxurious amenties.");

    }

    private void initializeView() {
        tvVentureName = (TextView) ll_Body.findViewById(R.id.tvVentureName);
        tv_phoneNumber = (TextView) ll_Body.findViewById(R.id.tv_phoneNumber);
        tv_location = (TextView) ll_Body.findViewById(R.id.tv_location);
        tv_propertyType = (TextView) ll_Body.findViewById(R.id.tv_propertyType);
        tv_direction = (TextView) ll_Body.findViewById(R.id.tv_direction);
        tv_description = (TextView) ll_Body.findViewById(R.id.tv_description);

        img_plot = (ImageView) ll_Body.findViewById(R.id.img_plot);
        img_layout = (ImageView) ll_Body.findViewById(R.id.img_layout);

        recycler_view = (RecyclerView) ll_Body.findViewById(R.id.recycler_view);
        propertyAdapter = new PropertyAdapter(new ArrayList<PropertyDO>());
        propertyAdapter.refresh(propertyDOList);
        recycler_view.setAdapter(propertyAdapter);
        recycler_view.setLayoutManager(new LinearLayoutManager(PropertyDetailsActivity.this));

    }

    public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.MyViewHolder> {


        private List<PropertyDO> propertyDOList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView tvVentureName, tvAddress, tvPhoneNumber;
            public ImageView ivVentureImage;
            public View parentView;

            public MyViewHolder(View view) {
                super(view);
                tvAddress = (TextView) view.findViewById(R.id.tvAddress);
                tvVentureName = (TextView) view.findViewById(R.id.tvVentureName);
                tvPhoneNumber = (TextView) view.findViewById(R.id.tvPhoneNumber);
                ivVentureImage = (ImageView) view.findViewById(R.id.ivVentureImage);
                parentView = view;
            }
        }


        public void refresh(List<PropertyDO> propertyDOList) {
            this.propertyDOList = propertyDOList;
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
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            PropertyDO propertyDO = propertyDOList.get(position);
            holder.tvVentureName.setText(propertyDO.getName());
            holder.tvAddress.setText(propertyDO.getAddress());
            holder.tvPhoneNumber.setText(propertyDO.getPhoneNumber());
            // holder.ivVentureImage.setText("2");
        }

        @Override
        public int getItemCount() {
            if (propertyDOList != null && propertyDOList.size() > 0)
                return propertyDOList.size();

            return 0;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
    }
}
