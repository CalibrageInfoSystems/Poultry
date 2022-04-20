package com.cis.poultry.service;

public interface APIConstantURL {


public static  final  String LOCAL_URL="http://183.82.111.111/PoultryAPI/api/";//live
//public static  final  String LOCAL_URL="http://183.82.111.111/PoultryAPI_Test/api/";

   // http://183.82.111.111/PoultryAPI_Test

    String login_url = "Login/UserLogin";
    String changepassword = "Login/ChangePassword";
    String getDashboardProductionDetails ="Dashboard/GetDashboardProductionDetails";
    String getDashboardPaymentsPackingAndProd ="Dashboard/GetDashboardPaymentsPackingAndProd";
    String GetVisitLogByDate ="Log/GetVisitLogByDate";
    String GetEggStockRegisterDetails ="Log/GetEggStockInfo";
    String GetEggSaleDetails ="Log/GetEggSaleDetails";
    String GetManageRateDetailsByDate ="Log/GetManageRateDetailsByDate";
    String getTrades ="CompanyInfo/GetTraderInfoById/null/";

    String GetFeedPurchaseDetails ="FarmActivity/GetFeedPurchaseDetails";

    String GetLookUpData ="FarmActivity/GetLookUpData/null/9";
    String GetAllTypeCdDmt ="UserInfo/GetAllTypeCdDmt/10";
    String GetFeedSummary ="FarmActivity/GetFeedSummary/28";



}
