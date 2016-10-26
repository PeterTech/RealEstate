package com.maxitech.realestate;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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

        TextView first;
       // ImageView iv;
        LinearLayout ll_myimage;
        //hjhj

        public MyViewHolder(View itemView) {
            super(itemView);
            first = (TextView) itemView.findViewById(R.id.firsttv);
          //  iv = (ImageView) itemView.findViewById(R.id.myimage);
            ll_myimage = (LinearLayout) itemView.findViewById(R.id.ll_myimage);
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
     //   holder.iv.setImageResource(user.ivid);
        holder.ll_myimage.setBackgroundResource(user.ivid);
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

}