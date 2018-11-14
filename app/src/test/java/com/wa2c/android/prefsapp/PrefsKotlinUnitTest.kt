package com.wa2c.android.prefs

import android.text.TextUtils
import com.google.common.reflect.TypeToken
import com.wa2c.android.prefsapp.R
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import java.io.Serializable
import java.math.BigDecimal
import java.math.BigInteger
import java.util.*

@RunWith(RobolectricTestRunner::class)
class PrefsKotlinUnitTest {

    private lateinit var prefs: Prefs

    @Before
    fun init() {
        val context = RuntimeEnvironment.application
        prefs = Prefs(context)
        prefs.sharedPreferences.edit().clear().apply()
    }

    @Test
    fun booleanTest() {
        val keyRes = R.string.prefkey_boolean

        assertTrue(prefs.getBoolean(keyRes, true))
        assertTrue(prefs.getBoolean(keyRes, false, R.bool.bool_value1))
        assertTrue(prefs.getBoolean(keyRes, false, R.integer.int_value1))
        assertTrue(prefs.getBoolean(keyRes, false, R.dimen.float_value1))
        assertTrue(prefs.getBoolean(keyRes, false, R.string.string_bool))

        assertFalse(prefs.getBoolean(keyRes))
        prefs.defaultBooleanValue = true
        assertTrue(prefs.getBoolean(keyRes))

        prefs.defaultBooleanValue = false
        prefs.putBoolean(keyRes, true)
        assertTrue(prefs.getBoolean(keyRes, false, R.bool.bool_value2))


        prefs[keyRes] = true
        val v : Boolean? = prefs[keyRes]
        assertTrue(v!!)

        prefs.remove(keyRes)
        assertNull(prefs.getBooleanOrNull(keyRes))
    }

    @Test
    fun byteTest() {
        val keyRes = R.string.prefkey_byte

        assertEquals(prefs.getByte(keyRes, 10), 10.toByte())
        assertEquals(prefs.getByte(keyRes, 10, R.integer.int_value1), 123.toByte())
        assertEquals(prefs.getByte(keyRes, 10, R.dimen.float_value1), 1.toByte())
        assertEquals(prefs.getByte(keyRes, 10, R.string.string_value1), (-123).toByte())

        prefs.defaultByteValue = 20
        assertEquals(prefs.getByte(keyRes), 20.toByte())

        prefs.putByte(keyRes, 30)
        assertEquals(prefs.getByte(keyRes, 0, R.integer.int_value1), 30.toByte())

        prefs[keyRes] = 40.toByte()
        val v : Byte? = prefs[keyRes]
        assertEquals(v, 40.toByte())

        prefs.remove(keyRes)
        assertNull(prefs.getByteOrNull(keyRes))
    }

    @Test
    fun shortTest() {
        val keyRes = R.string.prefkey_short
        prefs.remove(keyRes)

        assertNull(prefs.getShortOrNull(keyRes))
        assertEquals(prefs.getShort(keyRes, 10), 10.toShort())
        assertEquals(prefs.getShort(keyRes, 10, R.integer.int_value1), 123.toShort())
        assertEquals(prefs.getShort(keyRes, 10, R.dimen.float_value1), 1.toShort())
        assertEquals(prefs.getShort(keyRes, 10, R.string.string_value1), (-123).toShort())

        prefs.defaultShortValue = 20
        assertEquals(prefs.getShort(keyRes), 20.toShort())

        prefs.putShort(keyRes, 30)
        assertEquals(prefs.getShort(keyRes, 0, R.integer.int_value1), 30.toShort())

        prefs[keyRes] = 40.toShort()
        val v : Short? = prefs[keyRes]
        assertEquals(v, 40.toShort())

        prefs.remove(keyRes)
        assertNull(prefs.getIntOrNull(keyRes))
    }

    @Test
    fun intTest() {
        val keyRes = R.string.prefkey_int

        assertEquals(prefs.getInt(keyRes, 10), 10)
        assertEquals(prefs.getInt(keyRes, 10, R.integer.int_value1), 123)
        assertEquals(prefs.getInt(keyRes, 10, R.dimen.float_value1), 1)
        assertEquals(prefs.getInt(keyRes, 10, R.string.string_value1), -123)

        prefs.defaultIntValue = 20
        assertEquals(prefs.getInt(keyRes), 20)

        prefs.putInt(keyRes, 30)
        assertEquals(prefs.getInt(keyRes, 0, R.integer.int_value1), 30)

        prefs[keyRes] = 40
        val v : Int? = prefs[keyRes]
        assertEquals(v, 40)

        prefs.remove(keyRes)
        assertNull(prefs.getIntOrNull(keyRes))
    }

