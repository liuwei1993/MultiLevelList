package com.simon.multilist.core;

import com.simon.multilist.bean.Bean;
import com.simon.multilist.bean.Parent;

/**
 * Created by simon on 17-5-17.
 */
public interface OnItemClickListener {

    void onClickChild(Bean child);

    void onClickParent(Parent parent);

}
