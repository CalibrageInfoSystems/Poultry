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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.cis.poultry.Models.GetVisitLogByDateResponse;

import com.cis.poultry.Models.Layers;
import com.cis.poultry.Models.productdata;
import com.cis.poultry.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ProducttestListAdapter extends RecyclerView.Adapter<ProducttestListAdapter.MyViewHolder> {


    private Context context;
    private List<GetVisitLogByDateResponse.Result.ShedType> request_List;
    private List<GetVisitLogByDateResponse.Result.VisitLogDetail> layerslist;
    private ProductListAdapter.RequestAdapterListener listener;
    int row_index = -1;
    int hurt,died;
    DecimalFormat df = new DecimalFormat("#,###,##0");
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_item_title, noData,morality,eggs,prod,batch,age,feed,damageseggs,remaingeggs,remaingbirds;
        public ImageView image_less,image_more;
        public Button click;
        LinearLayout sublinear,linear;
        private RecyclerView rvSubItem;

        public MyViewHolder(View view) {
            super(view);
          noData = view.findViewById(R.id.noData);
            tv_item_title = view.findViewById(R.id.tv_item_title);
            rvSubItem = itemView.findViewById(R.id.rv_sub_item);
          rvSubItem.setVisibility(View.GONE);
          noData.setVisibility(View.VISIBLE);

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
    }


    public ProducttestListAdapter(Context context, List<GetVisitLogByDateResponse.Result.ShedType> request_List, List<GetVisitLogByDateResponse.Result.VisitLogDetail> layers_list) {
        this.context = context;

        this.request_List = request_List;
        this.layerslist = layers_list;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layerslisttest_items, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        //   final productdata settings = request_List.get(position);

        Log.e("layers_list===size90",layerslist.size()+"");
       holder.tv_item_title.setText(request_List.get(position).getShedType());

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.rvSubItem.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        if(layerslist.size()== 0){
            holder.rvSubItem.setVisibility(View.GONE);
            holder.noData.setVisibility(View.VISIBLE);
        }
        else{
            holder.rvSubItem.setVisibility(View.VISIBLE);
            holder.noData.setVisibility(View.GONE);
        }
        holder.rvSubItem.setVisibility(View.GONE);
        holder.noData.setVisibility(View.VISIBLE);
        List<GetVisitLogByDateResponse.Result.VisitLogDetail> layerdata =new ArrayList<>();
      List<Layers> layerlist =new ArrayList<>();
        for (GetVisitLogByDateResponse.Result.VisitLogDetail item:layerslist
        ) {


//            (Integer id, Integer shedId, String shedName, Integer farmId, String farmName, String batchName, Integer batchId, Integer ageinWeeks, Integer ageinDays, Integer openingBirds, Integer died, Integer hurt, Integer remainingBirds, Integer feed, Object damagedEggs, Object numberofEggs, Object totalEggs, Object percentage, String statusType, Integer statusTypeId, Integer createdByUserId, String createdDate, Integer updatedByUserId, String updatedDate, Integer shedTypeId, String shedType, Object remarks) {
//                this.id = id;
            if (item.getShedTypeId() == 4 && request_List.get(position).getShedTypeId()==4) {

                layerlist.add(new Layers(item.getId(), item.getShedId(), item.getShedName(), item.getFarmId(), item.getFarmName(), item.getBatchName(), item.getBatchId(), item.getAgeinWeeks(),
                        item.getAgeinDays(), item.getOpeningBirds(), item.getDied(), item.getHurt(), item.getRemainingBirds(), item.getFeed(), item.getDamagedEggs(), item.getNumberofEggs(), item.getTotalEggs(),
                        item.getPercentage(), item.getStatusType(), item.getStatusTypeId(), item.getCreatedByUserId(), item.getCreatedDate(), item.getUpdatedByUserId(), item.getUpdatedDate(), item.getShedTypeId(), item.getShedType(), item.getRemarks()));

                Log.e("====108", layerlist.size() + "");
                if (layerlist.size() != 0) {
                    holder.rvSubItem.setVisibility(View.VISIBLE);
                    holder.noData.setVisibility(View.GONE);
                    ProductListAdapter subItemAdapter = new ProductListAdapter(context, layerlist);
                    //  recyclerView.setAdapter(adapter);
                    ((SimpleItemAnimator)   holder.rvSubItem.getItemAnimator()).setSupportsChangeAnimations(false);
                    holder.rvSubItem.setLayoutManager(layoutManager);
                    holder.rvSubItem.setAdapter(subItemAdapter);
                    holder.rvSubItem.setRecycledViewPool(viewPool);
                }

            }


//
            else if (item.getShedTypeId() == 3 && request_List.get(position).getShedTypeId()==3) {
                holder.rvSubItem.setVisibility(View.VISIBLE);
                holder.noData.setVisibility(View.GONE);
                layerlist.add(new Layers(item.getId(), item.getShedId(), item.getShedName(), item.getFarmId(),item.getFarmName(),item.getBatchName(),item.getBatchId(),item.getAgeinWeeks(),
                        item.getAgeinDays(),item.getOpeningBirds(),item.getDied(),item.getHurt(),item.getRemainingBirds(),item.getFeed(),item.getDamagedEggs(),item.getNumberofEggs(),item.getTotalEggs(),
                        item.getPercentage(),item.getStatusType(),item.getStatusTypeId(),item.getCreatedByUserId(),item.getCreatedDate(),item.getUpdatedByUserId(),item.getUpdatedDate(),item.getShedTypeId(),item.getShedType(),item.getRemarks()));
                Log.e("====126",layerlist.size()+"");
//
                if(layerlist.size()!= 0){
                    holder.rvSubItem.setVisibility(View.VISIBLE);
                    holder.noData.setVisibility(View.GONE);
                    ProductListAdapter subItemAdapter = new ProductListAdapter(context, layerlist);
                    //  recyclerView.setAdapter(adapter);

                    holder.rvSubItem.setLayoutManager(layoutManager);
                    holder.rvSubItem.setAdapter(subItemAdapter);
                    holder.rvSubItem.setRecycledViewPool(viewPool);
                }



               Log.d("Anlysis============>", "Date Compatered added to list");
                //print log
            }

            else if (item.getShedTypeId() == 2 && request_List.get(position).getShedTypeId()== 2) {
                holder.rvSubItem.setVisibility(View.VISIBLE);
                holder.noData.setVisibility(View.GONE);
                layerlist.add(new Layers(item.getId(), item.getShedId(), item.getShedName(), item.getFarmId(),item.getFarmName(),item.getBatchName(),item.getBatchId(),item.getAgeinWeeks(),
                        item.getAgeinDays(),item.getOpeningBirds(),item.getDied(),item.getHurt(),item.getRemainingBirds(),item.getFeed(),item.getDamagedEggs(),item.getNumberofEggs(),item.getTotalEggs(),
                        item.getPercentage(),item.getStatusType(),item.getStatusTypeId(),item.getCreatedByUserId(),item.getCreatedDate(),item.getUpdatedByUserId(),item.getUpdatedDate(),item.getShedTypeId(),item.getShedType(),item.getRemarks()));

              Log.e("====143",layerlist.size()+"");
                if(layerlist.size()!= 0){
                    holder.rvSubItem.setVisibility(View.VISIBLE);
                    holder.noData.setVisibility(View.GONE);
                    ProductListAdapter subItemAdapter = new ProductListAdapter(context, layerlist);
                    //  recyclerView.setAdapter(adapter);

                    holder.rvSubItem.setLayoutManager(layoutManager);
                    holder.rvSubItem.setAdapter(subItemAdapter);
                    holder.rvSubItem.setRecycledViewPool(viewPool);
                }




            }


//
            }



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