    @Test
    fun longTest() {
        val keyRes = R.string.prefkey_long

        assertEquals(prefs.getLong(keyRes, 10), 10L)
        assertEquals(prefs.getLong(keyRes, 10, R.integer.int_value1), 123L)
        assertEquals(prefs.getLong(keyRes, 10, R.dimen.float_value1), 1L)
        assertEquals(prefs.getLong(keyRes, 10, R.string.string_value1), -123L)

        prefs.defaultLongValue = 20
        assertEquals(prefs.getLong(keyRes), 20L)

        prefs.putLong(keyRes, 30.toLong())
        assertEquals(prefs.getLong(keyRes, 0, R.integer.int_value1), 30L)

        prefs[keyRes] = 40L
        val v : Long? = prefs[keyRes]
        assertEquals(v, 40L)

        prefs.remove(keyRes)
        assertNull(prefs.getLongOrNull(keyRes))
    }

    @Test
    fun floatTest() {
        val keyRes = R.string.prefkey_float

        assertEquals(prefs.getFloat(keyRes, 1.1F), 1.1F, 0.001F)
        assertEquals(prefs.getFloat(keyRes, 1.1F, R.integer.int_value1), 123.0F, 0.001F)
        assertEquals(prefs.getFloat(keyRes, 1.1F, R.dimen.float_value1), 1.23F, 0.001F)
        assertEquals(prefs.getFloat(keyRes, 1.1F, R.string.string_value1), -123.0F, 0.001F)

        prefs.defaultFloatValue = 20.0F
        assertEquals(prefs.getFloat(keyRes), 20.0F, 0.001F)

        prefs.putFloat(keyRes, 1.23F)
        assertEquals(prefs.getFloat(keyRes, 0.0F, R.dimen.float_value1), 1.23F, 0.001F)

        prefs[keyRes] = 4.56F
        val v : Float? = prefs[keyRes]
        assertEquals(v, 4.56F)

        prefs.remove(keyRes)
        assertNull(prefs.getFloatOrNull(keyRes))
    }

    @Test
    fun doubleTest() {
        val keyRes = R.string.prefkey_double

        assertEquals(prefs.getDouble(keyRes, 1.1), 1.1, 0.001)
        assertEquals(prefs.getDouble(keyRes, 1.1, R.integer.int_value1), 123.0, 0.001)
        assertEquals(prefs.getDouble(keyRes, 1.1, R.dimen.float_value1), 1.23, 0.001)
        assertEquals(prefs.getDouble(keyRes, 1.1, R.string.string_value1), -123.0, 0.001)

        prefs.defaultDoubleValue = 20.0
        assertEquals(prefs.getDouble(keyRes), 20.0, 0.001)

        prefs.putDouble(keyRes, 1.23)
        assertEquals(prefs.getDouble(keyRes, 0.0, R.integer.int_value1), 1.23, 0.001)

        prefs[keyRes] = 4.56
        val v : Double? = prefs[keyRes]
        assertEquals(v!!, 4.56, 0.001)

        prefs.remove(keyRes)
        assertNull(prefs.getDoubleOrNull(keyRes))
    }

    @Test
    fun bigIntegerTest() {
        val keyRes = R.string.prefkey_short

        assertEquals(prefs.getBigInteger(keyRes, BigInteger.TEN), BigInteger.TEN)
        assertEquals(prefs.getBigInteger(keyRes, BigInteger.TEN, R.integer.int_value1), BigInteger("123"))
        assertEquals(prefs.getBigInteger(keyRes, BigInteger.TEN, R.dimen.float_value1), BigInteger("1"))
        assertEquals(prefs.getBigInteger(keyRes, BigInteger.TEN, R.string.string_value1), BigInteger("-123"))

        prefs.defaultBigIntegerValue = BigInteger("20")
        assertEquals(prefs.getBigInteger(keyRes), BigInteger("20"))

        prefs.putBigInteger(keyRes, BigInteger("30"))
        assertEquals(prefs.getBigInteger(keyRes, BigInteger.ZERO, R.integer.int_value1), BigInteger("30"))

        prefs[keyRes] = BigInteger("40")
        val v : BigInteger? = prefs[keyRes]
        assertEquals(v, BigInteger("40"))

        prefs.remove(keyRes)
        assertNull(prefs.getBigIntegerOrNull(keyRes))
    }

