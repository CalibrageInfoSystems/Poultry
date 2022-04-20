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
import com.cis.poultry.Fragments.ProductionFragment;
import com.cis.poultry.Models.LoginResponse;
import com.cis.poultry.Models.poultry_settings;
import com.cis.poultry.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PoultryListAdapterP extends RecyclerView.Adapter<PoultryListAdapterP.MyViewHolder> {


    private Context context;
    private List<LoginResponse.Result.UserFarm> request_List;
    private PoultryListAdapter.RequestAdapterListener listener;
    int row_index = 0;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, phone;
        public ImageView thumbnail;
        public Button click;
        LinearLayout relativeLayout;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.textView);
            thumbnail = view.findViewById(R.id.imageView);
            relativeLayout = view.findViewById(R.id.relativeLayout);

        }
    }


    public PoultryListAdapterP(Context context, List<LoginResponse.Result.UserFarm> request_List) {
        this.context = context;
        this.listener = listener;
        this.request_List = request_List;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.poultry_items, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.name.setText(request_List.get(position).getFarmName());
        if (ProductionFragment.Farm_Id == 2) {
            row_index = 1;
        } else {
            row_index = 0;
        }
        if (row_index == position) {
            holder.relativeLayout.setBackgroundResource(R.drawable.blue_bg);
            holder.name.setTextColor(Color.parseColor("#FFFFFF"));
            Picasso.with(context).load(R.drawable.egg_icon_white).error(R.drawable.egg_icon_white).transform(new CircleTransform()).into(holder.thumbnail);


        } else {
            holder.relativeLayout.setBackgroundResource(R.drawable.white_bg);
            holder.name.setTextColor(context.getColor(R.color.blue));
            Picasso.with(context).load(R.drawable.egg_icon).error(R.drawable.egg_icon).transform(new CircleTransform()).into(holder.thumbnail);

        }


//

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                int oldindex = row_index;
                row_index = position;
                notifyItemChanged(oldindex);
                notifyItemChanged(position);

//                try {
//                    PoultryListAdapter.this.finalize();
//                } catch (Throwable throwable) {
//                    throwable.printStackTrace();
//                }
                //two text views defined on top of the viewHolder

                Bundle bundle = new Bundle();
                bundle.putInt("FARMid", request_List.get(position).getFarmId());

                ProductionFragment bookFragment = new ProductionFragment();
                bookFragment.setArguments(bundle);

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, bookFragment).addToBackStack(null).commit();

//                Intent intent = new Intent(context, HomeFragment);
//                intent.putExtra("FARMid", request_List.get(position).getFarmId());
//                context.startActivity(intent);
            }

        });

        //  Picasso.with(context).load(settings.getImage()).error(R.drawable.selectlogo).transform(new CircleTransform()).into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return request_List.size();
    }


}
