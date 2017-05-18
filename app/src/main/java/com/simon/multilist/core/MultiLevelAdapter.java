package com.simon.multilist.core;

import android.view.View;

import com.simon.multilist.bean.Bean;
import com.simon.multilist.bean.Parent;
import com.simon.multilist.util.DataConverter;

import java.util.List;

/**
 *
 * Created by simon on 17-5-12.
 */

public abstract class MultiLevelAdapter<T extends Parent> extends MultiAdapter implements OnItemClickListener {

    private T dataRoot;

    public MultiLevelAdapter() {
        this(null);
    }

    public MultiLevelAdapter(T dataRoot) {
        super();
        this.dataRoot = dataRoot;
        setDataList(DataConverter.convert(dataRoot));
        registerViewHolderCreators();
        if(getViewHolderCreatorCount() == 0) {
            throw new IllegalStateException("register ViewHolderCreator please");
        }
    }

    public void setDataRoot(T dataRoot) {
        this.dataRoot = dataRoot;
        setDataList(DataConverter.convert(dataRoot));
    }


    /**
     * sub class register ViewHolderCreators here
     * */
    protected abstract void registerViewHolderCreators();

    @Override
    public abstract void onClickChild(Bean child);

    @Override
    public void onClickParent(Parent parent) {
        List<Bean> dataList = getDataList();
        int index = dataList.indexOf(parent);
        if(parent.isOpen()) {
            List<Bean> closeList = parent.close();
            dataList.removeAll(closeList);
            notifyItemRangeRemoved(index + 1, closeList.size());
        } else {
            List<Bean> list = parent.open();
            dataList.addAll(index + 1,list);
            notifyItemRangeInserted(index + 1, list.size());

        }
    }


    public static abstract class MultiLevelViewHolder<T extends Bean> extends MultiAdapter.BaseHolder<T> {

        private View itemView;

        public MultiLevelViewHolder(View itemView, int type, OnItemClickListener listener) {
            super(itemView, type);
            this.itemView = itemView;
            setOnClickListener(listener);
        }

        public void setOnClickListener(final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    T data = getData();
                    if(listener != null && data != null) {
                        if(data instanceof Parent) {
                            listener.onClickParent(((Parent) data));
                        } else {
                            listener.onClickChild(data);
                        }
                    }
                }
            });
        }

        @Override
        public abstract void bindViewHolder(T data);

    }


}