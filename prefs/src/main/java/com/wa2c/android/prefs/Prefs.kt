package com.wa2c.android.prefs

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Base64
import android.util.TypedValue
import com.google.gson.Gson
import java.lang.reflect.Type



/**
 * Preferences controller.
 */
class Prefs @JvmOverloads constructor(private val context: Context, name : String? = null) {

    /** Preferences. */
    val sharedPreferences : SharedPreferences = if (name == null) {
        PreferenceManager.getDefaultSharedPreferences(context)
    } else {
        context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }
    /** Gson. */
    private val gson = Gson()
    /** Editor */
    private val editor = sharedPreferences.edit()
    /** True if before apply. */
    private var beforeApply = false



    /** Check contains key. */
    fun contains(keyRes : Int) : Boolean {
        return contains(context.getString(keyRes))
    }
    /** Check contains key. */
    fun contains(key : String?) : Boolean {
        return sharedPreferences.contains(key)
    }



    /** Get a value from preference. */
    fun getBooleanOrNull(keyRes : Int) : Boolean? {
        return getBooleanOrNull(context.getString(keyRes))
    }
    /** Get a value from preference. */
    fun getBooleanOrNull(key : String) : Boolean? {
        return if (contains(key)) getBoolean(key) else null
    }
    /** Get a value from preference. */
    @JvmOverloads
    fun getBoolean(keyRes : Int, default: Boolean = false, defRes : Int = -1) : Boolean {
        return getBoolean(context.getString(keyRes), default, defRes)
    }
    /** Get a value from preference. */
    @JvmOverloads
    fun getBoolean(key : String, default: Boolean = false, defRes : Int = -1) : Boolean {
        return if (defRes > 0)
            sharedPreferences.getBoolean(key, context.resources.getBoolean(defRes))
        else
            sharedPreferences.getBoolean(key, default)
    }

    /** Get a value from preference. */
    fun getIntOrNull(keyRes : Int) : Int? {
        return getIntOrNull(context.getString(keyRes))
    }
    /** Get a value from preference. */
    fun getIntOrNull(key : String) : Int? {
        return if (contains(key)) getInt(key) else null
    }
    /** Get a value from preference. */
    @JvmOverloads
    fun getInt(keyRes : Int, default: Int = 0, defRes : Int = -1) : Int {
        return getInt(context.getString(keyRes), default, defRes)
    }
    /** Get a value from preference. */
    @JvmOverloads
    fun getInt(key : String, default: Int = 0, defRes : Int = -1) : Int {
        return if (defRes > 0)
            sharedPreferences.getInt(key, context.resources.getInteger(defRes))
        else
            sharedPreferences.getInt(key, default)
    }

    /** Get a value from preference. */
    fun getLongOrNull(keyRes : Int) : Long? {
        return getLongOrNull(context.getString(keyRes))
    }
    /** Get a value from preference. */
    fun getLongOrNull(key : String) : Long? {
        return if (contains(key)) getLong(key) else null
    }
    /** Get a value from preference. */
    @JvmOverloads
    fun getLong(keyRes : Int, default: Long = 0, defRes : Int = -1) : Long {
        return getLong(context.getString(keyRes), default, defRes)
    }
    /** Get a value from preference. */
    @JvmOverloads
    fun getLong(key : String, default: Long = 0, defRes : Int = -1) : Long {
        return if (defRes > 0)
            sharedPreferences.getLong(key, context.resources.getInteger(defRes).toLong())
        else
            sharedPreferences.getLong(key, default)
    }


    /** Get a value from preference. */
    fun getFloatOrNull(keyRes : Int) : Float? {
        return getFloatOrNull(context.getString(keyRes))
    }
    /** Get a value from preference. */
    fun getFloatOrNull(key : String) : Float? {
        return if (contains(key)) getFloat(key) else null
    }
    /** Get a value from preference. */
    @JvmOverloads
    fun getFloat(keyRes : Int, default: Float = 0f, defRes : Int = -1): Float {
        return getFloat(context.getString(keyRes), default, defRes)
    }
    /** Get a value from preference. */
    @JvmOverloads
    fun getFloat(key : String, default: Float = 0f, defRes : Int = -1) : Float {
        return if (defRes > 0) {
            val v = TypedValue()
            context.resources.getValue(defRes, v, true)
            sharedPreferences.getFloat(key, v.float)
        } else {
            sharedPreferences.getFloat(key, default)
        }
    }

