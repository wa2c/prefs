package com.wa2c.android.prefsapp

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.support.annotation.ColorRes
import android.support.annotation.IntegerRes
import android.support.annotation.StringRes
import android.util.TypedValue
import com.wa2c.android.prefs.Prefs
import java.text.SimpleDateFormat
import java.util.*

class CustomPrefs(context: Context, name: String? = null) : Prefs(context, name) {

    var dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())

    /** The default long value. */
    var defaultColorValue: Int = Color.WHITE


    @JvmOverloads
    fun getColor(@StringRes keyRes: Int, defValue: Int = defaultColorValue, @IntegerRes @ColorRes @StringRes defRes: Int = -1): Int {
        return getColor(context.getString(keyRes), defValue, defRes)
    }

    @SuppressLint("ResourceType")
    @JvmOverloads
    fun getColor(key: String, defValue: Int = defaultColorValue, @IntegerRes @ColorRes @StringRes defRes: Int = -1): Int {
        val color = getColorOrNull(key)
        if (color != null)
            return color

        if (defRes <= 0)
            return defValue

        val resourceVal = TypedValue()
        context.resources.getValue(defRes, resourceVal, true)
        return when {
            resourceVal.type == TypedValue.TYPE_STRING -> Color.parseColor(context.getString(defRes))
            resourceVal.type == TypedValue.TYPE_FIRST_COLOR_INT -> context.resources.getInteger(defRes)
            resourceVal.type == TypedValue.TYPE_FIRST_INT -> context.resources.getInteger(defRes)
            else -> throw ClassCastException("Invalid resource.")
        }
    }

    fun getColorOrNull(@StringRes keyRes: Int): Int? {
        return getColorOrNull(context.getString(keyRes))
    }

    fun getColorOrNull(key: String): Int? {
        return getIntOrNull(key)
    }



    @JvmOverloads
    fun getDate(@StringRes keyRes: Int, defValue: Date = Date(), @StringRes defRes: Int = -1): Date {
        return getDate(context.getString(keyRes), defValue, defRes)
    }

    @SuppressLint("ResourceType")
    @JvmOverloads
    fun getDate(key: String, defValue: Date = Date(), @StringRes defRes: Int = -1): Date {
        val date = getDateOrNull(key)
        if (date != null)
            return date

        if (defRes <= 0)
            return defValue

        val dateText = context.getString(defRes)
        return dateFormat.parse(dateText)
    }


    fun getDateNull(@StringRes keyRes: Int): Date? {
        return getDateOrNull(context.getString(keyRes))
    }

    fun getDateOrNull(key: String): Date? {
        val time = getLongOrNull(key)
        return if (time != null)
            Date(time)
        else
            null
    }


    // put

    fun putColor(@StringRes keyRes: Int, value: Int?): Prefs {
        return putColor(context.getString(keyRes), value)
    }

    fun putColor(key: String, value: Int?): Prefs {
        return putInt(key, value)
    }

    fun putDate(@StringRes keyRes: Int, value: Date?): Prefs {
        return putDate(context.getString(keyRes), value)
    }

    fun putDate(key: String, value: Date?): Prefs {
        return putLong(key, value?.time)
    }

}
