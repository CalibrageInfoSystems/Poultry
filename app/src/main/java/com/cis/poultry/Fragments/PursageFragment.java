package com.cis.poultry.Fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.cis.poultry.Adapter.EggSaleListAdapter;
import com.cis.poultry.Adapter.FeedSummaryAdapter;
import com.cis.poultry.Adapter.FeedTypesListAdapter;
import com.cis.poultry.Adapter.PoultryListAdapterP;
import com.cis.poultry.Adapter.PoultryListAdapterPur;
import com.cis.poultry.Adapter.PurchaseListAdapter;
import com.cis.poultry.Adapter.SaleTransactionAdapter;
import com.cis.poultry.Common.BaseFragment;
import com.cis.poultry.DashboardActivity;
import com.cis.poultry.Models.EggSaleObject;
import com.cis.poultry.Models.EggSaleresponse;
import com.cis.poultry.Models.FeedPurchaseObject;
import com.cis.poultry.Models.FeedPurchaseresponse;
import com.cis.poultry.Models.GetAllTypeCdDmt;
import com.cis.poultry.Models.GetLookUpData;
import com.cis.poultry.Models.LoginResponse;
import com.cis.poultry.Models.dueamountlist;
import com.cis.poultry.Models.purchasedetails;
import com.cis.poultry.Models.saledata;
import com.cis.poultry.Models.transactionDetails;
import com.cis.poultry.R;
import com.cis.poultry.localData.SharedPrefsData;
import com.cis.poultry.service.APIConstantURL;
import com.cis.poultry.service.ApiService;
import com.cis.poultry.service.ServiceFactory;
import com.google.android.material.textfield.TextInputLayout;
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
import java.util.Locale;

