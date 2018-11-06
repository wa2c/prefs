package com.wa2c.android.prefs

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.support.annotation.*
import android.util.Base64
import android.util.TypedValue
import com.google.gson.Gson
import java.io.*
import java.lang.reflect.Type
import java.math.BigDecimal
import java.math.BigInteger
import java.util.*


/**
 * SharedPreferences wrapper.
 * @param context A context.
 * @param name The preferences file name. Use default shared preferences if null.
 */
open class Prefs @JvmOverloads constructor(protected val context: Context, protected val name : String? = null) {

    /** SharedPreferences. */
    val sharedPreferences : SharedPreferences = if (name.isNullOrEmpty()) {
        PreferenceManager.getDefaultSharedPreferences(context)
    } else {
        context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }
    /** SharedPreferences.Editor */
    protected val editor = sharedPreferences.edit()
    /** Gson. */
    protected val gson : Gson by lazy { Gson() }
    /** True if editing before applying. */
    protected var beforeApply = false

    /** The default boolean value. */
    var defaultBooleanValue : Boolean = false
    /** The default byte value. */
    var defaultByteValue : Byte = 0
    /** The default short value. */
    var defaultShortValue : Short = 0
    /** The default int value. */
    var defaultIntValue : Int = 0
    /** The default long value. */
    var defaultLongValue : Long = 0
    /** The default float value. */
    var defaultFloatValue : Float = 0.0F
    /** The default double value. */
    var defaultDoubleValue : Double = 0.0
    /** The default BigInteger value. */
    var defaultBigIntegerValue : BigInteger = BigInteger.ZERO
    /** The default BigDecimal value. */
    var defaultBigDecimalValue : BigDecimal = BigDecimal.ZERO
    /** The default char value. */
    var defaultCharValue : Char = '\u0000'
    /** The default String value. */
    var defaultStringValue : String = ""
    /** The default String set value. */
    var defaultStringSetValue : Set<String> = HashSet(0)
    /** The default byte array value. */
    var defaultBinValue : ByteArray = ByteArray(0)


    // Contains

    /**
     * Checks whether the preferences contains a preference.
     * @param keyRes The string resource id of the preference name.
     * @return Returns true if the preference exists in the preferences, otherwise false.
     */
    fun contains(@StringRes keyRes: Int) : Boolean {
        return contains(context.getString(keyRes))
    }

    /**
     * Checks whether the preferences contains a preference to check.
     * @param key The preference name.
     * @return Returns true if the preference exists in the preferences, otherwise false.
     */
    fun contains(key : String) : Boolean {
        return sharedPreferences.contains(key)
    }



    // Get

    /**
     * Retrieve a boolean value from the preferences.
     * @param keyRes The string resource id of the preference name.
     * @param defValue Value to return if this preference does not exist. Ignores if defRes > 0.
     * @param defRes Resource id to return if this preference does not exist.
     * @return Returns the preference value if it exists, or default value.
     * @throws ClassCastException
     */
    @JvmOverloads
    fun getBoolean(@StringRes keyRes: Int, defValue: Boolean = defaultBooleanValue, @AnyRes defRes: Int = -1): Boolean {
        return getBoolean(context.getString(keyRes), defValue, defRes)
    }

    /**
     * Retrieve a boolean value from the preferences.
     * @param key The preference name.
     * @param defValue Value to return if this preference does not exist. Ignores if defRes > 0.
     * @param defRes Resource id to return if this preference does not exist.
     * @return Returns the preference value if it exists, or default value.
     * @throws ClassCastException
     */
    @JvmOverloads
    fun getBoolean(key: String, defValue: Boolean = defaultBooleanValue, @AnyRes defRes: Int = -1): Boolean {
        return getBooleanOrNull(key) ?: getDefaultValue(defValue, defRes)
    }

    /**
     * Retrieve a boolean value from the preferences.
     * @param keyRes The string resource id of the preference name.
     * @return Returns the preference value if it exists, or null.
     * @throws ClassCastException
     */
    fun getBooleanOrNull(@StringRes keyRes: Int): Boolean? {
        return getBooleanOrNull(context.getString(keyRes))
    }

    /**
     * Retrieve a boolean value from the preferences.
     * @param key The preference name.
     * @return Returns the preference value if it exists, or null.
     * @throws ClassCastException
     */
    fun getBooleanOrNull(key: String): Boolean? {
        return if (contains(key)) {
            sharedPreferences.getBoolean(key, false)
        } else {
            null
        }
    }



    /**
     * Retrieve a byte value from the preferences.
     * @param keyRes The string resource id of the preference name.
     * @param defValue Value to return if this preference does not exist. Ignores if defRes > 0.
     * @param defRes Resource id to return if this preference does not exist.
     * @return Returns the preference value if it exists, or default value.
     * @throws ClassCastException
     */
    @JvmOverloads
    fun getByte(@StringRes keyRes: Int, defValue: Byte = defaultByteValue, @IntegerRes @DimenRes @StringRes defRes: Int = -1): Byte {
        return getByte(context.getString(keyRes), defValue, defRes)
    }

    /**
     * Retrieve a byte value from the preferences.
     * @param key The preference name.
     * @param defValue Value to return if this preference does not exist. Ignores if defRes > 0.
     * @param defRes Resource id to return if this preference does not exist.
     * @return Returns the preference value if it exists, or default value.
     * @throws ClassCastException
     */
    @JvmOverloads
    fun getByte(key: String, defValue: Byte = defaultByteValue, @IntegerRes @DimenRes @StringRes defRes: Int = -1): Byte {
        return getByteOrNull(key) ?: getDefaultValue(defValue, defRes)
    }

    /**
     * Retrieve a byte value from the preferences.
     * @param keyRes The string resource id of the preference name.
     * @return Returns the preference value if it exists, or null.
     * @throws ClassCastException
     */
    fun getByteOrNull(@StringRes keyRes: Int): Byte? {
        return getByteOrNull(context.getString(keyRes))
    }

    /**
     * Retrieve a byte value from the preferences.
     * @param key The preference name.
     * @return Returns the preference value if it exists, or null.
     * @throws ClassCastException
     */
    fun getByteOrNull(key: String): Byte? {
        return if (contains(key)) {
            sharedPreferences.getInt(key, 0).toByte()
        } else {
            null
        }
    }



