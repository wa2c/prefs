package com.wa2c.android.prefs

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Base64
import android.util.TypedValue
import com.google.gson.Gson
import java.lang.reflect.Type
import java.math.BigDecimal
import java.math.BigInteger
import java.util.*


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
    private val gson : Gson by lazy { Gson() }
    /** Editor */
    private val editor = sharedPreferences.edit()
    /** True if before apply. */
    private var beforeApply = false


    var defaultBooleanValue : Boolean = false
    var defaultByteValue : Byte = 0
    var defaultShortValue : Short = 0
    var defaultIntValue : Int = 0
    var defaultLongValue : Long = 0
    var defaultFloatValue : Float = 0.0F
    var defaultDoubleValue : Double = 0.0
    var defaultBigIntegerValue : BigInteger = BigInteger.ZERO
    var defaultBigDecimalValue : BigDecimal = BigDecimal.ZERO
    var defaultCharValue : Char = '\u0000'
    var defaultStringValue : String = ""
    var defaultStringSetValue : Set<String> = HashSet()
    var defaultBinValue : ByteArray = ByteArray(0)


    /** Check contains key. */
    fun contains(keyRes : Int) : Boolean {
        return contains(context.getString(keyRes))
    }
    /** Check contains key. */
    fun contains(key : String?) : Boolean {
        return sharedPreferences.contains(key)
    }



    /** Get a value from preference. */
    @JvmOverloads
    fun getBoolean(keyRes : Int, default: Boolean = defaultBooleanValue, defRes : Int = -1) : Boolean {
        return getBoolean(context.getString(keyRes), default, defRes)
    }
    /** Get a value from preference. */
    @JvmOverloads
    fun getBoolean(key : String, default: Boolean = defaultBooleanValue, defRes : Int = -1) : Boolean {
        return getBooleanOrNull(key) ?: getDefaultValue(default, defRes)
    }
    /** Get a value from preference. */
    fun getBooleanOrNull(keyRes : Int) : Boolean? {
        return getBooleanOrNull(context.getString(keyRes))
    }
    /** Get a value from preference. */
    fun getBooleanOrNull(key : String) : Boolean? {
        return if (contains(key)) {
            sharedPreferences.getBoolean(key, false)
        } else {
            null
        }
    }

    /** Get a value from preference. */
    @JvmOverloads
    fun getByte(keyRes : Int, default: Byte = defaultByteValue, defRes : Int = -1) : Byte {
        return getByte(context.getString(keyRes), default, defRes)
    }
    /** Get a value from preference. */
    @JvmOverloads
    fun getByte(key : String, default: Byte = defaultByteValue, defRes : Int = -1) : Byte {
        return getByteOrNull(key) ?: getDefaultValue(default, defRes)
    }
    /** Get a value from preference. */
    fun getByteOrNull(keyRes : Int) : Byte? {
        return getByteOrNull(context.getString(keyRes))
    }
    /** Get a value from preference. */
    fun getByteOrNull(key : String) : Byte? {
        return if (contains(key)) {
            sharedPreferences.getInt(key, 0).toByte()
        } else {
            null
        }
    }

    /** Get a value from preference. */
    @JvmOverloads
    fun getShort(keyRes : Int, default: Short = defaultShortValue, defRes : Int = -1) : Short {
        return getShort(context.getString(keyRes), default, defRes)
    }
    /** Get a value from preference. */
    @JvmOverloads
    fun getShort(key : String, default: Short = defaultShortValue, defRes : Int = -1) : Short {
        return getShortOrNull(key) ?: getDefaultValue(default, defRes)
    }
    /** Get a value from preference. */
    fun getShortOrNull(keyRes : Int) : Short? {
        return getShortOrNull(context.getString(keyRes))
    }
    /** Get a value from preference. */
    fun getShortOrNull(key : String) : Short? {
        return if (contains(key)) {
            sharedPreferences.getInt(key, 0).toShort()
        } else {
            null
        }
    }

    /** Get a value from preference. */
    @JvmOverloads
    fun getInt(keyRes : Int, default: Int = defaultIntValue, defRes : Int = -1) : Int {
        return getInt(context.getString(keyRes), default, defRes)
    }
    /** Get a value from preference. */
    @JvmOverloads
    fun getInt(key : String, default: Int = defaultIntValue, defRes : Int = -1) : Int {
        return getIntOrNull(key) ?: getDefaultValue(default, defRes)
    }
    /** Get a value from preference. */
    fun getIntOrNull(keyRes : Int) : Int? {
        return getIntOrNull(context.getString(keyRes))
    }
    /** Get a value from preference. */
    fun getIntOrNull(key : String) : Int? {
        return if (contains(key)) {
            sharedPreferences.getInt(key, 0)
        } else {
            null
        }
    }

    /** Get a value from preference. */
    @JvmOverloads
    fun getLong(keyRes : Int, default: Long = defaultLongValue, defRes : Int = -1) : Long {
        return getLong(context.getString(keyRes), default, defRes)
    }
    /** Get a value from preference. */
    @JvmOverloads
    fun getLong(key : String, default: Long = defaultLongValue, defRes : Int = -1) : Long {
        return getLongOrNull(key) ?: getDefaultValue(default, defRes)
    }
    /** Get a value from preference. */
    fun getLongOrNull(keyRes : Int) : Long? {
        return getLongOrNull(context.getString(keyRes))
    }
    /** Get a value from preference. */
    fun getLongOrNull(key : String) : Long? {
        return if (contains(key)) {
            sharedPreferences.getLong(key, 0)
        } else {
            null
        }
    }

    /** Get a value from preference. */
    @JvmOverloads
    fun getFloat(keyRes : Int, default: Float = defaultFloatValue, defRes : Int = -1): Float {
        return getFloat(context.getString(keyRes), default, defRes)
    }
    /** Get a value from preference. */
    @JvmOverloads
    fun getFloat(key : String, default: Float = defaultFloatValue, defRes : Int = -1) : Float {
        return getFloatOrNull(key) ?: getDefaultValue(default, defRes)
    }
    /** Get a value from preference. */
    fun getFloatOrNull(keyRes : Int) : Float? {
        return getFloatOrNull(context.getString(keyRes))
    }
    /** Get a value from preference. */
    fun getFloatOrNull(key : String) : Float? {
        return if (contains(key)) {
            sharedPreferences.getFloat(key, 0F)
        } else {
            null
        }
    }

    /** Get a value from preference. */
    @JvmOverloads
    fun getDouble(keyRes : Int, default: Double = defaultDoubleValue, defRes : Int = -1): Double {
        return getDouble(context.getString(keyRes), default, defRes)
    }
    /** Get a value from preference. */
    @JvmOverloads
    fun getDouble(key : String, default: Double = defaultDoubleValue, defRes : Int = -1) : Double {
        return getDoubleOrNull(key) ?: getDefaultValue(default, defRes)
    }
    /** Get a value from preference. */
    fun getDoubleOrNull(keyRes : Int) : Double? {
        return getDoubleOrNull(context.getString(keyRes))
    }
    /** Get a value from preference. */
    fun getDoubleOrNull(key : String) : Double? {
        return if (contains(key)) {
            val v = sharedPreferences.getLong(key, 0)
            java.lang.Double.longBitsToDouble(v)
        } else {
            null
        }
    }

    /** Get a value from preference. */
    @JvmOverloads
    fun getBigInteger(keyRes : Int, default: BigInteger = defaultBigIntegerValue, defRes : Int = -1): BigInteger {
        return getBigInteger(context.getString(keyRes), default, defRes)
    }
    /** Get a value from preference. */
    @JvmOverloads
    fun getBigInteger(key : String, default: BigInteger = defaultBigIntegerValue, defRes : Int = -1) : BigInteger {
        return getBigIntegerOrNull(key) ?: getDefaultValue(default, defRes)
    }
    /** Get a value from preference. */
    fun getBigIntegerOrNull(keyRes : Int) : BigInteger? {
        return getBigIntegerOrNull(context.getString(keyRes))
    }
    /** Get a value from preference. */
    fun getBigIntegerOrNull(key : String) : BigInteger? {
        val v = getStringOrNull(key)
        return if (v != null) {
            BigInteger(v)
        } else {
            null
        }
    }

    /** Get a value from preference. */
    @JvmOverloads
    fun getBigDecimal(keyRes : Int, default: BigDecimal = defaultBigDecimalValue, defRes : Int = -1): BigDecimal {
        return getBigDecimal(context.getString(keyRes), default, defRes)
    }
    /** Get a value from preference. */
    @JvmOverloads
    fun getBigDecimal(key : String, default: BigDecimal = defaultBigDecimalValue, defRes : Int = -1) : BigDecimal {
        return getBigDecimalOrNull(key) ?: getDefaultValue(default, defRes)
    }
    /** Get a value from preference. */
    fun getBigDecimalOrNull(keyRes : Int) : BigDecimal? {
        return getBigDecimalOrNull(context.getString(keyRes))
    }
    /** Get a value from preference. */
    fun getBigDecimalOrNull(key : String) : BigDecimal? {
        val v = getStringOrNull(key)
        return if (v != null) {
            BigDecimal(v)
        } else {
            return null
        }
    }

    /** Get a value from preference. */
    @JvmOverloads
    fun getChar(keyRes : Int, default: Char = defaultCharValue, defRes : Int = -1) : Char {
        return getChar(context.getString(keyRes), default, defRes)
    }
    /** Get a value from preference. */
    @JvmOverloads
    fun getChar(key : String, default: Char = defaultCharValue, defRes : Int = -1) : Char {
        return  getCharOrNull(key) ?: getDefaultValue(default, defRes)
    }
    /** Get a value from preference. */
    fun getCharOrNull(keyRes : Int) : Char? {
        return getCharOrNull(context.getString(keyRes))
    }
    /** Get a value from preference. */
    fun getCharOrNull(key : String) : Char? {
        val v = sharedPreferences.getString(key, null)
        return if (!v.isNullOrEmpty()) {
            v[0]
        } else {
            null
        }
    }

    /** Get a value from preference. */
    @JvmOverloads
    fun getString(keyRes : Int, default: String = defaultStringValue, defRes : Int = -1) : String {
        return getString(context.getString(keyRes), default, defRes)
    }
    /** Get a value from preference. */
    @JvmOverloads
    fun getString(key : String, default: String = defaultStringValue, defRes : Int = -1) : String {
        return getStringOrNull(key) ?: getDefaultValue(default, defRes)
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
    fun getStringSet(keyRes : Int, default: Set<String?> = defaultStringSetValue, defRes : Int = -1) : Set<String?> {
        return getStringSet(context.getString(keyRes), default, defRes)
    }
    /** Get a value from preference. */
    @JvmOverloads
    fun getStringSet(key : String, default: Set<String?> = defaultStringSetValue, defRes : Int = -1) : Set<String?> {
        val v = getStringSetOrNull(key)
        return if (v != null) {
            return v
        } else {
            if (defRes > 0)
                context.resources.getStringArray(defRes).toSet()
            else
                default
        }
    }
    /** Get a value from preference. */
    fun getStringSetOrNull(keyRes : Int) : Set<String?>? {
        return getStringSetOrNull(context.getString(keyRes))
    }
    /** Get a value from preference. */
    fun getStringSetOrNull(key : String) : Set<String?>? {
        return sharedPreferences.getStringSet(key, null)
    }

    /** Get a value from preference.  */
    fun getBin(keyRes : Int, default: ByteArray = defaultBinValue) : ByteArray {
        return getBin(context.getString(keyRes), default)
    }
    /** Get a value from preference.  */
    fun getBin(key : String, default: ByteArray = defaultBinValue) : ByteArray {
        return getBinOrNull(key) ?: default
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

    /** Get a value from preference. */
    inline fun <reified T> getObject(keyRes: Int, default: T) : T {
        return getObjectOrNull(keyRes) ?: default
    }
    /** Get a value from preference. */
    inline fun <reified T> getObject(key: String, default: T) : T {
        return getObjectOrNull(key) ?: default
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
    /** Get a value from preference. */
    inline fun <reified T> getObjectOrNull(keyRes: Int) : T? {
        return getObjectOrNull(keyRes, T::class.java)
    }
    /** Get a value from preference. */
    inline fun <reified T> getObjectOrNull(key: String) : T? {
        return getObjectOrNull(key, T::class.java)
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



    /** Set a value to preference. */
    fun putBoolean(keyRes : Int, value : Boolean?) : Prefs {
        return putBoolean(context.getString(keyRes), value)
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
    fun putByte(keyRes : Int, value : Byte?) : Prefs {
        return putByte(context.getString(keyRes), value)
    }
    /** Set a value to preference. */
    fun putByte(key : String, value : Byte?) : Prefs {
        return putInt(key, value?.toInt())
    }

    /** Set a value to preference. */
    fun putShort(keyRes : Int, value : Short?) : Prefs {
        return putShort(context.getString(keyRes), value)
    }
    /** Set a value to preference. */
    fun putShort(key : String, value : Short?) : Prefs {
        return putInt(key, value?.toInt())
    }

    /** Set a value to preference. */
    fun putInt(keyRes : Int, value : Int?) : Prefs {
        return putInt(context.getString(keyRes), value)
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
        return putLong(context.getString(keyRes), value)
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
        return putFloat(context.getString(keyRes), value)
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
    fun putDouble(keyRes : Int, value : Double?) : Prefs {
        return putDouble(context.getString(keyRes), value)
    }
    /** Set a value to preference. */
    fun putDouble(key : String, value : Double?) : Prefs {
        if (value == null) {
            remove(key)
        } else {
            editor.putLong(key, java.lang.Double.doubleToRawLongBits(value))
        }
        if (!beforeApply) end()
        return this
    }

    /** Set a value to preference. */
    fun putBigInteger(keyRes : Int, value : BigInteger?) : Prefs {
        return putBigInteger(context.getString(keyRes), value)
    }
    /** Set a value to preference. */
    fun putBigInteger(key : String, value : BigInteger?) : Prefs {
        return putString(key, value?.toString())
    }

    /** Set a value to preference. */
    fun putBigDecimal(keyRes : Int, value : BigDecimal?) : Prefs {
        return putBigDecimal(context.getString(keyRes), value)
    }
    /** Set a value to preference. */
    fun putBigDecimal(key : String, value : BigDecimal?) : Prefs {
        return putString(key, value?.toPlainString())
    }

    /** Set a value to preference. */
    fun putChar(keyRes : Int, value : Char?) : Prefs {
        return putChar(context.getString(keyRes), value)
    }
    /** Set a value to preference. */
    fun putChar(key : String, value : Char?) : Prefs {
        editor.putString(key, value.toString())
        if (!beforeApply) end()
        return this
    }

    /** Set a value to preference. */
    fun putString(keyRes : Int, value : CharSequence?) : Prefs {
        return putString(context.getString(keyRes), value)
    }
    /** Set a value to preference. */
    fun putString(key : String, value : CharSequence?) : Prefs {
        editor.putString(key, value?.toString())
        if (!beforeApply) end()
        return this
    }

    /** Set a value to preference. */
    fun putStringSet(keyRes : Int, value : Set<String?>?) : Prefs {
        return putStringSet(context.getString(keyRes), value)
    }
    /** Set a value to preference. */
    fun putStringSet(key : String, value : Set<String?>?) : Prefs {
        editor.putStringSet(key, value)
        if (!beforeApply) end()
        return this
    }

    /** Set a value to preference. */
    fun putBin(keyRes : Int, value : ByteArray?) : Prefs {
        return putBin(context.getString(keyRes), value)
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
        return putObject(context.getString(keyRes), value)
    }
    /** Set a value to preference. */
    fun putObject(key : String, value : Any?) : Prefs {
        if (value == null) {
            editor.putString(key, null)
        } else {
            val json = gson.toJson(value)
            editor.putString(key, json)
        }
        if (!beforeApply) end()
        return this
    }



    /** Remove preference value. */
    fun remove(keyRes : Int) : Prefs {
        return remove(context.getString(keyRes))
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



    /**
     * Get resource value.
     * @param defRes A resource ID. (bool, integer, dimen, string)
     */
    private inline fun<reified T> getDefaultValue(default : T, defRes : Int) : T {
        if (defRes <= 0)
            return default

        val outputClass = T::class
        val resourceVal = TypedValue()
        context.resources.getValue(defRes, resourceVal, true)

        if (resourceVal.type == TypedValue.TYPE_INT_BOOLEAN) {
            val res = context.resources.getBoolean(defRes)
            val value : Any = when (outputClass) {
                Boolean::class -> res
                String::class -> res.toString()
                else -> throw ClassCastException()
            }
            return value as T
        } else if (resourceVal.type == TypedValue.TYPE_FIRST_INT) {
            val res = resourceVal.data
            val value : Any = when (outputClass) {
                Boolean::class -> (res != 0)
                Byte::class -> res.toByte()
                Short::class -> res.toShort()
                Integer::class -> res
                Long::class -> res.toLong()
                Float::class -> res.toFloat()
                Double::class -> res.toDouble()
                Char::class -> res.toChar()
                String::class -> res.toString()
                BigInteger::class -> res.toBigInteger()
                BigDecimal::class -> res.toBigDecimal()
                else -> throw ClassCastException()
            }
            return value as T
        } else if (resourceVal.type == TypedValue.TYPE_FLOAT) {
            val res = resourceVal.float
            val value : Any = when (outputClass) {
                Boolean::class -> (res != 0.0F)
                Byte::class -> res.toByte()
                Short::class -> res.toShort()
                Integer::class -> res.toInt()
                Long::class -> res.toLong()
                Float::class -> res
                Double::class -> res.toDouble()
                Char::class -> res.toChar()
                String::class -> res.toString()
                BigInteger::class -> BigInteger.valueOf(res.toLong())
                BigDecimal::class -> res.toBigDecimal()
                else -> throw ClassCastException()
            }
            return value as T
        } else if (resourceVal.type == TypedValue.TYPE_STRING) {
            val res = context.getString(defRes)
            val value : Any = when (outputClass) {
                Boolean::class -> res.toBoolean()
                Byte::class -> res.toByte()
                Short::class -> res.toShort()
                Integer::class -> res.toInt()
                Long::class -> res.toLong()
                Float::class -> res.toFloat()
                Double::class -> res.toDouble()
                Char::class -> res[0]
                String::class -> res
                BigInteger::class -> res.toBigInteger()
                BigDecimal::class -> res.toBigDecimal()
                else -> throw ClassCastException()
            }
            return value as T
        }

        throw java.lang.ClassCastException()
    }
}