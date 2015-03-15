package com.webacademy.march.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.webacademy.march.R;
import com.webacademy.march.api.SimpleItem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by student on 3/15/15.
 */
public class MenuItemsAdapter extends BaseAdapter {

    ViewHolder holder;
    private Context context;
    private List<SimpleItem> simpleItemList;
    private LayoutInflater inflater;

    public MenuItemsAdapter(Context context, List<SimpleItem> simpleItemList) {
        this.context = context;
        this.simpleItemList = simpleItemList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return simpleItemList.size();
    }

    @Override
    public SimpleItem getItem(int position) {
        return simpleItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View contentView = view;
        holder = new ViewHolder();
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.item_menu_simple, null, false);
            holder.tvDate = (TextView) contentView.findViewById(R.id.tvDate);
            holder.tvTitle = (TextView) contentView.findViewById(R.id.tvTitle);
            contentView.setTag(holder);
        } else {
            holder = (ViewHolder) contentView.getTag();
        }

        holder.tvTitle.setText(getItem(position).getTitle());

        DateFormat dateFormat = new SimpleDateFormat();
        String format = dateFormat.format(new Date(getItem(position).getData()));

        holder.tvDate.setText(format);

        return contentView;
    }

    static class ViewHolder {
        TextView tvTitle;
        TextView tvDate;
    }

}
