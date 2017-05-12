package com.simon.multilist.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * base parent bean
 * Created by simon on 17-5-12.
 */

public class Parent<T extends Bean> extends Bean {

    private List<T> children = new ArrayList<>();

    private boolean isOpen = false;

    public Parent(String name) {
        super(name);
    }

    public int getChildCount() {
        return children.size();
    }

    public void addChild(T child) {
        children.add(child);
    }

    public void removeChild(T child) {
        children.remove(child);
    }

    public List<T> getChildren() {
        return children;
    }

    public List<T> open() {
        isOpen = true;
        return children;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void close() {
        isOpen = false;
        for (T child : children) {
            if(child instanceof Parent) {
                ((Parent) child).close();
            }
        }
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