import dmax.dialog.SpotsDialog;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PursageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PursageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PursageFragment extends BaseFragment implements PurchaseListAdapter.listner {
    public static String TAG = "PursageFragment";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Subscription mSubscription;
    private SpotsDialog mdilogue;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FeedTypesListAdapter adapter;
    FeedSummaryAdapter feedadapter;
    TextInputLayout fromdatelbl, todatelbl;
    RecyclerView recyclerView;
    DecimalFormat dff = new DecimalFormat("##,##,##0.00");
TextView noData,noData_popup;
TextView close_txt;
    public String startdateStr, startDateformatted;
    public String enddateStr, endDateformatted;
RecyclerView terms_recycle;
    View view;
    Dialog myDialog;
TextView  close;
    double paidamt= 0.0;
    double outstandingamt= 0.0;
    Boolean isStartDate = false;
    Boolean isEndDate = false;
    List<String>get_feed = new ArrayList<String>();
    List<Integer> get_feed_Id = new ArrayList<Integer>();

    List<String>get_payment = new ArrayList<String>();
    List<Integer> get_payment_Id = new ArrayList<Integer>();
    Spinner paymentStatusSpinner, feedTypeSpinner;
    Button clear, search;
    ImageButton purchaseheader;
    LinearLayout statslayout, purchaseselectionlyt;

    public static final String DATE_FORMAT1 = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String DATE_FORMAT2 = "dd-MM-yyyy";
    RecyclerView recyclerView1;
    LoginResponse catagoriesList;
    public static  Integer Farm_Id ;
    int paymentstatusId = 28, feedTypeId = 1;
TextView dueamount;
    String feedtypename;
    String onemonthlast, currentdate;


    Boolean isClicked = false;

    Boolean isSearchClicked = false;

    TextView outstandingAmount, paidAmount, feedtypenamee,totalamountsummery;

    private List<purchasedetails> purchasedetails_List = new ArrayList<>();


    private EditText fromdate, todate;
    final Calendar myCalendar = Calendar.getInstance();

    List<Integer> getpaymentstatusId = new ArrayList<Integer>();
    private double mSubTotal = 0.0;
    private double mpaidTotal = 0.0;
    private  double due_amount =0.0;

    private OnFragmentInteractionListener mListener;

    public PursageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PursageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PursageFragment newInstance(String param1, String param2) {
        PursageFragment fragment = new PursageFragment();
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
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_pursage, container, false);

        intviews();
        setviews();

        return view;
    }



    private void intviews() {

        catagoriesList = SharedPrefsData.getCatagories(getActivity());
        Farm_Id =catagoriesList.getResult().getUserFarms().get(0).getFarmId();
        Bundle bundle = this.getArguments();
        if(bundle != null){
            Farm_Id = bundle.getInt("FARMid");

        }
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(getContext())
                .setTheme(R.style.Custom)
                .build();
        paymentStatusSpinner = view.findViewById(R.id.paymentstatusSpinner);
        totalamountsummery = view.findViewById(R.id.totalamountsummery);
        feedTypeSpinner = view.findViewById(R.id.feedtypeSpinner);
        fromdate =  view.findViewById(R.id.pfromdate_edittxt);
        todate =  view.findViewById(R.id.ptodate_edittxt);
        search =  view.findViewById(R.id.search);
        clear =  view.findViewById(R.id.clear);
        statslayout = view.findViewById(R.id.statslayout);
        fromdatelbl = view.findViewById(R.id.pfromdate_label);
        todatelbl = view.findViewById(R.id.ptodate_label);

        purchaseselectionlyt = view.findViewById(R.id.purchaseselectionlayout);

        purchaseheader = view.findViewById(R.id.purchaseheader);

        outstandingAmount = view.findViewById(R.id.outstandingamount);
        paidAmount = view.findViewById(R.id.paidamount);
       // feedtypenamee = view.findViewById(R.id.feedtypename);

        noData =view.findViewById(R.id.noData);

        recyclerView = (RecyclerView) view.findViewById(R.id.purchaserecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView1 = (RecyclerView)view. findViewById(R.id.recyclerview1);

        recyclerView1.setHasFixedSize(true);
        // recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        RecyclerView.LayoutManager mLayoutManager1 = new GridLayoutManager(getActivity() ,2);
        recyclerView1.setLayoutManager(mLayoutManager1);

        catagoriesList = SharedPrefsData.getCatagories(getActivity());
        if (catagoriesList.getResult().getUserFarms().size() == 1){
            recyclerView1.setVisibility(View.GONE);
            Farm_Id =catagoriesList.getResult().getUserFarms().get(0).getFarmId();
        }else {

            recyclerView1.setVisibility(View.VISIBLE);
            fetchpoultrydata();
        }

    }

    private void fetchpoultrydata() {
        PoultryListAdapterPur adapter = new PoultryListAdapterPur(getActivity(), catagoriesList.getResult().getUserFarms());
        recyclerView1.setAdapter(adapter);
    }

    private void fetchlayerdatadata() {
        purchasedetails a = new purchasedetails( "17/07/2020","Arun","188,601","18/07/2020","Paid");
        purchasedetails_List.add(a);
        a = new purchasedetails( "03/07/2020","Mahesh","363,784","05/07/2020", "Paid");
        purchasedetails_List.add(a);
        a = new purchasedetails( "19/05/2020","Roja","336,228","22/05/2020", "Paid");
        purchasedetails_List.add(a);
        a = new purchasedetails( "29/06/2020","Naveen","344,244","30/06/2020", "Paid");
        purchasedetails_List.add(a);

    }


    private void setviews() {


        purchaseheader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isClicked == false) {

                    purchaseselectionlyt.setVisibility(View.VISIBLE);
                    isClicked = true;
                }else if (isClicked == true){

                    purchaseselectionlyt.setVisibility(View.GONE);
                    isClicked = false;
                }
            }
        });
        totalamountsummery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               summerypopup();
            }
        });
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -30);

        Date datee = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        onemonthlast = format.format(datee);
        Log.i("onemonthlast", onemonthlast);
        fromdate.setText(onemonthlast);


        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
        currentdate = format1.format(myCalendar.getTime());
        Log.i("currentdate", currentdate+ "");
        todate.setText(currentdate);


        GetFeedPurchaseDetails();

        fromdate.addTextChangedListener(new ValidationTextWatcher(fromdate));
        todate.addTextChangedListener(new ValidationTextWatcher(todate));


        if (isOnline(getContext())) {

            GetLookUpData();
            GetAllTypeCdDmt();
            GetFeedPurchaseDetails();

        }
        else {
            showDialog(getActivity(), getResources().getString(R.string.Internet));
            //Toast.makeText(LoginActivity.this, "Please Check Internet Connection ", Toast.LENGTH_SHORT).show();
        }
