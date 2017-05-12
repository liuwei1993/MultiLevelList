package com.simon.multilist.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.simon.multilist.R;
import com.simon.multilist.demo.adapter.CityAdapter;
import com.simon.multilist.demo.bean.Cities;
import com.simon.multilist.util.DataConverter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView cityList = (RecyclerView) findViewById(R.id.city_list);
        cityList.setLayoutManager(new LinearLayoutManager(this));
        Cities beijing = DataConverter.createDemoData();
        CityAdapter cityAdapter = new CityAdapter(beijing);
        cityList.setAdapter(cityAdapter);
    }
}
