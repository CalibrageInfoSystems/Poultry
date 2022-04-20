package com.cis.poultry.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cis.poultry.Models.EggSaleresponse;
import com.cis.poultry.Models.transactionDetails;
import com.cis.poultry.R;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class SaleTransactionAdapter extends RecyclerView.Adapter<SaleTransactionAdapter.MyViewHolder> {

    private Context context;
    private List<EggSaleresponse.Result.SaleTransaction> request_List;
    private SaleTransactionAdapter.RequestAdapterListener listener;
    int row_index = -1;
    public String dateStr, formatteddateStr;
    DecimalFormat dff = new DecimalFormat("##,##,##0.00");
    DecimalFormat df = new DecimalFormat("##,##,##0");
    public static final String DATE_FORMAT1 = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String DATE_FORMAT2 = "dd-MM-yyyy";


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView transdate, transmode,transreceivedamount;
        public ImageView thumbnail;
        public Button click;
        LinearLayout sublinear;

        public MyViewHolder(View view) {
            super(view);
            transdate = view.findViewById(R.id.transdate);
            transmode = view.findViewById(R.id.transmodepayment);
            transreceivedamount = view.findViewById(R.id.transreceivedamount);
            sublinear = view.findViewById(R.id.sublinear);


//            view.setOnClickListener(new View.OnClickListener() {
//                //                @Override
//                public void onClick(View view) {
//
//                    sublinear.setVisibility(View.VISIBLE);
//                }
//            });
//
        }
    }


    public SaleTransactionAdapter(Context context, List<EggSaleresponse.Result.SaleTransaction> request_List) {
        this.context = context;

        this.request_List = request_List;

    }

    @Override
    public SaleTransactionAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.saletransactionrecycleadapter, parent, false);

        return new SaleTransactionAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final SaleTransactionAdapter.MyViewHolder holder, final int position) {

        Collections.sort(request_List, new Comparator<EggSaleresponse.Result.SaleTransaction>() {
            DateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            @Override
            public int compare(EggSaleresponse.Result.SaleTransaction lhs, EggSaleresponse.Result.SaleTransaction rhs) {
                try {
                    return f.parse(rhs.getTransactionDate()).compareTo(f.parse(lhs.getTransactionDate()));
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });
        dateStr = request_List.get(position).getTransactionDate() + "";

        try {
            formatteddateStr = formatDateFromDateString(DATE_FORMAT1, DATE_FORMAT2, dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.transdate.setText(formatteddateStr);
        holder.transmode.setText(request_List.get(position).getPaymentMode());
        holder.transreceivedamount.setText(dff.format(request_List.get(position).getReceivedAmount()));

        // Picasso.with(context).load(settings.getImage()).error(R.drawable.selectlogo).transform(new CircleTransform()).into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return request_List.size();
    }


    public interface RequestAdapterListener {
        void onContactSelected(transactionDetails request);
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
}
