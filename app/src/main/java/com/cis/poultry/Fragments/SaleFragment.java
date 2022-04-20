package com.cis.poultry.Fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

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
import android.widget.Toast;

import com.cis.poultry.Adapter.EggSaleListAdapter;
import com.cis.poultry.Adapter.PoultryListAdapterP;
import com.cis.poultry.Adapter.PoultryListAdapterS;
import com.cis.poultry.Adapter.ProductListAdapter;
import com.cis.poultry.Adapter.SaleTransactionAdapter;
import com.cis.poultry.DashboardActivity;
import com.cis.poultry.Models.EggSaleObject;
import com.cis.poultry.Models.EggSaleresponse;
import com.cis.poultry.Models.GetTraders;
import com.cis.poultry.Models.GetVisitLogByDateResponse;
import com.cis.poultry.Models.LoginResponse;
import com.cis.poultry.Models.VisitLogobject;
import com.cis.poultry.Models.getDashboardProdDetails;
import com.cis.poultry.Models.getDbPaymentsPackingAndProd;
import com.cis.poultry.Models.productdata;
import com.cis.poultry.Models.saledata;
import com.cis.poultry.Models.transactionDetails;
import com.cis.poultry.R;
import com.cis.poultry.localData.SharedPrefsData;
import com.cis.poultry.service.APIConstantURL;
import com.cis.poultry.service.ApiService;
import com.cis.poultry.service.ServiceFactory;
import com.google.android.material.tabs.TabLayout;
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
 * {@link SaleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SaleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SaleFragment extends Fragment {
    public static String TAG = "SaleFragment";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View view;
    List<Integer> get_trader_Id = new ArrayList<Integer>();
    Boolean isStartDate = false;
    Boolean isEndDate = false;
    private ViewPagerAdapter adapter;
    String onemonthlast, currentdate;

    public static String startdateStr, startDateformatted;
    public static String enddateStr, endDateformatted;

    public static final String DATE_FORMAT1 = "yyyy-MM-dd'T'HH:mm:ss";

    public static final String DATE_FORMAT2 = "dd-MM-yyyy";

    private Subscription mSubscription;
    private SpotsDialog mdilogue;
    ImageButton header;
    String selected_trader;

    LinearLayout statsLayout, dataentrylyt;
    TextInputLayout fromdateLbl, todateLbl;

    Boolean isClicked = false;



    Spinner sellerspin;
    List<String> spinnerArray = new ArrayList<String>();
    String traderId ;
    private List<saledata> sale_List = new ArrayList<>();
    private List<transactionDetails> transactionDetails_List = new ArrayList<>();

    private EditText fromdate, todate;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    TextView clear, noData, noData2;
    Button search;
    final Calendar myCalendar = Calendar.getInstance();

    RecyclerView recyclerView2, recyclerView;
    RecyclerView recyclerView1;
    LoginResponse catagoriesList;
    public static  Integer Farm_Id ;
    Boolean isSearchClicked = false;
    private OnFragmentInteractionListener mListener;

    public SaleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SaleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SaleFragment newInstance(String param1, String param2) {
        SaleFragment fragment = new SaleFragment();
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
        view = inflater.inflate(R.layout.fragment_sale, container, false);
         viewPager = (ViewPager) view.findViewById(R.id.viewpager);


        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);


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

        sellerspin = view.findViewById(R.id.sellerSpinner);
        fromdate = view.findViewById(R.id.fromdate_edittxt);
        todate = view.findViewById(R.id.todate_edittxt);
        search = view.findViewById(R.id.search);
        clear = view.findViewById(R.id.clear);
        fromdateLbl = view.findViewById(R.id.fromdate_label);
        todateLbl = view.findViewById(R.id.todate_label);
        statsLayout = view.findViewById(R.id.statslayout);
        dataentrylyt = view.findViewById(R.id.dataentrylyt);
        header = view.findViewById(R.id.header);


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
       // fetchpoultrydata();
    }

    private void fetchpoultrydata() {
        PoultryListAdapterS adapter = new PoultryListAdapterS(getActivity(), catagoriesList.getResult().getUserFarms());
        recyclerView1.setAdapter(adapter);
    }


    private void setviews() {


        getTraders();
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isClicked == false) {

                    dataentrylyt.setVisibility(View.VISIBLE);
                    isClicked = true;
                }else if (isClicked == true){

                    dataentrylyt.setVisibility(View.GONE);
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
                } else {
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
                isSearchClicked = true;
                startdateStr = fromdate.getText().toString();
                enddateStr = todate.getText().toString();

                try {
                    startDateformatted = formatDateFromDateString(DATE_FORMAT2, DATE_FORMAT1, startdateStr);
                    endDateformatted = formatDateFromDateString(DATE_FORMAT2, DATE_FORMAT1, enddateStr);

                    Log.e("date=========1", startDateformatted);
                    Log.e("date=========2", endDateformatted);
                } catch (ParseException e) {
                    e.printStackTrace();
                }



                if (validation()) {

                    if(spinnerArray.size()== 2){
//

                        if (selected_trader.equalsIgnoreCase("Select Trader")) {

                            Adapter adapter = new Adapter(getChildFragmentManager());
                            adapter.addFragment(new jayama_fragment(),  getResources().getString(R.string.str_jayama));
                            viewPager.setAdapter(adapter);

                            tabLayout.setupWithViewPager(viewPager);
                            Intent intent = new Intent("KEY");
                            intent.putExtra("todate", startDateformatted);
                            intent.putExtra("fromdate", endDateformatted);
                            intent.putExtra("traderId", "");

                            Log.e("dates==========>", startDateformatted + endDateformatted + "id====================" + traderId);
                            getActivity().sendBroadcast(intent);
                        }

                        else if(selected_trader.equalsIgnoreCase("Jai Maa Durga")){
                            Adapter adapter = new Adapter(getChildFragmentManager());
                            adapter.addFragment(new jayama_fragment(),  getResources().getString(R.string.str_jayama));

                            viewPager.setAdapter(adapter);

                            tabLayout.setupWithViewPager(viewPager);


                            Intent intent = new Intent("KEY");
                            intent.putExtra("todate", startDateformatted);
                            intent.putExtra("fromdate", endDateformatted);
                            intent.putExtra("traderId", "1");

                            Log.e("dates==========>", startDateformatted + endDateformatted + "id====================" + traderId);
                            getActivity().sendBroadcast(intent);

                        }
                    }
                    else{
                    if (selected_trader.equalsIgnoreCase("Select Trader")) {

                        Adapter adapter = new Adapter(getChildFragmentManager());
                        adapter.addFragment(new jayamadurga_fragment(),  getResources().getString(R.string.str_jayama));
                        adapter.addFragment(new localsale_fragment(),  getResources().getString(R.string.localsale));
                        adapter.addFragment(new brado_details(),  getResources().getString(R.string.str_videos));

                        viewPager.setAdapter(adapter);

                        tabLayout.setupWithViewPager(viewPager);
                    Intent intent = new Intent("KEY");
                    intent.putExtra("todate", startDateformatted);
                    intent.putExtra("fromdate", endDateformatted);
                    intent.putExtra("traderId", "");
                    getActivity().sendBroadcast(intent);
                }

                    else if(selected_trader.equalsIgnoreCase("Jai Maa Durga")){
                            Adapter adapter = new Adapter(getChildFragmentManager());
                            adapter.addFragment(new jayamadurga_fragment(),  getResources().getString(R.string.str_jayama));

                            viewPager.setAdapter(adapter);

                            tabLayout.setupWithViewPager(viewPager);


                            Intent intent = new Intent("KEY");
                            intent.putExtra("todate", startDateformatted);
                            intent.putExtra("fromdate", endDateformatted);
                            intent.putExtra("traderId", "1001");

                            Log.e("dates==========>", startDateformatted + endDateformatted + "id====================" + traderId);
                            getActivity().sendBroadcast(intent);

                        }
                    else  if(selected_trader.equalsIgnoreCase("Brand Agro")){
                            Adapter adapter = new Adapter(getChildFragmentManager());
                            adapter.addFragment(new jayamadurga_fragment(),  getResources().getString(R.string.str_videos));

                            viewPager.setAdapter(adapter);

                            tabLayout.setupWithViewPager(viewPager);


                            Intent intent = new Intent("KEY");
                            intent.putExtra("todate", startDateformatted);
                            intent.putExtra("fromdate", endDateformatted);
                            intent.putExtra("traderId", "1002");

                            Log.e("dates==========>", startDateformatted + endDateformatted + "id====================" + traderId);
                            getActivity().sendBroadcast(intent);}

                    else  if(selected_trader.equalsIgnoreCase("Local Sales")){
                         Adapter adapter = new Adapter(getChildFragmentManager());
                         adapter.addFragment(new jayamadurga_fragment(),  getResources().getString(R.string.localsale));

                         viewPager.setAdapter(adapter);

                         tabLayout.setupWithViewPager(viewPager);


                         Intent intent = new Intent("KEY");
                         intent.putExtra("todate", startDateformatted);
                         intent.putExtra("fromdate", endDateformatted);
                         intent.putExtra("traderId", "1003");

                         Log.e("dates==========>", startDateformatted + endDateformatted + "id====================" + traderId);
                         getActivity().sendBroadcast(intent);

                    }
                   // LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
                  //  GetEggSaleDetails();


                }
                    dataentrylyt.setVisibility(View.GONE);}
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSearchClicked = false;
                fromdate.setText(onemonthlast);
                todate.setText(currentdate);



                try {
                    startDateformatted = formatDateFromDateString(DATE_FORMAT2, DATE_FORMAT1, onemonthlast);
                    endDateformatted = formatDateFromDateString(DATE_FORMAT2, DATE_FORMAT1, currentdate);

                    Log.e("date=========1", startDateformatted);
                    Log.e("date=========2", endDateformatted);
                } catch (ParseException e) {
                    e.printStackTrace();
                }      if(spinnerArray.size()== 2){
//

                    if (selected_trader.equalsIgnoreCase("Select Trader")) {

                        Adapter adapter = new Adapter(getChildFragmentManager());
                        adapter.addFragment(new jayama_fragment(),  getResources().getString(R.string.str_jayama));
                        viewPager.setAdapter(adapter);

                        tabLayout.setupWithViewPager(viewPager);
                        Intent intent = new Intent("KEY");
                        intent.putExtra("todate", startDateformatted);
                        intent.putExtra("fromdate", endDateformatted);
                        intent.putExtra("traderId", "");

                        Log.e("dates==========>", startDateformatted + endDateformatted + "id====================" + traderId);
                        getActivity().sendBroadcast(intent);
                    }

                    else if(selected_trader.equalsIgnoreCase("Jai Maa Durga")){
                        Adapter adapter = new Adapter(getChildFragmentManager());
                        adapter.addFragment(new jayama_fragment(),  getResources().getString(R.string.str_jayama));

                        viewPager.setAdapter(adapter);

                        tabLayout.setupWithViewPager(viewPager);


                        Intent intent = new Intent("KEY");
                        intent.putExtra("todate", startDateformatted);
                        intent.putExtra("fromdate", endDateformatted);
                        intent.putExtra("traderId", "1");

                        Log.e("dates==========>", startDateformatted + endDateformatted + "id====================" + traderId);
                        getActivity().sendBroadcast(intent);

                    }
                }
                else{
                    if (selected_trader.equalsIgnoreCase("Select Trader")) {

                        Adapter adapter = new Adapter(getChildFragmentManager());
                        adapter.addFragment(new jayamadurga_fragment(),  getResources().getString(R.string.str_jayama));
                        adapter.addFragment(new localsale_fragment(),  getResources().getString(R.string.localsale));
                        adapter.addFragment(new brado_details(),  getResources().getString(R.string.str_videos));

                        viewPager.setAdapter(adapter);

                        tabLayout.setupWithViewPager(viewPager);
                        Intent intent = new Intent("KEY");
                        intent.putExtra("todate", startDateformatted);
                        intent.putExtra("fromdate", endDateformatted);
                        intent.putExtra("traderId", "");
                        getActivity().sendBroadcast(intent);
                    }

                    else if(selected_trader.equalsIgnoreCase("Jai Maa Durga")){
                        Adapter adapter = new Adapter(getChildFragmentManager());
                        adapter.addFragment(new jayamadurga_fragment(),  getResources().getString(R.string.str_jayama));

                        viewPager.setAdapter(adapter);

                        tabLayout.setupWithViewPager(viewPager);


                        Intent intent = new Intent("KEY");
                        intent.putExtra("todate", startDateformatted);
                        intent.putExtra("fromdate", endDateformatted);
                        intent.putExtra("traderId", "1001");

                        Log.e("dates==========>", startDateformatted + endDateformatted + "id====================" + traderId);
                        getActivity().sendBroadcast(intent);

                    }
                    else  if(selected_trader.equalsIgnoreCase("Brand Agro")){
                        Adapter adapter = new Adapter(getChildFragmentManager());
                        adapter.addFragment(new jayamadurga_fragment(),  getResources().getString(R.string.str_videos));

                        viewPager.setAdapter(adapter);

                        tabLayout.setupWithViewPager(viewPager);


                        Intent intent = new Intent("KEY");
                        intent.putExtra("todate", startDateformatted);
                        intent.putExtra("fromdate", endDateformatted);
                        intent.putExtra("traderId", "1002");

                        Log.e("dates==========>", startDateformatted + endDateformatted + "id====================" + traderId);
                        getActivity().sendBroadcast(intent);}

                    else  if(selected_trader.equalsIgnoreCase("Local Sales")){
                        Adapter adapter = new Adapter(getChildFragmentManager());
                        adapter.addFragment(new jayamadurga_fragment(),  getResources().getString(R.string.localsale));

                        viewPager.setAdapter(adapter);

                        tabLayout.setupWithViewPager(viewPager);


                        Intent intent = new Intent("KEY");
                        intent.putExtra("todate", startDateformatted);
                        intent.putExtra("fromdate", endDateformatted);
                        intent.putExtra("traderId", "1003");

                        Log.e("dates==========>", startDateformatted + endDateformatted + "id====================" + traderId);
                        getActivity().sendBroadcast(intent);

                    }
                    // LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
                    //  GetEggSaleDetails();


                }
                dataentrylyt.setVisibility(View.GONE);
               // GetEggSaleDetails();
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
            fromdateLbl.setError("Please Enter From Date");
            requestFocus(fromdate);
        } else {
            fromdateLbl.setErrorEnabled(false);

        }
        return true;
    }

    private boolean validate_todate() {
        if (TextUtils.isEmpty(todate.getText().toString())) {
            todateLbl.setError("Please Enter To Date");
            requestFocus(todate);
        } else {
            todateLbl.setErrorEnabled(false);

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
            fromdateLbl.setError("Please Enter From Date");
            return false;
        } else if (TextUtils.isEmpty(todate.getText().toString().trim())) {
            //  passwordEdt.setError(getString(R.string.err_please_enter_password));
            todateLbl.setError("Please Enter To Date");
            return false;
        }

        return true;
    }


    private void getTraders() {
    //    mdilogue.show();
        ApiService service = ServiceFactory.createRetrofitService(getActivity(), ApiService.class);
        mSubscription = service.getTraderss(APIConstantURL.getTrades + Farm_Id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetTraders>() {
                    @Override
                    public void onCompleted() {
                        mdilogue.dismiss();

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetTraders getTraders) {
                        mdilogue.dismiss();
                        if (getTraders.getListResult() != null) {
                         spinnerArray.add("Select Trader");
                            for (GetTraders.ListResult data : getTraders.getListResult()) {

                                spinnerArray.add(data.getName());
                                get_trader_Id.add(data.getId());
                                traderId = data.getId()+"";
                                Log.e("traderId==>11111111",traderId);


                            }


                            ArrayAdapter list = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, spinnerArray);
                            list.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            sellerspin.setAdapter(list);
                            if(spinnerArray.size()== 2){
                                Adapter adapter = new Adapter(getChildFragmentManager());
                                adapter.addFragment(new jayama_fragment(),  getResources().getString(R.string.str_jayama));


                                viewPager.setAdapter(adapter);

                                tabLayout.setupWithViewPager(viewPager);
                            }
                            else{

//
                                Adapter adapter = new Adapter(getChildFragmentManager());
                                adapter.addFragment(new jayamadurga_fragment(),  getResources().getString(R.string.str_jayama));
                                adapter.addFragment(new localsale_fragment(),  getResources().getString(R.string.localsale));
                                adapter.addFragment(new brado_details(),  getResources().getString(R.string.str_videos));

                                viewPager.setAdapter(adapter);

                                tabLayout.setupWithViewPager(viewPager);}
                            sellerspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                                     selected_trader = sellerspin.getItemAtPosition(sellerspin.getSelectedItemPosition()).toString();



//                                    else if(traderId.equalsIgnoreCase("1002")){
//                                        Adapter adapter = new Adapter(getChildFragmentManager());
//                                        adapter.addFragment(new brado_details(),  getResources().getString(R.string.str_videos));
//
//
//                                        viewPager.setAdapter(adapter);
//
//                                        tabLayout.setupWithViewPager(viewPager);}
//                                    else   if(traderId.equalsIgnoreCase("1003")){
//                                        Adapter adapter = new Adapter(getChildFragmentManager());
//
//                                        adapter.addFragment(new localsale_fragment(),  getResources().getString(R.string.localsale));
//
//                                        viewPager.setAdapter(adapter);
//
//                                        tabLayout.setupWithViewPager(viewPager);}
                                    //spin_plot_dist.setSelection(2);
                                    //paymentstatusId = get_payment_Id.get(paymentStatusSpinner.getSelectedItemPosition());
                                    Log.d("selected_trader", "dist_id======" + selected_trader);



                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {
                                    // DO Nothing here
                                }
                            });


//                            if(getTraders.getAffectedRecords() == 1 ){
//
//
//                                String[] tabnames = { spinnerArray};
//                                Log.e("==========>1",tabnames+"");
//
//                                adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(),spinnerArray);
//                                SharedPrefsData.getInstance(getActivity()).updateIntValue(getActivity(),"count",tabnames.length);
//                                viewPager.setAdapter(adapter);
//                                tabLayout.setupWithViewPager(viewPager);
//
//                            }else
//                            {
//
//                                String[] tabnames = {getResources().getString(R.string.str_jayama),getResources().getString(R.string.str_videos)};
//                                Log.e("==========>2",tabnames+"");
//                                SharedPrefsData.getInstance(getActivity()).updateIntValue(getActivity(),"countt",tabnames.length);
//                                adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(),tabnames);
//                                viewPager.setAdapter(adapter);
//                                tabLayout.setupWithViewPager(viewPager);
//                            }




                        } else {
                            Log.e("nodada====", "nodata===custom2");

                        }

                    }

                });
    }



//    private JsonObject EggSaleRequestObject() {
//
//
//        EggSaleObject eggSaleObject = new EggSaleObject();
//        if (isSearchClicked == true) {
//        eggSaleObject.setTraderId(traderId);
//        eggSaleObject.setFromDate(startDateformatted);
//        eggSaleObject.setToDate(endDateformatted);
//        eggSaleObject.setFarmId(Farm_Id);
//
//        }
//
//        if (isSearchClicked == false) {
//            eggSaleObject.setTraderId(null);
//            eggSaleObject.setFromDate(startDateformatted);
//            eggSaleObject.setToDate(endDateformatted);
//            eggSaleObject.setFarmId(Farm_Id);
//        }
//
//
//        return new Gson().toJsonTree(eggSaleObject).getAsJsonObject();
//
//    }


    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}