    @Test
    fun bigDecimalTest() {
        val keyRes = R.string.prefkey_short
        prefs.remove(keyRes)

        assertNull(prefs.getBigDecimalOrNull(keyRes))
        assertEquals(prefs.getBigDecimal(keyRes, BigDecimal("1.1")), BigDecimal("1.1"))
        assertEquals(prefs.getBigDecimal(keyRes, BigDecimal("1.1"), R.integer.int_value1), BigDecimal("123"))
        assertEquals(prefs.getBigDecimal(keyRes, BigDecimal("1.1"), R.dimen.float_value1), BigDecimal("1.23"))
        assertEquals(prefs.getBigDecimal(keyRes, BigDecimal("1.1"), R.string.string_value1), BigDecimal("-123"))

        prefs.defaultBigDecimalValue = BigDecimal("20")
        assertEquals(prefs.getBigDecimal(keyRes), BigDecimal("20"))

        prefs[keyRes] = BigDecimal("40")
        val v : BigDecimal? = prefs[keyRes]
        assertEquals(v, BigDecimal("40"))

        prefs.putBigDecimal(keyRes, BigDecimal("1.23"))
        assertEquals(prefs.getBigDecimal(keyRes, BigDecimal.ZERO, R.integer.int_value1), BigDecimal("1.23"))
    }

    @Test
    fun charTest() {
        val keyRes = R.string.prefkey_char

        assertEquals(prefs.getChar(keyRes, 'a'), 'a')
        assertEquals(prefs.getChar(keyRes, 'a', R.integer.int_value1), '1')
        assertEquals(prefs.getChar(keyRes, 'a', R.dimen.float_value1), '1')
        assertEquals(prefs.getChar(keyRes, 'a', R.string.string_value1), '-')

        prefs.defaultCharValue = 'b'
        assertEquals(prefs.getChar(keyRes), 'b')

        prefs.putChar(keyRes, 'c')
        assertEquals(prefs.getChar(keyRes, ' ', R.integer.int_value1), 'c')

        prefs[keyRes] = 'd'
        val v : Char? = prefs[keyRes]
        assertEquals(v, 'd')

        prefs.remove(keyRes)
        assertNull(prefs.getCharOrNull(keyRes))
    }

    @Test
    fun stringTest() {
        val keyRes = R.string.prefkey_string

        assertEquals(prefs.getString(keyRes, "abc"), "abc")
        assertEquals(prefs.getString(keyRes, "abc", R.bool.bool_value1), "true")
        assertEquals(prefs.getString(keyRes, "abc", R.integer.int_value1), "123")
        assertEquals(prefs.getString(keyRes, "abc", R.dimen.float_value1), "1.23")
        assertEquals(prefs.getString(keyRes, "abc", R.string.string_value1), "-123")

        prefs.defaultStringValue = "cde"
        assertEquals(prefs.getString(keyRes), "cde")

        prefs.putString(keyRes, "fgh")
        assertEquals(prefs.getString(keyRes, "", R.integer.int_value1), "fgh")

        prefs[keyRes] = "ijk"
        val v : String? = prefs[keyRes]
        assertEquals(v, "ijk")

        prefs.remove(keyRes)
        assertNull(prefs.getStringOrNull(keyRes))
    }

    @Test
    fun stringSetTest() {
        val keyRes = R.string.prefkey_string_set

        assertEquals(prefs.getStringSet(keyRes, object : HashSet<String>() {
            init {
                add("1")
                add("2")
            }
        }), object : HashSet<String>() {
            init {
                add("1")
                add("2")
            }
        })
        assertEquals(
            prefs.getStringSet(keyRes, HashSet<String>(), R.array.array_string_set),
            object : HashSet<String>() {
                init {
                    add("a")
                    add("1")
                }
            })

        prefs.defaultStringSetValue = object : HashSet<String>() {
            init {
                add("10")
                add("20")
            }
        }
        assertEquals(prefs.getStringSet(keyRes), object : HashSet<String>() {
            init {
                add("10")
                add("20")
            }
        })

        prefs.putStringSet(keyRes, object : HashSet<String>() {
            init {
                add("aa")
                add("bb")
            }
        })
        assertEquals(
            prefs.getStringSet(keyRes, HashSet<String>(), R.array.array_string_set),
            object : HashSet<String>() {
                init {
                    add("aa")
                    add("bb")
                }
            })

        prefs[keyRes] = object : HashSet<String>() {
            init {
                add("cc")
                add("dd")
            }
        }
        val v : Set<String>? = prefs[keyRes]
        assertEquals(
            v,
            object : HashSet<String>() {
                init {
                    add("cc")
                    add("dd")
                }
            })

        prefs.remove(keyRes)
        assertNull(prefs.getStringSetOrNull(keyRes))
    }

