package com.cis.poultry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.cis.poultry.Adapter.PoultryListAdapter;
import com.cis.poultry.Fragments.HomeFragment;
import com.cis.poultry.Fragments.ProductionFragment;
import com.cis.poultry.Fragments.PursageFragment;
import com.cis.poultry.Fragments.SaleFragment;
import com.cis.poultry.Fragments.StockFragment;
import com.cis.poultry.Models.LoginResponse;
import com.cis.poultry.Models.poultry_settings;
import com.cis.poultry.localData.Constants;
import com.cis.poultry.localData.SharedPrefsData;
import com.google.android.material.navigation.NavigationView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import rx.Subscription;

import static androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private Subscription mSubscription;
    private SpotsDialog mdilogue;
    ChipNavigationBar bottomNav;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    Toolbar toolbar;
    private NavigationView nv;
    Integer mSelectedItem;
    private TextView  dialogMessage;
    private List<LoginResponse.Result.UserFarm> request_List = new ArrayList<>();
    private Button ok_btn;
    TextView cancel_btn;
    LoginResponse catagoriesList;
    String farm_name;
    RecyclerView recyclerView;
    TextView heading;
    public static  Integer Farm_Id ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initviews();
        setViews();
    }

    private void initviews() {
        if (getIntent() != null) {
            Farm_Id = getIntent().getIntExtra("FARMid", 1);

        }
        Log.d("Farm_Id===========", Farm_Id + "");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        nv = (NavigationView) findViewById(R.id.nv);
        dl = (DrawerLayout) findViewById(R.id.activity_main);
        heading = (TextView)findViewById(R.id.heading);

      //  toolbar.setNavigationIcon(R.drawable.menu_toggle);

        bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setItemSelected(R.id.action_home, true);
        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, homeFragment, "homeTag")
                .commit();

//         recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
//
//        recyclerView.setHasFixedSize(true);
//       // recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
//        RecyclerView.LayoutManager mLayoutManager1 = new GridLayoutManager(this ,2);
//        recyclerView.setLayoutManager(mLayoutManager1);

        catagoriesList = SharedPrefsData.getCatagories(DashboardActivity.this);
        Log.e("============>1", catagoriesList.getResult().getUserFarms().size() + "");
        if (catagoriesList.getResult().getUserFarms().size() == 1){
            Log.e("============>2", catagoriesList.getResult().getUserFarms().get(0).getFarmName());
             farm_name =catagoriesList.getResult().getUserFarms().get(0).getFarmName();
            Log.e("============>3",farm_name);
            heading.setText(farm_name);
            Farm_Id =catagoriesList.getResult().getUserFarms().get(0).getFarmId();
    }


//        t = new ActionBarDrawerToggle(this, dl,farm_name,farm_name);
//
//        dl.addDrawerListener(t);
//        t.syncState();
     //   request_List = catagoriesList.getResult().getUserFarms().size()
       // fetchpoultrydata();

    }

    private void fetchpoultrydata() {
//        PoultryListAdapter adapter = new PoultryListAdapter(DashboardActivity.this, catagoriesList.getResult().getUserFarms());
//        recyclerView.setAdapter(adapter);
    }

    private void setViews() {
        initToolBar();
        nv.setNavigationItemSelectedListener(this);


        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                Fragment fragment = null;

                switch (id) {

                    case R.id.action_home: {
                        viewFragment(new HomeFragment(), HomeFragment.TAG);
                        break;
                    }
                    case R.id.action_production: {
                        viewFragment(new ProductionFragment(), ProductionFragment.TAG);
                        break;
                    }
                    case R.id.action_sale: {

                        viewFragment(new SaleFragment(), SaleFragment.TAG);
                        break;
//
                    }
                    case R.id.action_stock: {

                        viewFragment(new StockFragment(), StockFragment.TAG);
                        break;

                    }
                    case R.id.action_bill: {

                        viewFragment(new PursageFragment(), PursageFragment.TAG);
                        break;

                    }


                }


            }
    private void viewFragment(Fragment fragment, String name) {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        // 1. Know how many fragments there are in the stack
        final int count = fragmentManager.getBackStackEntryCount();
        // 2. If the fragment is **not** "home type", save it to the stack
        if (name.equals(HomeFragment.TAG)) {
            fragmentTransaction.addToBackStack(name);
        }
        // Commit !
        fragmentTransaction.commit();
        // 3. After the commit, if the fragment is not an "home type" the back stack is changed, triggering the
        // OnBackStackChanged callback
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                // If the stack decreases it means I clicked the back button
                if (fragmentManager.getBackStackEntryCount() <= count) {
                    // pop all the fragment and remove the listener
                    fragmentManager.popBackStack(HomeFragment.TAG, POP_BACK_STACK_INCLUSIVE);
                    fragmentManager.removeOnBackStackChangedListener(this);
                    // set the home button selected
                    bottomNav.setItemSelected(R.id.action_home,true);
                }
            }
        });
    }


});}

    public void initToolBar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.menu_toggle);
        toolbar.setTitle(farm_name);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onClick(View v) {
                        if (!dl.isDrawerOpen(Gravity.START)) dl.openDrawer(Gravity.START);
                        else dl.closeDrawer(Gravity.END);
                    }
                }
        );
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        Log.e("id===", String.valueOf(id));
  if (id == R.id.action_home) {

      HomeFragment homeFragment = new HomeFragment();
      getSupportFragmentManager().beginTransaction()
              .add(R.id.content_frame, homeFragment, null)
              .commit();

      bottomNav.setItemSelected(R.id.action_home, true);
//      // Select home item
//      bottom_navigation.setSelectedItemId(id);
//      // finish();
//      bottom_navigation.setSelectedItemId(R.id.action_home);
      if (this.dl.isDrawerOpen(GravityCompat.START))
          this.dl.closeDrawer(GravityCompat.START);


        }
  else if (id == R.id.action_production) {

            ProductionFragment homeFragment = new ProductionFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_frame, homeFragment, null)
                    .commit();

            bottomNav.setItemSelected(R.id.action_production, true);
