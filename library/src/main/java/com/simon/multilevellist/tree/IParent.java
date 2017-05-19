package com.simon.multilevellist.tree;

import java.util.List;

/**
 * interface of parent node
 * Created by simon on 17-5-19.
 */

public interface IParent {

    /**
     * @return sub nodes count
     * */
    int getChildCount();

    /**
     * @param child sub node to add
     * */
    void addChild(INode child);

    /**
     * @param child sub node to remove
     * */
    void removeChild(INode child);

    /**
     * @return sub nodes list
     * */
    List<INode> getChildren();

    List<INode> open();

    boolean isOpen();

    /**
     * @return items count to close.
     * */
    List<INode> close();

}
