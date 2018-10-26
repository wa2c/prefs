package com.wa2c.android.prefsapp;

import android.content.Context;
import android.text.TextUtils;
import com.google.common.reflect.TypeToken;
import com.wa2c.android.prefs.Prefs;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;

import static org.junit.Assert.*;


@RunWith(RobolectricTestRunner.class)
public class PrefsJavaUnitTest {

    private Prefs prefs;

    @Before
    public void init() {
        Context context = RuntimeEnvironment.application;
        prefs = new Prefs(context);
        prefs.getSharedPreferences().edit().clear().apply();
    }

    @Test
    public void booleanTest() {
        int keyRes = R.string.prefkey_boolean;

        assertTrue(prefs.getBoolean(keyRes, true));
        assertTrue(prefs.getBoolean(keyRes, false, R.bool.bool_value1));
        assertTrue(prefs.getBoolean(keyRes, false, R.integer.int_value1));
        assertTrue(prefs.getBoolean(keyRes, false, R.dimen.float_value1));
        assertTrue(prefs.getBoolean(keyRes, false, R.string.string_bool));

        assertFalse(prefs.getBoolean(keyRes));
        prefs.setDefaultBooleanValue(true);
        assertTrue(prefs.getBoolean(keyRes));

        prefs.setDefaultBooleanValue(false);
        prefs.putBoolean(keyRes, true);
        assertTrue(prefs.getBoolean(keyRes, false, R.bool.bool_value2));

        prefs.remove(keyRes);
        assertNull(prefs.getBooleanOrNull(keyRes));
    }

    @Test
    public void byteTest() {
        int keyRes = R.string.prefkey_byte;

        assertEquals(prefs.getByte(keyRes, (byte) 10),  10);
        assertEquals(prefs.getByte(keyRes, (byte) 10, R.integer.int_value1), 123);
        assertEquals(prefs.getByte(keyRes, (byte) 10, R.dimen.float_value1), 1);
        assertEquals(prefs.getByte(keyRes, (byte) 10, R.string.string_value1), -123);

        prefs.setDefaultByteValue((byte) 20);
        assertEquals(prefs.getByte(keyRes), 20);

        prefs.putByte(keyRes, (byte) 30);
        assertEquals(prefs.getByte(keyRes, (byte) 0, R.integer.int_value1), 30);

        prefs.remove(keyRes);
        assertNull(prefs.getByteOrNull(keyRes));
    }

    @Test
    public void shortTest() {
        int keyRes = R.string.prefkey_short;
        prefs.remove(keyRes);

        assertNull(prefs.getShortOrNull(keyRes));
        assertEquals(prefs.getShort(keyRes, (short) 10),  10);
        assertEquals(prefs.getShort(keyRes, (short) 10, R.integer.int_value1), 123);
        assertEquals(prefs.getShort(keyRes, (short) 10, R.dimen.float_value1), 1);
        assertEquals(prefs.getShort(keyRes, (short) 10, R.string.string_value1), -123);
        
        prefs.setDefaultShortValue((short) 20);
        assertEquals(prefs.getShort(keyRes), 20);

        prefs.putShort(keyRes, (short) 30);
        assertEquals(prefs.getShort(keyRes, (short) 0, R.integer.int_value1), 30);
    }
    
    @Test
    public void intTest() {
        int keyRes = R.string.prefkey_int;

        assertEquals(prefs.getInt(keyRes, 10),  10);
        assertEquals(prefs.getInt(keyRes, 10, R.integer.int_value1), 123);
        assertEquals(prefs.getInt(keyRes, 10, R.dimen.float_value1), 1);
        assertEquals(prefs.getInt(keyRes, 10, R.string.string_value1), -123);

        prefs.setDefaultIntValue(20);
        assertEquals(prefs.getInt(keyRes), 20);

        prefs.putInt(keyRes, 30);
        assertEquals(prefs.getInt(keyRes, 0, R.integer.int_value1), 30);

        prefs.remove(keyRes);
        assertNull(prefs.getIntOrNull(keyRes));
    }