//        List<String> spinnerArray =  new ArrayList<String>();
//        spinnerArray.add("Payment Status Type");
//        spinnerArray.add("Success");
//        spinnerArray.add("False");
//
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                getActivity(), android.R.layout.simple_spinner_item, spinnerArray);
//
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        paymentStatusSpinner.setAdapter(adapter);




//
//        ArrayAdapter<String> feedadapter = new ArrayAdapter<String>(
//                getActivity(), android.R.layout.simple_spinner_item, feedTypeArr);
//
//        feedadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        feedTypeSpinner.setAdapter(feedadapter);


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                if (isStartDate == true) {
                    updatestartdateLabel();
                }else {
                    updateenddateLabel();
                }
            }

        };

        fromdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                isStartDate = true;
                isEndDate = false;

                // TODO Auto-generated method stub
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        todate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                isStartDate = false;
                isEndDate = true;

                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validation()) {

                    isSearchClicked = true;
                    statslayout.setVisibility(View.VISIBLE);
                    GetFeedPurchaseDetails();

                    purchaseselectionlyt.setVisibility(View.GONE);

                }

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isSearchClicked = false;

                fromdate.setText(onemonthlast);
                todate.setText(currentdate);
                feedTypeSpinner.setSelection(0);
                paymentStatusSpinner.setSelection(0);
                GetFeedPurchaseDetails();


            }
        });

    }


    private void summerypopup() {
        GetFeedSummary();
        myDialog = new Dialog(getActivity());

        myDialog.setContentView(R.layout.amountsplitpopup);
        //myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        close_txt = (TextView) myDialog.findViewById(R.id.close_txt);
        terms_recycle = (RecyclerView) myDialog.findViewById(R.id.recycler_term);
        dueamount =(TextView) myDialog.findViewById(R.id.dueamount);
        close = (TextView) myDialog.findViewById(R.id.close);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        terms_recycle.setLayoutManager(mLayoutManager);
        // add OK and Cancel buttons

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        close_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        myDialog.show();

    }

    private void GetFeedSummary() {
        mdilogue.show();
        ApiService service = ServiceFactory.createRetrofitService(getContext(), ApiService.class);
        mSubscription = service.getduesummary(APIConstantURL.GetFeedSummary )
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<dueamountlist>() {
                    @Override
                    public void onCompleted() {
                        mdilogue.dismiss();

                    }
                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(dueamountlist dueamountlist) {

                        if (dueamountlist.getListResult().size() != 0 && dueamountlist.getListResult() != null) {

                            feedadapter = new FeedSummaryAdapter(getActivity(), dueamountlist.getListResult());
                            terms_recycle.setAdapter(feedadapter);
                            due_amount = feedadapter.grandTotal();

                            dueamount.setText(dff.format(due_amount));
                        }
                        else{

                        }
                    }


                });
    }

