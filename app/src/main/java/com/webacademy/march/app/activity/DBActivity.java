package com.webacademy.march.app.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.webacademy.march.R;
import com.webacademy.march.app.db.DBHelper;

public class DBActivity extends Activity {

    EditText etName;
    EditText etAge;
    Button bAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
        bAdd = (Button) findViewById(R.id.bAdd);
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

                } else {
                    Toast.makeText(getApplicationContext(), "ERROR!!!!!", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }


}