    /**
     * Retrieve a short value from the preferences.
     * @param keyRes The string resource id of the preference name.
     * @param defValue Value to return if this preference does not exist. Ignores if defRes > 0.
     * @param defRes Resource id to return if this preference does not exist.
     * @return Returns the preference value if it exists, or default value.
     * @throws ClassCastException
     */
    @JvmOverloads
    fun getShort(@StringRes keyRes: Int, defValue: Short = defaultShortValue, @IntegerRes @DimenRes @StringRes defRes: Int = -1): Short {
        return getShort(context.getString(keyRes), defValue, defRes)
    }

    /**
     * Retrieve a short value from the preferences.
     * @param key The preference name.
     * @param defValue Value to return if this preference does not exist. Ignores if defRes > 0.
     * @param defRes Resource id to return if this preference does not exist.
     * @return Returns the preference value if it exists, or default value.
     * @throws ClassCastException
     */
    @JvmOverloads
    fun getShort(key: String, defValue: Short = defaultShortValue, @IntegerRes @DimenRes @StringRes defRes: Int = -1): Short {
        return getShortOrNull(key) ?: getDefaultValue(defValue, defRes)
    }

    /**
     * Retrieve a short value from the preferences.
     * @param keyRes The string resource id of the preference name.
     * @return Returns the preference value if it exists, or null.
     * @throws ClassCastException
     */
    fun getShortOrNull(@StringRes keyRes: Int): Short? {
        return getShortOrNull(context.getString(keyRes))
    }

    /**
     * Retrieve a short value from the preferences.
     * @param key The preference name.
     * @return Returns the preference value if it exists, or null.
     * @throws ClassCastException
     */
    fun getShortOrNull(key: String): Short? {
        return if (contains(key)) {
            sharedPreferences.getInt(key, 0).toShort()
        } else {
            null
        }
    }



    /**
     * Retrieve a int value from the preferences.
     * @param keyRes The string resource id of the preference name.
     * @param defValue Value to return if this preference does not exist. Ignores if defRes > 0.
     * @param defRes Resource id to return if this preference does not exist.
     * @return Returns the preference value if it exists, or default value.
     * @throws ClassCastException
     */
    @JvmOverloads
    fun getInt(@StringRes keyRes: Int, defValue: Int = defaultIntValue, @IntegerRes @DimenRes @StringRes defRes: Int = -1): Int {
        return getInt(context.getString(keyRes), defValue, defRes)
    }

    /**
     * Retrieve a int value from the preferences.
     * @param key The preference name.
     * @param defValue Value to return if this preference does not exist. Ignores if defRes > 0.
     * @param defRes Resource id to return if this preference does not exist.
     * @return Returns the preference value if it exists, or default value.
     * @throws ClassCastException
     */
    @JvmOverloads
    fun getInt(key: String, defValue: Int = defaultIntValue, @IntegerRes @DimenRes @StringRes defRes: Int = -1): Int {
        return getIntOrNull(key) ?: getDefaultValue(defValue, defRes)
    }

    /**
     * Retrieve a int value from the preferences.
     * @param keyRes The string resource id of the preference name.
     * @return Returns the preference value if it exists, or null.
     * @throws ClassCastException
     */
    fun getIntOrNull(@StringRes keyRes: Int): Int? {
        return getIntOrNull(context.getString(keyRes))
    }

    /**
     * Retrieve a int value from the preferences.
     * @param key The preference name.
     * @return Returns the preference value if it exists, or null.
     * @throws ClassCastException
     */
    fun getIntOrNull(key: String): Int? {
        return if (contains(key)) {
            sharedPreferences.getInt(key, 0)
        } else {
            null
        }
    }



    /**
     * Retrieve a long value from the preferences.
     * @param keyRes The string resource id of the preference name.
     * @param defValue Value to return if this preference does not exist. Ignores if defRes > 0.
     * @param defRes Resource id to return if this preference does not exist.
     * @return Returns the preference value if it exists, or default value.
     * @throws ClassCastException
     */
    @JvmOverloads
    fun getLong(@StringRes keyRes: Int, defValue: Long = defaultLongValue, @IntegerRes @DimenRes @StringRes defRes: Int = -1): Long {
        return getLong(context.getString(keyRes), defValue, defRes)
    }

    /**
     * Retrieve a long value from the preferences.
     * @param key The preference name.
     * @param defValue Value to return if this preference does not exist. Ignores if defRes > 0.
     * @param defRes Resource id to return if this preference does not exist.
     * @return Returns the preference value if it exists, or default value.
     * @throws ClassCastException
     */
    @JvmOverloads
    fun getLong(key: String, defValue: Long = defaultLongValue, @IntegerRes @DimenRes @StringRes defRes: Int = -1): Long {
        return getLongOrNull(key) ?: getDefaultValue(defValue, defRes)
    }

    /**
     * Retrieve a long value from the preferences.
     * @param keyRes The string resource id of the preference name.
     * @return Returns the preference value if it exists, or null.
     * @throws ClassCastException
     */
    fun getLongOrNull(@StringRes keyRes: Int): Long? {
        return getLongOrNull(context.getString(keyRes))
    }

    /**
     * Retrieve a long value from the preferences.
     * @param key The preference name.
     * @return Returns the preference value if it exists, or null.
     * @throws ClassCastException
     */
    fun getLongOrNull(key: String): Long? {
        return if (contains(key)) {
            sharedPreferences.getLong(key, 0)
        } else {
            null
        }
    }



    /**
     * Retrieve a float value from the preferences.
     * @param keyRes The string resource id of the preference name.
     * @param defValue Value to return if this preference does not exist. Ignores if defRes > 0.
     * @param defRes Resource id to return if this preference does not exist.
     * @return Returns the preference value if it exists, or default value.
     * @throws ClassCastException
     */
    @JvmOverloads
    fun getFloat(@StringRes keyRes: Int, defValue: Float = defaultFloatValue, @IntegerRes @DimenRes @StringRes defRes: Int = -1): Float {
        return getFloat(context.getString(keyRes), defValue, defRes)
    }

