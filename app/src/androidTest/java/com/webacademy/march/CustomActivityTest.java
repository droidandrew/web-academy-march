package com.webacademy.march;

import android.test.ActivityInstrumentationTestCase2;

import com.webacademy.march.app.activity.CustomViewActivity;
import com.webacademy.march.ui.widget.CustomView;

/**
 * Created by student on 4/5/15.
 */
public class CustomActivityTest extends ActivityInstrumentationTestCase2<CustomViewActivity> {

    CustomViewActivity viewActivity;

    public CustomActivityTest(String pkg, Class<CustomViewActivity> activityClass) {
        super(pkg, activityClass);
    }


    public CustomActivityTest(Class<CustomViewActivity> activityClass) {
        super(activityClass);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        viewActivity = getActivity();
    }


    public void testIds() throws Exception {
        assertNotNull(viewActivity);
        CustomView view = (CustomView) viewActivity.findViewById(R.id.custom);
        assertNotNull(view);

    }
}
