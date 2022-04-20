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
import com.cis.poultry.Fragments.HomeFragment;
import com.cis.poultry.Models.LoginResponse;
import com.cis.poultry.Models.getDbPaymentsPackingAndProd;
import com.cis.poultry.Models.poultry_settings;
import com.cis.poultry.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SaleListAdapter extends RecyclerView.Adapter<SaleListAdapter.MyViewHolder> {


    private Context context;
    private List<getDbPaymentsPackingAndProd.Sale> request_List;
    private PoultryListAdapter.RequestAdapterListener listener;
    int row_index = 0;

    public  double grandTotal() {
        double sum = 0;
        for(int i = 0; i < request_List.size(); i++) {

            sum = sum + request_List.get(i).getPackedBoxes();
        }
        return sum;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView packer1, packone;
//        public ImageView thumbnail;
//        public Button click;
//        LinearLayout relativeLayout;

        public MyViewHolder(View view) {
            super(view);
            packer1 = view.findViewById(R.id.packer1);
            packone = view.findViewById(R.id.packone);


        }
    }


    public SaleListAdapter(Context context, List<getDbPaymentsPackingAndProd.Sale> request_List) {
        this.context = context;
        this.listener = listener;
        this.request_List = request_List;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sale_items, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.packer1.setText(request_List.get(position).getName());
        holder.packone.setText(request_List.get(position).getPackedBoxes()+"");



//

    }

    @Override
    public int getItemCount() {
        return request_List.size();
    }


    public interface RequestAdapterListener {
        void onContactSelected(poultry_settings request);
    }
}
