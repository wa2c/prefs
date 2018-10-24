package com.wa2c.android.prefs

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class PrefsKotlinUnitTest {

    private var prefs: Prefs? = null

    @Before
    fun init() {
        val context = RuntimeEnvironment.application
        prefs = Prefs(context)
    }

    @Test
    fun put() {

    }

}
