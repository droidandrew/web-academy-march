package com.webacademy.march.app.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.webacademy.march.R;
import com.webacademy.march.app.fragment.DetailFragment;
import com.webacademy.march.app.fragment.MenuFragment;


public class TestFragmentActivity extends Activity implements MenuFragment.OnMenuFragmentListener {

    boolean isPortret;
    MenuFragment menuFragment;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fragment);

        isPortret = getResources().getBoolean(R.bool.port);
        transaction = getFragmentManager().beginTransaction();

        if (savedInstanceState == null) {
            menuFragment = new MenuFragment();
            transaction.replace(R.id.container, menuFragment);
        } else {
            if (isPortret) {
                transaction.remove(getFragmentManager().findFragmentById(R.id.container2));
            }
        }
        if (!isPortret) {
            transaction.replace(R.id.container2, DetailFragment.newInstance("Test", "Test"));
        }
        transaction.commit();


    }

    @Override
    public void onItemClick(int id) {
        transaction = getFragmentManager().beginTransaction();
        DetailFragment fragment = DetailFragment.newInstance("Test " + id, "Test");
        transaction.replace(R.id.container2, fragment);
        if (isPortret) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }


    public static class TestFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_test_1, container, false);
            return rootView;
        }
    }


}