    /**
     * Retrieve a float value from the preferences.
     * @param key The preference name.
     * @param defValue Value to return if this preference does not exist. Ignores if defRes > 0.
     * @param defRes Resource id to return if this preference does not exist.
     * @return Returns the preference value if it exists, or default value.
     * @throws ClassCastException
     */
    @JvmOverloads
    fun getFloat(@StringRes key: String, defValue: Float = defaultFloatValue, @IntegerRes @DimenRes @StringRes defRes: Int = -1): Float {
        return getFloatOrNull(key) ?: getDefaultValue(defValue, defRes)
    }

    /**
     * Retrieve a float value from the preferences.
     * @param keyRes The string resource id of the preference name.
     * @return Returns the preference value if it exists, or null.
     * @throws ClassCastException
     */
    fun getFloatOrNull(@StringRes keyRes: Int): Float? {
        return getFloatOrNull(context.getString(keyRes))
    }

    /**
     * Retrieve a float value from the preferences.
     * @param key The preference name.
     * @return Returns the preference value if it exists, or null.
     * @throws ClassCastException
     */
    fun getFloatOrNull(key: String): Float? {
        return if (contains(key)) {
            sharedPreferences.getFloat(key, 0F)
        } else {
            null
        }
    }



    /**
     * Retrieve a double value from the preferences.
     * @param keyRes The string resource id of the preference name.
     * @param defValue Value to return if this preference does not exist. Ignores if defRes > 0.
     * @param defRes Resource id to return if this preference does not exist.
     * @return Returns the preference value if it exists, or default value.
     * @throws ClassCastException
     */
    @JvmOverloads
    fun getDouble(keyRes: Int, defValue: Double = defaultDoubleValue, @IntegerRes @DimenRes @StringRes defRes: Int = -1): Double {
        return getDouble(context.getString(keyRes), defValue, defRes)
    }

    /**
     * Retrieve a double value from the preferences.
     * @param key The preference name.
     * @param defValue Value to return if this preference does not exist. Ignores if defRes > 0.
     * @param defRes Resource id to return if this preference does not exist.
     * @return Returns the preference value if it exists, or default value.
     * @throws ClassCastException
     */
    @JvmOverloads
    fun getDouble(key: String, defValue: Double = defaultDoubleValue, @IntegerRes @DimenRes @StringRes defRes: Int = -1): Double {
        return getDoubleOrNull(key) ?: getDefaultValue(defValue, defRes)
    }

    /**
     * Retrieve a double value from the preferences.
     * @param keyRes The string resource id of the preference name.
     * @return Returns the preference value if it exists, or null.
     * @throws ClassCastException
     */
    fun getDoubleOrNull(@StringRes keyRes: Int): Double? {
        return getDoubleOrNull(context.getString(keyRes))
    }

    /**
     * Retrieve a double value from the preferences.
     * @param key The preference name.
     * @return Returns the preference value if it exists, or null.
     * @throws ClassCastException
     */
    fun getDoubleOrNull(key: String): Double? {
        return if (contains(key)) {
            val v = sharedPreferences.getLong(key, 0)
            java.lang.Double.longBitsToDouble(v)
        } else {
            null
        }
    }



    /**
     * Retrieve a BigInteger value from the preferences.
     * @param keyRes The string resource id of the preference name.
     * @param defValue Value to return if this preference does not exist. Ignores if defRes > 0.
     * @param defRes Resource id to return if this preference does not exist.
     * @return Returns the preference value if it exists, or default value.
     * @throws ClassCastException
     */
    @JvmOverloads
    fun getBigInteger(@StringRes keyRes: Int, defValue: BigInteger = defaultBigIntegerValue, @IntegerRes @DimenRes @StringRes defRes: Int = -1): BigInteger {
        return getBigInteger(context.getString(keyRes), defValue, defRes)
    }

    /**
     * Retrieve a BitInteger value from the preferences.
     * @param key The preference name.
     * @param defValue Value to return if this preference does not exist. Ignores if defRes > 0.
     * @param defRes Resource id to return if this preference does not exist.
     * @return Returns the preference value if it exists, or default value.
     * @throws ClassCastException
     */
    @JvmOverloads
    fun getBigInteger(key: String, defValue: BigInteger = defaultBigIntegerValue, @IntegerRes @DimenRes @StringRes defRes: Int = -1): BigInteger {
        return getBigIntegerOrNull(key) ?: getDefaultValue(defValue, defRes)
    }

    /**
     * Retrieve a BitInteger value from the preferences.
     * @param keyRes The string resource id of the preference name.
     * @return Returns the preference value if it exists, or null.
     * @throws ClassCastException
     */
    fun getBigIntegerOrNull(@StringRes keyRes: Int): BigInteger? {
        return getBigIntegerOrNull(context.getString(keyRes))
    }

    /**
     * Retrieve a BitInteger value from the preferences.
     * @param key The preference name.
     * @return Returns the preference value if it exists, or null.
     * @throws ClassCastException
     */
    fun getBigIntegerOrNull(key: String): BigInteger? {
        val v = getStringOrNull(key)
        return if (v != null) {
            BigInteger(v)
        } else {
            null
        }
    }



    /**
     * Retrieve a BigDecimal value from the preferences.
     * @param keyRes The string resource id of the preference name.
     * @param defValue Value to return if this preference does not exist. Ignores if defRes > 0.
     * @param defRes Resource id to return if this preference does not exist.
     * @return Returns the preference value if it exists, or default value.
     * @throws ClassCastException
     */
    @JvmOverloads
    fun getBigDecimal(@StringRes keyRes: Int, defValue: BigDecimal = defaultBigDecimalValue, @IntegerRes @DimenRes @StringRes defRes: Int = -1): BigDecimal {
        return getBigDecimal(context.getString(keyRes), defValue, defRes)
    }

    /**
     * Retrieve a BigDecimal value from the preferences.
     * @param key The preference name.
     * @param defValue Value to return if this preference does not exist. Ignores if defRes > 0.
     * @param defRes Resource id to return if this preference does not exist.
     * @return Returns the preference value if it exists, or default value.
     * @throws ClassCastException
     */
    @JvmOverloads
    fun getBigDecimal(key: String, defValue: BigDecimal = defaultBigDecimalValue, @IntegerRes @DimenRes @StringRes defRes: Int = -1): BigDecimal {
        return getBigDecimalOrNull(key) ?: getDefaultValue(defValue, defRes)
    }