    @Test
    public void longTest() {
        int keyRes = R.string.prefkey_long;

        assertEquals(prefs.getLong(keyRes, (long) 10),  10);
        assertEquals(prefs.getLong(keyRes, (long) 10, R.integer.int_value1), 123);
        assertEquals(prefs.getLong(keyRes, (long) 10, R.dimen.float_value1), 1);
        assertEquals(prefs.getLong(keyRes, (long) 10, R.string.string_value1), -123);

        prefs.setDefaultLongValue((long) 20);
        assertEquals(prefs.getLong(keyRes), 20);

        prefs.putLong(keyRes, (long) 30);
        assertEquals(prefs.getLong(keyRes, (long) 0, R.integer.int_value1), 30);

        prefs.remove(keyRes);
        assertNull(prefs.getLongOrNull(keyRes));
    }

    @Test
    public void floatTest() {
        int keyRes = R.string.prefkey_float;

        assertEquals(prefs.getFloat(keyRes, (float) 1.1),  1.1f, 0.001);
        assertEquals(prefs.getFloat(keyRes, (float) 1.1, R.integer.int_value1), 123f, 0.001);
        assertEquals(prefs.getFloat(keyRes, (float) 1.1, R.dimen.float_value1), 1.23f, 0.001);
        assertEquals(prefs.getFloat(keyRes, (float) 1.1, R.string.string_value1), -123f, 0.001);

        prefs.setDefaultFloatValue((float) 20);
        assertEquals(prefs.getFloat(keyRes), 20f, 0.001);

        prefs.putFloat(keyRes, 1.23f);
        assertEquals(prefs.getFloat(keyRes, (float) 0, R.dimen.float_value1), 1.23f, 0.001);

        prefs.remove(keyRes);
        assertNull(prefs.getFloatOrNull(keyRes));
    }

    @Test
    public void doubleTest() {
        int keyRes = R.string.prefkey_double;

        assertEquals(prefs.getDouble(keyRes, 1.1),  1.1, 0.001);
        assertEquals(prefs.getDouble(keyRes, 1.1, R.integer.int_value1), 123, 0.001);
        assertEquals(prefs.getDouble(keyRes, 1.1, R.dimen.float_value1), 1.23, 0.001);
        assertEquals(prefs.getDouble(keyRes, 1.1, R.string.string_value1), -123, 0.001);

        prefs.setDefaultDoubleValue((double) 20);
        assertEquals(prefs.getDouble(keyRes), 20, 0.001);

        prefs.putDouble(keyRes, 1.23);
        assertEquals(prefs.getDouble(keyRes,0, R.integer.int_value1), 1.23, 0.001);

        prefs.remove(keyRes);
        assertNull(prefs.getDoubleOrNull(keyRes));
    }

    @Test
    public void bigIntegerTest() {
        int keyRes = R.string.prefkey_short;

        assertEquals(prefs.getBigInteger(keyRes, BigInteger.TEN),  BigInteger.TEN);
        assertEquals(prefs.getBigInteger(keyRes, BigInteger.TEN, R.integer.int_value1), new BigInteger("123"));
        assertEquals(prefs.getBigInteger(keyRes, BigInteger.TEN, R.dimen.float_value1), new BigInteger("1"));
        assertEquals(prefs.getBigInteger(keyRes, BigInteger.TEN, R.string.string_value1), new BigInteger("-123"));

        prefs.setDefaultBigIntegerValue(new BigInteger("20"));
        assertEquals(prefs.getBigInteger(keyRes), new BigInteger("20"));

        prefs.putBigInteger(keyRes, new BigInteger("30"));
        assertEquals(prefs.getBigInteger(keyRes, BigInteger.ZERO, R.integer.int_value1), new BigInteger("30"));

        prefs.remove(keyRes);
        assertNull(prefs.getBigIntegerOrNull(keyRes));
    }

