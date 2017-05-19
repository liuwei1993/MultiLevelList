package com.simon.multilist.core;

import com.simon.multilist.demo.bean.BaseNode;
import com.simon.multilist.demo.bean.BaseParentNode;

/**
 * 多级列表item点击事件监听器
 * Created by simon on 17-5-17.
 */
public interface OnMultiLevelItemClickListener {

    void onClickChild(BaseNode child);

    void onClickParent(BaseParentNode parent);

}