    @Test
    fun binTest() {
        val keyRes = R.string.prefkey_bin

        assertArrayEquals(prefs.getBin(keyRes, byteArrayOf(0, 1, 2, 3)), byteArrayOf(0, 1, 2, 3))

        prefs.defaultBinValue = byteArrayOf(1, 2, 3, 4)
        assertArrayEquals(prefs.getBin(keyRes), byteArrayOf(1, 2, 3, 4))

        prefs.putBin(keyRes, byteArrayOf(2, 3, 4, 5))
        assertArrayEquals(prefs.getBin(keyRes), byteArrayOf(2, 3, 4, 5))

        prefs[keyRes] = byteArrayOf(3, 4, 5, 6)
        val v : ByteArray? = prefs[keyRes]
        assertArrayEquals(v, byteArrayOf(3, 4, 5, 6))

        prefs.remove(keyRes)
        assertNull(prefs.getBinOrNull(keyRes))
    }


    @Test
    fun serializableTest() {
        val keyRes = R.string.prefkey_serializable

        val obj = SerializableTestData()
        obj.id = 10
        obj.name = "abc"

        assertEquals(
            prefs.getSerializable(keyRes, obj), SerializableTestData(id = 10, name = "abc")
        )
        assertEquals(
            prefs.getSerializable(keyRes, SerializableTestData::class.java, obj), SerializableTestData(id = 10, name = "abc")
        )

        prefs.putSerializable(keyRes, obj)
        assertEquals(
            prefs.getSerializable(keyRes, SerializableTestData()), SerializableTestData(id = 10, name = "abc")
        )
        assertEquals(
            prefs.getSerializable(keyRes, SerializableTestData::class.java, SerializableTestData()), SerializableTestData(id = 10, name = "abc")
        )

        prefs[keyRes] = obj
        val v : SerializableTestData? = prefs[keyRes]
        assertEquals(
            v, SerializableTestData(id = 10, name = "abc")
        )

        prefs.remove(keyRes)
        assertNull(prefs.getSerializableOrNull<SerializableTestData>(keyRes))
        assertNull(prefs.getSerializableOrNull(keyRes, SerializableTestData::class.java))
    }

    @Test
    fun objectTest() {
        val keyRes = R.string.prefkey_object

        val type = object : TypeToken<ObjectTestData>(){}.type
        val obj = ObjectTestData()
        obj.id = 10
        obj.name = "abc"

        assertEquals(
            prefs.getObject(keyRes, obj), ObjectTestData(id = 10, name = "abc")
        )
        assertEquals(
            prefs.getObject(keyRes, ObjectTestData::class.java, obj), ObjectTestData(id = 10, name = "abc")
        )
        assertEquals(
            prefs.getObject(keyRes, type, obj), ObjectTestData(id = 10, name = "abc")
        )

        prefs.putObject(keyRes, obj)
        assertEquals(
            prefs.getObject(keyRes, ObjectTestData()), ObjectTestData(id = 10, name = "abc")
        )
        assertEquals(
            prefs.getObject(keyRes, ObjectTestData::class.java, ObjectTestData()), ObjectTestData(id = 10, name = "abc")
        )
        assertEquals(
            prefs.getObject(keyRes, type, ObjectTestData()), ObjectTestData(id = 10, name = "abc")
        )

        prefs[keyRes] = obj
        val v : ObjectTestData? = prefs[keyRes]
        assertEquals(
            v, ObjectTestData(id = 10, name = "abc")
        )

        prefs.remove(keyRes)
        assertNull(prefs.getObjectOrNull<ObjectTestData>(keyRes))
        assertNull(prefs.getObjectOrNull(keyRes, ObjectTestData::class.java))
        assertNull(prefs.getObjectOrNull(keyRes, type))
    }

    @Test
    fun beginEndTest() {
        prefs.begin()
            .putInt(R.string.prefkey_int, 100)
            .putString(R.string.prefkey_string, "abc")
            .putBigInteger(R.string.prefkey_big_integer, BigInteger("1000"))
            .end()

        assertEquals(prefs.getInt(R.string.prefkey_int), 100)
        assertEquals(prefs.getString(R.string.prefkey_string), "abc")
        assertEquals(prefs.getBigInteger(R.string.prefkey_big_integer), BigInteger("1000"))
    }

    /**
     * Serializable test class.
     */
    data class SerializableTestData (
        var name: String? = null,
        var id: Int = 0
    ) : Serializable

    /**
     * Serializable test class.
     */
    class ObjectTestData(var name: String? = null, var id: Int = 0) {
        override fun equals(other: Any?): Boolean {
            if (this === other)
                return true
            if (other == null)
                return false
            if (other !is ObjectTestData)
                return false

            val testObj = other as ObjectTestData?
            return testObj!!.id == this.id && TextUtils.equals(testObj.name, this.name)
        }

        override fun hashCode(): Int {
            var result = name?.hashCode() ?: 0
            result = 31 * result + id
            return result
        }
    }

}

