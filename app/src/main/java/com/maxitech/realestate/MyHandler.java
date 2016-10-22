package com.maxitech.realestate;

import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Avyukt on 10/22/2016.
 */
public class MyHandler extends RecyclerView.Adapter<MyHandler.MyViewHolder> {

    List<User> mylist;

    public MyHandler(List<User> mylist) {
        this.mylist = mylist;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView first, last;
        ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);
            first = (TextView) itemView.findViewById(R.id.firsttv);
            last = (TextView) itemView.findViewById(R.id.secondtv);
            iv = (ImageView) itemView.findViewById(R.id.myimage);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View ItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new MyViewHolder(ItemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        User user = mylist.get(position);
        holder.first.setText(user.firstname);
        holder.last.setText(user.lasname);
        holder.iv.setImageResource(user.ivid);
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

}