    /**
     * Retrieve a BigDecimal value from the preferences.
     * @param keyRes The string resource id of the preference name.
     * @return Returns the preference value if it exists, or null.
     * @throws ClassCastException
     */
    fun getBigDecimalOrNull(@StringRes keyRes: Int): BigDecimal? {
        return getBigDecimalOrNull(context.getString(keyRes))
    }

    /**
     * Retrieve a BigDecimal value from the preferences.
     * @param key The preference name.
     * @return Returns the preference value if it exists, or null.
     * @throws ClassCastException
     */
    fun getBigDecimalOrNull(key: String): BigDecimal? {
        val v = getStringOrNull(key)
        return if (v != null) {
            BigDecimal(v)
        } else {
            return null
        }
    }



    /**
     * Retrieve a Char value from the preferences.
     * @param keyRes The string resource id of the preference name.
     * @param defValue Value to return if this preference does not exist. Ignores if defRes > 0.
     * @param defRes Resource id to return if this preference does not exist.
     * @return Returns the preference value if it exists, or default value.
     * @throws ClassCastException
     */
    @JvmOverloads
    fun getChar(@StringRes keyRes: Int, defValue: Char = defaultCharValue, @IntegerRes @DimenRes @StringRes defRes: Int = -1): Char {
        return getChar(context.getString(keyRes), defValue, defRes)
    }

    /**
     * Retrieve a Char value from the preferences.
     * @param key The preference name.
     * @param defValue Value to return if this preference does not exist. Ignores if defRes > 0.
     * @param defRes Resource id to return if this preference does not exist.
     * @return Returns the preference value if it exists, or default value.
     * @throws ClassCastException
     */
    @JvmOverloads
    fun getChar(key: String, defValue: Char = defaultCharValue, @IntegerRes @DimenRes @StringRes defRes: Int = -1): Char {
        return getCharOrNull(key) ?: getDefaultValue(defValue, defRes)
    }

    /**
     * Retrieve a Char value from the preferences.
     * @param keyRes The string resource id of the preference name.
     * @return Returns the preference value if it exists, or null.
     * @throws ClassCastException
     */
    fun getCharOrNull(@StringRes keyRes: Int): Char? {
        return getCharOrNull(context.getString(keyRes))
    }

    /**
     * Retrieve a Char value from the preferences.
     * @param key The preference name.
     * @return Returns the preference value if it exists, or null.
     * @throws ClassCastException
     */
    fun getCharOrNull(key: String): Char? {
        val v = sharedPreferences.getString(key, null)
        return if (!v.isNullOrEmpty()) {
            v[0]
        } else {
            null
        }
    }



    /**
     * Retrieve a String value from the preferences.
     * @param keyRes The string resource id of the preference name.
     * @param defValue Value to return if this preference does not exist. Ignores if defRes > 0.
     * @param defRes Resource id to return if this preference does not exist.
     * @return Returns the preference value if it exists, or default value.
     * @throws ClassCastException
     */
    @JvmOverloads
    fun getString(@StringRes keyRes: Int, defValue: String = defaultStringValue, @IntegerRes @DimenRes @StringRes defRes: Int = -1): String {
        return getString(context.getString(keyRes), defValue, defRes)
    }

    /**
     * Retrieve a String value from the preferences.
     * @param key The preference name.
     * @param defValue Value to return if this preference does not exist. Ignores if defRes > 0.
     * @param defRes Resource id to return if this preference does not exist.
     * @return Returns the preference value if it exists, or default value.
     * @throws ClassCastException
     */
    @JvmOverloads
    fun getString(key: String, defValue: String = defaultStringValue, @IntegerRes @DimenRes @StringRes defRes: Int = -1): String {
        return getStringOrNull(key) ?: getDefaultValue(defValue, defRes)
    }

    /**
     * Retrieve a String value from the preferences.
     * @param keyRes The string resource id of the preference name.
     * @return Returns the preference value if it exists, or null.
     * @throws ClassCastException
     */
    fun getStringOrNull(@StringRes keyRes: Int): String? {
        return getStringOrNull(context.getString(keyRes))
    }

    /**
     * Retrieve a String value from the preferences.
     * @param key The preference name.
     * @return Returns the preference value if it exists, or null.
     * @throws ClassCastException
     */
    fun getStringOrNull(key: String): String? {
        return sharedPreferences.getString(key, null)
    }



    /**
     * Retrieve a String set value from the preferences.
     * @param keyRes The string resource id of the preference name.
     * @param defValue Value to return if this preference does not exist. Ignores if defRes > 0.
     * @param defRes Resource id to return if this preference does not exist.
     * @return Returns the preference value if it exists, or default value.
     * @throws ClassCastException
     */
    @JvmOverloads
    fun getStringSet(@StringRes keyRes: Int, defValue: Set<String?> = defaultStringSetValue, @ArrayRes defRes: Int = -1): Set<String?> {
        return getStringSet(context.getString(keyRes), defValue, defRes)
    }

    /**
     * Retrieve a String set value from the preferences.
     * @param key The preference name.
     * @param defValue Value to return if this preference does not exist. Ignores if defRes > 0.
     * @param defRes Resource id to return if this preference does not exist.
     * @return Returns the preference value if it exists, or default value.
     * @throws ClassCastException
     */
    @JvmOverloads
    fun getStringSet(key: String, defValue: Set<String?> = defaultStringSetValue, @ArrayRes defRes: Int = -1): Set<String?> {
        return getStringSetOrNull(key) ?: getDefaultValue(defValue, defRes)
    }

    /**
     * Retrieve a String set value from the preferences.
     * @param keyRes The string resource id of the preference name.
     * @return Returns the preference value if it exists, or null.
     * @throws ClassCastException
     */
    fun getStringSetOrNull(@StringRes keyRes: Int): Set<String?>? {
        return getStringSetOrNull(context.getString(keyRes))
    }