    /** Get a value from preference. */
    fun getStringOrNull(keyRes : Int) : String? {
        return getStringOrNull(context.getString(keyRes))
    }
    /** Get a value from preference. */
    fun getStringOrNull(key : String) : String? {
        return sharedPreferences.getString(key, null)
    }
    /** Get a value from preference. */
    @JvmOverloads
    fun getString(keyRes : Int, default: String = "", defRes : Int = -1) : String {
        return getString(context.getString(keyRes), default, defRes)
    }
    /** Get a value from preference. */
    @JvmOverloads
    fun getString(key : String, default: String = "", defRes : Int = -1) : String {
        return if (defRes > 0)
            sharedPreferences.getString(key, context.getString(defRes))
        else
            sharedPreferences.getString(key, default)
    }

    /** Get a value from preference. */
    fun getStringSetOrNull(keyRes : Int) : Set<String?>? {
        return getStringSetOrNull(context.getString(keyRes))
    }
    /** Get a value from preference. */
    fun getStringSetOrNull(key : String) : Set<String?>? {
        return sharedPreferences.getStringSet(key, null)
    }
    /** Get a value from preference. */
    @JvmOverloads
    fun getStringSet(keyRes : Int, default: Set<String?> = HashSet(), defRes : Int = -1) : Set<String?> {
        return getStringSet(context.getString(keyRes), default, defRes)
    }
    /** Get a value from preference. */
    @JvmOverloads
    fun getStringSet(key : String, default: Set<String?> = HashSet(), defRes : Int = -1) : Set<String?> {
       return if (defRes > 0)
            sharedPreferences.getStringSet(key, context.resources.getStringArray(defRes).toSet())
        else
            sharedPreferences.getStringSet(key, default)
    }

    /** Get a value from preference. */
    fun getBinOrNull(keyRes : Int) : ByteArray? {
        return getBinOrNull(context.getString(keyRes))
    }
    /** Get a value from preference.  */
    fun getBinOrNull(key : String) : ByteArray? {
        val base64Bytes = getStringOrNull(key)
        if (base64Bytes.isNullOrEmpty())
            return null
        return Base64.decode(base64Bytes, Base64.DEFAULT)
    }
    /** Get a value from preference.  */
    fun getBin(keyRes : Int, default: ByteArray) : ByteArray {
        return getBin(context.getString(keyRes), default)
    }
    /** Get a value from preference.  */
    fun getBin(key : String, default: ByteArray) : ByteArray {
        return getBinOrNull(key) ?: default
    }

    /** Get a value from preference. */
    fun <T> getObjectOrNull(keyRes: Int, classOfT: Class<T>): T? {
        return getObjectOrNull(context.getString(keyRes), classOfT)
    }
    /** Get a value from preference.  */
    fun <T> getObjectOrNull(key: String, classOfT: Class<T>): T? {
        val json = sharedPreferences.getString(key, null)
        return gson.fromJson(json, classOfT)
    }
    /** Get a value from preference. */
    fun <T> getObjectOrNull(keyRes: Int, typeOfT: Type): T? {
        return getObjectOrNull(context.getString(keyRes), typeOfT)
    }
    /** Get a value from preference.  */
    fun <T> getObjectOrNull(key: String, typeOfT: Type): T? {
        val json = sharedPreferences.getString(key, null)
        return gson.fromJson(json, typeOfT)
    }
    /** Get a value from preference. */
    fun <T> getObject(keyRes: Int, classOfT: Class<T>, default: T): T {
        return getObject(context.getString(keyRes), classOfT, default)
    }
    /** Get a value from preference.  */
    fun <T> getObject(key: String, classOfT: Class<T>, default: T): T {
        return getObjectOrNull(key, classOfT) ?: default
    }
    /** Get a value from preference. */
    fun <T> getObject(keyRes: Int, typeOfT: Type, default: T): T {

        return getObject(context.getString(keyRes), typeOfT, default)
    }
    /** Get a value from preference.  */
    fun <T> getObject(key: String, typeOfT: Type, default: T): T {
        return getObjectOrNull(key, typeOfT) ?: default
    }



