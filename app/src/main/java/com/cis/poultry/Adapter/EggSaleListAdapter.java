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

import androidx.recyclerview.widget.RecyclerView;

import com.cis.poultry.Models.EggSaleresponse;
import com.cis.poultry.Models.Layers;
import com.cis.poultry.Models.saledata;
import com.cis.poultry.R;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class EggSaleListAdapter extends RecyclerView.Adapter<EggSaleListAdapter.MyViewHolder> {

    private Context context;
    private List<EggSaleresponse.Result.SaleRegisterDetail> request_List;
    private EggSaleListAdapter.RequestAdapterListener listener;
    int row_index = -1;
    public String dateStr, formatteddateStr;

    public static final String DATE_FORMAT1 = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String DATE_FORMAT2 = "dd-MM-yyyy";

    DecimalFormat dff = new DecimalFormat("##,##,##0.00");
    DecimalFormat df = new DecimalFormat("##,##,##0");
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView date, noofcartons,billrate,billamount, lorryno, neccrate, cumulativeamount;
        public ImageView thumbnail;
        public Button click;
        LinearLayout sublinear,linear;
        public ImageView image_less, image_more;

        public MyViewHolder(View view) {
            super(view);
            date = view.findViewById(R.id.saledate);
            noofcartons = view.findViewById(R.id.salecartons);
            billrate = view.findViewById(R.id.salebillrate);
            billamount = view.findViewById(R.id.salebillamount);

            lorryno = view.findViewById(R.id.lorryNo);
            neccrate = view.findViewById(R.id.neccrate);
            cumulativeamount= view.findViewById(R.id.cummulativeAmount);
            sublinear = view.findViewById(R.id.sublinear);
            linear = view.findViewById(R.id.linear);
            image_less = view.findViewById(R.id.image_less);
            image_more = view.findViewById(R.id.image_more);
//            view.setOnClickListener(new View.OnClickListener() {
//                //                @Override
//                public void onClick(View view) {
//
//                    sublinear.setVisibility(View.VISIBLE);
//                }
//            });
//
        }

        public void bind(EggSaleresponse.Result.SaleRegisterDetail movie) {
            boolean expanded = movie.isExpanded();

            sublinear.setVisibility(expanded ? View.VISIBLE : View.GONE);
            lorryno.setText(movie.getLorryNumber() + "");
            neccrate.setText(movie.getNECCRate() + "");
            cumulativeamount.setText(dff.format(movie.getCummulativeBillAmount()));
            if (sublinear.getVisibility() == View.VISIBLE) {
                image_less.setVisibility(View.VISIBLE);
                image_more.setVisibility(View.GONE);
            } else {
                image_less.setVisibility(View.GONE);
                image_more.setVisibility(View.VISIBLE);
            }

        }
    }


    public EggSaleListAdapter(Context context,List<EggSaleresponse.Result.SaleRegisterDetail> request_List) {
        this.context = context;

        this.request_List = request_List;

    }

    @Override
    public EggSaleListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.eggsalelistrecycleadapter, parent, false);

        return new EggSaleListAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final EggSaleListAdapter.MyViewHolder holder, final int position) {
        final EggSaleresponse.Result.SaleRegisterDetail movie = request_List.get(position);
        Collections.sort(request_List, new Comparator<EggSaleresponse.Result.SaleRegisterDetail>() {
            DateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            @Override
            public int compare(EggSaleresponse.Result.SaleRegisterDetail lhs, EggSaleresponse.Result.SaleRegisterDetail rhs) {
                try {
                    return f.parse(rhs.getDate()).compareTo(f.parse(lhs.getDate()));
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });
        dateStr = request_List.get(position).getDate() + "";

        try {
            formatteddateStr = formatDateFromDateString(DATE_FORMAT1, DATE_FORMAT2, dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        holder.date.setText(formatteddateStr);
        holder.noofcartons.setText(df.format(request_List.get(position).getNumberofBoxes()));
        holder.billrate.setText(dff.format(request_List.get(position).getBillRate() ));
        holder.billamount.setText(dff.format(request_List.get(position).getBillAmount()));

        holder.lorryno.setText(request_List.get(position).getLorryNumber() + "");
        holder.neccrate.setText(request_List.get(position).getNECCRate() + "");
        holder.cumulativeamount.setText(dff.format(request_List.get(position).getCummulativeBillAmount()));



        if (row_index == position) {
            holder.sublinear.setVisibility(View.VISIBLE);
            holder.date.setTextColor(Color.parseColor("#337ab7"));
            holder.billrate.setTextColor(Color.parseColor("#337ab7"));
            holder.billamount.setTextColor(Color.parseColor("#337ab7"));
            holder.noofcartons.setTextColor(Color.parseColor("#337ab7"));
            //  holder.prod.setTextColor(Color.parseColor("#337ab7"));
            holder.linear.setBackgroundColor(Color.parseColor("#d0d9f0"));
            holder.image_more.setVisibility(View.GONE);
            holder.image_less.setVisibility(View.VISIBLE);
            holder.bind(movie);
            // holder.createdDateTextView.setVisibility(View.VISIBLE);

        } else {
            holder.sublinear.setVisibility(View.GONE);

            holder.date.setTextColor(Color.parseColor("#000000"));
            holder.billrate.setTextColor(Color.parseColor("#000000"));
            holder.noofcartons.setTextColor(Color.parseColor("#000000"));
            holder.billamount.setTextColor(Color.parseColor("#000000"));
            //    holder.prod.setTextColor(Color.parseColor("#000000"));
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
    private void sortListByDate(List<EggSaleresponse.Result.SaleRegisterDetail> theArrayListEvents) {
        Collections.sort(theArrayListEvents, new EventDetailSortByDate());
    }


    @Override
    public int getItemCount() {
        return request_List.size();
    }


    public interface RequestAdapterListener {
        void onContactSelected(saledata request);
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

    private class EventDetailSortByDate implements java.util.Comparator<EggSaleresponse.Result.SaleRegisterDetail> {
        @Override
        public int compare(EggSaleresponse.Result.SaleRegisterDetail customerEvents1, EggSaleresponse.Result.SaleRegisterDetail customerEvents2) {
            Date DateObject1 = StringToDate(customerEvents1.getDate());
            Date DateObject2 = StringToDate(customerEvents2.getDate());

            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(DateObject1);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(DateObject2);

            int month1 = cal1.get(Calendar.MONTH);
            int month2 = cal2.get(Calendar.MONTH);

            if (month1 < month2)
                return -1;
            else if (month1 == month2)
                return cal1.get(Calendar.DAY_OF_MONTH) - cal2.get(Calendar.DAY_OF_MONTH);

            else return 1;
        }
    }


    public static Date StringToDate(String theDateString) {
        Date returnDate = new Date();
        if (theDateString.contains("-")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            try {
                returnDate = dateFormat.parse(theDateString);
            } catch (ParseException e) {
                SimpleDateFormat altdateFormat = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    returnDate = altdateFormat.parse(theDateString);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            try {
                returnDate = dateFormat.parse(theDateString);
            } catch (ParseException e) {
                SimpleDateFormat altdateFormat = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    returnDate = altdateFormat.parse(theDateString);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return returnDate;
    }
}