    /**
     * Retrieve a String set value from the preferences.
     * @param key The preference name.
     * @return Returns the preference value if it exists, or null.
     * @throws ClassCastException
     */
    fun getStringSetOrNull(key: String): Set<String?>? {
        return sharedPreferences.getStringSet(key, null)
    }



    /**
     * Retrieve a byte array value from the preferences.
     * @param keyRes The string resource id of the preference name.
     * @param defValue Value to return if this preference does not exist.
     * @param defRes Resource id to return if this preference does not exist.
     * @return Returns the preference value if it exists, or default value.
     * @throws ClassCastException
     */
    @JvmOverloads
    fun getBin(@StringRes keyRes: Int, defValue: ByteArray = defaultBinValue, @AnyRes defRes: Int = -1): ByteArray {
        return getBin(context.getString(keyRes), defValue, defRes)
    }

    /**
     * Retrieve a byte array value from the preferences.
     * @param key The preference name.
     * @param defValue Value to return if this preference does not exist.
     * @param defRes Resource id to return if this preference does not exist.
     * @return Returns the preference value if it exists, or default value.
     * @throws ClassCastException
     */
    @JvmOverloads
    fun getBin(key: String, defValue: ByteArray = defaultBinValue, @AnyRes defRes: Int = -1): ByteArray {
        return getBinOrNull(key) ?: getDefaultValue(defValue, defRes)
    }

    /**
     * Retrieve a byte array value from the preferences.
     * @param keyRes The string resource id of the preference name.
     * @return Returns the preference value if it exists, or null.
     * @throws ClassCastException
     */
    fun getBinOrNull(@StringRes keyRes: Int): ByteArray? {
        return getBinOrNull(context.getString(keyRes))
    }

    /**
     * Retrieve a byte array value from the preferences.
     * @param key The preference name.
     * @return Returns the preference value if it exists, or null.
     * @throws ClassCastException
     */
    fun getBinOrNull(key: String): ByteArray? {
        val base64Bytes = getStringOrNull(key)
        if (base64Bytes.isNullOrEmpty())
            return null
        return Base64.decode(base64Bytes, Base64.DEFAULT)
    }



    /**
     * Retrieve a Serializable value from the preferences. Keep the class when using Proguard.
     * @param keyRes The string resource id of the preference name.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or default value.
     * @throws ClassCastException
     */
    inline fun <reified T : Serializable> getSerializable(@StringRes keyRes: Int, defValue: T): T {
        return getSerializable(keyRes, T::class.java, defValue)
    }

    /**
     * Retrieve a Serializable value from the preferences. Keep the class when using Proguard.
     * @param key The preference name.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or default value.
     * @throws ClassCastException
     */
    inline fun <reified T : Serializable> getSerializable(key: String, defValue: T): T {
        return getSerializable(key, T::class.java, defValue)
    }

    /**
     * Retrieve a Serializable value from the preferences. Keep the class when using Proguard.
     * @param keyRes The string resource id of the preference name.
     * @param classOfT The class of T.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or default value.
     * @throws ClassCastException
     */
    fun <T : Serializable> getSerializable(@StringRes keyRes: Int, classOfT: Class<T>, defValue: T): T {
        return getSerializable(context.getString(keyRes), classOfT, defValue)
    }

    /**
     * Retrieve a Serializable value from the preferences. Keep the class when using Proguard.
     * @param key The preference name.
     * @param classOfT The class of T.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or default value.
     * @throws ClassCastException
     */
    fun <T : Serializable> getSerializable(key: String, classOfT: Class<T>, defValue: T): T {
        return getSerializableOrNull(key, classOfT) ?: defValue
    }

    /**
     * Retrieve a Serializable value from the preferences. Keep the class when using Proguard.
     * @param keyRes The string resource id of the preference name.
     * @return Returns the preference value if it exists, or null.
     * @throws ClassCastException
     */
    inline fun <reified T : Serializable> getSerializableOrNull(@StringRes keyRes: Int): T? {
        return getSerializableOrNull(keyRes, T::class.java)
    }

    /**
     * Retrieve a Serializable value from the preferences. Keep the class when using Proguard.
     * @param key The preference name.
     * @return Returns the preference value if it exists, or null.
     * @throws ClassCastException
     */
    inline fun <reified T : Serializable> getSerializableOrNull(key: String): T? {
        return getSerializableOrNull(key, T::class.java)
    }

    /**
     * Retrieve a Serializable value from the preferences. Keep the class when using Proguard.
     * @param keyRes The string resource id of the preference name.
     * @param classOfT The class of T.
     * @return Returns the preference value if it exists, or null.
     * @throws ClassCastException
     */
    fun <T : Serializable> getSerializableOrNull(@StringRes keyRes: Int, classOfT: Class<T>): T? {
        return getSerializableOrNull(context.getString(keyRes), classOfT)
    }

    /**
     * Retrieve a Serializable value from the preferences. Keep the class when using Proguard.
     * @param key The preference name.
     * @param classOfT The class of T.
     * @return Returns the preference value if it exists, or null.
     * @throws ClassCastException
     */
    fun <T : Serializable> getSerializableOrNull(key: String, classOfT: Class<T>): T? {
        val value = getBinOrNull(key)
        if (value == null || value.isEmpty()) {
            return null
        }
        ObjectInputStream(ByteArrayInputStream(value)).use {
            return classOfT.cast(it.readObject())
        }
    }



    /**
     * Retrieve a Object value from the preferences. Keep the class when using Proguard.
     * @param keyRes The string resource id of the preference name.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or default value.
     * @throws ClassCastException
     */
    inline fun <reified T> getObject(@StringRes keyRes: Int, defValue: T): T {
        return getObject(keyRes, T::class.java, defValue)
    }

    /**
     * Retrieve a Object value from the preferences. Keep the class when using Proguard.
     * @param key The preference name.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or default value.
     * @throws ClassCastException
     */
    inline fun <reified T> getObject(key: String, defValue: T): T {
        return getObject(key, T::class.java, defValue)
    }