    @Test
    public void bigDecimalTest() {
        int keyRes = R.string.prefkey_short;
        prefs.remove(keyRes);

        assertNull(prefs.getBigDecimalOrNull(keyRes));
        assertEquals(prefs.getBigDecimal(keyRes, new BigDecimal("1.1")),  new BigDecimal("1.1"));
        assertEquals(prefs.getBigDecimal(keyRes, new BigDecimal("1.1"), R.integer.int_value1), new BigDecimal("123"));
        assertEquals(prefs.getBigDecimal(keyRes, new BigDecimal("1.1"), R.dimen.float_value1), new BigDecimal("1.23"));
        assertEquals(prefs.getBigDecimal(keyRes, new BigDecimal("1.1"), R.string.string_value1), new BigDecimal("-123"));

        prefs.setDefaultBigDecimalValue(new BigDecimal("20"));
        assertEquals(prefs.getBigDecimal(keyRes), new BigDecimal("20"));

        prefs.putBigDecimal(keyRes, new BigDecimal("1.23"));
        assertEquals(prefs.getBigDecimal(keyRes, BigDecimal.ZERO, R.integer.int_value1), new BigDecimal("1.23"));
    }

    @Test
    public void charTest() {
        int keyRes = R.string.prefkey_char;

        assertEquals(prefs.getChar(keyRes, 'a'),  'a');
        assertEquals(prefs.getChar(keyRes, 'a', R.integer.int_value1), '1');
        assertEquals(prefs.getChar(keyRes, 'a', R.dimen.float_value1), '1');
        assertEquals(prefs.getChar(keyRes, 'a', R.string.string_value1), '-');

        prefs.setDefaultCharValue('b');
        assertEquals(prefs.getChar(keyRes), 'b');

        prefs.putChar(keyRes, 'c');
        assertEquals(prefs.getChar(keyRes, ' ', R.integer.int_value1), 'c');

        prefs.remove(keyRes);
        assertNull(prefs.getCharOrNull(keyRes));
    }

    @Test
    public void stringTest() {
        int keyRes = R.string.prefkey_string;

        assertEquals(prefs.getString(keyRes, "abc"),  "abc");
        assertEquals(prefs.getString(keyRes, "abc", R.bool.bool_value1), "true");
        assertEquals(prefs.getString(keyRes, "abc", R.integer.int_value1), "123");
        assertEquals(prefs.getString(keyRes, "abc", R.dimen.float_value1), "1.23");
        assertEquals(prefs.getString(keyRes, "abc", R.string.string_value1), "-123");

        prefs.setDefaultStringValue("cde");
        assertEquals(prefs.getString(keyRes), "cde");

        prefs.putString(keyRes, "fgh");
        assertEquals(prefs.getString(keyRes, "", R.integer.int_value1), "fgh");

        prefs.remove(keyRes);
        assertNull(prefs.getStringOrNull(keyRes));
    }

    @Test
    public void stringSetTest() {
        int keyRes = R.string.prefkey_string_set;

        assertEquals(prefs.getStringSet(keyRes, new HashSet<String>() {{ add("1"); add("2"); }}), new HashSet<String>() {{ add("1"); add("2"); }});
        assertEquals(prefs.getStringSet(keyRes, new HashSet<String>(), R.array.array_string_set), new HashSet<String>() {{ add("a"); add("1"); }});

        prefs.setDefaultStringSetValue(new HashSet<String>() {{ add("10"); add("20"); }});
        assertEquals(prefs.getStringSet(keyRes), new HashSet<String>() {{ add("10"); add("20"); }});

        prefs.putStringSet(keyRes, new HashSet<String>() {{ add("aa"); add("bb"); }});
        assertEquals(prefs.getStringSet(keyRes, new HashSet<String>(), R.array.array_string_set), new HashSet<String>() {{ add("aa"); add("bb"); }});

        prefs.remove(keyRes);
        assertNull(prefs.getStringSetOrNull(keyRes));
    }

