package com.simon.multilist.core;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.simon.multilist.bean.Bean;

import java.util.List;

/**
 * 多级列表adapter
 * Created by simon on 17-5-12.
 */

public abstract class MultiAdapter extends RecyclerView.Adapter<MultiAdapter.BaseHolder> {

    private static final String TAG = MultiAdapter.class.getSimpleName();

    private SparseArray<ViewHolderCreator> creatorSet = new SparseArray<>();

    private List<Bean> dataList;

    public MultiAdapter() {
    }

    public MultiAdapter(List<Bean> dataList) {
        this.dataList = dataList;
    }

    public void setDataList(List<Bean> dataList) {
        this.dataList = dataList;
    }

    public List<Bean> getDataList() {
        return dataList;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolderCreator viewHolderCreator = getViewHolderCreator(viewType);
        if(viewHolderCreator == null) {
            Log.e(TAG,"cannot find ViewHolderCreator for viewType " + viewType);
            throw new IllegalStateException("cannot find ViewHolderCreator for viewType " + viewType);
        }
        return viewHolderCreator.create(parent.getContext(), parent);
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        if (dataList != null) {
            Bean data = dataList.get(position);
            holder.updateData(data);
        }
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return getItemViewType(dataList.get(position));
    }

    public abstract int getItemViewType(Bean data);

    public void registerViewHolderCreator(int dataType, ViewHolderCreator creator) {
        if(creator != null) {
            creatorSet.put(dataType, creator);
        }
    }

    private @Nullable ViewHolderCreator getViewHolderCreator(int dataType) {
        return creatorSet.get(dataType);
    }

    protected int getViewHolderCreatorCount() {
        return creatorSet.size();
    }


    protected static abstract class BaseHolder<B extends Bean> extends RecyclerView.ViewHolder {

        private B data;

        private int type;

        public BaseHolder(View itemView, int type) {
            super(itemView);
            setType(type);
        }

        public BaseHolder(View itemView, B data, int type) {
            this(itemView,type);
            setData(data);
        }

        private void setData(B data) {
            this.data = data;
        }

        public B getData() {
            return data;
        }

        public void updateData(B data) {
            setData(data);
            bindViewHolder(data);
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }

        public abstract void bindViewHolder(B data);

    }

    public interface ViewHolderCreator {

        @NonNull MultiAdapter.BaseHolder create(Context context, ViewGroup parent);

    }


}