    /**
     * Retrieve a Object value from the preferences. Keep the class when using Proguard.
     * @param keyRes The string resource id of the preference name.
     * @param classOfT The class of T.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or default value.
     * @throws ClassCastException
     */
    fun <T> getObject(@StringRes keyRes: Int, classOfT: Class<T>, defValue: T): T {
        return getObject(context.getString(keyRes), classOfT, defValue)
    }

    /**
     * Retrieve a Object value from the preferences. Keep the class when using Proguard.
     * @param key The preference name.
     * @param classOfT The class of T.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or default value.
     * @throws ClassCastException
     */
    fun <T> getObject(key: String, classOfT: Class<T>, defValue: T): T {
        return getObjectOrNull(key, classOfT) ?: defValue
    }

    /**
     * Retrieve a Object value from the preferences. Keep the class when using Proguard.
     * @param keyRes The string resource id of the preference name.
     * @param typeOfT The type of T.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or default value.
     * @throws ClassCastException
     */
    fun <T> getObject(@StringRes keyRes: Int, typeOfT: Type, defValue: T): T {
        return getObject(context.getString(keyRes), typeOfT, defValue)
    }

    /**
     * Retrieve a Object value from the preferences. Keep the class when using Proguard.
     * @param key The preference name.
     * @param typeOfT The type of T.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or default value.
     * @throws ClassCastException
     */
    fun <T> getObject(key: String, typeOfT: Type, defValue: T): T {
        return getObjectOrNull(key, typeOfT) ?: defValue
    }

    /**
     * Retrieve a Object value from the preferences. Keep the class when using Proguard.
     * @param keyRes The string resource id of the preference name.
     * @return Returns the preference value if it exists, or null.
     * @throws ClassCastException
     */
    inline fun <reified T> getObjectOrNull(@StringRes keyRes: Int): T? {
        return getObjectOrNull(keyRes, T::class.java)
    }

    /**
     * Retrieve a Object value from the preferences. Keep the class when using Proguard.
     * @param key The preference name.
     * @return Returns the preference value if it exists, or null.
     * @throws ClassCastException
     */
    inline fun <reified T> getObjectOrNull(key: String): T? {
        return getObjectOrNull(key, T::class.java)
    }

    /**
     * Retrieve a Object value from the preferences. Keep the class when using Proguard.
     * @param keyRes The string resource id of the preference name.
     * @param classOfT The class of T.
     * @return Returns the preference value if it exists, or null.
     * @throws ClassCastException
     */
    fun <T> getObjectOrNull(@StringRes keyRes: Int, classOfT: Class<T>): T? {
        return getObjectOrNull(context.getString(keyRes), classOfT)
    }

    /**
     * Retrieve a Object value from the preferences. Keep the class when using Proguard.
     * @param key The preference name.
     * @param classOfT The class of T.
     * @return Returns the preference value if it exists, or null.
     * @throws ClassCastException
     */
    fun <T> getObjectOrNull(key: String, classOfT: Class<T>): T? {
        val json = sharedPreferences.getString(key, null)
        return gson.fromJson(json, classOfT)
    }

    /**
     * Retrieve a Object value from the preferences. Keep the class when using Proguard.
     * @param keyRes The string resource id of the preference name.
     * @param typeOfT The type of T.
     * @return Returns the preference value if it exists, or null.
     * @throws ClassCastException
     */
    fun <T> getObjectOrNull(@StringRes keyRes: Int, typeOfT: Type): T? {
        return getObjectOrNull(context.getString(keyRes), typeOfT)
    }

    /**
     * Retrieve a Object value from the preferences. Keep the class when using Proguard.
     * @param key The preference name.
     * @param typeOfT The type of T.
     * @return Returns the preference value if it exists, or null.
     * @throws ClassCastException
     */
    fun <T> getObjectOrNull(key: String, typeOfT: Type): T? {
        val json = sharedPreferences.getString(key, null)
        return gson.fromJson(json, typeOfT)
    }



    // Put

    /**
     * Set a boolean value to the preferences.
     * @param keyRes The string resource id of the preference name.
     * @param value The new value for the preference. Remove value if null.
     * @return A reference to this object.
     */
    fun putBoolean(@StringRes keyRes: Int, value: Boolean?): Prefs {
        return putBoolean(context.getString(keyRes), value)
    }

    /**
     * Set a boolean value to the preferences.
     * @param key The preference name.
     * @param value The new value for the preference. Remove value if null.
     * @return A reference to this object.
     */
    fun putBoolean(key: String, value: Boolean?): Prefs {
        if (value == null) {
            remove(key)
        } else {
            editor.putBoolean(key, value)
        }
        if (!beforeApply) end()
        return this
    }



    /**
     * Set a byte value to the preferences.
     * @param keyRes The string resource id of the preference name.
     * @param value The new value for the preference. Remove value if null.
     * @return A reference to this object.
     */
    fun putByte(@StringRes keyRes: Int, value: Byte?): Prefs {
        return putByte(context.getString(keyRes), value)
    }

    /**
     * Set a byte value to the preferences.
     * @param key The preference name.
     * @param value The new value for the preference. Remove value if null.
     * @return A reference to this object.
     */
    fun putByte(key: String, value: Byte?): Prefs {
        return putInt(key, value?.toInt())
    }



    /**
     * Set a short value to the preferences.
     * @param keyRes The string resource id of the preference name.
     * @param value The new value for the preference. Remove value if null.
     * @return A reference to this object.
     */
    fun putShort(@StringRes keyRes: Int, value: Short?): Prefs {
        return putShort(context.getString(keyRes), value)
    }

    /**
     * Set a short value to the preferences.
     * @param key The preference name.
     * @param value The new value for the preference. Remove value if null.
     * @return A reference to this object.
     */
    fun putShort(key: String, value: Short?): Prefs {
        return putInt(key, value?.toInt())
    }



    /**
     * Set a int value to the preferences.
     * @param keyRes The string resource id of the preference name.
     * @param value The new value for the preference. Remove value if null.
     * @return A reference to this object.
     */
    fun putInt(@StringRes keyRes: Int, value: Int?): Prefs {
        return putInt(context.getString(keyRes), value)
    }