    @Test
    public void binTest() {
        int keyRes = R.string.prefkey_bin;

        assertArrayEquals(prefs.getBin(keyRes, new byte[] {0, 1, 2, 3}), new byte[] {0, 1, 2, 3});

        prefs.setDefaultBinValue(new byte[] {1, 2, 3, 4});
        assertArrayEquals(prefs.getBin(keyRes), new byte[] {1, 2, 3, 4});

        prefs.putBin(keyRes, new byte[] {2, 3, 4, 5});
        assertArrayEquals(prefs.getBin(keyRes), new byte[] {2, 3, 4, 5});

        prefs.remove(keyRes);
        assertNull(prefs.getBinOrNull(keyRes));
    }

    @Test
    public void serializableTest() {
        int keyRes = R.string.prefkey_serializable;

        SerializableTestData obj = new SerializableTestData();
        obj.id = 10;
        obj.name = "abc";

        assertEquals(prefs.getSerializable(keyRes, SerializableTestData.class, obj), new SerializableTestData() {{ this.id = 10; this.name = "abc"; }});

        prefs.putSerializable(keyRes, obj);
        assertEquals(prefs.getSerializable(keyRes, SerializableTestData.class, new SerializableTestData()), new SerializableTestData() {{ this.id = 10; this.name = "abc"; }});

        prefs.remove(keyRes);
        assertNull(prefs.getSerializableOrNull(keyRes, SerializableTestData.class));
    }


    @Test
    public void objectTest() {
        int keyRes = R.string.prefkey_object;

        Type type = new TypeToken<ObjectTestData>(){}.getType();
        ObjectTestData obj = new ObjectTestData();
        obj.id = 10;
        obj.name = "abc";

        assertEquals(prefs.getObject(keyRes, ObjectTestData.class, obj), new ObjectTestData() {{ this.id = 10; this.name = "abc"; }});
        assertEquals(prefs.getObject(keyRes, type, obj), new ObjectTestData() {{ this.id = 10; this.name = "abc"; }});

        prefs.putObject(keyRes, obj);
        assertEquals(prefs.getObject(keyRes, ObjectTestData.class, new ObjectTestData()),  new ObjectTestData() {{ this.id = 10; this.name = "abc"; }});
        assertEquals(prefs.getObject(keyRes, type, new ObjectTestData()),  new ObjectTestData() {{ this.id = 10; this.name = "abc"; }});

        prefs.remove(keyRes);
        assertNull(prefs.getObjectOrNull(keyRes, ObjectTestData.class));
        assertNull(prefs.getObjectOrNull(keyRes, type));
    }

    @Test
    public void beginEndTest() {
        prefs.begin()
                .putInt(R.string.prefkey_int, 100)
                .putString(R.string.prefkey_string, "abc")
                .putBigInteger(R.string.prefkey_big_integer, new BigInteger("1000"))
                .end();

        assertEquals(prefs.getInt(R.string.prefkey_int), 100);
        assertEquals(prefs.getString(R.string.prefkey_string), "abc");
        assertEquals(prefs.getBigInteger(R.string.prefkey_big_integer), new BigInteger("1000"));
    }




    /**
     * Serializable test class.
     */
    static class SerializableTestData extends ObjectTestData implements Serializable {
        String name;
        int id;

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (!(obj instanceof ObjectTestData))
                return false;

            SerializableTestData testObj = (SerializableTestData)obj;
            return testObj.id == this.id && TextUtils.equals(testObj.name, this.name);
        }
    }

    /**
     * Serializable test class.
     */
    static class ObjectTestData {
        String name;
        int id;

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (!(obj instanceof ObjectTestData))
                return false;

            ObjectTestData testObj = (ObjectTestData)obj;
            return testObj.id == this.id && TextUtils.equals(testObj.name, this.name);
        }
    }

}
