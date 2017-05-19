package com.simon.multilist.demo.bean;

import com.simon.multilist.core.tree.INode;
import com.simon.multilist.core.tree.IParent;

import java.util.ArrayList;
import java.util.List;

/**
 * base parent node
 * Created by simon on 17-5-12.
 */

public class BaseParentNode extends BaseNode implements IParent{

    private List<INode> children = new ArrayList<>();

    private boolean isOpen = false;

    public BaseParentNode(String name) {
        super(name);
    }

    public int getChildCount() {
        return children.size();
    }

    public void addChild(INode child) {
        children.add(child);
    }

    public void removeChild(INode child) {
        children.remove(child);
    }

    public List<INode> getChildren() {
        return children;
    }

    public List<INode> open() {
        isOpen = true;
        return children;
    }

    public boolean isOpen() {
        return isOpen;
    }

    /**
    * @return items count to close.
    * */
    public List<INode> close() {
        List<INode> list = new ArrayList<>();
        isOpen = false;
        for (INode child : children) {
            list.add(child);
            if(child instanceof IParent && ((IParent) child).isOpen()) {
                list.addAll(((IParent) child).close());
            }
        }
        return list;
    }

}