    /**
     * Set a int value to the preferences.
     * @param key The preference name.
     * @param value The new value for the preference. Remove value if null.
     * @return A reference to this object.
     */
    fun putInt(key: String, value: Int?): Prefs {
        if (value == null) {
            remove(key)
        } else {
            editor.putInt(key, value)
        }
        if (!beforeApply) end()
        return this
    }



    /**
     * Set a long value to the preferences.
     * @param keyRes The string resource id of the preference name.
     * @param value The new value for the preference. Remove value if null.
     * @return A reference to this object.
     */
    fun putLong(@StringRes keyRes: Int, value: Long?): Prefs {
        return putLong(context.getString(keyRes), value)
    }

    /**
     * Set a long value to the preferences.
     * @param key The preference name.
     * @param value The new value for the preference. Remove value if null.
     * @return A reference to this object.
     */
    fun putLong(key: String, value: Long?): Prefs {
        if (value == null) {
            remove(key)
        } else {
            editor.putLong(key, value)
        }
        if (!beforeApply) end()
        return this
    }



    /**
     * Set a float value to the preferences.
     * @param keyRes The string resource id of the preference name.
     * @param value The new value for the preference. Remove value if null.
     * @return A reference to this object.
     */
    fun putFloat(@StringRes keyRes: Int, value: Float?): Prefs {
        return putFloat(context.getString(keyRes), value)
    }

    /**
     * Set a float value to the preferences.
     * @param key The preference name.
     * @param value The new value for the preference. Remove value if null.
     * @return A reference to this object.
     */
    fun putFloat(key: String, value: Float?): Prefs {
        if (value == null) {
            remove(key)
        } else {
            editor.putFloat(key, value)
        }
        if (!beforeApply) end()
        return this
    }



    /**
     * Set a double value to the preferences.
     * @param keyRes The string resource id of the preference name.
     * @param value The new value for the preference. Remove value if null.
     * @return A reference to this object.
     */
    fun putDouble(@StringRes keyRes: Int, value: Double?): Prefs {
        return putDouble(context.getString(keyRes), value)
    }

    /**
     * Set a double value to the preferences.
     * @param key The preference name.
     * @param value The new value for the preference. Remove value if null.
     * @return A reference to this object.
     */
    fun putDouble(key: String, value: Double?): Prefs {
        if (value == null) {
            remove(key)
        } else {
            editor.putLong(key, java.lang.Double.doubleToRawLongBits(value))
        }
        if (!beforeApply) end()
        return this
    }



    /**
     * Set a BigInteger value to the preferences.
     * @param keyRes The string resource id of the preference name.
     * @param value The new value for the preference. Remove value if null.
     * @return A reference to this object.
     */
    fun putBigInteger(@StringRes keyRes: Int, value: BigInteger?): Prefs {
        return putBigInteger(context.getString(keyRes), value)
    }

    /**
     * Set a BigInteger value to the preferences.
     * @param key The preference name.
     * @param value The new value for the preference. Remove value if null.
     * @return A reference to this object.
     */
    fun putBigInteger(key: String, value: BigInteger?): Prefs {
        return putString(key, value?.toString())
    }



    /**
     * Set a BigDecimal value to the preferences. This does not guarantee digit accuracy of the saved value.
     * @param keyRes The string resource id of the preference name.
     * @param value The new value for the preference. Remove value if null.
     * @return A reference to this object.
     */
    fun putBigDecimal(@StringRes keyRes: Int, value: BigDecimal?): Prefs {
        return putBigDecimal(context.getString(keyRes), value)
    }

    /**
     * Set a BigDecimal value to the preferences. This does not guarantee digit accuracy of the saved value.
     * @param key The preference name.
     * @param value The new value for the preference. Remove value if null.
     * @return A reference to this object.
     */
    fun putBigDecimal(key: String, value: BigDecimal?): Prefs {
        return putString(key, value?.toPlainString())
    }



    /**
     * Set a char value to the preferences.
     * @param keyRes The string resource id of the preference name.
     * @param value The new value for the preference. Remove value if null.
     * @return A reference to this object.
     */
    fun putChar(@StringRes keyRes: Int, value: Char?): Prefs {
        return putChar(context.getString(keyRes), value)
    }

    /**
     * Set a char value to the preferences.
     * @param key The preference name.
     * @param value The new value for the preference. Remove value if null.
     * @return A reference to this object.
     */
    fun putChar(key: String, value: Char?): Prefs {
        editor.putString(key, value.toString())
        if (!beforeApply) end()
        return this
    }



    /**
     * Set a String value to the preferences.
     * @param keyRes The string resource id of the preference name.
     * @param value The new value for the preference. Remove value if null.
     * @return A reference to this object.
     */
    fun putString(@StringRes keyRes: Int, value: CharSequence?): Prefs {
        return putString(context.getString(keyRes), value)
    }

    /**
     * Set a String value to the preferences.
     * @param key The preference name.
     * @param value The new value for the preference. Remove value if null.
     * @return A reference to this object.
     */
    fun putString(key: String, value: CharSequence?): Prefs {
        editor.putString(key, value?.toString())
        if (!beforeApply) end()
        return this
    }



    /**
     * Set a String set value to the preferences.
     * @param keyRes The string resource id of the preference name.
     * @param value The new value for the preference. Remove value if null.
     * @return A reference to this object.
     */
    fun putStringSet(@StringRes keyRes: Int, value: Set<String?>?): Prefs {
        return putStringSet(context.getString(keyRes), value)
    }

    /**
     * Set a String set value to the preferences.
     * @param key The preference name.
     * @param value The new value for the preference. Remove value if null.
     * @return A reference to this object.
     */
    fun putStringSet(key: String, value: Set<String?>?): Prefs {
        editor.putStringSet(key, value)
        if (!beforeApply) end()
        return this
    }



    /**
     * Set a byte array value to the preferences.
     * @param keyRes The string resource id of the preference name.
     * @param value The new value for the preference. Remove value if null.
     * @return A reference to this object.
     */
    fun putBin(@StringRes keyRes: Int, value: ByteArray?): Prefs {
        return putBin(context.getString(keyRes), value)
    }

