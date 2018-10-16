package com.wa2c.android.prefsapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private lateinit var testJava: TestJava
    private lateinit var testKotlin: TestKotlin


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testJava = TestJava(this)
        testKotlin = TestKotlin(this)
    }
}
