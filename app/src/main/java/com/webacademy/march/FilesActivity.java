package com.webacademy.march;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * Created by student on 3/21/15.
 */
public class FilesActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "{FilesActivity}";
    TextView textView;
    Button button;
    Switch aSwitch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files);

        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        textView = (TextView) findViewById(R.id.text);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

        findViewById(R.id.button2).setOnClickListener(this);

        aSwitch = (Switch) findViewById(R.id.switch_a);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(TAG, "isChecked " + isChecked);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(getString(R.string.key_checked), isChecked);
                editor.apply();
            }
        });

        boolean checked = sharedPreferences.getBoolean(getString(R.string.key_checked), false);
        aSwitch.setChecked(checked);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button:

                try {
                    textView.setText(getStringFromAsset("test.txt"));
                } catch (IOException e) {
                    textView.setText("ERROR");
                }

                break;

            case R.id.button2:
                File file = new File(Environment.getExternalStorageDirectory(), "test.txt");
                try {
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    PrintWriter printWriter = new PrintWriter(file);
                    try {
                        printWriter.write("Text string test");
                    } finally {
                        printWriter.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }


    }

    private String getStringFromAsset(String filename) throws IOException {
        AssetManager am = getAssets();
        InputStream inputStream = null;
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        try {
            inputStream = am.open(filename);
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            int result = bis.read();
            while (result != -1) {
                byte b = (byte) result;
                buf.write(b);
                result = bis.read();
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return buf.toString();
    }

}
