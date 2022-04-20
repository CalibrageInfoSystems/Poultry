package com.cis.poultry.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cis.poultry.Adapter.EggSaleListAdapter;
import com.cis.poultry.Adapter.SaleTransactionAdapter;
import com.cis.poultry.Common.BaseFragment;
import com.cis.poultry.Models.EggSaleObject;
import com.cis.poultry.Models.EggSaleresponse;
import com.cis.poultry.R;
import com.cis.poultry.localData.SharedPrefsData;
import com.cis.poultry.service.ApiService;
import com.cis.poultry.service.ServiceFactory;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.cis.poultry.Fragments.SaleFragment.Farm_Id;


public class TabFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    int position;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView billingamount, noofcatons, receivedamount, dueamount;
    TextView clear, noData, noData2;
    public String startdateStr, startDateformatted;
    public String enddateStr, endDateformatted;
    public static final String DATE_FORMAT2 = "dd-MM-yyyy";
    public static final String DATE_FORMAT1 = "yyyy-MM-dd'T'HH:mm:ss";
    DecimalFormat dff = new DecimalFormat("#,###,##0.00");
    DecimalFormat df = new DecimalFormat("#,###,##0");
    private Subscription mSubscription;
    private SpotsDialog mdilogue;
    RecyclerView recyclerView2, recyclerView;
    final Calendar myCalendar = Calendar.getInstance();
    String onemonthlast, currentdate;
    private int count;
    LinearLayout mainlinear;
    private TextView textView, txt_no;
    private OnFragmentInteractionListener mListener;
View view;
    public TabFragment() {
        // Required empty public constructor
    }


    public static Fragment getInstance(int position, int count) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        bundle.putInt("title", count);
        TabFragment tabFragment = new TabFragment();
        tabFragment.setArguments(bundle);
        count = count;

        return tabFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt("pos");

      Log.e("position==========",    position+"");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tab, container, false);
        textView = (TextView) view.findViewById(R.id.textView);
        textView.setText("Fragment " + (position + 1));
        init(view);
        setViews();
        return view;
    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
////        init(view);
////        setViews();
//
//
//    }

    private void init(View view) {


// textView.setVisibility(View.GONE);
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
        mainlinear =  view.findViewById(R.id.mainlinear);
        recyclerView2 = (RecyclerView) view.findViewById(R.id.transactionrecyclerview);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        count = SharedPrefsData.getInstance(getContext()).getIntFromSharedPrefs("count");


    }
    private void setViews() {
        count = SharedPrefsData.getInstance(getContext()).getIntFromSharedPrefs("count");
        Log.e("", " --- analysis ----- getTabsCount --->> count :" + count);
        if (isOnline(getContext())) {
            GetEggSaleDetails();



        }
        else {
            showDialog(getActivity(), getResources().getString(R.string.Internet));

        }




    }
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


                            List<EggSaleresponse.Result.SaleRegisterDetail> listsale1 = new ArrayList<>();
                            List<EggSaleresponse.Result.SaleRegisterDetail> listsale2 = new ArrayList<>();
                            List<EggSaleresponse.Result.SaleRegisterDetail> listsale3 = new ArrayList<>();

                            List<EggSaleresponse.Result.SaleTransaction> listsalet1 = new ArrayList<>();
                            List<EggSaleresponse.Result.SaleTransaction> listsalet2 = new ArrayList<>();
                            List<EggSaleresponse.Result.SaleTransaction> listsalet3 = new ArrayList<>();

                            List<EggSaleresponse.Result.Trader> listsaletrd1 = new ArrayList<>();
                            List<EggSaleresponse.Result.Trader> listsaletrd2 = new ArrayList<>();
                            List<EggSaleresponse.Result.Trader> listsaletrd3 = new ArrayList<>();

                            for (EggSaleresponse.Result.SaleRegisterDetail listResult :
                                    eggSaleresponse.getResult().getSaleRegisterDetails()) {
                                if (listResult.getTraderId()== 1001)
                                    listsale1.add(listResult);
                                else  if (listResult.getTraderId()== 1002)
                                    listsale2.add(listResult);
                                else
                                    listsale3.add(listResult);


                            }
                            for (EggSaleresponse.Result.SaleTransaction listResult :
                                    eggSaleresponse.getResult().getSaleTransactions()) {
                                if (listResult.getTraderId()== 1001)
                                    listsalet1.add(listResult);
                                else  if (listResult.getTraderId()== 1002)
                                    listsalet2.add(listResult);
                                else
                                    listsalet3.add(listResult);


                            }
                            for (EggSaleresponse.Result.SaleRegisterDetail listResult :
                                    eggSaleresponse.getResult().getSaleRegisterDetails()) {
                                if (listResult.getTraderId()== 1001)
                                    listsale1.add(listResult);
                                else if (listResult.getTraderId()== 1002)
                                    listsale2.add(listResult);
                                else
                                    listsale3.add(listResult);

                            }

                            for (EggSaleresponse.Result.Trader listResult :
                                    eggSaleresponse.getResult().getTraders()) {
                                if (listResult.getId()== 1001)
                                    listsaletrd1.add(listResult);
                                else  if (listResult.getId()== 1002)
                                    listsaletrd2.add(listResult);
                                else
                                    listsaletrd3.add(listResult);


                            }