    /** Set a value to preference. */
    fun putBoolean(keyRes : Int, value : Boolean?) : Prefs {
        putBoolean(context.getString(keyRes), value)
        return this
    }
    /** Set a value to preference. */
    fun putBoolean(key : String, value : Boolean?) : Prefs {
        if (value == null) {
            remove(key)
        } else {
            editor.putBoolean(key, value)
        }
        if (!beforeApply) end()
        return this
    }

    /** Set a value to preference. */
    fun putInt(keyRes : Int, value : Int?) : Prefs {
        putInt(context.getString(keyRes), value)
        return this
    }
    /** Set a value to preference. */
    fun putInt(key : String, value : Int?) : Prefs {
        if (value == null) {
            remove(key)
        } else {
            editor.putInt(key, value)
        }
        if (!beforeApply) end()
        return this
    }

    /** Set a value to preference. */
    fun putLong(keyRes : Int, value : Long?) : Prefs {
        putLong(context.getString(keyRes), value)
        return this
    }
    /** Set a value to preference. */
    fun putLong(key : String, value : Long?) : Prefs {
        if (value == null) {
            remove(key)
        } else {
            editor.putLong(key, value)
        }
        if (!beforeApply) end()
        return this
    }

    /** Set a value to preference. */
    fun putFloat(keyRes : Int, value : Float?) : Prefs {
        putFloat(context.getString(keyRes), value)
        return this
    }
    /** Set a value to preference. */
    fun putFloat(key : String, value : Float?) : Prefs {
        if (value == null) {
            remove(key)
        } else {
            editor.putFloat(key, value)
        }
        if (!beforeApply) end()
        return this
    }

    /** Set a value to preference. */
    fun putString(keyRes : Int, value : CharSequence?) : Prefs {
        putString(context.getString(keyRes), value)
        return this
    }
    /** Set a value to preference. */
    fun putString(key : String, value : CharSequence?) : Prefs {
        editor.putString(key, value?.toString())
        if (!beforeApply) end()
        return this
    }

    /** Set a value to preference. */
    fun putStringSet(keyRes : Int, value : Set<String?>?) : Prefs {
        putStringSet(context.getString(keyRes), value)
        return this
    }
    /** Set a value to preference. */
    fun putStringSet(key : String, value : Set<String?>?) : Prefs {
        editor.putStringSet(key, value)
        if (!beforeApply) end()
        return this
    }

    /** Set a value to preference. */
    fun putBin(keyRes : Int, value : ByteArray?) : Prefs {
        putBin(context.getString(keyRes), value)
        return this
    }
    /** Set a value to preference. */
    fun putBin(key : String, value : ByteArray?) : Prefs {
        if (value == null) {
            editor.putString(key, null)
        } else {
            val base64Bytes = Base64.encodeToString(value, Base64.DEFAULT)
            editor.putString(key, base64Bytes)
        }
        if (!beforeApply) end()
        return this
    }

    /** Set a value to preference. */
    fun putObject(keyRes : Int, value : Any?) : Prefs {
        putObject(context.getString(keyRes), value)
        return this
    }
    /** Set a value to preference. */
    fun putObject(key : String, value : Any?) : Prefs {
        if (value == null) {
            editor.putString(key, null)
        } else {
            val json = Gson().toJson(value)
            editor.putString(key, json)
        }
        if (!beforeApply) end()
        return this
    }



    /** Remove preference value. */
    fun remove(keyRes : Int) : Prefs {
        remove(context.getString(keyRes))
        return this
    }
    /** Remove preference value. */
    fun remove(key : String) : Prefs {
        editor.remove(key)
        if (!beforeApply) end()
        return this
    }

    /** Begin putting. */
    fun begin() : Prefs {
        end()
        beforeApply = true
        return this
    }

    /** Apply put values. */
    fun end() : Unit {
        editor.apply()
        beforeApply = false
    }


    /** Get all values. */
    fun all() : Map<String, *> {
        return sharedPreferences.all
    }

}