//    private void ShowPopup() {
//
//        myDialog.setContentView(R.layout.amountsplitpopup);
//        //myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//
//
//
//        // add OK and Cancel buttons
//
//        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
//        myDialog.show();
//
//    }


    private void GetAllTypeCdDmt() {

        get_payment.clear();
            mdilogue.show();
            ApiService service = ServiceFactory.createRetrofitService(getContext(), ApiService.class);
            mSubscription = service.getpaymenttype(APIConstantURL.GetAllTypeCdDmt )
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetAllTypeCdDmt>() {
                        @Override
                        public void onCompleted() {
                            mdilogue.dismiss();

                        }
                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(GetAllTypeCdDmt getAllTypeCdDmt) {



                            if (getAllTypeCdDmt.getListResult() != null) {
                                get_payment.add(" Payment Status Type");
                                for (GetAllTypeCdDmt.ListResult data : getAllTypeCdDmt.getListResult()
                                ) {
                                    get_payment.add(data.getDesc());
                                    get_payment_Id.add(data.getTypeCdId());
                                   // paymentstatusId = data.getTypeCdId();
                                }


                                ArrayAdapter aa = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,get_payment);
                                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                paymentStatusSpinner.setAdapter(aa);

                                paymentStatusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                                        String selected_state = paymentStatusSpinner.getItemAtPosition(paymentStatusSpinner.getSelectedItemPosition()).toString();
                                            //spin_plot_dist.setSelection(2);
                                   //paymentstatusId = get_payment_Id.get(paymentStatusSpinner.getSelectedItemPosition());
                                            Log.d("paymentstatusId111", "dist_id======" + paymentstatusId);



                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                        // DO Nothing here
                                    }
                                });



                            } else {
                                Log.e("nodada====", "nodata===custom2");

                            }

                        }

                    });
        }




    private void GetLookUpData() {
        get_feed.clear();
        mdilogue.show();
        ApiService service = ServiceFactory.createRetrofitService(getContext(), ApiService.class);
        mSubscription = service.getfeeddata(APIConstantURL.GetLookUpData )
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetLookUpData>() {
                    @Override
                    public void onCompleted() {
                        mdilogue.dismiss();

                    }
                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetLookUpData getLookUpData) {



                        if (getLookUpData.getListResult() != null) {
                           get_feed.add("Select");
                            for (GetLookUpData.ListResult data : getLookUpData.getListResult()
                            ) {
                                get_feed.add(data.getNAME());
                                get_feed_Id.add(data.getId());
                            }


                            ArrayAdapter aa = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,get_feed);
                            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            feedTypeSpinner.setAdapter(aa);

                            feedTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                                    String selected_state = feedTypeSpinner.getItemAtPosition(feedTypeSpinner.getSelectedItemPosition()).toString();
                                    //spin_plot_dist.setSelection(2);
                                  //  feedTypeId = get_feed_Id.get(feedTypeSpinner.getSelectedItemPosition());
                                    feedtypename =  get_feed.get(feedTypeSpinner.getSelectedItemPosition());

                                    Log.d("feedtypename11", feedtypename + "");

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {
                                    // DO Nothing here
                                }
                            });




                        } else {
                            Log.e("nodada====", "nodata===custom2");

                        }

                    }

                });
    }




    private void GetFeedPurchaseDetails() {
        mdilogue.show();
        JsonObject object = FeedPurchaseRequestObject();
        ApiService service = ServiceFactory.createRetrofitService(getActivity(), ApiService.class);
        mSubscription = service.postfeedpurchaseresponse(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FeedPurchaseresponse>() {
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
                    public void onNext(FeedPurchaseresponse feedPurchaseresponse) {
                        mdilogue.dismiss();
                        if (feedPurchaseresponse.getIsSuccess()) {
                            if (feedPurchaseresponse.getResult().getFeedTypes().size() != 0 && feedPurchaseresponse.getResult().getFeedTypes() != null) {
                                noData.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                                adapter = new FeedTypesListAdapter(getActivity(), feedPurchaseresponse.getResult().getFeedTypes(),feedPurchaseresponse.getResult().getFeedPurchaseDetails());
                              recyclerView.setAdapter(adapter);
                                mSubTotal = adapter.grandTotal();
                                mpaidTotal = adapter.grandpaidTotal();
                                outstandingAmount.setText(getString(R.string.Rs) + dff.format(mSubTotal));
                                paidAmount.setText(getString(R.string.Rs) + dff.format(mpaidTotal));




//                                adapter = new PurchaseListAdapter(getActivity(), feedPurchaseresponse.getResult().getFeedPurchaseDetails());
//                                recyclerView.setAdapter(adapter);
 //  mSubTotal = adapter.grandTotal();



//                                for (int i = 0; i<= feedPurchaseresponse.getResult().getFeedPurchaseDetails().size(); i++){
//
//                                    if ( feedPurchaseresponse.getResult().getFeedPurchaseDetails().get(i).getPaymentStatusId() == 28){
//
//                                        outstandingamt += feedPurchaseresponse.getResult().getFeedPurchaseDetails().get(i).getFinalAmount();
//
//                                        Log.d("outstandingamt", outstandingamt + "");
//
//                                    }
//
//
//
//                                    outstandingAmount.setText(outstandingamt + "");
//
//
//                                    if ( feedPurchaseresponse.getResult().getFeedPurchaseDetails().get(i).getPaymentStatusId() == 27){
//
//                                        paidamt += feedPurchaseresponse.getResult().getFeedPurchaseDetails().get(i).getFinalAmount();
//
//                                        Log.d("paidamt", paidamt + "");
//                                    }
//
//
//
//
//                                    paidAmount.setText(paidamt + "");
//
//                                }







                                Log.e("==============", mSubTotal + "paid========="+ mpaidTotal);
                            } else {
                                noData.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.GONE);
                                outstandingAmount.setText(getString(R.string.Rs) + "0.00");
                                paidAmount.setText(getString(R.string.Rs) + "0.00");
                            }

                        }
                    }


                });
    }

    private JsonObject FeedPurchaseRequestObject() {

        startdateStr = fromdate.getText().toString();
        enddateStr = todate.getText().toString();

        try {
            startDateformatted = formatDateFromDateString(DATE_FORMAT2, DATE_FORMAT1, startdateStr);
            endDateformatted = formatDateFromDateString(DATE_FORMAT2, DATE_FORMAT1, enddateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        FeedPurchaseObject feedPurchaseObject = new FeedPurchaseObject();


        if (isSearchClicked == true) {

            if (feedTypeSpinner.getSelectedItemPosition() == 0)
            feedPurchaseObject.setFeedTypeId(null);
            else
                feedPurchaseObject.setFeedTypeId(get_feed_Id.get(feedTypeSpinner.getSelectedItemPosition()-1));

            if (paymentStatusSpinner.getSelectedItemPosition() == 0)
                feedPurchaseObject.setPaymentStatusId(null);
            else

            feedPurchaseObject.setPaymentStatusId(get_payment_Id.get(paymentStatusSpinner.getSelectedItemPosition()-1));
            feedPurchaseObject.setFromDate(startDateformatted);
            feedPurchaseObject.setToDate(endDateformatted);
            feedPurchaseObject.setFarmId(Farm_Id);
        }

        if (isSearchClicked == false) {
            feedPurchaseObject.setFeedTypeId(null);
            feedPurchaseObject.setPaymentStatusId(null);
            feedPurchaseObject.setFromDate(startDateformatted);
            feedPurchaseObject.setToDate(endDateformatted);
            feedPurchaseObject.setFarmId(Farm_Id);
        }



        return new Gson().toJsonTree(feedPurchaseObject).getAsJsonObject();

    }


    private void updatestartdateLabel() {
        String myFormat = "dd-MM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        fromdate.setText(sdf.format(myCalendar.getTime()));
    }

    private void updateenddateLabel() {
        String myFormat = "dd-MM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        todate.setText(sdf.format(myCalendar.getTime()));
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

    @Override
    public void updated(int po, ArrayList<FeedPurchaseresponse.Result.FeedPurchaseDetail> dueamount) {

Log.d("due===amount",dueamount.get(0).getTotalAmounts()+"");

    }


    private class ValidationTextWatcher implements TextWatcher {

        private View view;

        private ValidationTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }


        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.pfromdate_edittxt:
                    validate_fromdate();
                    break;
                case R.id.ptodate_edittxt:
                    validate_todate();
                    break;
            }
        }
    }

    private boolean validate_fromdate() {
        if (TextUtils.isEmpty(fromdate.getText().toString())) {
            fromdatelbl.setError("Please Enter From Date");
            requestFocus(fromdate);
        } else {
            fromdatelbl.setErrorEnabled(false);

        }
        return true;
    }

    private boolean validate_todate() {
        if (TextUtils.isEmpty(todate.getText().toString())) {
            todatelbl.setError("Please Enter To Date");
            requestFocus(todate);
        } else {
            todatelbl.setErrorEnabled(false);

        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private boolean validation() {

        if (TextUtils.isEmpty(fromdate.getText().toString().trim())) {
            fromdatelbl.setError("Please Enter From Date");
            return false;
        } else if (TextUtils.isEmpty(todate.getText().toString().trim())) {
            //  passwordEdt.setError(getString(R.string.err_please_enter_password));
            todatelbl.setError("Please Enter To Date");
            return false;
        }

        return true;
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
