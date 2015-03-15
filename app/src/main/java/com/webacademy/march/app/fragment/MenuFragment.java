package com.webacademy.march.app.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.webacademy.march.R;

import java.io.Serializable;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment implements View.OnClickListener {


    OnMenuFragmentListener onMenuFragmentListener;


    public MenuFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        this.onMenuFragmentListener = (OnMenuFragmentListener) activity;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);

        rootView.findViewById(R.id.button1).setOnClickListener(this);
        rootView.findViewById(R.id.button2).setOnClickListener(this);


        return rootView;
    }


    @Override
    public void onClick(View v) {
        int itemId = -1;
        switch (v.getId()) {
            case R.id.button1:
                itemId = 1;
                break;
            case R.id.button2:
                itemId = 2;
                break;
        }

        if (itemId > 0) {
            if (onMenuFragmentListener != null) {
                onMenuFragmentListener.onItemClick(itemId);
            }
        }
    }



    public interface OnMenuFragmentListener extends Serializable{

        public void onItemClick(int id);

    }


}
