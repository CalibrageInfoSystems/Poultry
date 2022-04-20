package com.cis.poultry.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.cis.poultry.Models.FeedPurchase;
import com.cis.poultry.Models.FeedPurchaseresponse;
import com.cis.poultry.Models.GetVisitLogByDateResponse;
import com.cis.poultry.Models.Layers;
import com.cis.poultry.Models.productdata;
import com.cis.poultry.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class FeedTypesListAdapter extends RecyclerView.Adapter<FeedTypesListAdapter.MyViewHolder> {


    private Context context;
    private List<FeedPurchaseresponse.Result.FeedType> request_List;
    private List<FeedPurchaseresponse.Result.FeedPurchaseDetail> FeedPurchaselist;
    private ProductListAdapter.RequestAdapterListener listener;
    int row_index = -1;
    PurchaseListAdapter subItemAdapter;
    int hurt,died;
    DecimalFormat df = new DecimalFormat("##,##,##0");
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    public double grandTotal() {
        double sum = 0;
        for(int i = 0; i < FeedPurchaselist.size(); i++)
        {
            if(FeedPurchaselist.get(i).getPaymentStatusId()== 28)
            sum = sum + FeedPurchaselist.get(i).getFinalAmount();
        }
        return sum;
    }
    public double grandpaidTotal() {
        double sum = 0;
        for(int i = 0; i < FeedPurchaselist.size(); i++)
        {
            if(FeedPurchaselist.get(i).getPaymentStatusId()== 27)
                sum = sum + FeedPurchaselist.get(i).getFinalAmount();
        }
        return sum;
    }


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


    public FeedTypesListAdapter(Context context, List<FeedPurchaseresponse.Result.FeedType> request_List, List<FeedPurchaseresponse.Result.FeedPurchaseDetail> FeedPurchase_list) {
        this.context = context;

        this.request_List = request_List;
        this.FeedPurchaselist = FeedPurchase_list;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feedtype_items, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        //   final productdata settings = request_List.get(position);

        Log.e("layers_list===size90",FeedPurchaselist.size()+"");
        holder.tv_item_title.setText(request_List.get(position).getName());

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.rvSubItem.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        if(FeedPurchaselist.size()== 0){
            holder.rvSubItem.setVisibility(View.GONE);
            holder.noData.setVisibility(View.VISIBLE);
        }
        else{
            holder.rvSubItem.setVisibility(View.VISIBLE);
            holder.noData.setVisibility(View.GONE);
        }


        holder.rvSubItem.setVisibility(View.GONE);
        holder.noData.setVisibility(View.VISIBLE);

        List<FeedPurchase> feedlist =new ArrayList<>();
        for (FeedPurchaseresponse.Result.FeedPurchaseDetail item:FeedPurchaselist
        ) {

            //                this.id = id;
            if (item.getFeedTypeId() == request_List.get(position).getId()) {

                feedlist.add(new FeedPurchase(item.getId(), item.getDate(), item.getBrokerName(), item.getCost(), item.getName(), item.getTown(), item.getWeight(), item.getBillDate(),
                        item.getLorryNumber(), item.getFreight(), item.getBillAmount(), item.getNetAmount(), item.getChequeNumber(), item.getChequeDate(), item.getBrokerId(), item.getFeedTypeId(), item.getIsActive(),
                        item.getFeedType(), item.getCreatedByUserId(), item.getCreatedDate(), item.getPaymentTypeId(), item.getPaymentType(), item.getRemarks(), item.getTotalAmounts(), item.getBillAmounts(), item.getReceiveddate(), item.getInvoiceNumber(), item.getPaymentStatusId(), item.getPaymentStatus(), item.getAdditionalAxpenses(), item.getConsiderFreight(),
                        item.getUOMTypeId(), item.getUOMType(), item.getDifferenceAmount(), item.getFinalAmount(), item.getCost(), item.getDueDate()));

                Log.e("====108", feedlist.size() + "");
                if (feedlist.size() != 0) {
                    holder.rvSubItem.setVisibility(View.VISIBLE);
                    holder.noData.setVisibility(View.GONE);
                     subItemAdapter = new PurchaseListAdapter(context, feedlist);
                    //  recyclerView.setAdapter(adapter);
                    ((SimpleItemAnimator) holder.rvSubItem.getItemAnimator()).setSupportsChangeAnimations(false);
                    holder.rvSubItem.setLayoutManager(layoutManager);
                    holder.rvSubItem.setAdapter(subItemAdapter);
                    holder.rvSubItem.setRecycledViewPool(viewPool);
                }


                //public FeedPurchase(Integer id, String date, String brokerName, Double cost, String name, String town, Double weight, String billDate, String lorryNumber, Double freight, Double billAmount, Double netAmount, Object chequeNumber, Object chequeDate, Integer brokerId, Integer feedTypeId, Boolean isActive, String feedType, Integer createdByUserId, String createdDate, Integer paymentTypeId, String paymentType, Object remarks, Double totalAmounts, Double billAmounts, String receiveddate, Object invoiceNumber, Integer paymentStatusId, String paymentStatus, Object additionalAxpenses, Boolean considerFreight, Integer uOMTypeId, String uOMType, Object differenceAmount, Double finalAmount, Object gST, String dueDate) {

//
//
            }


            // Picasso.with(context).load(settings.getImage()).error(R.drawable.selectlogo).transform(new CircleTransform()).into(holder.thumbnail);

        }}

    @Override
    public int getItemCount() {
        return request_List.size();
    }


    public interface RequestAdapterListener {
        void onContactSelected(productdata request);
    }

}

