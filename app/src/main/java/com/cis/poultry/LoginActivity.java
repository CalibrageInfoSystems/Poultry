package com.cis.poultry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cis.poultry.Common.BaseActivity;
import com.cis.poultry.Models.LoginRequest;
import com.cis.poultry.Models.LoginResponse;
import com.cis.poultry.localData.Constants;
import com.cis.poultry.localData.SharedPrefsData;
import com.cis.poultry.service.ApiService;
import com.cis.poultry.service.ServiceFactory;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

import dmax.dialog.SpotsDialog;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginActivity extends BaseActivity {
    private EditText userNameEdt, passwordEdt;
    private Button loginBtn ;
    private Subscription mSubscription;
    private SpotsDialog mdilogue;
    AppCompatTextView app_version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final int langID = SharedPrefsData.getInstance(this).getIntFromSharedPrefs("lang");
        setContentView(R.layout.activity_login);
        /* intializing and assigning ID's */
        initViews();
        /* Navigation's and using the views */
        setViews();
    }

    private void initViews() {

        loginBtn =  findViewById(R.id.loginBtn);
        userNameEdt =  findViewById(R.id.username_edittxt);
        passwordEdt = findViewById(R.id.password_edittxt);
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .build();
    }
    private void setViews() {

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOnline()) {
                    if (validations()) {
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                SharedPrefsData.putBool(LoginActivity.this, Constants.IS_LOGIN, true);
//                                /* Create an Intent that will start the Menu-Activity. */
//                                Intent i = new Intent( LoginActivity.this, DashboardActivity.class);
//
//                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//                                startActivity(i);
//                                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
//
//                                finish();
//                            }
//                        }, 300);


                        UserLogin();
                    }
                }
                else {
                    showDialog(LoginActivity.this, getResources().getString(R.string.Internet));
                }


            }
        });
    }
    private boolean validations() {
        if (TextUtils.isEmpty(userNameEdt.getText().toString())) {
            userNameEdt.setError(getString(R.string.err_please_enter_username));
          //  showDialog(LoginActivity.this, getResources().getString(R.string.err_please_enter_username));
            return false;
        } else if (TextUtils.isEmpty(passwordEdt.getText().toString().trim())) {
            passwordEdt.setError(getString(R.string.err_please_enter_password));
        //    showDialog(LoginActivity.this, getResources().getString(R.string.err_please_enter_password));
            return false;
        }
        return true;
    }
//
    private void UserLogin() {
        mdilogue.show();
        JsonObject object = loginPageObject();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getLoginPage(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginResponse>() {
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
                        //showDialog(LoginActivity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(final LoginResponse loginResponse) {
                        mdilogue.dismiss();
                        if (loginResponse.getIsSuccess()) {
                            loginBtn.setEnabled(false);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    /* Create an Intent that will start the Menu-Activity. */
                                    SharedPrefsData.putBool(LoginActivity.this, Constants.IS_LOGIN, true);
                                    SharedPrefsData.saveCatagories(LoginActivity.this, loginResponse);
                                    SharedPrefsData.getInstance(LoginActivity.this).updateStringValue(LoginActivity.this, Constants.USER_ID, loginResponse.getResult().getUserDetails().get(0).getId()+"");
                                    Log.e("created_useid==", loginResponse.getResult().getUserFarms().get(0).getFarmName()+"");
                                    startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                                    finish();
                                }
                            }, 300);

                        } else {
                            showDialog(LoginActivity.this, getResources().getString(R.string.Invalid_user));
                        }
                    }


                });}

    private JsonObject loginPageObject() {
        LoginRequest requestModel = new LoginRequest();
        requestModel.setUserName(userNameEdt.getText().toString().trim());
        requestModel.setPassword(passwordEdt.getText().toString().trim());

        return new Gson().toJsonTree(requestModel).getAsJsonObject();
    }
    @Override
    public void onBackPressed() {
        Intent start = new Intent(Intent.ACTION_MAIN);
        start.addCategory(Intent.CATEGORY_HOME);
        start.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(start);
        //   super.onBackPressed();

        // return;
    }
//
}



