package com.simon.multilist.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.simon.multilist.R;
import com.simon.multilist.demo.adapter.NewCityAdapter;
import com.simon.multilist.demo.bean.Area;
import com.simon.multilist.demo.bean.Cities;
import com.simon.multilist.demo.bean.City;
import com.simon.multilist.demo.bean.Street;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView cityList = (RecyclerView) findViewById(R.id.city_list);
        cityList.setLayoutManager(new LinearLayoutManager(this));
        Cities cities = createDemoData();

//        CityAdapter cityAdapter = new CityAdapter(cities);

        NewCityAdapter newCityAdapter = new NewCityAdapter(this, cities);
        cityList.setAdapter(newCityAdapter);
    }


    public static Cities createDemoData() {

        Cities cities = new Cities("重要城市");

        City beijing = new City("北京");

        Area chaoyang = new Area("朝阳");
        Street sanlitun = new Street("三里屯");
        Street wangjing = new Street("望京");
        chaoyang.addChild(sanlitun);
        chaoyang.addChild(wangjing);
        chaoyang.open();
        Area haidian = new Area("海淀");
        Street zhongguancun = new Street("中关村");
        Street wudaokou = new Street("五道口");
        haidian.addChild(zhongguancun);
        haidian.addChild(wudaokou);

        beijing.addChild(chaoyang);
        beijing.addChild(haidian);
        beijing.open();

        City shanghai = new City("上海");

        Area dongqu = new Area("东区");
        dongqu.addChild(new Street("街道一"));
        dongqu.addChild(new Street("街道二"));
        chaoyang.open();
        Area xiqu = new Area("西区");
        xiqu.addChild(new Street("街道三"));
        xiqu.addChild(new Street("街道四"));

        shanghai.addChild(dongqu);
        shanghai.addChild(xiqu);
        shanghai.open();

        cities.addChild(beijing);
        cities.addChild(shanghai);
        cities.open();

        return cities;
    }


}
