package com.cis.poultry.Fragments;

import android.app.DatePickerDialog;
import android.content.Context;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cis.poultry.Adapter.PoultryListAdapterP;
import com.cis.poultry.Adapter.PoultryListAdapterST;
import com.cis.poultry.Adapter.ProductListAdapter;
import com.cis.poultry.Adapter.StockListAdapter;
import com.cis.poultry.Common.BaseFragment;
import com.cis.poultry.DashboardActivity;
import com.cis.poultry.Models.EggStockRegisterResponse;
import com.cis.poultry.Models.EggStockRegisterobject;
import com.cis.poultry.Models.GetVisitLogByDateResponse;
import com.cis.poultry.Models.LoginResponse;
import com.cis.poultry.Models.VisitLogobject;
import com.cis.poultry.Models.productdata;
import com.cis.poultry.R;
import com.cis.poultry.localData.SharedPrefsData;
import com.cis.poultry.service.ApiService;
import com.cis.poultry.service.ServiceFactory;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
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
 * {@link StockFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StockFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StockFragment extends BaseFragment {
    public static String TAG = "StockFragment";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private List<productdata> product_List = new ArrayList<>();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
   View view;
    Boolean isStartDate = false;
    Boolean isEndDate = false;
   EditText fromdate, todate;
   TextInputLayout fromDatelbl, toDatelbl;
    private Subscription mSubscription;
    private SpotsDialog mdilogue;
    private OnFragmentInteractionListener mListener;
    RecyclerView recyclerView;
    LinearLayout statslayout, stockdateselection;
    String onemonthlast, currentdate;
    ImageButton stockheader;
    Boolean isClicked = false;

    TextView clear;
    Button search;
    final Calendar myCalendar = Calendar.getInstance();

    public String startdateStr, startDateformatted;
    public String enddateStr, endDateformatted;
    public static final String DATE_FORMAT1 = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String DATE_FORMAT2 = "dd-MM-yyyy";
    RecyclerView recyclerView1;
    LoginResponse catagoriesList;
    public static  Integer Farm_Id ;
    public StockFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StockFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StockFragment newInstance(String param1, String param2) {
        StockFragment fragment = new StockFragment();
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
        view = inflater.inflate(R.layout.fragment_stock,
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

        fromdate = view.findViewById(R.id.fromdate_edittxt);
        todate = view.findViewById(R.id.todate_edittxt);
        fromDatelbl = view.findViewById(R.id.fromdate_label);
        toDatelbl = view.findViewById(R.id.todate_label);


        stockdateselection = view.findViewById(R.id.stockdateselection);
        stockheader = view.findViewById(R.id.stockheader);

        search = view.findViewById(R.id.search);
        clear = view.findViewById(R.id.clear);

        statslayout = view.findViewById(R.id.statslayout);

        recyclerView = (RecyclerView) view.findViewById(R.id    .recyclerview);
        //StockListAdapter adapter = new StockListAdapter(getActivity(), product_List);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //recyclerView.setAdapter(adapter);
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
        PoultryListAdapterST adapter = new PoultryListAdapterST(getActivity(), catagoriesList.getResult().getUserFarms());
        recyclerView1.setAdapter(adapter);
    }


    private void setviews() {

        stockheader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isClicked == false) {

                    stockdateselection.setVisibility(View.VISIBLE);
                    isClicked = true;
                }else if (isClicked == true){

                    stockdateselection.setVisibility(View.GONE);
                    isClicked = false;
                }
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

        if (isOnline(getContext())) {
            GetEggStockRegisterDetails();
        }
        else {
            showDialog(getActivity(), getResources().getString(R.string.Internet));

        }

        fromdate.addTextChangedListener(new ValidationTextWatcher(fromdate));
        todate.addTextChangedListener(new ValidationTextWatcher(todate));


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

                    GetEggStockRegisterDetails();
                    stockdateselection.setVisibility(View.GONE);

                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fromdate.setText(onemonthlast);
                todate.setText(currentdate);
                GetEggStockRegisterDetails();
            }
        });


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
                case R.id.fromdate_edittxt:
                    validate_fromdate();
                    break;
                case R.id.todate_edittxt:
                    validate_todate();
                    break;
            }
        }
    }

    private boolean validate_fromdate() {
        if (TextUtils.isEmpty(fromdate.getText().toString())) {
            fromDatelbl.setError("Please Enter From Date");
            requestFocus(fromdate);
        } else {
            fromDatelbl.setErrorEnabled(false);

        }
        return true;
    }

    private boolean validate_todate() {
        if (TextUtils.isEmpty(todate.getText().toString())) {
            toDatelbl.setError("Please Enter To Date");
            requestFocus(todate);
        } else {
            toDatelbl.setErrorEnabled(false);

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
            fromDatelbl.setError("Please Enter From Date");
            return false;
        } else if (TextUtils.isEmpty(todate.getText().toString().trim())) {
            //  passwordEdt.setError(getString(R.string.err_please_enter_password));
            toDatelbl.setError("Please Enter To Date");
            return false;
        }

        return true;
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

    private void GetEggStockRegisterDetails() {
        mdilogue.show();
        JsonObject object =StockRegisterObject();
        ApiService service = ServiceFactory.createRetrofitService(getActivity(), ApiService.class);
        mSubscription = service.postStockRegister(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<EggStockRegisterResponse>() {
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
                    public void onNext(EggStockRegisterResponse eggStockRegisterResponse) {
                        if (eggStockRegisterResponse.getIsSuccess()) {
                            Log.d("StockRegisterResponse", eggStockRegisterResponse.getListResult().get(0).getDate()+"") ;

                            if (eggStockRegisterResponse.getListResult().size() != 0) {
                                StockListAdapter adapter = new StockListAdapter(getActivity(), eggStockRegisterResponse.getListResult());
                                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                recyclerView.setAdapter(adapter);
                            }

                        }
                    }





                });}

    private JsonObject StockRegisterObject() {

        startdateStr = fromdate.getText().toString();
        enddateStr = todate.getText().toString();

        try {
            startDateformatted = formatDateFromDateString(DATE_FORMAT2, DATE_FORMAT1, startdateStr);
            endDateformatted  = formatDateFromDateString(DATE_FORMAT2, DATE_FORMAT1, enddateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        EggStockRegisterobject requestModel = new EggStockRegisterobject();
        requestModel.setFromDate(startDateformatted);
        requestModel.setToDate(endDateformatted);
        requestModel.setFarmId(Farm_Id);


        return new Gson().toJsonTree(requestModel).getAsJsonObject();


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
