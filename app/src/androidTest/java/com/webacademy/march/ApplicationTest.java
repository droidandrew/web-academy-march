package com.webacademy.march;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<MyApp> {
    public ApplicationTest() {
        super(MyApp.class);
    }

    public void testSimple() throws Exception {

        assertEquals("gqryqgwhe".hashCode(), MyApp.getAppId("gqryqgwhe"));

    }
}