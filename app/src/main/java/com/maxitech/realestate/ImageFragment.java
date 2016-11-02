package com.maxitech.realestate;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Avyukt on 11/1/2016.
 */
public class ImageFragment extends Fragment {
    private LinearLayout llContent;
    private ImageView img_plot;
    private int imageResouce;

    public ImageFragment() {
        super();
    }

    @SuppressLint("ValidFragment")
    public ImageFragment(int imageResouce) {
        this.imageResouce = imageResouce;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        llContent = (LinearLayout) inflater.inflate(R.layout.pager_item, container, false);
        img_plot = (ImageView) llContent.findViewById(R.id.img_plot);
        img_plot.setImageResource(imageResouce);

        img_plot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = ((BitmapDrawable) img_plot.getDrawable()).getBitmap();
                ((BaseActivity)getActivity()).showImagePopUp(bitmap);

            }
        });
        return llContent;
    }

}
