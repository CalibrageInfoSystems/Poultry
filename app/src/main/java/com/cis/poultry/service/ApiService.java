package com.cis.poultry.service;



import com.cis.poultry.Models.ChangepwResponse;
import com.cis.poultry.Models.EggSaleresponse;
import com.cis.poultry.Models.EggStockRegisterResponse;
import com.cis.poultry.Models.FeedPurchaseresponse;
import com.cis.poultry.Models.GetAllTypeCdDmt;
import com.cis.poultry.Models.GetLookUpData;
import com.cis.poultry.Models.GetTraders;
import com.cis.poultry.Models.GetVisitLogByDateResponse;
import com.cis.poultry.Models.LoginResponse;

import com.cis.poultry.Models.ManageRateDetailResp;
import com.cis.poultry.Models.dueamountlist;
import com.cis.poultry.Models.getDashboardProdDetails;
import com.cis.poultry.Models.getDbPaymentsPackingAndProd;

import com.google.gson.JsonObject;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;
import rx.Observable;

public interface ApiService {



@POST(APIConstantURL.login_url)
Observable<LoginResponse>getLoginPage (@Body JsonObject data);

    @POST(APIConstantURL.changepassword)
    Observable<ChangepwResponse>getchangepass (@Body JsonObject data);
//    @GET
//    Observable<GetServiceOrdersByFarmerId> GetServiceOrdersByFarmerId(@Url String url);
//
//


    @POST(APIConstantURL.GetManageRateDetailsByDate)
    Observable<ManageRateDetailResp> postManageRate(@Body JsonObject data);

    @POST(APIConstantURL.getDashboardProductionDetails)
    Observable<getDashboardProdDetails> postdashboarddetails(@Body JsonObject data);

    @POST(APIConstantURL.getDashboardPaymentsPackingAndProd)
    Observable<getDbPaymentsPackingAndProd> postdashboardpackdetails(@Body JsonObject data);


    @POST(APIConstantURL.GetVisitLogByDate)
    Observable<GetVisitLogByDateResponse> postVisitLogByDate(@Body JsonObject data);
    @POST(APIConstantURL.GetEggStockRegisterDetails)
    Observable<EggStockRegisterResponse> postStockRegister(@Body JsonObject data);
    @POST(APIConstantURL.GetEggSaleDetails)
    Observable<EggSaleresponse> postSaleresponse(@Body JsonObject data);
    @POST(APIConstantURL.GetFeedPurchaseDetails)
    Observable<FeedPurchaseresponse> postfeedpurchaseresponse(@Body JsonObject data);


    @GET
    Observable<getDashboardProdDetails> getdashboardproddetails(@Url String url);

    @GET
    Observable<GetTraders> getTraderss(@Url String url);

    @GET
    Observable<getDbPaymentsPackingAndProd> getDbPaymentsAndProd(@Url String url);


    @GET
    Observable<GetLookUpData> getfeeddata(@Url String url);

    @GET
    Observable<GetAllTypeCdDmt> getpaymenttype(@Url String url);

    @GET
    Observable<dueamountlist> getduesummary(@Url String url);

}
