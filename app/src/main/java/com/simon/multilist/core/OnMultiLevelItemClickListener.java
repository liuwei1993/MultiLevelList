package com.simon.multilist.core;

import com.simon.multilist.bean.Bean;
import com.simon.multilist.bean.Parent;

/**
 * 多级列表item点击事件监听器
 * Created by simon on 17-5-17.
 */
public interface OnMultiLevelItemClickListener {

    void onClickChild(Bean child);

    void onClickParent(Parent parent);

}
