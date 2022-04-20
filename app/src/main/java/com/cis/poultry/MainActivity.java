//package com.cis.poultry;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.Intent;
//import android.os.Bundle;
//
//import com.cis.poultry.Adapter.PoultryListAdapter;
//import com.cis.poultry.Common.BaseActivity;
//import com.cis.poultry.Models.poultry_settings;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MainActivity extends BaseActivity implements PoultryListAdapter.RequestAdapterListener {
//    private List<poultry_settings> request_List = new ArrayList<>();
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
//       PoultryListAdapter adapter = new PoultryListAdapter(this, request_List);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);
//        fetchpoultrydata();
//    }
//
//    private void fetchpoultrydata() {
//        int[] covers = new int[]{
//                R.drawable.egg_icon_white,
//
//        };
//        poultry_settings a = new poultry_settings( getResources().getString(R.string.poultryname), covers[0]);
//        request_List.add(a);
//     a = new poultry_settings( getResources().getString(R.string.poultryname2), covers[0]);
//        request_List.add(a);
//    }
//
//    @Override
//    public void onContactSelected(poultry_settings request) {
//
//            Intent intent = new Intent(this,DashboardActivity.class);
//
//            startActivity(intent);
//
//    }
//}
