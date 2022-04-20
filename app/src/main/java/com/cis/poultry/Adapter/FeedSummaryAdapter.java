package com.cis.poultry.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.cis.poultry.Common.CircleTransform;
import com.cis.poultry.Fragments.SaleFragment;
import com.cis.poultry.Models.LoginResponse;
import com.cis.poultry.Models.dueamountlist;
import com.cis.poultry.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class FeedSummaryAdapter extends RecyclerView.Adapter<FeedSummaryAdapter.MyViewHolder> {


    private Context context;
    private List<dueamountlist.ListResult> request_List;
    DecimalFormat df = new DecimalFormat("##,##,##0");

    public double grandTotal() {
        double sum = 0;
        for(int i = 0; i < request_List.size(); i++) {

            sum = sum + request_List.get(i).getTotalAmount();
        }
        return sum;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, amount;
        public ImageView thumbnail;
        public Button click;
        LinearLayout relativeLayout;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.feed);
            amount = view.findViewById(R.id.amount);


        }
    }


    public FeedSummaryAdapter(Context context, List<dueamountlist.ListResult> request_List) {
        this.context = context;

        this.request_List = request_List;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_items, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.name.setText(request_List.get(position).getName());
        holder.amount.setText(df.format(request_List.get(position).getTotalAmount()));


        //  Picasso.with(context).load(settings.getImage()).error(R.drawable.selectlogo).transform(new CircleTransform()).into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return request_List.size();
    }


}
