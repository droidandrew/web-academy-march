package com.webacademy.march.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 3/15/15.
 */
public class DataFactory {

    public static ArrayList<SimpleItem> getDataItems() {
        ArrayList<SimpleItem> items = new ArrayList<SimpleItem>();
        items.add(new SimpleItem("Title 1", "Content 1", System.currentTimeMillis()));
        items.add(new SimpleItem("Title 2", "Content 2", System.currentTimeMillis()));
        items.add(new SimpleItem("Title 3", "Content 3", System.currentTimeMillis()));
        items.add(new SimpleItem("Title 4", "Content 4", System.currentTimeMillis()));
        items.add(new SimpleItem("Title 5", "Content 5", System.currentTimeMillis()));
        items.add(new SimpleItem("Title 6", "Content 6", System.currentTimeMillis()));
        items.add(new SimpleItem("Title 7", "Content 7", System.currentTimeMillis()));
        items.add(new SimpleItem("Title 8", "Content 8", System.currentTimeMillis()));
        items.add(new SimpleItem("Title 9", "Content 9", System.currentTimeMillis()));
        return items;
    }

}
