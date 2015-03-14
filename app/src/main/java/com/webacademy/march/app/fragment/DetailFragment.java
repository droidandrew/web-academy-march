package com.webacademy.march.app.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.webacademy.march.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {


    public static final String KEY_TITLE = "t";
    public static final String KEY_CONTENT = "c";
    String title;
    String content;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(String tittle, String content) {
        DetailFragment f = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE, tittle);
        bundle.putString(KEY_CONTENT, content);
        f.setArguments(bundle);
        return f;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (getArguments() != null) {
            title = getArguments().getString(KEY_TITLE);
            content = getArguments().getString(KEY_CONTENT);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        TextView tvTitle = (TextView) rootView.findViewById(R.id.title);
        tvTitle.setText(title);

        TextView tvContent = (TextView) rootView.findViewById(R.id.content);
        tvContent.setText(content);

        return rootView;
    }


}
