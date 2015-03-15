package com.webacademy.march.app.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.webacademy.march.R;
import com.webacademy.march.api.SimpleItem;
import com.webacademy.march.app.adapter.MenuItemsAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment implements View.OnClickListener {


    private static final String KEY_ITEMS = "i";
    OnMenuFragmentListener onMenuFragmentListener;
    List<SimpleItem> items;

    public MenuFragment() {
    }

    public static MenuFragment newInstance(ArrayList<SimpleItem> items) {
        MenuFragment f = new MenuFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_ITEMS, items);
        f.setArguments(bundle);
        return f;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.onMenuFragmentListener = (OnMenuFragmentListener) activity;
        if (getArguments() != null) {
            items = (ArrayList<SimpleItem>) getArguments().getSerializable(KEY_ITEMS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.listView);

        MenuItemsAdapter adapter = new MenuItemsAdapter(getActivity(), items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (onMenuFragmentListener != null) {
                    onMenuFragmentListener.onItemClick(position + 1);
                }

            }
        });


        return rootView;
    }


    @Override
    public void onClick(View v) {

    }



    public interface OnMenuFragmentListener extends Serializable{

        public void onItemClick(int id);

    }


}
