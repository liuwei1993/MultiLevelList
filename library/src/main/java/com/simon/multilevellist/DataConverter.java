package com.simon.multilevellist;

import android.support.annotation.NonNull;

import com.simon.multilevellist.tree.INode;
import com.simon.multilevellist.tree.IParent;

import java.util.ArrayList;
import java.util.List;

/**
 * convert tree to list
 * Created by simon on 17-5-12.
 */

public class DataConverter {

    public static List<INode> convert(@NonNull IParent rootNode) {
        ArrayList<INode> targetList = new ArrayList<>();
        List<? extends INode> children = rootNode.getChildren();
        for (INode child : children) {
            targetList.add(child);
            if(child instanceof IParent) {
                IParent parent = (IParent) child;
                if((parent).isOpen()){
                    List<? extends INode> convert = convert(parent);
                    targetList.addAll(convert);
                }
            }
        }
        return targetList;
    }

}
