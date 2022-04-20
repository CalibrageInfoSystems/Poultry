package com.cis.poultry;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cis.poultry.Common.BaseActivity;
import com.cis.poultry.Models.ChangepwResponse;
import com.cis.poultry.Models.LoginRequest;
import com.cis.poultry.Models.LoginResponse;
import com.cis.poultry.Models.changepwRequest;
import com.cis.poultry.R;
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


public class Changepassword extends BaseActivity {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View v;
    EditText old_password,new_Password, Confirm_password;
    TextInputLayout oldpass_layout,newpass_label,confirmpass_label;
    Button Submit;
    private Subscription mSubscription;
    private SpotsDialog mdilogue;
    LoginResponse loginressponse;
    int User_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_changepassword);

        initviews();
        setviews();

    }

    private void initviews() {
        old_password = (EditText)findViewById(R.id.oldpassword);
        new_Password = (EditText)findViewById(R.id.newpassword);
        Confirm_password = (EditText)findViewById(R.id.confirm_password);
        Submit =(Button)findViewById(R.id.submitbtn);
        oldpass_layout =(TextInputLayout)findViewById(R.id.oldpass_layout);
        newpass_label =findViewById(R.id.newpass_label);
        confirmpass_label = findViewById(R.id.confirmpass_label);
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .build();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_left);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                finish();
        }
        });

     
    }
    private void setviews() {
        old_password.addTextChangedListener(new ValidationTextWatcher(old_password));
        new_Password.addTextChangedListener(new ValidationTextWatcher(new_Password));
        old_password.addTextChangedListener(new ValidationTextWatcher(old_password));
        loginressponse = SharedPrefsData.getCatagories(Changepassword.this);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOnline()) {
                    if (validations()) {
changepassword();
                    }
                }
                else {
                    showDialog(Changepassword.this, getResources().getString(R.string.Internet));
                }


            }
        });
    }

    private void changepassword() {
        mdilogue.show();
        JsonObject object = ChangepwPageObject();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getchangepass(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ChangepwResponse>() {
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
                        showDialog(Changepassword.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(ChangepwResponse changepwResponse) {
                        Submit.setEnabled(false);

                        mdilogue.dismiss();

                        if (changepwResponse.getIsSuccess()==1) {
                            Toast.makeText(Changepassword.this, changepwResponse.getMessage(), Toast.LENGTH_LONG)
                                    .show();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    /* Create an Intent that will start the Menu-Activity. */

                                    startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                                    finish();
                                }
                            }, 300);

                        } else {
                            showDialog(Changepassword.this, changepwResponse.getMessage());
                        }
                    }


                });}

    private JsonObject ChangepwPageObject() {
        User_id =  loginressponse.getResult().getUserDetails().get(0).getId();
        changepwRequest requestModel = new changepwRequest();
        requestModel.setUserId(User_id);
        requestModel.setOldPassword(old_password.getText().toString().trim());
        requestModel.setNewPassword(new_Password.getText().toString().trim());
        requestModel.setConformPassword(Confirm_password.getText().toString().trim());

        return new Gson().toJsonTree(requestModel).getAsJsonObject();
    }

    private boolean validations() {
        if (TextUtils.isEmpty(old_password.getText().toString())) {
            oldpass_layout.setError(getString(R.string.err_please_enter_oldpassword));
            //  showDialog(LoginActivity.this, getResources().getString(R.string.err_please_enter_username));
            return false;
        } else if (TextUtils.isEmpty(new_Password.getText().toString().trim())) {
            newpass_label.setError(getString(R.string.err_please_enter_newpassword));
            //    showDialog(LoginActivity.this, getResources().getString(R.string.err_please_enter_password));
            return false;
        }
        else if (TextUtils.isEmpty(Confirm_password.getText().toString().trim())) {
            confirmpass_label.setError(getString(R.string.err_please_enter_confirmpassword));
            //    showDialog(LoginActivity.this, getResources().getString(R.string.err_please_enter_password));
            return false;
        }

        else if(!new_Password.getText().toString().trim().equals(Confirm_password.getText().toString().trim())){
            confirmpass_label.setError(getResources().getString(R.string.error_confirm));
            return false;
        }
        return true;
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

                case R.id.oldpassword:
                    validateoldpassword();
                    break;
                
                case R.id.newpassword:
                    validate_password();
                    break;
                case R.id.confirm_password:
                    validaeconfirmpassword();
                    break;


            }
        }
    }

    private boolean validateoldpassword() {
        if (TextUtils.isEmpty(old_password.getText().toString())) {
            oldpass_layout.setError(getResources().getString(R.string.err_please_enter_oldpassword));
            requestFocus(old_password);
        } else {
            oldpass_layout.setErrorEnabled(false);

        }
        return true;
    }

    private boolean validate_password() {
        if (TextUtils.isEmpty(new_Password.getText().toString().trim())) {
            newpass_label.setError(getResources().getString(R.string.err_please_enter_newpassword));
            requestFocus(new_Password);
        }
        else if( new_Password.length() < 6)
        {
            newpass_label.setError("Password must have 6 Characters minimum");
            return false;
        }
        else {
            newpass_label.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validaeconfirmpassword() {
        if (TextUtils.isEmpty(Confirm_password.getText().toString().trim())) {
            confirmpass_label.setError(getResources().getString(R.string.err_please_enter_confirmpassword));
            requestFocus(Confirm_password);
        }
//        else if(!pass.equals(Confirm_pass)){
//            confirm_pass.setError(getResources().getString(R.string.error_confirm));
//            requestFocus(confirmpass_edittxt);
//            return false;
//        }
        else {
            confirmpass_label.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
