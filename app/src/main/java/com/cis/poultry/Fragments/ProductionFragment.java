package com.cis.poultry.Fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.cis.poultry.Adapter.PoultryListAdapterP;
import com.cis.poultry.Adapter.ProducttestListAdapter;
import com.cis.poultry.Common.BaseFragment;
import com.cis.poultry.Models.GetVisitLogByDateResponse;
import com.cis.poultry.Models.LoginResponse;
import com.cis.poultry.Models.VisitLogobject;
import com.cis.poultry.Models.productdata;
import com.cis.poultry.R;
import com.cis.poultry.localData.SharedPrefsData;
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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductionFragment extends BaseFragment {
    public static String TAG = "ProductionFragment";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private List<productdata> product_List = new ArrayList<>();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
   RecyclerView recyclerview;
    View view;
    TextView availablebirds,moralitycount,remaingbirds,total_eggs,cartonscount,productonpercentage,num_eggs,damagedeggs,feed;
    private Subscription mSubscription;
    private SpotsDialog mdilogue;
    private OnFragmentInteractionListener mListener;
    RecyclerView recyclerView;
    EditText date;
    String currentDate,datee,reformattedDate;
    int Year, Month, Day, Hour, Minute,clusterId;
    DatePickerDialog picker ;
    Calendar calendar ;
    RecyclerView recyclerView1;
    LoginResponse catagoriesList;
    public static  Integer Farm_Id ;
    Integer ShedTypeId ;
    public static final String DATE_FORMAT1 = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String DATE_FORMAT2 = "dd-MM-yyyy";
    DecimalFormat df = new DecimalFormat("##,##,##0");
    TextView lay_count,growerscount,chicks_scount,totalvalue,mlaycount,mgrowers_count,mchicksscount,mtotal_value,noData;
    public ProductionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductionFragment newInstance(String param1, String param2) {
        ProductionFragment fragment = new ProductionFragment();
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
         view = inflater.inflate(R.layout.fragment_production,
                container, false);
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

        availablebirds =view.findViewById(R.id.availablebirdss);
        moralitycount = view.findViewById(R.id.moralitycount);
        remaingbirds =view.findViewById(R.id.remaingbirds);
        total_eggs = view.findViewById(R.id.total_eggs);
        cartonscount =view.findViewById(R.id.cartonscount);
        productonpercentage = view.findViewById(R.id.productonpercentage);
        num_eggs= view.findViewById(R.id.num_eggs);
        damagedeggs = view.findViewById(R.id.damagedeggs);
        feed =view.findViewById(R.id.feed);
        lay_count = view.findViewById(R.id.lay_count);
        growerscount =view.findViewById(R.id.growerscount);
        chicks_scount = view.findViewById(R.id.chicks_scount);
        totalvalue =view.findViewById(R.id.totalvalue);
        noData =view.findViewById(R.id.noData);
      //  mlaycount,mgrowers_count,mchicksscount,mtotal_value
        mlaycount = view.findViewById(R.id.mlaycount);
        mgrowers_count =view.findViewById(R.id.mgrowers_count);
        mchicksscount = view.findViewById(R.id.mchicksscount);
        mtotal_value =view.findViewById(R.id.mtotal_value);
         recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        date =view.findViewById(R.id.date);
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
        //   request_List = catagoriesList.getResult().getUserFarms().size()




    }

    private void fetchpoultrydata() {
        PoultryListAdapterP adapter = new PoultryListAdapterP(getActivity(), catagoriesList.getResult().getUserFarms());
        recyclerView1.setAdapter(adapter);
    }

    private void GetVisitLogByDate(String reformattedDate) {
        mdilogue.show();
        JsonObject object =VisitLogObjectt(reformattedDate);
        ApiService service = ServiceFactory.createRetrofitService(getActivity(), ApiService.class);
        mSubscription = service.postVisitLogByDate(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetVisitLogByDateResponse>() {
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
                    public void onNext(GetVisitLogByDateResponse getVisitLogByDateResponse) {
                        mdilogue.dismiss();
                        if(getVisitLogByDateResponse.getIsSuccess()) {
//                            Log.d("getVisitLogByDateResponse===1", getVisitLogByDateResponse.getResult().getVisitLogDetails() + "");
//
//                            Log.d("getVisitLogByDateResponse====", getVisitLogByDateResponse.getResult().getVisitLogSummary().get(0).getDamagedEggs() + "");
                            if (getVisitLogByDateResponse.getResult().getShedTypes().size() != 0) {
                                noData.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                                ProducttestListAdapter adapter = new ProducttestListAdapter(getActivity(), getVisitLogByDateResponse.getResult().getShedTypes(),getVisitLogByDateResponse.getResult().getVisitLogDetails());
                                recyclerView.setAdapter(adapter);
                            }
                            else {
                                noData.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.GONE);
                            }




                            if (getVisitLogByDateResponse.getResult().getVisitLogSummary().size() != 0) {



                                double Feed = 0.0;

                                double Availablebirds = 0.0;
                                double remainingbirds = 0.0;
                                double morality = 0.0;
                                double totalfeed = 0.0;
                                double hurt = 0.0;
                                double totalAvailablebirds = 0.0;
                                double totalremainingbirds = 0.0;
                                double totalmorality = 0.0;
                                double totalhurt = 0.0;
                                for (int i = 0; i < getVisitLogByDateResponse.getResult().getVisitLogSummary().size(); i++) {
                                    ShedTypeId = getVisitLogByDateResponse.getResult().getVisitLogSummary().get(i).getShedTypeId();
                                    Log.e("==========ShedTypeId",ShedTypeId+"");


//
                                    if (getVisitLogByDateResponse.getResult().getVisitLogSummary().get(i).getRemainingBirds() == null){
                                        remainingbirds = 0.0;}
                                    else {
                                        remainingbirds = getVisitLogByDateResponse.getResult().getVisitLogSummary().get(i).getRemainingBirds();
                                    }

                                    totalremainingbirds += remainingbirds;

                                    if (getVisitLogByDateResponse.getResult().getVisitLogSummary().get(i).getOpeningBirds() == null){
                                        Availablebirds = 0.0;}
                                    else {
                                        Availablebirds = getVisitLogByDateResponse.getResult().getVisitLogSummary().get(i).getOpeningBirds();
                                    }
                                    totalAvailablebirds += Availablebirds;
                                    if (getVisitLogByDateResponse.getResult().getVisitLogSummary().get(i).getDied() == null){
                                        morality = 0.0;}
                                    else {
                                        morality = getVisitLogByDateResponse.getResult().getVisitLogSummary().get(i).getDied();
                                    }
                                    if (getVisitLogByDateResponse.getResult().getVisitLogSummary().get(i).getHurt() == null){
                                        hurt = 0.0;}
                                    else {
                                        hurt = getVisitLogByDateResponse.getResult().getVisitLogSummary().get(i).getHurt();
                                    }

                                    totalmorality += morality;



                                    totalhurt += hurt;

                                    if (getVisitLogByDateResponse.getResult().getVisitLogSummary().get(i).getFeed() == null)
                                        Feed = 0.0;
                                    else {
                                        Feed = getVisitLogByDateResponse.getResult().getVisitLogSummary().get(i).getFeed();
                                    }

                                    totalfeed += Feed;

                                    if (ShedTypeId == 4) {
                                        if(getVisitLogByDateResponse.getResult().getVisitLogSummary().get(i).getPercentage()!=null)
                                            productonpercentage.setText(getVisitLogByDateResponse.getResult().getVisitLogSummary().get(i).getPercentage() + "");
                                        else
                                            productonpercentage.setText("0");
                                        if(getVisitLogByDateResponse.getResult().getVisitLogSummary().get(i).getTotalEggs()!=null)
                                        total_eggs.setText(df.format(getVisitLogByDateResponse.getResult().getVisitLogSummary().get(i).getTotalEggs()));
                                        else
                                            total_eggs.setText("0");
                                        if(getVisitLogByDateResponse.getResult().getVisitLogSummary().get(i).getNumberofEggs()!=null)

                                        num_eggs .setText(df.format(getVisitLogByDateResponse.getResult().getVisitLogSummary().get(i).getNumberofEggs()));
                                        else
                                            num_eggs .setText("0");
                                        if(getVisitLogByDateResponse.getResult().getVisitLogSummary().get(i).getDamagedEggs()!=null)
                                        damagedeggs.setText(df.format(getVisitLogByDateResponse.getResult().getVisitLogSummary().get(i).getDamagedEggs()));
                                        else
                                            damagedeggs.setText("0");
                                        if (getVisitLogByDateResponse.getResult().getVisitLogSummary().get(i).getCartons() == null)
                                            cartonscount.setText("0");
                                        else

                                            cartonscount.setText(getVisitLogByDateResponse.getResult().getVisitLogSummary().get(i).getCartons() + "");
                                    }
                                    else{
                                        productonpercentage.setText("0"+ "%");
                                        total_eggs.setText("0");
                                        num_eggs .setText("0");
                                        damagedeggs.setText("0");

                                    }



                                    mtotal_value.setText(df.format(totalmorality + totalhurt));

                                    availablebirds.setText(df.format(totalAvailablebirds));
                                    remaingbirds.setText(df.format(totalremainingbirds));
                                    totalvalue.setText(df.format(totalAvailablebirds));
                                    moralitycount.setText(df.format(totalmorality + totalhurt));
                                    feed.setText(df.format(totalfeed));



                                }
                            }
                            else{
                                moralitycount.setText("0");
                                availablebirds.setText("0");
                                remaingbirds.setText( "0");
                                total_eggs.setText("0");
                                cartonscount.setText( "0");
                                productonpercentage.setText("0");
                                lay_count.setText( "0");
                                growerscount.setText("0");
                                chicks_scount.setText("0");
                                totalvalue.setText("0");
                                mlaycount.setText("0");
                                mgrowers_count.setText("0");
                                mchicksscount.setText("0");
                                mtotal_value.setText( "0");

                                num_eggs .setText("0");
                                damagedeggs.setText("0");
                                feed.setText("0");
                            }
                            //  mlaycount,mgrowers_count,mchicksscount,mtotal_value
                            Log.e("=============188", getVisitLogByDateResponse.getResult().getShedTypes().size()+"");
                            Log.e("=============189", getVisitLogByDateResponse.getResult().getVisitLogDetails().size()+"");
//
//                            if(getVisitLogByDateResponse.getResult().getVisitLogDetails()!= null && getVisitLogByDateResponse.getResult().getVisitLogDetails().size()!=0 ){
//                                ProductListAdapter adapter = new ProductListAdapter(getActivity(), getVisitLogByDateResponse.getResult().getVisitLogDetails());
//                                recyclerView.setAdapter(adapter);
//                            }
                        }



                    }




                });}
