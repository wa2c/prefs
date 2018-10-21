package com.wa2c.android.prefsapp

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.wa2c.android.prefs.Prefs
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var prefs : Prefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        prefs = Prefs(this)

//        Log.d("test", resources.getResourceTypeName(R.bool.bool_value1))
//        Log.d("test", resources.getResourceTypeName(R.integer.int_value1))
//        Log.d("test", resources.getResourceTypeName(R.dimen.float_value1))
//        Log.d("test", resources.getResourceTypeName(R.string.string_value1))
//        Log.d("test", resources.getResourceTypeName(R.bool.bool_value2))
//        Log.d("test", resources.getResourceTypeName(R.integer.int_value2))
//        Log.d("test", resources.getResourceTypeName(R.dimen.float_value2))
//        Log.d("test", resources.getResourceTypeName(R.string.string_value2))

        initialize()

    }

    private fun initialize() {
        
        clearButton.setOnClickListener(clickListener)

        booleanSaveButton.setOnClickListener(clickListener)
        booleanLoadButton.setOnClickListener(clickListener)
        
        byteSaveButton.setOnClickListener(clickListener)
        byteLoadButton.setOnClickListener(clickListener)

        shortSaveButton.setOnClickListener(clickListener)
        shortLoadButton.setOnClickListener(clickListener)

        intSaveButton.setOnClickListener(clickListener)
        intLoadButton.setOnClickListener(clickListener)

        longSaveButton.setOnClickListener(clickListener)
        longLoadButton.setOnClickListener(clickListener)


        floatSaveButton.setOnClickListener(clickListener)
        floatLoadButton.setOnClickListener(clickListener)

        doubleSaveButton.setOnClickListener(clickListener)
        doubleLoadButton.setOnClickListener(clickListener)

        bigIntegerSaveButton.setOnClickListener(clickListener)
        bigIntegerLoadButton.setOnClickListener(clickListener)

        bigDecimalSaveButton.setOnClickListener(clickListener)
        bigDecimalLoadButton.setOnClickListener(clickListener)

        charSaveButton.setOnClickListener(clickListener)
        charLoadButton.setOnClickListener(clickListener)

        stringSaveButton.setOnClickListener(clickListener)
        stringLoadButton.setOnClickListener(clickListener)

    }

    private val clickListener = buttonClickListener()

    inner class buttonClickListener : View.OnClickListener {
        override fun onClick(v: View) {
            try {
                when (v) {
                    clearButton -> {
                        prefs
                            .begin()
                            .remove(R.string.prefkey_boolean)
                            .remove(R.string.prefkey_byte)
                            .remove(R.string.prefkey_short)
                            .remove(R.string.prefkey_int)
                            .remove(R.string.prefkey_long)
                            .remove(R.string.prefkey_float)
                            .remove(R.string.prefkey_double)
                            .remove(R.string.prefkey_big_integer)
                            .remove(R.string.prefkey_big_decimal)
                            .remove(R.string.prefkey_char)
                            .remove(R.string.prefkey_string)
                            .end()
                    }

                    booleanSaveButton -> prefs.putBoolean(R.string.prefkey_boolean, booleanInputCheckBox.isChecked)
                    booleanLoadButton -> booleanOutputCheckBox.isChecked = prefs.getBoolean(R.string.prefkey_boolean)

                    byteSaveButton -> prefs.putByte(R.string.prefkey_byte, byteInputEditText.text.toString().toByte())
                    byteLoadButton -> byteOutputTextView.text = prefs.getByte(R.string.prefkey_byte).toString()

                    shortSaveButton -> prefs.putShort(R.string.prefkey_short, shortInputEditText.text.toString().toShort())
                    shortLoadButton -> shortOutputTextView.text = prefs.getShort(R.string.prefkey_short).toString()

                    intSaveButton -> prefs.putInt(R.string.prefkey_int, intInputEditText.text.toString().toInt())
                    intLoadButton -> intOutputTextView.text = prefs.getInt(R.string.prefkey_int).toString()

                    longSaveButton -> prefs.putLong(R.string.prefkey_long, longInputEditText.text.toString().toLong())
                    longLoadButton -> longOutputTextView.text = prefs.getLong(R.string.prefkey_long).toString()

                    floatSaveButton -> prefs.putFloat(R.string.prefkey_float, floatInputEditText.text.toString().toFloat())
                    floatLoadButton -> floatOutputTextView.text = prefs.getFloat(R.string.prefkey_float).toString()

                    doubleSaveButton -> prefs.putDouble(R.string.prefkey_double, doubleInputEditText.text.toString().toDouble())
                    doubleLoadButton -> doubleOutputTextView.text = prefs.getDouble(R.string.prefkey_double).toString()

                    bigIntegerSaveButton -> prefs.putBigInteger(R.string.prefkey_big_integer, bigIntegerInputEditText.text.toString().toBigInteger())
                    bigIntegerLoadButton -> bigIntegerOutputTextView.text = prefs.getBigInteger(R.string.prefkey_big_integer).toString()

                    bigDecimalSaveButton -> prefs.putBigDecimal(R.string.prefkey_big_decimal, bigDecimalInputEditText.text.toString().toBigDecimal())
                    bigDecimalLoadButton -> bigDecimalOutputTextView.text = prefs.getBigDecimal(R.string.prefkey_big_decimal).toString()

                    charSaveButton -> prefs.putChar(R.string.prefkey_char, charInputEditText.text.toString()[0])
                    charLoadButton -> charOutputTextView.text = prefs.getChar(R.string.prefkey_char).toString()

                    stringSaveButton -> prefs.putString(R.string.prefkey_string, stringInputEditText.text)
                    stringLoadButton -> stringOutputTextView.text = prefs.getString(R.string.prefkey_string)

                }



            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
            }

        }

    }


}
