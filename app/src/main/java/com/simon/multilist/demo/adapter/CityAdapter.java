package com.simon.multilist.demo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.simon.multilist.R;
import com.simon.multilist.bean.Bean;
import com.simon.multilist.bean.Parent;
import com.simon.multilist.core.MultiAdapter;
import com.simon.multilist.core.OnMultiLevelItemClickListener;
import com.simon.multilist.demo.bean.Area;
import com.simon.multilist.demo.bean.City;
import com.simon.multilist.demo.bean.Cities;
import com.simon.multilist.demo.bean.Street;
import com.simon.multilist.util.DataConverter;

/**
 * 扩展自多类型列表Adapter实现
 * Created by simon on 17-5-12.
 */

public class CityAdapter extends MultiAdapter implements OnMultiLevelItemClickListener {

    private Cities cities;

    public CityAdapter(Cities city) {
        super();
        this.cities = city;
        setDataList(DataConverter.convert(city));
        init();
    }



    @Override
    public int getItemViewType(Bean data) {

        if(data instanceof City) return TYPE_CITY;

        if(data instanceof Area) return TYPE_AREA;

        if(data instanceof Street) return TYPE_STREET;

        return 0;
    }

    private static final int TYPE_CITY = 1;
    private static final int TYPE_AREA = 2;
    private static final int TYPE_STREET = 3;

    private void init() {
        registerViewHolderCreator(TYPE_CITY, new CityViewHolderCreator());
        registerViewHolderCreator(TYPE_AREA, new AreaViewHolderCreator());
        registerViewHolderCreator(TYPE_STREET, new StreetViewHolderCreator());
    }

    @Override
    public void onClickChild(Bean child) {
        Log.d("test","on click " + child.getName());
    }

    @Override
    public void onClickParent(Parent parent) {
        if(parent.isOpen()) {
            parent.close();
        } else {
            parent.open();
        }
        setDataList(DataConverter.convert(cities));
        notifyDataSetChanged();
    }

    private class CityViewHolderCreator implements MultiAdapter.ViewHolderCreator {

        @NonNull
        @Override
        public BaseHolder create(Context context, ViewGroup parent) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_city, parent, false);
            return new CityViewHolder(view, TYPE_CITY, CityAdapter.this);
        }
    }

    private static class CityViewHolder extends MultiAdapter.BaseHolder<City> {

        private TextView tvCityName;

        CityViewHolder(View itemView, int type, OnMultiLevelItemClickListener listener) {
            super(itemView, type);
            tvCityName = (TextView) itemView.findViewById(R.id.city_name);
            setOnClickListener(listener);
        }

        public void setOnClickListener(final OnMultiLevelItemClickListener listener) {
            tvCityName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    City data = getData();
                    if(listener != null && data != null) {
                        listener.onClickParent(data);
                    }
                }
            });
        }

        @Override
        public void bindViewHolder(City data) {
            tvCityName.setText(data.getName());
        }
    }

    private  class AreaViewHolderCreator implements MultiAdapter.ViewHolderCreator {

        @NonNull
        @Override
        public BaseHolder create(Context context, ViewGroup parent) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_area, parent, false);
            return new AreaViewHolder(view, TYPE_AREA, CityAdapter.this);
        }
    }

    private static class AreaViewHolder extends MultiAdapter.BaseHolder<Area> {

        private TextView tvAreaName;

        AreaViewHolder(View itemView, int type, OnMultiLevelItemClickListener listener) {
            super(itemView, type);
            tvAreaName = (TextView) itemView.findViewById(R.id.area_name);
            setOnClickListener(listener);
        }

        public void setOnClickListener(final OnMultiLevelItemClickListener listener) {
            tvAreaName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Area data = getData();
                    if(listener != null && data != null) {
                        listener.onClickParent(data);
                    }
                }
            });
        }

        @Override
        public void bindViewHolder(Area data) {
            tvAreaName.setText(data.getName());
        }
    }


    private class StreetViewHolderCreator implements MultiAdapter.ViewHolderCreator {

        @NonNull
        @Override
        public BaseHolder create(Context context, ViewGroup parent) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_street, parent, false);
            return new StreetViewHolder(view, TYPE_STREET, CityAdapter.this);
        }
    }

    private static class StreetViewHolder extends MultiAdapter.BaseHolder<Street> {

        private TextView tvStreetName;

        StreetViewHolder(View itemView, int type, OnMultiLevelItemClickListener listener) {
            super(itemView, type);
            tvStreetName = (TextView) itemView.findViewById(R.id.street_name);
            setOnClickListener(listener);
        }

        public void setOnClickListener(final OnMultiLevelItemClickListener listener) {
            tvStreetName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Street data = getData();
                    if(listener != null && data != null) {
                        listener.onClickChild(data);
                    }
                }
            });
        }

        @Override
        public void bindViewHolder(Street data) {
            tvStreetName.setText(data.getName());
        }
    }


}
