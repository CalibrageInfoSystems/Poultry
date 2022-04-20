package com.cis.poultry.Fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cis.poultry.Adapter.EggSaleListAdapter;
import com.cis.poultry.Adapter.SaleTransactionAdapter;
import com.cis.poultry.Models.EggSaleObject;
import com.cis.poultry.Models.EggSaleresponse;
import com.cis.poultry.R;
import com.cis.poultry.service.ApiService;
import com.cis.poultry.service.ServiceFactory;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.cis.poultry.Fragments.SaleFragment.Farm_Id;
import static com.cis.poultry.Fragments.SaleFragment.formatDateFromDateString;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link jayama_fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link jayama_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class jayama_fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String to_date, from_date;
    String traderId;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View view;
    private brado_details.OnFragmentInteractionListener mListener;
    TextView billingamount, noofcatons, receivedamount, dueamount;
    TextView clear, noData, noData2;
    public String startdateStr, startDateformatted;
    public String enddateStr, endDateformatted;
    public static final String DATE_FORMAT2 = "dd-MM-yyyy";
    public static final String DATE_FORMAT1 = "yyyy-MM-dd'T'HH:mm:ss";
    DecimalFormat dff = new DecimalFormat("##,##,##0.00");
    DecimalFormat df = new DecimalFormat("##,##,##0");
    private Subscription mSubscription;
    private SpotsDialog mdilogue;
    RecyclerView recyclerView2, recyclerView;
    final Calendar myCalendar = Calendar.getInstance();
    String onemonthlast, currentdate;

    public jayama_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment jayama_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static jayama_fragment newInstance(String param1, String param2) {
        jayama_fragment fragment = new jayama_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_jayama_fragment, container, false);


        intviews();
        setviews();

        return view;

    }
    private void intviews() {
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(getContext())
                .setTheme(R.style.Custom)
                .build();
        billingamount = view.findViewById(R.id.billingamount);
        noofcatons = view.findViewById(R.id.noofcartons);
        receivedamount = view.findViewById(R.id.receivedamount);
        dueamount = view.findViewById(R.id.dueamount);
        noData = view.findViewById(R.id.noData);
        noData2 = view.findViewById(R.id.noData2);
        recyclerView = (RecyclerView) view.findViewById(R.id.salerecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView2 = (RecyclerView) view.findViewById(R.id.transactionrecyclerview);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
    private void setviews() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -30);

        Date datee = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        onemonthlast = format.format(datee);
        Log.i("onemonthlast", onemonthlast);



        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
        currentdate = format1.format(myCalendar.getTime());
        Log.i("currentdate", currentdate+ "");


        GetEggSaleDetailsdefault();

    }

    private void GetEggSaleDetailsdefault()
      {
            mdilogue.show();
            JsonObject object = EggSaleRequestObject();
            ApiService service = ServiceFactory.createRetrofitService(getActivity(), ApiService.class);
            mSubscription = service.postSaleresponse(object)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<EggSaleresponse>() {
                        @Override
                        public void onCompleted() {
                            mdilogue.dismiss();
                        }

                        @Override
                        public void onError(Throwable e) {
                            if (e instanceof HttpException) {
                                ((HttpException) e).code();
                                ((HttpException) e).message();
                                ((HttpException) e).response().errorBody();
                                try {
                                    ((HttpException) e).response().errorBody().string();
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                                e.printStackTrace();
                            }
                            mdilogue.dismiss();
                            // showDialog(getActivity(), getString(R.string.server_error));
                        }

                        @Override
                        public void onNext(EggSaleresponse eggSaleresponse) {
                            mdilogue.dismiss();
                            if (eggSaleresponse.getIsSuccess()) {

                                if (eggSaleresponse.getResult().getTraders().size() != 0) {
                                    billingamount.setText(getString(R.string.Rs) + dff.format(eggSaleresponse.getResult().getTraders().get(0).getBillingAmount()));
                                    noofcatons.setText(dff.format(eggSaleresponse.getResult().getTraders().get(0).getNumberofBoxes()) );
                                    receivedamount.setText(getString(R.string.Rs) + dff.format(eggSaleresponse.getResult().getTraders().get(0).getReceivedAmount()));
                                    dueamount.setText(getString(R.string.Rs) + dff.format(eggSaleresponse.getResult().getTraders().get(0).getDueAmount()));
                                } else {
                                    billingamount.setText(getString(R.string.Rs) + "0.00");
                                    noofcatons.setText("0");
                                    receivedamount.setText(getString(R.string.Rs) + "0.00");
                                    dueamount.setText(getString(R.string.Rs) + "0.00");
                                }
                                if (eggSaleresponse.getResult().getSaleTransactions().size() != 0) {
                                    noData.setVisibility(View.GONE);
                                    recyclerView2.setVisibility(View.VISIBLE);
                                    SaleTransactionAdapter adapter = new SaleTransactionAdapter(getActivity(), eggSaleresponse.getResult().getSaleTransactions());
                                    recyclerView2.setAdapter(adapter);
                                } else {
                                    noData.setVisibility(View.VISIBLE);
                                    recyclerView2.setVisibility(View.GONE);
                                }

                                if (eggSaleresponse.getResult().getSaleRegisterDetails().size() != 0) {
                                    noData2.setVisibility(View.GONE);
                                    recyclerView.setVisibility(View.VISIBLE);
                                    EggSaleListAdapter adapter = new EggSaleListAdapter(getActivity(), eggSaleresponse.getResult().getSaleRegisterDetails());
                                    recyclerView.setAdapter(adapter);

                                } else {
                                    noData2.setVisibility(View.VISIBLE);
                                    recyclerView.setVisibility(View.GONE);
                                }
                                Log.d("EggSaleresponse", "isSuccess");
                            }


                        }


                    });
        }



    private BroadcastReceiver mNotificationReceiver = new BroadcastReceiver() {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onReceive(Context context, Intent intent) {



            to_date = intent.getStringExtra("todate");
            from_date = intent.getStringExtra("fromdate");
            traderId  = intent.getStringExtra("traderId");

            Log.e("dates==========>",to_date+from_date+"id===================="+traderId);
            GetEggSaleDetails();
        }
    };

    private void GetEggSaleDetails() {
        mdilogue.show();
        JsonObject object = EggSaleRequestObjectt();
        ApiService service = ServiceFactory.createRetrofitService(getActivity(), ApiService.class);
        mSubscription = service.postSaleresponse(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<EggSaleresponse>() {
                    @Override
                    public void onCompleted() {
                        mdilogue.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            ((HttpException) e).code();
                            ((HttpException) e).message();
                            ((HttpException) e).response().errorBody();
                            try {
                                ((HttpException) e).response().errorBody().string();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            e.printStackTrace();
                        }
                        mdilogue.dismiss();
                        // showDialog(getActivity(), getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(EggSaleresponse eggSaleresponse) {
                        mdilogue.dismiss();
                        if (eggSaleresponse.getIsSuccess()) {
                            if (eggSaleresponse.getResult().getTraders().size() != 0) {
                                billingamount.setText(getString(R.string.Rs) + dff.format(eggSaleresponse.getResult().getTraders().get(0).getBillingAmount()));
                                noofcatons.setText(dff.format(eggSaleresponse.getResult().getTraders().get(0).getNumberofBoxes()) );
                                receivedamount.setText(getString(R.string.Rs) + dff.format(eggSaleresponse.getResult().getTraders().get(0).getReceivedAmount()));
                                dueamount.setText(getString(R.string.Rs) + dff.format(eggSaleresponse.getResult().getTraders().get(0).getDueAmount()));
                            } else {
                                billingamount.setText(getString(R.string.Rs) + "0.00");
                                noofcatons.setText("0");
                                receivedamount.setText(getString(R.string.Rs) + "0.00");
                                dueamount.setText(getString(R.string.Rs) + "0.00");
                            }
                            if (eggSaleresponse.getResult().getSaleTransactions().size() != 0) {
                                noData.setVisibility(View.GONE);
                                recyclerView2.setVisibility(View.VISIBLE);
                                SaleTransactionAdapter adapter = new SaleTransactionAdapter(getActivity(), eggSaleresponse.getResult().getSaleTransactions());
                                recyclerView2.setAdapter(adapter);
                            } else {
                                noData.setVisibility(View.VISIBLE);
                                recyclerView2.setVisibility(View.GONE);
                            }

                            if (eggSaleresponse.getResult().getSaleRegisterDetails().size() != 0) {
                                noData2.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                                EggSaleListAdapter adapter = new EggSaleListAdapter(getActivity(), eggSaleresponse.getResult().getSaleRegisterDetails());
                                recyclerView.setAdapter(adapter);

                            } else {
                                noData2.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.GONE);
                            }
                            Log.d("EggSaleresponse", "isSuccess");
                        }


                    }


                });
    }

    private JsonObject EggSaleRequestObjectt() {
        EggSaleObject eggSaleObject = new EggSaleObject();

        eggSaleObject.setTraderId(traderId);
        eggSaleObject.setFromDate(to_date);
        eggSaleObject.setToDate(from_date);
        eggSaleObject.setFarmId(Farm_Id);




        return new Gson().toJsonTree(eggSaleObject).getAsJsonObject();
    }


    private JsonObject EggSaleRequestObject() {
        try {
            startDateformatted = formatDateFromDateString(DATE_FORMAT2, DATE_FORMAT1, onemonthlast);
            endDateformatted = formatDateFromDateString(DATE_FORMAT2, DATE_FORMAT1, currentdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        EggSaleObject eggSaleObject = new EggSaleObject();

        eggSaleObject.setTraderId(null);
        eggSaleObject.setFromDate(startDateformatted);
        eggSaleObject.setToDate(endDateformatted);
        eggSaleObject.setFarmId(Farm_Id);


//        startdateStr = fromdate.getText().toString();
//        enddateStr = todate.getText().toString();
//

//
//        EggSaleObject eggSaleObject = new EggSaleObject();
////        if (isSearchClicked == true) {
////            eggSaleObject.setTraderId(traderId);
////            eggSaleObject.setFromDate(startDateformatted);
////            eggSaleObject.setToDate(endDateformatted);
////            eggSaleObject.setFarmId(Farm_Id);
////
////        }
////
////        if (isSearchClicked == false) {
//        eggSaleObject.setTraderId(null);
//        eggSaleObject.setFromDate("2020-01-23T00:00:00");
//        eggSaleObject.setToDate("2020-10-23T00:00:00");
//        eggSaleObject.setFarmId(2);
        //    }


        return new Gson().toJsonTree(eggSaleObject).getAsJsonObject();

    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    @Override
    public void onResume() {
        super.onResume();
        getContext().registerReceiver(mNotificationReceiver, new IntentFilter("KEY"));
    }

    @Override
    public void onPause() {
        super.onPause();
        getContext().unregisterReceiver(mNotificationReceiver);
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
