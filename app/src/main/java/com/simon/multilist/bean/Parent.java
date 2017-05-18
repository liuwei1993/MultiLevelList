package com.simon.multilist.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * base parent bean
 * Created by simon on 17-5-12.
 */

public class Parent extends Bean {

    private List<Bean> children = new ArrayList<>();

    private boolean isOpen = false;

    public Parent(String name) {
        super(name);
    }

    public int getChildCount() {
        return children.size();
    }

    public void addChild(Bean child) {
        children.add(child);
    }

    public void removeChild(Bean child) {
        children.remove(child);
    }

    public List<Bean> getChildren() {
        return children;
    }

    public List<Bean> open() {
        isOpen = true;
        return children;
    }

    public boolean isOpen() {
        return isOpen;
    }

    /**
    * @return items count to close.
    * */
    public List<Bean> close() {
        List<Bean> list = new ArrayList<>();
        isOpen = false;
        for (Bean child : children) {
            list.add(child);
            if(child instanceof Parent && ((Parent) child).isOpen()) {
                list.addAll(((Parent) child).close());
            }
        }
        return list;
    }

   /* @Override
    public String toString() {
        return "{" +
                "name=" + getName() +
                "children=" + children +
                ", isOpen=" + isOpen +
                '}';
    }*/
}
