package com.maxitech.realestate;

import android.app.ActionBar;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
   // private ImageView img_plot, img_layout;
    private String consultantName = "";
    private ArrayList<PropertyDO> propertyDOList;
    private RecyclerView recycler_view;
    private PropertyAdapter propertyAdapter;
    private ViewPager plot_pager,layout_pager;
    private ViewPagerAdapter plot_adapter,layout_adapter;
   private int[] mResources = {
            R.drawable.plot,
            R.drawable.image_four,
            R.drawable.image_one,
            R.drawable.image_three,
    };


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
        plot_adapter = new ViewPagerAdapter(getSupportFragmentManager());
        plot_pager.setAdapter(plot_adapter);

        layout_adapter = new ViewPagerAdapter(getSupportFragmentManager());
        layout_pager.setAdapter(layout_adapter);

        loadDetails();
                ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        showPgerImages();
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
            }
        });
        tv_phoneNumber.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                try {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + propertyDO.getPhoneNumber()));
                    if (ActivityCompat.checkSelfPermission(PropertyDetailsActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivity(callIntent);
                } catch (ActivityNotFoundException activityException) {
                    Log.e("Calling a Phone Number", "Call failed", activityException);
                }
            }
        });

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

//        img_plot = (ImageView) ll_Body.findViewById(R.id.img_plot);
//        img_layout = (ImageView) ll_Body.findViewById(R.id.img_layout);

        plot_pager = (ViewPager) ll_Body.findViewById(R.id.plot_pager);
        layout_pager = (ViewPager) ll_Body.findViewById(R.id.layout_paager);


        recycler_view = (RecyclerView) ll_Body.findViewById(R.id.recycler_view);
        propertyAdapter = new PropertyAdapter(new ArrayList<PropertyDO>());
        propertyAdapter.refresh(propertyDOList);
        recycler_view.setAdapter(propertyAdapter);
        recycler_view.setLayoutManager(new LinearLayoutManager(PropertyDetailsActivity.this));


       /* img_plot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = ((BitmapDrawable) img_plot.getDrawable()).getBitmap();
                showImagePopUp(bitmap);

            }
        });
*/
      /*  img_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = ((BitmapDrawable) img_layout.getDrawable()).getBitmap();
                showImagePopUp(bitmap);

            }
        });*/


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
private  void showPgerImages(){
    plot_adapter.clearFragments();
    plot_adapter.addFragment(new ImageFragment(mResources[0]),"");
    plot_adapter.addFragment(new ImageFragment(mResources[1]),"");
    plot_adapter.addFragment(new ImageFragment(mResources[2]),"");
    plot_adapter.addFragment(new ImageFragment(mResources[3]),"");
    plot_adapter.notifyDataSetChanged();
    plot_pager.setAdapter(plot_adapter);

    layout_adapter.clearFragments();
    layout_adapter.addFragment(new ImageFragment(mResources[0]),"");
    layout_adapter.addFragment(new ImageFragment(mResources[1]),"");
    layout_adapter.addFragment(new ImageFragment(mResources[2]),"");
    layout_adapter.addFragment(new ImageFragment(mResources[3]),"");
    layout_adapter.notifyDataSetChanged();
    layout_adapter.notifyDataSetChanged();
    layout_pager.setAdapter(layout_adapter);
}
}