if(position==0) {
    if (eggSaleresponse.getResult().getTraders().size() != 0) {
        billingamount.setText(getString(R.string.Rs) + dff.format(listsaletrd2.get(0).getBillingAmount()));
        noofcatons.setText(dff.format(listsaletrd2.get(0).getNumberofBoxes()));
        receivedamount.setText(getString(R.string.Rs) + dff.format(listsaletrd2.get(0).getReceivedAmount()));
        dueamount.setText(getString(R.string.Rs) + dff.format(listsaletrd2.get(0).getDueAmount()));
    } else {
        billingamount.setText(getString(R.string.Rs) + "0.00");
        noofcatons.setText("0");
        receivedamount.setText(getString(R.string.Rs) + "0.00");
        dueamount.setText(getString(R.string.Rs) + "0.00");
    }
    if (eggSaleresponse.getResult().getSaleTransactions().size() != 0) {
        noData.setVisibility(View.GONE);
        recyclerView2.setVisibility(View.VISIBLE);
        SaleTransactionAdapter adapter = new SaleTransactionAdapter(getActivity(), listsalet2);
        recyclerView2.setAdapter(adapter);
    } else {
        noData.setVisibility(View.VISIBLE);
        recyclerView2.setVisibility(View.GONE);
    }

    if (eggSaleresponse.getResult().getSaleRegisterDetails().size() != 0) {
        noData2.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        EggSaleListAdapter adapter = new EggSaleListAdapter(getActivity(), listsale2);
        recyclerView.setAdapter(adapter);

    } else {
        noData2.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }
}
                           else if(position==1) {
                                if (eggSaleresponse.getResult().getTraders().size() != 0) {
                                    billingamount.setText(getString(R.string.Rs) + dff.format(listsaletrd1.get(0).getBillingAmount()));
                                    noofcatons.setText(dff.format(listsaletrd1.get(0).getNumberofBoxes()));
                                    receivedamount.setText(getString(R.string.Rs) + dff.format(listsaletrd1.get(0).getReceivedAmount()));
                                    dueamount.setText(getString(R.string.Rs) + dff.format(listsaletrd1.get(0).getDueAmount()));
                                } else {
                                    billingamount.setText(getString(R.string.Rs) + "0.00");
                                    noofcatons.setText("0");
                                    receivedamount.setText(getString(R.string.Rs) + "0.00");
                                    dueamount.setText(getString(R.string.Rs) + "0.00");
                                }
                                if (eggSaleresponse.getResult().getSaleTransactions().size() != 0) {
                                    noData.setVisibility(View.GONE);
                                    recyclerView2.setVisibility(View.VISIBLE);
                                    SaleTransactionAdapter adapter = new SaleTransactionAdapter(getActivity(), listsalet1);
                                    recyclerView2.setAdapter(adapter);
                                } else {
                                    noData.setVisibility(View.VISIBLE);
                                    recyclerView2.setVisibility(View.GONE);
                                }

                                if (eggSaleresponse.getResult().getSaleRegisterDetails().size() != 0) {
                                    noData2.setVisibility(View.GONE);
                                    recyclerView.setVisibility(View.VISIBLE);
                                    EggSaleListAdapter adapter = new EggSaleListAdapter(getActivity(), listsale1);
                                    recyclerView.setAdapter(adapter);

                                } else {
                                    noData2.setVisibility(View.VISIBLE);
                                    recyclerView.setVisibility(View.GONE);
                                }
                            }
else  {
    if (eggSaleresponse.getResult().getTraders().size() != 0) {
        billingamount.setText(getString(R.string.Rs) + dff.format(listsaletrd3.get(0).getBillingAmount()));
        noofcatons.setText(dff.format(listsaletrd3.get(0).getNumberofBoxes()));
        receivedamount.setText(getString(R.string.Rs) + dff.format(listsaletrd3.get(0).getReceivedAmount()));
        dueamount.setText(getString(R.string.Rs) + dff.format(listsaletrd3.get(0).getDueAmount()));
    } else {
        billingamount.setText(getString(R.string.Rs) + "0.00");
        noofcatons.setText("0");
        receivedamount.setText(getString(R.string.Rs) + "0.00");
        dueamount.setText(getString(R.string.Rs) + "0.00");
    }
    if (listsalet3.size() != 0) {
        noData.setVisibility(View.GONE);
        recyclerView2.setVisibility(View.VISIBLE);
        SaleTransactionAdapter adapter = new SaleTransactionAdapter(getActivity(), listsalet3);
        recyclerView2.setAdapter(adapter);
    } else {
        noData.setVisibility(View.VISIBLE);
        recyclerView2.setVisibility(View.GONE);
    }

    if (listsale3.size() != 0) {
        noData2.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        EggSaleListAdapter adapter = new EggSaleListAdapter(getActivity(), listsale3);
        recyclerView.setAdapter(adapter);

    } else {
        noData2.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }
}
                        }


                    }


                });
    }

    private JsonObject EggSaleRequestObjectt() {
        EggSaleObject eggSaleObject = new EggSaleObject();

        eggSaleObject.setTraderId(null);
        eggSaleObject.setFromDate("2020-01-23T00:00:00");
        eggSaleObject.setToDate("2020-10-23T00:00:00");
        eggSaleObject.setFarmId(Farm_Id);




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
