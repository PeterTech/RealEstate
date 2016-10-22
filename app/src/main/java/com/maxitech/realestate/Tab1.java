package com.maxitech.realestate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Avyukt on 10/22/2016.
 */
public class Tab1 extends Fragment {

    RecyclerView rview;
    View view;
    List<User> mylist=new ArrayList<>();
    MyHandler myHandler;
    public Tab1(){
        Log.e("In Tab1","Tab created");
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.customlayout,container,false);
        rview=(RecyclerView)view.findViewById(R.id.myrview);
        loaddata();
        myHandler=new MyHandler(mylist);
        RecyclerView.LayoutManager mLayoutManager=new GridLayoutManager(view.getContext(),1,GridLayoutManager.VERTICAL,false);
        rview.setAdapter(myHandler);
        rview.setHasFixedSize(true);
        rview.setFocusable(true);
        rview.setHasFixedSize(true);
        rview.setLayoutManager(mLayoutManager);
        return view;
    }
    public void loaddata(){
        User one=new User("Manoj Kumar","Lingampalli",R.drawable.image_four);
        User two=new User("Mallikarjuna","Kurva",R.drawable.image_three);
        User three=new User("Abinash","Barla",R.drawable.image_one);
        User four=new User("Manoj Kumar","Lingampalli",R.drawable.image_four);
        User five=new User("Mallikarjuna","Kurva",R.drawable.image_three);
        User six=new User("Abinash","Barla",R.drawable.image_one);
        mylist.add(one);
        mylist.add(two);
        mylist.add(three);
        mylist.add(four);
        mylist.add(five);
        mylist.add(six);
    }
}

