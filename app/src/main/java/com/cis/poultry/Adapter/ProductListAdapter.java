package com.cis.poultry.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.cis.poultry.Common.CircleTransform;
import com.cis.poultry.Models.GetVisitLogByDateResponse;
import com.cis.poultry.Models.Layers;
import com.cis.poultry.Models.poultry_settings;
import com.cis.poultry.Models.productdata;
import com.cis.poultry.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.MyViewHolder> {


    private Context context;
   // private List<GetVisitLogByDateResponse.Result.VisitLogDetail> request_List;
    List<Layers> request_List =new ArrayList<>();
    private ProductListAdapter.RequestAdapterListener listener;
    int row_index = -1;
    int selectedrow_index ;
    int hurt,died;
    DecimalFormat dff = new DecimalFormat("#,###,##0.00");
    DecimalFormat df = new DecimalFormat("#,###,##0");
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView availablebirds, sheds,morality,eggs,prod,batch,age,feed,damageseggs,remaingeggs,remaingbirds;
        public ImageView image_less,image_more;
        public Button click;
        LinearLayout sublinear,linear;

        public MyViewHolder(View view) {
            super(view);
            sheds = view.findViewById(R.id.sheds);
            availablebirds = view.findViewById(R.id.availablebirds);
            morality = view.findViewById(R.id.morality);
            eggs = view.findViewById(R.id.eggs);
            prod = view.findViewById(R.id.product);
            sublinear = view.findViewById(R.id.sublinear);
            linear = view.findViewById(R.id.linear);
            image_less =view.findViewById(R.id.image_less);
            image_more =view.findViewById(R.id.image_more);
            batch = view.findViewById(R.id.batch);
            age = view.findViewById(R.id.age);
            feed = view.findViewById(R.id.feed);
            damageseggs = view.findViewById(R.id.damageseggs);
            remaingeggs = view.findViewById(R.id.remaingeggs);
            remaingbirds = view.findViewById(R.id.remaingbirds);

//            view.setOnClickListener(new View.OnClickListener() {
//                //                @Override
//                public void onClick(View view) {
//                    int oldindex=  row_index;
//                    row_index = position;
//                    notifyItemChanged(oldindex);
//                    notifyItemChanged(position);
//
//                    sublinear.setVisibility(View.VISIBLE);
//                }
//            });
//
        }

        public void bind(Layers movie) {
            boolean expanded = movie.isExpanded();

            sublinear.setVisibility(expanded ? View.VISIBLE : View.GONE);
            batch.setText(movie.getBatchName());
            age.setText(movie.getAgeinDays()+"");
            feed.setText(movie.getFeed()+"");
            damageseggs.setText(movie.getDamagedEggs()+"");
            remaingeggs.setText(movie.getRemainingBirds()+""); remaingbirds.setText(movie.getFeed()+"");
            if( sublinear.getVisibility() == View.VISIBLE) {
                image_less.setVisibility(View.VISIBLE);
                image_more.setVisibility(View.GONE);
            }
            else {
                image_less.setVisibility(View.GONE);
                image_more.setVisibility(View.VISIBLE);
            }

        }
    }


    public ProductListAdapter(Context context,  List<Layers> request_List) {
        this.context = context;

        this.request_List = request_List;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layerslist_items, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        final Layers movie = request_List.get(position);
        if( holder.sublinear.getVisibility() == View.VISIBLE) {
            holder.image_less.setVisibility(View.VISIBLE);
            holder.image_more.setVisibility(View.GONE);
        }
        else {
            holder.image_less.setVisibility(View.GONE);
            holder.image_more.setVisibility(View.VISIBLE);
        }


        if(row_index== position)
        {
            holder.sublinear.setVisibility(View.VISIBLE);
            holder.availablebirds.setTextColor(Color.parseColor("#337ab7"));
            holder.sheds.setTextColor(Color.parseColor("#337ab7"));
            holder.eggs.setTextColor(Color.parseColor("#337ab7"));
            holder.morality.setTextColor(Color.parseColor("#337ab7"));
            holder.prod.setTextColor(Color.parseColor("#337ab7"));
            holder.linear.setBackgroundColor(Color.parseColor("#d0d9f0"));

                holder.image_less.setVisibility(View.VISIBLE);
                holder.image_more.setVisibility(View.GONE);
            holder.bind(movie);
            // holder.createdDateTextView.setVisibility(View.VISIBLE);

        }else{
            holder.sublinear.setVisibility(View.GONE);

            holder.availablebirds.setTextColor(Color.parseColor("#000000"));
            holder.sheds.setTextColor(Color.parseColor("#000000"));
            holder.eggs.setTextColor(Color.parseColor("#000000"));
            holder.morality.setTextColor(Color.parseColor("#000000"));
            holder.prod.setTextColor(Color.parseColor("#000000"));
            holder.linear.setBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.image_more.setVisibility(View.VISIBLE);
            holder.image_less.setVisibility(View.GONE);
            //  holder.createdDateTextView.setVisibility(View.GONE);
        }






     holder.availablebirds.setText(df.format(request_List.get(position).getOpeningBirds()));
        holder.sheds.setText(request_List.get(position).getShedName());
        Log.e("===",request_List.get(position).getShedName());

        if(request_List.get(position).getHurt()!=null){
             hurt = request_List.get(position).getHurt();
        }else{
             hurt = 0;
        }

        if(request_List.get(position).getDied()!=null){
            died = request_List.get(position).getDied();
        }else{
            died = 0;
        }
        Log.e("======hurt",hurt+"");
        Log.e("======died",died+"");
        if(request_List.get(position).getDied()!=null) {
            holder.morality.setText(request_List.get(position).getDied() + hurt +"");
        }
        else{
            holder.morality.setText("0");
     }
        if(request_List.get(position).getTotalEggs()!=null)
        holder.eggs.setText(df.format(request_List.get(position).getTotalEggs()));
        else{
            holder.eggs.setText("0");
        }
        if(request_List.get(position).getPercentage()!=null)
        holder.prod.setText(request_List.get(position).getPercentage()+"");
        else{
            holder.prod.setText("0.0");
        }
       // atch,age,feed,damageseggs,remaingeggs,remaingbirds
        holder.batch.setText(request_List.get(position).getBatchName());
        holder.age.setText(request_List.get(position).getAgeinWeeks()+" / "+request_List.get(position).getAgeinDays());
//        if(request_List.get(position).getPercentage()!=null)
//            holder.prod.setText(request_List.get(position).getPercentage()+"");
//        else{
//            holder.prod.setText(" ");
//        }
        if(request_List.get(position).getDamagedEggs()!=null)
            holder.damageseggs.setText(df.format(request_List.get(position).getDamagedEggs()));
        else{
            holder.damageseggs.setText("0");
        }
        if(request_List.get(position).getNumberofEggs()!=null)
            holder.remaingeggs.setText(df.format(request_List.get(position).getNumberofEggs()));
        else{
            holder.remaingeggs.setText("0");
        }
        if(request_List.get(position).getRemainingBirds()!=null)
            holder.remaingbirds.setText(df.format(request_List.get(position).getRemainingBirds()));
        else{
            holder.remaingbirds.setText("0");
        }
        holder.feed.setText(request_List.get(position).getFeed()+"");



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean expanded = movie.isExpanded();
                movie.setExpanded(!expanded);
              //  notifyItemChanged(position);
                int oldindex=  row_index;
                row_index = position;
                notifyItemChanged(oldindex);
                notifyItemChanged(position);
            }
        });



//        holder.linear.setOnClickListener(new View.OnClickListener() {
//                  @Override
//            public void onClick(View view) {
//
//
//                int oldindex=  row_index;
//              row_index = position;
//                notifyItemChanged(oldindex);
//                notifyItemChanged(position);
//
//             //   holder.sublinear.setVisibility(View.VISIBLE);
//            }
//        });
       // Picasso.with(context).load(settings.getImage()).error(R.drawable.selectlogo).transform(new CircleTransform()).into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return request_List.size();
    }


    public interface RequestAdapterListener {
        void onContactSelected(productdata request);
    }

}