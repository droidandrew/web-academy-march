package com.webacademy.march.app.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.webacademy.march.R;
import com.webacademy.march.app.db.DBHelper;

/**
 * Created by student on 3/22/15.
 */
public class UsersCursorAdapter extends CursorAdapter {

    LayoutInflater inflater;
    int idName;
    int idAge;
    TextView name;
    TextView age;

    public UsersCursorAdapter(Context context, Cursor c) {
        super(context, c);
        init(context);
    }

    public UsersCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
        init(context);
    }

    public UsersCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        init(context);
    }

    private void init(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_user, parent, false);
        name = (TextView) view.findViewById(R.id.tvItemName);
        age = (TextView) view.findViewById(R.id.tvItemAge);
        idName = cursor.getColumnIndex(DBHelper.UserTable.NAME);
        idAge = cursor.getColumnIndex(DBHelper.UserTable.AGE);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        name.setText(cursor.getString(idName));
        age.setText(String.valueOf(cursor.getInt(idAge)));
    }
}
