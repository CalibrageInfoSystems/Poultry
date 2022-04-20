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

import com.cis.poultry.Models.EggStockRegisterResponse;
import com.cis.poultry.Models.FeedPurchase;
import com.cis.poultry.Models.FeedPurchaseresponse;
import com.cis.poultry.Models.purchasedetails;
import com.cis.poultry.Models.transactionDetails;
import com.cis.poultry.R;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PurchaseListAdapter extends RecyclerView.Adapter<PurchaseListAdapter.MyViewHolder> {

    private Context context;
    private List<FeedPurchase> request_List;
    private PurchaseListAdapter.RequestAdapterListener listener;
    private listner listner;
    int row_index = -1;
    DecimalFormat df = new DecimalFormat("##,##,##0");
    DecimalFormat dff = new DecimalFormat("##,##,##0.00");
    public static final String DATE_FORMAT1 = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String DATE_FORMAT2 = "dd-MM-yyyy";


    public String dateStr, formatteddateStr, duedateStr, chequedateStr, formattedduedateStr, billdateStr, formattedbilldateStr,formattedchequedateStr;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView purchaseDate, purchaseName,purchaseNetamount,purchaseDuedate,purchaseStatus;
        public TextView brokername, rateperton,weight,billamount,totalamount,freight, billdate,chequenumber,chequedate;

        public ImageView thumbnail;
        public Button click;
        LinearLayout sublinear,linear;
        public ImageView image_less, image_more;

        public MyViewHolder(View view) {
            super(view);
            purchaseDate = view.findViewById(R.id.purchasedate);
            purchaseName = view.findViewById(R.id.purchaseName);
            purchaseNetamount = view.findViewById(R.id.purchasenetamount);
            purchaseDuedate = view.findViewById(R.id.purchaseduedate);
            purchaseStatus = view.findViewById(R.id.purchaseStatus);
            sublinear = view.findViewById(R.id.sublinear);
            linear = view.findViewById(R.id.linear);
            image_less = view.findViewById(R.id.image_less);
            image_more = view.findViewById(R.id.image_more);

            brokername = view.findViewById(R.id.broker);
            rateperton = view.findViewById(R.id.rateperton);
            weight = view.findViewById(R.id.weight);
            billamount = view.findViewById(R.id.billamount);
            totalamount = view.findViewById(R.id.purtotalamount);
            freight = view.findViewById(R.id.freight);
            billdate = view.findViewById(R.id.billdate);
            chequenumber = view.findViewById(R.id.chequenumber);
            chequedate = view.findViewById(R.id.chequedate);

//            view.setOnClickListener(new View.OnClickListener() {
//                //                @Override
//                public void onClick(View view) {
//
//                    sublinear.setVisibility(View.VISIBLE);
//                }
//            });
//
        }

        public void bind(FeedPurchase movie) {
            boolean expanded = movie.isExpanded();

            sublinear.setVisibility(expanded ? View.VISIBLE : View.GONE);

            if(movie.getBrokerName()!=null){
                brokername.setText(movie.getBrokerName());
            }else{
                brokername.setText("");
            }


            if(movie.getCost()!=null){
                rateperton.setText(movie.getCost() + "");
            }else{
                rateperton.setText("");
            }


            if(movie.getWeight()!=null){
                weight.setText(movie.getWeight() + "");
            }else{
                weight.setText("");
            }



            if(movie.getBillAmount()!=null){
                billamount.setText(dff.format(movie.getBillAmount() ));
            }else{
                billamount.setText("");
            }


            if(movie.getTotalAmounts()!=null){
                totalamount.setText(dff.format(movie.getTotalAmounts()));
            }else{
                totalamount.setText("");
            }


            if(movie.getFreight()!=null){
                freight.setText(movie.getFreight() + "");
            }else{
                freight.setText("");
            }


            billdate.setText(formattedbilldateStr + "");
            if(movie.getChequeNumber()!=null){
                chequenumber.setText(movie.getChequeNumber() + "");
            }else{
                chequenumber.setText("");
            }


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



    public PurchaseListAdapter(Context context, List<FeedPurchase> request_List) {
        this.context = context;

        this.request_List = request_List;

    }

    @Override
    public PurchaseListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.purchaserecycleadapter, parent, false);

        return new PurchaseListAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final PurchaseListAdapter.MyViewHolder holder, final int position) {
        final FeedPurchase movie = request_List.get(position);


       // final purchasedetails settings = request_List.get(position);

        dateStr = request_List.get(position).getReceiveddate() + "";
        duedateStr = request_List.get(position).getDueDate() + "";
        billdateStr = request_List.get(position).getBillDate() + "";
        if(request_List.get(position).getChequeDate()!= null) {
            chequedateStr = request_List.get(position).getChequeDate() + "";

            try {
            formattedchequedateStr = formatDateFromDateString(DATE_FORMAT1, DATE_FORMAT2, chequedateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            holder.chequedate.setText(formattedchequedateStr + "");
        }
        else{
            holder.chequedate.setText( "");
        }

        try {
            formatteddateStr = formatDateFromDateString(DATE_FORMAT1, DATE_FORMAT2, dateStr);
            formattedduedateStr = formatDateFromDateString(DATE_FORMAT1, DATE_FORMAT2, duedateStr);
            formattedbilldateStr = formatDateFromDateString(DATE_FORMAT1, DATE_FORMAT2, billdateStr);
         //   formattedchequedateStr = formatDateFromDateString(DATE_FORMAT1, DATE_FORMAT2, chequedateStr);


        } catch (ParseException e) {
            e.printStackTrace();
        }


        holder.purchaseDate.setText(formatteddateStr + "");
        if(request_List.get(position).getName()!=null){
            holder.purchaseName.setText(request_List.get(position).getName());
        }else{
            holder.purchaseName.setText("");
        }
        if(request_List.get(position).getNetAmount()!=null){
            holder.purchaseNetamount.setText(dff.format(request_List.get(position).getNetAmount() ));
        }else{
            holder.purchaseNetamount.setText("");
        }

        holder.purchaseDuedate.setText(formattedduedateStr + "");
        holder.purchaseStatus.setText(request_List.get(position).getPaymentStatus());
        if(request_List.get(position).getBrokerName()!=null){
            holder.brokername.setText(request_List.get(position).getBrokerName());
        }else{
            holder.brokername.setText("");
        }


        if(request_List.get(position).getCost()!=null){
            holder.rateperton.setText(request_List.get(position).getCost() + "");
        }else{
            holder.rateperton.setText("");
        }


        if(request_List.get(position).getWeight()!=null){
            holder.weight.setText(request_List.get(position).getWeight() + "");
        }else{
            holder.weight.setText("");
        }



        if(request_List.get(position).getBillAmount()!=null){
            holder.billamount.setText(dff.format(request_List.get(position).getBillAmount() ));
        }else{
            holder.billamount.setText("");
        }


        if(request_List.get(position).getTotalAmounts()!=null){
            holder.totalamount.setText(dff.format(request_List.get(position).getTotalAmounts()));
        }else{
            holder.totalamount.setText("");
        }


        if(request_List.get(position).getFreight()!=null){
            holder.freight.setText(request_List.get(position).getFreight() + "");
        }else{
            holder.freight.setText("");
        }


        holder.billdate.setText(formattedbilldateStr + "");
        if(request_List.get(position).getChequeNumber()!=null){
            holder.chequenumber.setText(request_List.get(position).getChequeNumber() + "");
        }else{
            holder.chequenumber.setText("");
        }




        if (row_index == position) {
            holder.sublinear.setVisibility(View.VISIBLE);
            holder.purchaseDate.setTextColor(Color.parseColor("#337ab7"));
            holder.purchaseName.setTextColor(Color.parseColor("#337ab7"));
            holder.purchaseNetamount.setTextColor(Color.parseColor("#337ab7"));
            holder.purchaseStatus.setTextColor(Color.parseColor("#337ab7"));
           holder.purchaseDuedate.setTextColor(Color.parseColor("#337ab7"));
            holder.linear.setBackgroundColor(Color.parseColor("#d0d9f0"));
            holder.image_more.setVisibility(View.GONE);
            holder.image_less.setVisibility(View.VISIBLE);
            holder.bind(movie);
            // holder.createdDateTextView.setVisibility(View.VISIBLE);

        } else {


            holder.sublinear.setVisibility(View.GONE);

            holder.purchaseDate.setTextColor(Color.parseColor("#000000"));
            holder.purchaseName.setTextColor(Color.parseColor("#000000"));
            holder.purchaseNetamount.setTextColor(Color.parseColor("#000000"));
            holder.purchaseStatus.setTextColor(Color.parseColor("#000000"));
              holder.billdate.setTextColor(Color.parseColor("#000000"));
            holder.purchaseDuedate.setTextColor(Color.parseColor("#000000"));
            holder.linear.setBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.image_more.setVisibility(View.VISIBLE);
            holder.image_less.setVisibility(View.GONE);
            //  holder.createdDateTextView.setVisibility(View.GONE);
        }

        if(request_List.get(position).getPaymentStatusId()== 28){
            holder.purchaseDate.setTextColor(Color.parseColor("#A71F5E"));
            holder.purchaseName.setTextColor(Color.parseColor("#A71F5E"));
            holder.purchaseNetamount.setTextColor(Color.parseColor("#A71F5E"));
            holder.purchaseStatus.setTextColor(Color.parseColor("#A71F5E"));
            holder.billdate.setTextColor(Color.parseColor("#A71F5E"));
            holder.purchaseDuedate.setTextColor(Color.parseColor("#A71F5E"));
            holder.chequedate.setTextColor(Color.parseColor("#A71F5E"));
            holder.chequenumber.setTextColor(Color.parseColor("#A71F5E"));
            holder.freight.setTextColor(Color.parseColor("#A71F5E"));

            holder.totalamount.setTextColor(Color.parseColor("#A71F5E"));
            holder.billamount.setTextColor(Color.parseColor("#A71F5E"));
            holder.weight.setTextColor(Color.parseColor("#A71F5E"));
            holder.rateperton.setTextColor(Color.parseColor("#A71F5E"));
            holder.brokername.setTextColor(Color.parseColor("#A71F5E"));
        }
        else{
            holder.purchaseDate.setTextColor(Color.parseColor("#018201"));
            holder.purchaseName.setTextColor(Color.parseColor("#018201"));
            holder.purchaseNetamount.setTextColor(Color.parseColor("#018201"));
            holder.purchaseStatus.setTextColor(Color.parseColor("#018201"));
            holder.billdate.setTextColor(Color.parseColor("#018201"));
            holder.purchaseDuedate.setTextColor(Color.parseColor("#018201"));
            holder.chequedate.setTextColor(Color.parseColor("#018201"));
            holder.chequenumber.setTextColor(Color.parseColor("#018201"));
            holder.freight.setTextColor(Color.parseColor("#018201"));

            holder.totalamount.setTextColor(Color.parseColor("#018201"));
            holder.billamount.setTextColor(Color.parseColor("#018201"));
            holder.weight.setTextColor(Color.parseColor("#018201"));
            holder.rateperton.setTextColor(Color.parseColor("#018201"));
            holder.brokername.setTextColor(Color.parseColor("#018201"));


        }
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
        // Picasso.with(context).load(settings.getImage()).error(R.drawable.selectlogo).transform(new CircleTransform()).into(holder.thumbnail);

    }

    public double grandTotal() {
        double totalpaidPrice = 0.0;
        for(int i = 0; i < request_List.size(); i++)
        {
            totalpaidPrice = totalpaidPrice + request_List.get(i).getFinalAmount();
        }

        return totalpaidPrice;
    }

    @Override
    public int getItemCount() {
        return request_List.size();
    }


    public interface RequestAdapterListener {
        void onContactSelected(purchasedetails request);
    }

    public static String formatDateFromDateString(String inputDateFormat, String outputDateFormat, String inputDate) throws ParseException {
        Date mParsedDate;
        String mOutputDateString;
        SimpleDateFormat mInputDateFormat = new SimpleDateFormat(inputDateFormat, java.util.Locale.getDefault());
        SimpleDateFormat mOutputDateFormat = new SimpleDateFormat(outputDateFormat, java.util.Locale.getDefault());
        mParsedDate = mInputDateFormat.parse(inputDate);
        mOutputDateString = mOutputDateFormat.format(mParsedDate);
        return mOutputDateString;

    }


    public interface listner {
        void updated(int po, ArrayList<FeedPurchaseresponse.Result.FeedPurchaseDetail> dueamount);
    }

}
