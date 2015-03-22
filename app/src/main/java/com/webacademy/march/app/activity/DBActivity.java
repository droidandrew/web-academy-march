package com.webacademy.march.app.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.webacademy.march.R;
import com.webacademy.march.app.adapter.UsersCursorAdapter;
import com.webacademy.march.app.db.DBHelper;

public class DBActivity extends Activity {

    EditText etName;
    EditText etAge;
    Button bAdd;
    ListView listView;
    UsersCursorAdapter usersCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
        bAdd = (Button) findViewById(R.id.bAdd);
        listView = (ListView) findViewById(R.id.listView);

        initData();


        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stName = etName.getText().toString();
                String stAge = etAge.getText().toString();

                if (!TextUtils.isEmpty(stName) && !TextUtils.isEmpty(stAge) && TextUtils.isDigitsOnly(stAge)) {
                    DBHelper helper = new DBHelper(DBActivity.this);
                    SQLiteDatabase database = helper.getWritableDatabase();
                    ContentValues cv = new ContentValues();
                    cv.put(DBHelper.UserTable.NAME, stName);
                    cv.put(DBHelper.UserTable.AGE, Integer.parseInt(stAge));
                    database.insert(DBHelper.UserTable.TABLE, null, cv);
                    etName.setText("");
                    etAge.setText("");
                    initData();
                } else {
                    Toast.makeText(getApplicationContext(), "ERROR!!!!!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void initData() {
        if (usersCursorAdapter == null) {
            usersCursorAdapter = new UsersCursorAdapter(this, getCursor());
            listView.setAdapter(usersCursorAdapter);
        } else {
            usersCursorAdapter.swapCursor(getCursor());
            usersCursorAdapter.notifyDataSetChanged();
        }
    }


    private Cursor getCursor() {
        DBHelper helper = new DBHelper(DBActivity.this);
        SQLiteDatabase database = helper.getReadableDatabase();
        return database.query(DBHelper.UserTable.TABLE, null, null, null, null, null, null);
    }



}