//    private void GetVisitLogByDate() {
//        mdilogue.show();
//        JsonObject object =VisitLogObject();
//        ApiService service = ServiceFactory.createRetrofitService(getActivity(), ApiService.class);
//        mSubscription = service.postVisitLogByDate(object)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<GetVisitLogByDateResponse>() {
//                    @Override
//                    public void onCompleted() {
//                        mdilogue.dismiss();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        if (e instanceof HttpException) {
//                            ((HttpException) e).code();
//                            ((HttpException) e).message();
//                            ((HttpException) e).response().errorBody();
//                            try {
//                                ((HttpException) e).response().errorBody().string();
//                            } catch (IOException e1) {
//                                e1.printStackTrace();
//                            }
//                            e.printStackTrace();
//                        }
//                        mdilogue.dismiss();
//                       // showDialog(getActivity(), getString(R.string.server_error));
//                    }
//
//                    @Override
//                    public void onNext(GetVisitLogByDateResponse getVisitLogByDateResponse) {
//                        mdilogue.dismiss();
//                        if(getVisitLogByDateResponse.getIsSuccess()) {
////                            Log.d("getVisitLogByDateResponse===1", getVisitLogByDateResponse.getResult().getVisitLogDetails() + "");
////
////                            Log.d("getVisitLogByDateResponse====", getVisitLogByDateResponse.getResult().getVisitLogSummary().get(0).getDamagedEggs() + "");
//                            if (getVisitLogByDateResponse.getResult().getVisitLogDetails().size() != 0) {
//                                noData.setVisibility(View.GONE);
//                                recyclerView.setVisibility(View.VISIBLE);
//                                ProductListAdapter adapter = new ProductListAdapter(getActivity(), getVisitLogByDateResponse.getResult().getVisitLogDetails());
//                                recyclerView.setAdapter(adapter);
//                            }
//                            else {
//                                noData.setVisibility(View.VISIBLE);
//                                recyclerView.setVisibility(View.GONE);
//                            }
//                            if (getVisitLogByDateResponse.getResult().getVisitLogSummary().size() != 0){
//                                moralitycount.setText(getVisitLogByDateResponse.getResult().getVisitLogSummary().get(0).getDied() + "");
//                                availablebirds.setText(getVisitLogByDateResponse.getResult().getVisitLogSummary().get(0).getOpeningBirds() + "");
//                                remaingbirds.setText(getVisitLogByDateResponse.getResult().getVisitLogSummary().get(0).getRemainingBirds() + "");
//                            total_eggs.setText(getVisitLogByDateResponse.getResult().getVisitLogSummary().get(0).getTotalEggs() + "");
//                            cartonscount.setText(getVisitLogByDateResponse.getResult().getVisitLogSummary().get(0).getFeed() + "");
//                            productonpercentage.setText(getVisitLogByDateResponse.getResult().getVisitLogSummary().get(0).getPercentage() + "");
//                            lay_count.setText(getVisitLogByDateResponse.getResult().getVisitLogSummary().get(0).getOpeningBirds() + "");
//                            growerscount.setText("0");
//                            chicks_scount.setText("0");
//                            totalvalue.setText(getVisitLogByDateResponse.getResult().getVisitLogSummary().get(0).getOpeningBirds() + "");
//                                mlaycount.setText(getVisitLogByDateResponse.getResult().getVisitLogSummary().get(0).getDied() + "");
//                                mgrowers_count.setText("0");
//                                mchicksscount.setText("0");
//                                mtotal_value.setText(getVisitLogByDateResponse.getResult().getVisitLogSummary().get(0).getDied() + "");
//
//                        }
//                            else{
//                                moralitycount.setText("0");
//                                availablebirds.setText("0");
//                                remaingbirds.setText( "0");
//                                total_eggs.setText("0");
//                                cartonscount.setText( "0");
//                                productonpercentage.setText("0");
//                                lay_count.setText( "0");
//                                growerscount.setText("0");
//                                chicks_scount.setText("0");
//                                totalvalue.setText("0");
//                                mlaycount.setText("0");
//                                mgrowers_count.setText("0");
//                                mchicksscount.setText("0");
//                                mtotal_value.setText( "0");
//                            }
//                            //  mlaycount,mgrowers_count,mchicksscount,mtotal_value
//                            Log.e("=============188", getVisitLogByDateResponse.getResult().getShedTypes().size()+"");
//                            Log.e("=============189", getVisitLogByDateResponse.getResult().getVisitLogDetails().size()+"");
////
////                            if(getVisitLogByDateResponse.getResult().getVisitLogDetails()!= null && getVisitLogByDateResponse.getResult().getVisitLogDetails().size()!=0 ){
////                                ProductListAdapter adapter = new ProductListAdapter(getActivity(), getVisitLogByDateResponse.getResult().getVisitLogDetails());
////                                recyclerView.setAdapter(adapter);
////                            }
//                        }
//
//
//
//                    }
//
//
//
//
//                });}

    private JsonObject VisitLogObject() {

        VisitLogobject requestModel = new VisitLogobject();
        requestModel.setVisitedDate(null);
        requestModel.setFarmId(Farm_Id);


        return new Gson().toJsonTree(requestModel).getAsJsonObject();

}
    private JsonObject VisitLogObjectt(String reformattedDate) {

        VisitLogobject requestModel = new VisitLogobject();
        requestModel.setVisitedDate(reformattedDate);
        requestModel.setFarmId(Farm_Id);


        return new Gson().toJsonTree(requestModel).getAsJsonObject();

    }

    private void setviews() {
//        datee = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
//        date.setText(datee);
//
//        SimpleDateFormat input = new SimpleDateFormat("dd-MM-yyyy");
//        SimpleDateFormat output = new SimpleDateFormat("yyyy/MM/dd");
//        try {
//            Date oneWayTripDate = input.parse(datee);
//
//            reformattedDate = output.format(oneWayTripDate);
//            //  GetAll_tokens_closed();
//            Log.e("===============", "======sending_date===========" + reformattedDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        if (isOnline(getContext())) {
            GetVisitLogBynulldate();
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
                                growerscount.setText("0");
                                mgrowers_count.setText("0");
                                lay_count.setText("0");
                                mlaycount.setText("0");
                                chicks_scount.setText("0");
                                mchicksscount.setText("0");
                                if (isOnline(getContext())) {
                                    GetVisitLogByDate(reformattedDate);
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

    private void GetVisitLogBynulldate() {
        mdilogue.show();
        JsonObject object = VisitLogObject();
        ApiService service = ServiceFactory.createRetrofitService(getActivity(), ApiService.class);
        mSubscription = service.postVisitLogByDate(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetVisitLogByDateResponse>() {
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
                    public void onNext(GetVisitLogByDateResponse getVisitLogByDateResponse) {
                        mdilogue.dismiss();
                        if (getVisitLogByDateResponse.getIsSuccess()) {
//                            Log.d("getVisitLogByDateResponse===1", getVisitLogByDateResponse.getResult().getVisitLogDetails() + "");
//
//                            Log.d("getVisitLogByDateResponse====", getVisitLogByDateResponse.getResult().getVisitLogSummary().get(0).getDamagedEggs() + "");
                            if (getVisitLogByDateResponse.getResult().getShedTypes().size() != 0) {
                                reformattedDate = getVisitLogByDateResponse.getResult().getShedTypes().get(0).getLogDate();
                              //  currentDate = (dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

Log.e("Logdate===============",reformattedDate);



                                try {
                                    currentDate = formatDateFromDateString(DATE_FORMAT1, DATE_FORMAT2, reformattedDate);
                                    date.setText(currentDate);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                GetVisitLogByDate(reformattedDate);
                            }
                        }
                    }
                });
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
