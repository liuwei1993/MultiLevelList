package com.simon.multilevellist;

import android.view.View;

import com.simon.multilevellist.tree.INode;
import com.simon.multilevellist.tree.IParent;

import java.util.List;

/**
 * 扩展自多类型Adapter的多级Adapter
 * Created by simon on 17-5-12.
 */

public abstract class MultiLevelAdapter<T extends IParent> extends MultiAdapter implements OnMultiLevelItemClickListener {

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
    public abstract void onClickChild(INode child);

    @Override
    public void onClickParent(IParent parent) {
        List<INode> dataList = getDataList();
        int index = dataList.indexOf(parent);
        if(parent.isOpen()) {
            List<INode> closeList = parent.close();
            dataList.removeAll(closeList);
            notifyItemRangeRemoved(index + 1, closeList.size());
        } else {
            List<INode> list = parent.open();
            dataList.addAll(index + 1,list);
            notifyItemRangeInserted(index + 1, list.size());

        }
    }


    public static abstract class MultiLevelViewHolder<T extends INode> extends MultiAdapter.BaseHolder<T> {

        private View itemView;

        public MultiLevelViewHolder(View itemView, int type, OnMultiLevelItemClickListener listener) {
            super(itemView, type);
            this.itemView = itemView;
            setOnClickListener(listener);
        }

        public void setOnClickListener(final OnMultiLevelItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    T data = getData();
                    if(listener != null && data != null) {
                        if(data instanceof IParent) {
                            listener.onClickParent(((IParent) data));
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