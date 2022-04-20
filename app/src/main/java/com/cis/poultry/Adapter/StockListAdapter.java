package com.cis.poultry.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cis.poultry.Models.EggStockRegisterResponse;
import com.cis.poultry.Models.Layers;
import com.cis.poultry.Models.productdata;
import com.cis.poultry.R;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StockListAdapter extends RecyclerView.Adapter<StockListAdapter.MyViewHolder> {


    private Context context;
    private List<EggStockRegisterResponse.ListResult> request_List;
    public String dateStr, formatteddateStr;

    int row_index = -1;

    public static final String DATE_FORMAT1 = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String DATE_FORMAT2 = "dd-MM-yyyy";
    DecimalFormat df = new DecimalFormat("##,##,##0");
    DecimalFormat dff = new DecimalFormat("##,##,##0.00");
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView date, billrate, morality, packing, prod,daily_product,neccrate,totalamount,openstock,damaged,closestock,freeissue,cullbirdrate,Remarks;
        public ImageView image_less, image_more;
        public Button click;
        LinearLayout sublinear, linear;



        public MyViewHolder(View view) {
            super(view);
            billrate = view.findViewById(R.id.billrate);
            date = view.findViewById(R.id.date);
            morality = view.findViewById(R.id.morality);
            packing = view.findViewById(R.id.packing);
            daily_product = view.findViewById(R.id.daily_product);
            neccrate = view.findViewById(R.id.neccrate);
            totalamount = view.findViewById(R.id.totalamount);
            openstock = view.findViewById(R.id.openstock);
            damaged = view.findViewById(R.id.damaged);
         freeissue = view.findViewById(R.id.freeissue);
            Remarks = view.findViewById(R.id.remarks);
            cullbirdrate = view.findViewById(R.id.cullbirdrate);
            closestock = view.findViewById(R.id.closestock);
            sublinear = view.findViewById(R.id.sublinear);
            linear = view.findViewById(R.id.linear);
            image_less = view.findViewById(R.id.image_less);
            image_more = view.findViewById(R.id.image_more);
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

        public void bind(EggStockRegisterResponse.ListResult movie) {
            boolean expanded = movie.isExpanded();

            sublinear.setVisibility(expanded ? View.VISIBLE : View.GONE);

            if(movie.getDailyProduction()!=null)
                daily_product.setText(movie.getDailyProduction()+"");
            else
                daily_product.setText("0.0");
            if(movie.getNECCRate()!=null)
                neccrate.setText(movie.getNECCRate()+"");
            else
                neccrate.setText("0.0");
            if(movie.getPulpRate()!=null)
                totalamount.setText(dff.format(movie.getPulpRate()));
            else
                totalamount.setText("0.0");
            if(movie.getOpeningStock()!=null)
                openstock.setText(movie.getOpeningStock()+"");
            else
                openstock.setText("0.0");

            if(movie.getDamage()!=null)
                damaged.setText(movie.getDamage()+"");
            else
                damaged.setText("0.0");

            if(movie.getClosingStock()!=null)
                closestock.setText(movie.getClosingStock()+"");
            else
                closestock.setText("0.0");

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


    public StockListAdapter(Context context, List<EggStockRegisterResponse.ListResult> request_List) {
        this.context = context;

        this.request_List = request_List;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stocklist_items, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final EggStockRegisterResponse.ListResult movie = request_List.get(position);

        dateStr = request_List.get(position).getDate() + "";

        try {
            formatteddateStr = formatDateFromDateString(DATE_FORMAT1, DATE_FORMAT2, dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.date.setText(formatteddateStr);


        if(request_List.get(position).getTotal()!=null)
        holder.morality.setText(request_List.get(position).getTotal() + "");
        else
            holder.morality.setText("0.0");

        if(request_List.get(position).getBillRate()!=null)
            holder.billrate.setText(df.format(request_List.get(position).getBillRate()));
        else
            holder.billrate.setText("0.0");
        if(request_List.get(position).getPacking()!=null)
            holder.packing.setText(df.format(request_List.get(position).getPacking()));
        else
            holder.packing.setText("0");

        if(request_List.get(position).getDailyProduction()!=null)
            holder.daily_product.setText(request_List.get(position).getDailyProduction()+"");
        else
            holder.daily_product.setText("0.0");
        if(request_List.get(position).getNECCRate()!=null)
            holder.neccrate.setText(request_List.get(position).getNECCRate()+"");
        else
            holder.neccrate.setText("0.0");
        if(request_List.get(position).getPulpRate()!=null)
            holder.totalamount.setText(dff.format(request_List.get(position).getPulpRate()));
        else
            holder.totalamount.setText("0.0");
        if(request_List.get(position).getOpeningStock()!=null)
            holder.openstock.setText(request_List.get(position).getOpeningStock()+"");
        else
            holder.openstock.setText("0.0");

        if(request_List.get(position).getDamage()!=null)
            holder.damaged.setText(request_List.get(position).getDamage()+"");
        else
            holder.damaged.setText("0.0");

        if(request_List.get(position).getClosingStock()!=null)
            holder.closestock.setText(request_List.get(position).getClosingStock()+"");
        else
            holder.closestock.setText("0.0");

        if(request_List.get(position).getFreeIssue()!=null)
            holder.freeissue.setText(request_List.get(position).getFreeIssue()+"");
        else
            holder.freeissue.setText("0");

        if(request_List.get(position).getCullRate()!=null)
            holder.cullbirdrate.setText(request_List.get(position).getCullRate()+"");
        else
            holder.cullbirdrate.setText("0");

        if(request_List.get(position).getRemarks()!=null)
            holder.Remarks.setText(request_List.get(position).getRemarks()+"");
        else
            holder.Remarks.setText(" ");
        if (row_index == position) {
            holder.sublinear.setVisibility(View.VISIBLE);
            holder.date.setTextColor(Color.parseColor("#337ab7"));
            holder.billrate.setTextColor(Color.parseColor("#337ab7"));
            holder.packing.setTextColor(Color.parseColor("#337ab7"));
            holder.morality.setTextColor(Color.parseColor("#337ab7"));
          holder.closestock.setTextColor(Color.parseColor("#337ab7"));
            holder.linear.setBackgroundColor(Color.parseColor("#d0d9f0"));
            holder.image_more.setVisibility(View.GONE);
            holder.image_less.setVisibility(View.VISIBLE);
            holder.bind(movie);
            // holder.createdDateTextView.setVisibility(View.VISIBLE);

        } else {
            holder.sublinear.setVisibility(View.GONE);

            holder.date.setTextColor(Color.parseColor("#000000"));
            holder.billrate.setTextColor(Color.parseColor("#000000"));
            holder.packing.setTextColor(Color.parseColor("#000000"));
            holder.morality.setTextColor(Color.parseColor("#000000"));
            holder.closestock.setTextColor(Color.parseColor("#000000"));
            holder.linear.setBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.image_more.setVisibility(View.VISIBLE);
            holder.image_less.setVisibility(View.GONE);
            //  holder.createdDateTextView.setVisibility(View.GONE);
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

    @Override
    public int getItemCount() {
        return request_List.size();
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