    /**
     * Set a byte array value to the preferences.
     * @param key The preference name.
     * @param value The new value for the preference. Remove value if null.
     * @return A reference to this object.
     */
    fun putBin(key: String, value: ByteArray?): Prefs {
        if (value == null) {
            editor.putString(key, null)
        } else {
            val base64Bytes = Base64.encodeToString(value, Base64.DEFAULT)
            editor.putString(key, base64Bytes)
        }
        if (!beforeApply) end()
        return this
    }



    /**
     * Set a Serializable value to the preferences.. Keep the class when using Proguard.
     * @param keyRes The string resource id of the preference name.
     * @param value The new value for the preference. Remove value if null.
     * @return A reference to this object.
     */
    fun putSerializable(@StringRes keyRes: Int, value: Serializable?): Prefs {
        return putSerializable(context.getString(keyRes), value)
    }

    /**
     * Set a Serializable value to the preferences.. Keep the class when using Proguard.
     * @param key The preference name.
     * @param value The new value for the preference. Remove value if null.
     * @return A reference to this object.
     */
    fun putSerializable(key: String, value: Serializable?): Prefs {
        if (value == null) {
            putBin(key, null)
        } else {
            ByteArrayOutputStream().use {
                ObjectOutputStream(it).use {
                    it.writeObject(value)
                }
                putBin(key, it.toByteArray())
            }
        }
        if (!beforeApply) end()
        return this
    }



    /**
     * Set a Object value to the preferences.. Keep the class when using Proguard.
     * @param keyRes The string resource id of the preference name.
     * @param value The new value for the preference. Remove value if null.
     * @return A reference to this object.
     */
    fun putObject(@StringRes keyRes: Int, value: Any?): Prefs {
        return putObject(context.getString(keyRes), value)
    }

    /**
     * Set a Object value to the preferences.. Keep the class when using Proguard.
     * @param key The preference name.
     * @param value The new value for the preference. Remove value if null.
     * @return A reference to this object.
     */
    fun putObject(key: String, value: Any?): Prefs {
        if (value == null) {
            editor.putString(key, null)
        } else {
            val json = gson.toJson(value)
            editor.putString(key, json)
        }
        if (!beforeApply) end()
        return this
    }



    // Remove

    /**
     * Remov ae value from the preferences.
     * @param keyRes The string resource id of the preference name.
     * @return A reference to this object.
     */
    fun remove(@StringRes keyRes: Int): Prefs {
        return remove(context.getString(keyRes))
    }

    /**
     * Remove a value from the preferences.
     * @param key TThe preference name.
     * @return A reference to this object.
     */
    fun remove(key: String): Prefs {
        editor.remove(key)
        if (!beforeApply) end()
        return this
    }


    // Begin / End

    /**
     * Begin setting values until calling end method.
     */
    fun begin(): Prefs {
        end()
        beforeApply = true
        return this
    }

    /**
     * Write set values from  calling begin method.
     */
    fun end() {
        editor.apply()
        beforeApply = false
    }



    // Default value

    /**
     * Retrieve a default value.
     * @param defValue A default value. Ignores if defRes > 0.
     * @param defRes A resource id of default value. (type: bool, integer, dimen, string, array-string)
     * @return A default value from defValue or resource.
     */
    private inline fun<reified T> getDefaultValue(defValue : T, defRes : Int) : T {
        if (defRes <= 0)
            return defValue

        val outputClass = T::class

        val typeName = context.resources.getResourceTypeName(defRes)
        if (typeName == "array") {
            // string-array
            val res = context.resources.getStringArray(defRes)
            val value : Any = when (outputClass) {
                Set::class -> res.toSet()
                else -> throw ClassCastException("Invalid resource.")
            }
            return value as T
        } else {
            val resourceVal = TypedValue()
            context.resources.getValue(defRes, resourceVal, true)

            if (resourceVal.type == TypedValue.TYPE_INT_BOOLEAN) {
                val res = context.resources.getBoolean(defRes)
                val value : Any = when (outputClass) {
                    Boolean::class -> res
                    String::class -> res.toString()
                    else -> throw ClassCastException("Invalid resource.")
                }
                return value as T
            } else if (resourceVal.type == TypedValue.TYPE_FIRST_INT) {
                val res = resourceVal.data
                val value : Any = when (outputClass) {
                    Boolean::class -> (res != 0)
                    Byte::class -> res.toByte()
                    Short::class -> res.toShort()
                    Int::class -> res
                    Long::class -> res.toLong()
                    Float::class -> res.toFloat()
                    Double::class -> res.toDouble()
                    Char::class -> res.toString()[0]
                    String::class -> res.toString()
                    BigInteger::class -> res.toBigInteger()
                    BigDecimal::class -> res.toBigDecimal()
                    else -> throw ClassCastException("Invalid resource.")
                }
                return value as T
            } else if (resourceVal.type == TypedValue.TYPE_FLOAT) {
                val res = resourceVal.float
                val value : Any = when (outputClass) {
                    Boolean::class -> (res != 0.0F)
                    Byte::class -> res.toByte()
                    Short::class -> res.toShort()
                    Int::class -> res.toInt()
                    Long::class -> res.toLong()
                    Float::class -> res
                    Double::class -> res.toDouble()
                    Char::class -> res.toString()[0]
                    String::class -> res.toString()
                    BigInteger::class -> BigInteger.valueOf(res.toLong())
                    BigDecimal::class -> res.toBigDecimal()
                    else -> throw ClassCastException("Invalid resource.")
                }
                return value as T
            } else if (resourceVal.type == TypedValue.TYPE_STRING) {
                val res = context.getString(defRes)
                val value : Any = when (outputClass) {
                    Boolean::class -> res.toBoolean()
                    Byte::class -> res.toByte()
                    Short::class -> res.toShort()
                    Int::class -> res.toInt()
                    Long::class -> res.toLong()
                    Float::class -> res.toFloat()
                    Double::class -> res.toDouble()
                    Char::class -> res[0]
                    String::class -> res
                    BigInteger::class -> res.toBigInteger()
                    BigDecimal::class -> res.toBigDecimal()
                    ByteArray::class -> context.resources.openRawResource(defRes).readBytes()
                    else -> throw ClassCastException("Invalid resource.")
                }
                return value as T
            }
        }

        throw  ClassCastException("Invalid resource.")
    }
}