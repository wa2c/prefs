package com.wa2c.android.prefs;

import android.content.Context;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

@RunWith(RobolectricTestRunner.class)
public class PrefsJavaUnitTest {

    private Prefs prefs;

    @Before
    public void init() {
        Context context = RuntimeEnvironment.application;
        prefs = new Prefs(context);
    }

    @Test
    public void put() {

    }

}
