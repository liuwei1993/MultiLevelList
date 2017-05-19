package com.simon.multilevellist;

import com.simon.multilevellist.tree.INode;
import com.simon.multilevellist.tree.IParent;

/**
 * 多级列表item点击事件监听器
 * Created by simon on 17-5-17.
 */
public interface OnMultiLevelItemClickListener {

    void onClickChild(INode child);

    void onClickParent(IParent parent);

}