//      // Select home item
//      bottom_navigation.setSelectedItemId(id);
//      // finish();
//      bottom_navigation.setSelectedItemId(R.id.action_home);
            if (this.dl.isDrawerOpen(GravityCompat.START))
                this.dl.closeDrawer(GravityCompat.START);


        }
        else if (id == R.id.action_sale) {

            SaleFragment homeFragment = new SaleFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_frame, homeFragment, null)
                    .commit();

            bottomNav.setItemSelected(R.id.action_sale, true);
//      // Select home item
//      bottom_navigation.setSelectedItemId(id);
//      // finish();
//      bottom_navigation.setSelectedItemId(R.id.action_home);
            if (this.dl.isDrawerOpen(GravityCompat.START))
                this.dl.closeDrawer(GravityCompat.START);


        }
        else if (id == R.id.action_stock) {

            SaleFragment homeFragment = new SaleFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_frame, homeFragment, null)
                    .commit();

            bottomNav.setItemSelected(R.id.action_stock, true);
//      // Select home item
//      bottom_navigation.setSelectedItemId(id);
//      // finish();
//      bottom_navigation.setSelectedItemId(R.id.action_home);
            if (this.dl.isDrawerOpen(GravityCompat.START))
                this.dl.closeDrawer(GravityCompat.START);


        }      else if (id == R.id.action_bill) {

      SaleFragment homeFragment = new SaleFragment();
      getSupportFragmentManager().beginTransaction()
              .add(R.id.content_frame, homeFragment, null)
              .commit();

      bottomNav.setItemSelected(R.id.action_bill, true);
//      // Select home item
//      bottom_navigation.setSelectedItemId(id);
//      // finish();
//      bottom_navigation.setSelectedItemId(R.id.action_home);
      if (this.dl.isDrawerOpen(GravityCompat.START))
          this.dl.closeDrawer(GravityCompat.START);


  }

  else if (id == R.id.action_change) {


            mSelectedItem = menuItem.getItemId();

      new Handler().postDelayed(new Runnable() {
//                            @Override
                            public void run() {

                                Intent i = new Intent(getApplicationContext(), Changepassword.class);

                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                startActivity(i);
                                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);

                                finish();
                            }
                        }, 300);
        }
  else if (id == R.id.nav_logout) {
      //  bottom_navigation.setSelectedItemId(R.id.action_requests);
      //popupdialog to show message to logout the application
      logOutDialog();
  }
//  else if (id == R.id.action_3f) {
//
//            mSelectedItem = item.getItemId();
//            viewFragment(new RequestsFragment(), My3FFragment.TAG);
//            bottom_navigation.setSelectedItemId(R.id.action_3f);
//            if (this.dl.isDrawerOpen(GravityCompat.START))
//                this.dl.closeDrawer(GravityCompat.START);
//        } else if (id == R.id.My_request) {
//            mSelectedItem = item.getItemId();
//            viewFragment(new RequestsFragment(), RequestsFragment.TAG);
//            bottom_navigation.setSelectedItemId(R.id.action_requests);
//            if (this.dl.isDrawerOpen(GravityCompat.START))
//                this.dl.closeDrawer(GravityCompat.START);
//        } else if (id == R.id.nav_logout) {
//            //  bottom_navigation.setSelectedItemId(R.id.action_requests);
//            //popupdialog to show message to logout the application
//            logOutDialog();
      // }
        return true;
    }
    private void logOutDialog() {


        final Dialog dialog = new Dialog(DashboardActivity.this, R.style.DialogSlideAnim);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_logout);
        dialogMessage = dialog.findViewById(R.id.dialogMessage);
        dialogMessage.setText(getString(R.string.alert_logout));
        cancel_btn = dialog.findViewById(R.id.cancel_btn);
        ok_btn = dialog.findViewById(R.id.ok_btn);
/**
 * @param OnClickListner
 */
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                updateResources(getApplicationContext(), "en-US");
//                SharedPrefsData.getInstance(getApplicationContext()).ClearData(getApplicationContext());
                SharedPrefsData.putBool(DashboardActivity.this, Constants.IS_LOGIN, false);
                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });

/**
 * @param OnClickListner
 */
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
