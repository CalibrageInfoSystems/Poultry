package com.cis.poultry.Fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cis.poultry.Adapter.PoultryListAdapter;
import com.cis.poultry.Adapter.SaleListAdapter;
import com.cis.poultry.Common.BaseFragment;
import com.cis.poultry.Models.Dashboardobject;
import com.cis.poultry.Models.LoginResponse;
import com.cis.poultry.Models.ManageRateDetailRequestObject;
import com.cis.poultry.Models.ManageRateDetailResp;
import com.cis.poultry.Models.VisitLogobject;
import com.cis.poultry.Models.getDashboardProdDetails;
import com.cis.poultry.Models.getDbPaymentsPackingAndProd;
import com.cis.poultry.R;
import com.cis.poultry.localData.SharedPrefsData;
import com.cis.poultry.service.APIConstantURL;
import com.cis.poultry.service.ApiService;
import com.cis.poultry.service.ServiceFactory;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment {
    public static String TAG = "HomeFragment";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextView layepercentage, growerspercentage, birdslayercount, birdsgrowerscount, birdschickscount, birdstotalvalue, prodcatonscount, prodopeningstock, ptotalcountt, mlaycount, mgrowerscount,
            mchicksscount, mtotalValue, telayers, tegrowers, tetotal, packerone, packertwo, date, packertotal, packeroneCount, packertwoCount, inwardpay, outwardpay, differencepay,closingStock;
    int diffvalue;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Subscription mSubscription;
    DatePickerDialog picker ;
    private SpotsDialog mdilogue;
    View view;
    Double differencepayamount;
    String datetimevaluereq;
    DecimalFormat dff = new DecimalFormat("##,##,##0.00");
    DecimalFormat dfc = new DecimalFormat("##,##,##0.0");
    DecimalFormat df = new DecimalFormat("##,##,##0");
    RecyclerView recyclerView;
    LoginResponse catagoriesList;
    public static final String DATE_FORMAT1 = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String DATE_FORMAT2 = "dd-MM-yyyy";
    public static Integer Farm_Id  ;
    String datee,reformattedDate,currentDate;
    private OnFragmentInteractionListener mListener;
RecyclerView saledetailsRecycleview;
TextView paperrate,pulprate,cullrate;
LinearLayout ManageRateDetails;
    private  double salepackeges =0.0;
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        view = inflater.inflate(R.layout.fragment_home, container, false);

        intviews();
        setviews();

        return view;
    }

    private void intviews() {
        catagoriesList = SharedPrefsData.getCatagories(getActivity());
        Farm_Id =catagoriesList.getResult().getUserFarms().get(0).getFarmId();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            Farm_Id = bundle.getInt("FARMid");
            Log.e("============Farm_Id",Farm_Id+"");

        }


        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(getContext())
                .setTheme(R.style.Custom)
                .build();

        layepercentage = view.findViewById(R.id.layepercentage);
        growerspercentage = view.findViewById(R.id.growerspercentage);
        birdslayercount = view.findViewById(R.id.layercount);
        birdsgrowerscount = view.findViewById(R.id.growerscount);
        birdschickscount = view.findViewById(R.id.chickscount);
        birdstotalvalue = view.findViewById(R.id.totalvalue);
        prodcatonscount = view.findViewById(R.id.prodCartonscount);
        prodopeningstock = view.findViewById(R.id.popeningStock);
        ptotalcountt = view.findViewById(R.id.total);
        mlaycount = view.findViewById(R.id.laycount);
        mgrowerscount = view.findViewById(R.id.growers_count);
        mchicksscount = view.findViewById(R.id.chicksscount);
        mtotalValue = view.findViewById(R.id.total_value);
        telayers = view.findViewById(R.id.layerscount);
        tegrowers = view.findViewById(R.id.egggrowscount);
        tetotal = view.findViewById(R.id.total_eggs);
        packerone = view.findViewById(R.id.packer1);
        saledetailsRecycleview = view.findViewById(R.id.saledetailsRecycleview);
        packertotal = view.findViewById(R.id.totalpackager);
        packeroneCount = view.findViewById(R.id.packone);
      ManageRateDetails = view.findViewById(R.id.ManageRateDetails);
        inwardpay = view.findViewById(R.id.paymentinwards);
        outwardpay = view.findViewById(R.id.paymentoutwards);
        differencepay = view.findViewById(R.id.difference);
        closingStock = view.findViewById(R.id.closingStock);
        date = view.findViewById(R.id.date);
        paperrate = view.findViewById(R.id.paperrate);
        pulprate = view.findViewById(R.id.pulprate);
        cullrate= view.findViewById(R.id.cullrate);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);

        recyclerView.setHasFixedSize(true);
        // recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        RecyclerView.LayoutManager mLayoutManager1 = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager1);

        saledetailsRecycleview.setHasFixedSize(true);
        // recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        RecyclerView.LayoutManager mLayoutManager3 = new GridLayoutManager(getActivity(), 1);
        saledetailsRecycleview.setLayoutManager(mLayoutManager3);
        //   request_List = catagoriesList.getResult().getUserFarms().size()
        if (catagoriesList.getResult().getUserFarms().size() == 1){
            recyclerView.setVisibility(View.GONE);

        }else {

            recyclerView.setVisibility(View.VISIBLE);
            fetchpoultrydata();
        }
    }

    private void fetchpoultrydata() {
        PoultryListAdapter adapter = new PoultryListAdapter(getActivity(), catagoriesList.getResult().getUserFarms());
        recyclerView.setAdapter(adapter);
    }


    private void setviews() {

        if (isOnline(getContext())) {
            getDBProductionDetailsnull();


        }
        else {
            showDialog(getActivity(), getResources().getString(R.string.Internet));

        }
        date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                currentDate = (dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                date.setText(currentDate);



                                SimpleDateFormat input = new SimpleDateFormat("dd-MM-yyyy");
                                SimpleDateFormat output = new SimpleDateFormat("yyyy/MM/dd");
                                try {
                                    Date oneWayTripDate = input.parse(currentDate);

                                    reformattedDate = output.format(oneWayTripDate);
                                    //  GetAll_tokens_closed();
                                    Log.e("===============", "======sending_date===========" + reformattedDate);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                if (isOnline(getContext())) {
                                    getDBProductionDetails(reformattedDate);
                                    getDbPaymentsPackingProd(reformattedDate);
                                    GetManageRateDetailsByDate(reformattedDate);
                                }
                                else {
                                    showDialog(getActivity(), getResources().getString(R.string.Internet));

                                }

                            }
                        }, year, month, day);
                picker.show();
                picker.getDatePicker().setMaxDate(System.currentTimeMillis());


            }


        });
    }

    private void GetManageRateDetailsByDate(String reformattedDate) {
        mdilogue.show();
        JsonObject object = ManageRateDetailRequestObject(reformattedDate);
        ApiService service = ServiceFactory.createRetrofitService(getActivity(), ApiService.class);
        mSubscription = service.postManageRate(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ManageRateDetailResp>() {
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
                    public void onNext(ManageRateDetailResp manageRateDetailResp) {




                        mdilogue.dismiss();
                        if (manageRateDetailResp.getIsSuccess()) {
                            if( manageRateDetailResp.getResult()!=null){
                                ManageRateDetails.setVisibility(View.VISIBLE);
                                paperrate.setText(dff.format(manageRateDetailResp.getResult().getNECCRate()));
                                pulprate.setText(dff.format(manageRateDetailResp.getResult().getPulpRate()));
                                cullrate.setText(dff.format(manageRateDetailResp.getResult().getCullRate()));
                            }
                         else{
                                ManageRateDetails.setVisibility(View.GONE);
                            }
                       }}});}


    private JsonObject ManageRateDetailRequestObject(String reformattedDate) {
        ManageRateDetailRequestObject requestModel = new ManageRateDetailRequestObject();
        requestModel.setDate(reformattedDate);
        requestModel.setFarmId(Farm_Id);


        return new Gson().toJsonTree(requestModel).getAsJsonObject();
    }

    private void getDBProductionDetailsnull() {
        mdilogue.show();
        JsonObject object = DashboardObject();
        ApiService service = ServiceFactory.createRetrofitService(getActivity(), ApiService.class);
        mSubscription = service.postdashboarddetails(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<getDashboardProdDetails>() {
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
                    public void onNext(getDashboardProdDetails getDashboardProdDetails) {


                        mdilogue.dismiss();
                        if (getDashboardProdDetails.getIsSuccess()) {
                           if( getDashboardProdDetails.getListResult().get(0).getDate()!=null){
                            reformattedDate = getDashboardProdDetails.getListResult().get(0).getDate();}
                           else{
                               datee = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                               date.setText(datee);

                               SimpleDateFormat input = new SimpleDateFormat("dd-MM-yyyy");
                               SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                               try {
                                   Date oneWayTripDate = input.parse(datee);

                                   reformattedDate = output.format(oneWayTripDate);
                                   //  GetAll_tokens_closed();
                                   Log.e("===============", "======sending_date===========" + reformattedDate);
                               } catch (ParseException e) {
                                   e.printStackTrace();
                               }
                           }

                            //  currentDate = (dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            Log.e("Logdate===============",reformattedDate);



                            try {
                                currentDate = formatDateFromDateString(DATE_FORMAT1, DATE_FORMAT2, reformattedDate);
                                date.setText(currentDate);

                                getDBProductionDetails(reformattedDate);
                                getDbPaymentsPackingProd(reformattedDate);
                                GetManageRateDetailsByDate(reformattedDate);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                        }}});}

    public static String formatDateFromDateString(String inputDateFormat, String outputDateFormat, String inputDate) throws ParseException {
        Date mParsedDate;
        String mOutputDateString;
        SimpleDateFormat mInputDateFormat = new SimpleDateFormat(inputDateFormat, java.util.Locale.getDefault());
        SimpleDateFormat mOutputDateFormat = new SimpleDateFormat(outputDateFormat, java.util.Locale.getDefault());
        mParsedDate = mInputDateFormat.parse(inputDate);
        mOutputDateString = mOutputDateFormat.format(mParsedDate);
        return mOutputDateString;

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
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    private void getDBProductionDetails(String reformattedDate) {


        mdilogue.show();
        JsonObject object = DashboardObjectt(reformattedDate);
        ApiService service = ServiceFactory.createRetrofitService(getActivity(), ApiService.class);
        mSubscription = service.postdashboarddetails(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<getDashboardProdDetails>() {
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
                    public void onNext(getDashboardProdDetails getDashboardProdDetails) {


                        mdilogue.dismiss();
                        if (getDashboardProdDetails.getIsSuccess()) {


                            List<getDashboardProdDetails.ListResult> getDashboardProdDetailsList = getDashboardProdDetails.getListResult();

                            Log.d("getDBLayerProduction", getDashboardProdDetailsList.get(0).getLayerProduction() + "");
                            layepercentage.setText(getDashboardProdDetailsList.get(0).getLayerProduction() + " %");
                            growerspercentage.setText(getDashboardProdDetailsList.get(0).getGrowerProduction() + " %");
                            if (getDashboardProdDetailsList.get(0).getLayerChicks() == null) {
                                birdslayercount.setText("0");
                            } else {
                                birdslayercount.setText(df.format(getDashboardProdDetailsList.get(0).getLayerChicks()));
                            }

                            if (getDashboardProdDetailsList.get(0).getGrowerChicks() == null) {
                                birdsgrowerscount.setText("0");
                            } else {
                                birdsgrowerscount.setText(df.format(getDashboardProdDetailsList.get(0).getGrowerChicks()));
                            }

                            if (getDashboardProdDetailsList.get(0).getChickShedChicks() == null) {
                                birdschickscount.setText("0");
                            } else {
                                birdschickscount.setText(df.format(getDashboardProdDetailsList.get(0).getChickShedChicks()));
                            }
                            if (getDashboardProdDetailsList.get(0).getLayerMortality() == null) {
                                mlaycount.setText("0");
                            } else {
                                mlaycount.setText(getDashboardProdDetailsList.get(0).getLayerMortality() + "");
                            }
                            if (getDashboardProdDetailsList.get(0).getGrowerMortality() == null) {
                                mgrowerscount.setText("0");
                            } else {
                                mgrowerscount.setText(getDashboardProdDetailsList.get(0).getGrowerMortality() + "");
                            }
                            if (getDashboardProdDetailsList.get(0).getChickMortality() == null) {
                                mchicksscount.setText("0");
                            } else {
                                mchicksscount.setText(getDashboardProdDetailsList.get(0).getChickMortality() + "");
                            }
                            if (getDashboardProdDetailsList.get(0).getLayerChicks() == null || getDashboardProdDetailsList.get(0).getGrowerChicks() == null || getDashboardProdDetailsList.get(0).getChickShedChicks() == null) {
                                birdstotalvalue.setText("0");
                            } else {
                                birdstotalvalue.setText(df.format((getDashboardProdDetailsList.get(0).getLayerChicks() + getDashboardProdDetailsList.get(0).getGrowerChicks() + getDashboardProdDetailsList.get(0).getChickShedChicks())));

                            }
                            if (getDashboardProdDetailsList.get(0).getLayerMortality() == null || getDashboardProdDetailsList.get(0).getGrowerMortality() == null || getDashboardProdDetailsList.get(0).getChickMortality() == null) {
                                mtotalValue.setText("0");
                            } else {
                                mtotalValue.setText(df.format(getDashboardProdDetailsList.get(0).getLayerMortality() + getDashboardProdDetailsList.get(0).getGrowerMortality() + getDashboardProdDetailsList.get(0).getChickMortality()));
                            }
//                          int totalbirds = getDashboardProdDetailsList.get(0).getLayerChicks() + getDashboardProdDetailsList.get(0).getGrowerChicks() + getDashboardProdDetailsList.get(0).getChickShedChicks();
//                           int totalMortality = getDashboardProdDetailsList.get(0).getLayerMortality() + getDashboardProdDetailsList.get(0).getGrowerMortality() + getDashboardProdDetailsList.get(0).getChickMortality();
//                           Double totaleggs = getDashboardProdDetailsList.get(0).getLayerEggs() + getDashboardProdDetailsList.get(0).getGrowerEggs();


                            //  mtotalValue.setText(Integer.parseInt(mlaycount.getText().toString()+mgrowerscount.getText().toString() +mchicksscount.getText().toString() ));

                            if (getDashboardProdDetailsList.get(0).getLayerEggs() == null) {
                                telayers.setText("0");
                            } else {
                                telayers.setText(df.format(getDashboardProdDetailsList.get(0).getLayerEggs()));
                            }
                            if (getDashboardProdDetailsList.get(0).getGrowerEggs() == null) {
                                tegrowers.setText("0");
                            } else {
                                tegrowers.setText(df.format(getDashboardProdDetailsList.get(0).getGrowerEggs()));
                            }
                            if (getDashboardProdDetailsList.get(0).getLayerEggs() == null || getDashboardProdDetailsList.get(0).getGrowerEggs() == null) {
                                tetotal.setText("0");
                            } else {
                                tetotal.setText(df.format(getDashboardProdDetailsList.get(0).getLayerEggs() + getDashboardProdDetailsList.get(0).getGrowerEggs()));
                            }
                            // tetotal.setText(Integer.parseInt(tegrowers.getText().toString() +tegrowers.getText().toString() ));


                        } else {
                            Log.e("nodada====", "nodata===custom2");

                        }

                    }

                });
    }


    private void getDbPaymentsPackingProd(String reformattedDate) {
        mdilogue.show();

        JsonObject object = DashboardObjectt(reformattedDate);
        ApiService service = ServiceFactory.createRetrofitService(getActivity(), ApiService.class);
        mSubscription = service.postdashboardpackdetails(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<getDbPaymentsPackingAndProd>() {
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
                    public void onNext(getDbPaymentsPackingAndProd paymentsPackingAndProd) {
                        mdilogue.dismiss();
                        if (paymentsPackingAndProd.getIsSuccess() == true) {
                            Log.e("=============data", "=============1");
                            // Log.e("=============data",paymentsPackingAndProd.getResult().getDailyStock().get(0).getProduction()+"");

                            if (paymentsPackingAndProd.getResult().getDailyStock().size() != 0 && paymentsPackingAndProd.getResult().getDailyStock() != null) {
                                prodcatonscount.setText(dfc.format(paymentsPackingAndProd.getResult().getDailyStock().get(0).getProduction() ));
                                prodopeningstock.setText(dfc.format(paymentsPackingAndProd.getResult().getDailyStock().get(0).getOpeningStock()));
                                ptotalcountt.setText(dfc.format(paymentsPackingAndProd.getResult().getDailyStock().get(0).getTotal() ));
                                double total_amount =paymentsPackingAndProd.getResult().getDailyStock().get(0).getTotal();
                                double packing = paymentsPackingAndProd.getResult().getDailyStock().get(0).getPacking();
                                double close_stock =total_amount - packing;
                                closingStock.setText(dfc.format(paymentsPackingAndProd.getResult().getDailyStock().get(0).getClosingBalance()));
                            } else {
                                prodcatonscount.setText("0");
                                prodopeningstock.setText("0");
                                ptotalcountt.setText("0");
                                closingStock.setText("0");
                            }

                            if (paymentsPackingAndProd.getResult().getSales().size() != 0 && paymentsPackingAndProd.getResult().getSales() != null) {
                                SaleListAdapter adapter = new SaleListAdapter(getActivity(), paymentsPackingAndProd.getResult().getSales());
                                saledetailsRecycleview.setAdapter(adapter);

                                salepackeges = adapter.grandTotal();
//
//                                packerone.setText(paymentsPackingAndProd.getResult().getSales().get(0).getName() + "");
//                                packeroneCount.setText(df.format(paymentsPackingAndProd.getResult().getSales().get(0).getPackedBoxes()));
                              packertotal.setText(salepackeges +"");
                            } else {
//                                packerone.setText( "");
//                                packeroneCount.setText("");
                              packertotal.setText("0");
                            }
                            //   inwardpay.setText(getString(R.string.Rs) +paymentsPackingAndProd.getResult().getInwardPayment().getInwardPayment());
                            if (paymentsPackingAndProd.getResult().getInwardPayment().getInwardPayment() == null) {
                                inwardpay.setText(getString(R.string.Rs) + "0.00");
                                differencepayamount = paymentsPackingAndProd.getResult().getOutwardPayment().getOutwardPayment() ;
                            } else {

                                inwardpay.setText(getString(R.string.Rs) + dff.format(paymentsPackingAndProd.getResult().getInwardPayment().getInwardPayment()));
                            }

                            if (paymentsPackingAndProd.getResult().getOutwardPayment().getOutwardPayment() == null) {
                                 differencepayamount = paymentsPackingAndProd.getResult().getInwardPayment().getInwardPayment() ;
                                outwardpay.setText(getString(R.string.Rs) + "0.00");
                            } else {

                                outwardpay.setText(getString(R.string.Rs) + dff.format(paymentsPackingAndProd.getResult().getOutwardPayment().getOutwardPayment()));
                            }

                            if (paymentsPackingAndProd.getResult().getInwardPayment().getInwardPayment() == null || paymentsPackingAndProd.getResult().getOutwardPayment().getOutwardPayment() == null) {
                                differencepay.setText(getString(R.string.Rs) + "0.00");
                            } else {
                                differencepayamount = paymentsPackingAndProd.getResult().getInwardPayment().getInwardPayment() - paymentsPackingAndProd.getResult().getOutwardPayment().getOutwardPayment();
                                Log.e("===========>", differencepayamount + "");
                            }
                                DecimalFormat format = new DecimalFormat("#0,00,000.00");
                                String formatteddifference = dff.format(differencepayamount);
                                if(differencepayamount > 0.0)
                                {
                                    Log.e("===========>",differencepayamount+" is a positive number");

                                    differencepay.setTextColor(Color.parseColor("#3adf9a"));
                                    differencepay.setText(getString(R.string.Rs) + formatteddifference);
                                }
                                else if(differencepayamount < 0.0)
                                {
                                    differencepay.setTextColor(Color.parseColor("#FFC93437"));
                                    Log.e("===========>",differencepayamount+" is a negative number");

                                    differencepay.setText(getString(R.string.Rs) + formatteddifference);
                                }




                            Log.d("getDbPaymentPackingProd", "isSuccess");

                        } else {
                            Log.e("nodada====", "nodata===custom2");

                        }

                    }

                });
    }




    private JsonObject DashboardObject() {
        Dashboardobject requestModel = new Dashboardobject();
        requestModel.setVisitedDate(null);
        requestModel.setFarmId(Farm_Id);


        return new Gson().toJsonTree(requestModel).getAsJsonObject();

    }

    private JsonObject DashboardObjectt(String reformattedDate) {
        Dashboardobject requestModel = new Dashboardobject();
        requestModel.setVisitedDate(reformattedDate);
        requestModel.setFarmId(Farm_Id);


        return new Gson().toJsonTree(requestModel).getAsJsonObject();

    }

}
