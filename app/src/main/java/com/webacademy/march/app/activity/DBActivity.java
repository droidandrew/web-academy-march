package com.webacademy.march.app.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.webacademy.march.MyApp;
import com.webacademy.march.MyIntentService;
import com.webacademy.march.R;
import com.webacademy.march.app.adapter.UsersCursorAdapter;
import com.webacademy.march.app.db.DBHelper;

import java.util.concurrent.atomic.AtomicBoolean;

public class DBActivity extends Activity {

    private static final String TAG = "{DBActivity}";
    EditText etName;
    EditText etAge;
    Button bAdd;

    ListView listView;
    UsersCursorAdapter usersCursorAdapter;

    CounterAsyncTask asyncTask;

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
                addRow(stName, stAge);

                ((MyApp) getApplication()).setOnServiceHelperListener(new MyApp.OnServiceHelperListener() {
                    @Override
                    public void onDataUpdate(final int counter) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                bAdd.setText("Counter from service = " + counter);
                            }
                        });
                    }
                });

                startService(new Intent(getApplicationContext(), MyIntentService.class));

            }
        });

    }

    private void addRow(String stName, String stAge) {
        if (!TextUtils.isEmpty(stName) && !TextUtils.isEmpty(stAge) && TextUtils.isDigitsOnly(stAge)) {
            DBHelper helper = new DBHelper(this);
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

    class CounterAsyncTask extends AsyncTask<Integer, Integer, String> {

        AtomicBoolean isCancel = new AtomicBoolean(false);

        TextView v;

        public CounterAsyncTask(TextView v) {
            this.v = v;
        }

        public void setCancel() {
            isCancel = new AtomicBoolean(true);
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            v.setText("Ready to start");

        }

        @Override
        protected String doInBackground(Integer... params) {
            int i = params[0];
            while (i++ < 100) {

                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);
                if (isCancel.get()) {
                    return "Canceled";
                }
            }

            return "Counter complete";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            v.setText("Counter = " + values[0]);

            addRow("Name  " + values[0], values[0].toString());

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            v.setText(s);

        }
    }


}
