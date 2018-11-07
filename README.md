Prefs - SharedPreferences wrapper library
=========================================

This is a wrapper library for operating Android SharedPreferences.
This provides various data input / output methods.

## Feature

### Data Types Support

Add some operable data types. All types have get and put methods.  Actually, it is converted to SharedPreferences type.

Following are the supported types, get/set methods, and actual types in SharedPreferences.

| Type          | get             | put             | Actual Type   | Conversion Process          |
|---------------|-----------------|-----------------|---------------|-----------------------------|
| Boolean       | getBoolean      | putBoolean      | Boolean       |                             |
| Byte          | getByte         | putByte         | Int           |                             |
| Short         | getShort        | putShort        | Int           |                             |
| Int           | getInt          | putInt          | Int           |                             |
| Long          | getLong         | putLong         | Long          |                             |
| Float         | getFloat        | putFloat        | Float         |                             |
| Double        | getDouble       | putDouble       | Long          | doubleToRawLongBits         |
| BigInteger    | getBigInteger   | putBigInteger   | String        | toString                    |
| BigDecimal    | getBigDecimal   | putBigDecimal   | String        | toPlainString               |
| Char          | getChar         | putChar         | String        | toString                    |
| String        | getString       | putString       | String        |                             |
| Set\<String\> | getStringSet    | putStringSet    | Set\<String\> |                             |
| Byte[]        | getBin          | putBin          | String        | Base64                      |
| Serializable  | getSerializable | putSerializable | String        | ObjectOutputStream + Base64 |
| Object        | getObject       | putObject       | String        | Gson                        |

Serializable type and Object type depend on implementation of class whether or not they can get /put correctly. It can not process correctly if it obfuscated by proguard etc.

### Initialize

Initialize the instance as follows. Use default SharedPreferences when the name omitted.

~~~kotlin
// Default
var prefs1 = Prefs(context)
// Named
var prefs2 = Prefs(context, "name")
~~~

### Put Methods

The put methods are followings. The edit() and apply() are not necessary. They support the method chaining.

~~~kotlin
// Normal
prefs.putInt("pref_1", 123)
// Method chaining
prefs
    .putString("pref_2", "abc")
    .putString("pref_3", "def")
~~~

Use begin() and end() to put multiple values at once.

~~~kotlin
prefs
    .begin()
    .putBoolean("val1", true)
    .putInt("val2", 1)
    .putLong("val3", 1L)
    .putFloat("val4", 1.0f)
    .putString("val5", "1")
    .end()
~~~


### Get Method With Non-null And Nullable

All types have non-null get method and nullable get method. The nullable get method has "OrNull" at the end of the name.

~~~kotlin
// Int?
val v1 = prefs.getIntOrNull("pref_v1")
// Int
val v2 = prefs.getInt("pref_v2", 0)
// String?
val v3 = prefs.getStringOrNull("prefs_v3")
// String
val v4 = prefs.getString("prefs_v4", "")
~~~

### Get Method Without Default Value

The non-null get methods can omit default value except for Serializable type and Object type.

~~~kotlin
// Omitted
val v1 = prefs.getInt("pref_v1")
// Same as above
val v2 = prefs.getInt("pref_v2", 0)
// Omitted
val v3 = prefs.getString("prefs_v3")
// Same as above
val v4 = prefs.getString("prefs_v4", "")
~~~

The default values are defined as the property as follows.

~~~kotlin
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
var defaultStringSetValue : Set<String> = HashSet(0)
var defaultBinValue : ByteArray = ByteArray(0)
~~~

### Using Resource ID

All types can use string resource ID for get and put preference key.

~~~kotlin
// get - key by string
val v1 = prefs.getIntOrNull("pref_v1") 
// get - key by strig resource ID
val v2 = prefs.getIntOrNull(R.string.pref_v2)
// put - key by string
prefs.putInt("pref_v3", 123) 
// put - key by strig resource ID
prefs.putInt(R.string.pref_v4, 456)
~~~

The non-null get methods can use the resource ID for default value except for Serializable type and Object type.
The third argument is the resource ID for default value. The second arugument is ignored when use the resource ID.

~~~kotlin
// Using the third argument (The second argument is ignored)
val v1 = prefs.getInt(R.string.pref_v1, -1, R.string.v1)
// Using the named argument
val v2 = prefs.getInt(R.string.pref_v2, defRes = R.integer.v2) 
~~~

The available resource types different for each data type. They are follows.

| Type          | bool | integer | float | dimen | string | array-string | raw, etc. |
|:--------------|:----:|:-------:|:-----:|:-----:|:------:|:------------:|:---------:|
| Boolean       |   *  |    *    |   *   |   *   |    *   |       -      |     -     |
| Byte          |   -  |    *    |   *   |   *   |    *   |       -      |     -     |
| Short         |   -  |    *    |   *   |   *   |    *   |       -      |     -     |
| Int           |   -  |    *    |   *   |   *   |    *   |       -      |     -     |
| Long          |   -  |    *    |   *   |   *   |    *   |       -      |     -     |
| Float         |   -  |    *    |   *   |   *   |    *   |       -      |     -     |
| Double        |   -  |    *    |   *   |   *   |    *   |       -      |     -     |
| BigInteger    |   -  |    *    |   *   |   *   |    *   |       -      |     -     |
| BigDecimal    |   -  |    *    |   *   |   *   |    *   |       -      |     -     |
| Char          |   -  |    *    |   *   |   *   |    *   |       -      |     -     |
| String        |   *  |    *    |   *   |   *   |    *   |       -      |     -     |
| Set\<String\> |   -  |    -    |   -   |   -   |    -   |       *      |     -     |
| Byte[]        |   -  |    -    |   -   |   -   |    -   |       -      |     *     |

### Type Conversion

The get methods in Serializeble type and Object type can use type inference. (They can use type parameter too.) They can not use in Java.

~~~kotlin
// Type inference
var v1: SampleObject? = pref.getObjectOrNull("pref_v1")
// Type parametet
var v2 = prefs.getObjectOrNull<SampleObject?>("pref_v2")
~~~

When using from other than Kotlin, they can define return value data type by giving Class or Type (only Object type) object.

~~~kotlin
// Class
var v3 = prefs.getObjectOrNull("pref_v3", ObjectTestData::class.java)
// Type
var v4 = prefs.getObjectOrNull("pref_v4", object: TypeToken<ObjectTestData>(){}.type)
~~~

## Environment

- Android API Level 14 and over

## Install

Add the following description to the build.gradle file.

```gradle
    repositories {
        maven { url 'https://github.com/wa2c/prefs/raw/master/repository/' }
    }

    dependencies {
        implementation 'com.wa2c.android:prefs:0.1.0'
    }
```

## Sample

See the sample app and the test codes.

* [Sample app](https://github.com/wa2c/prefs)
* [Kotlin test](https://github.com/wa2c/prefs/blob/master/app/src/test/java/com/wa2c/android/prefsapp/PrefsKotlinUnitTest.kt)
* [Java test](https://github.com/wa2c/prefs/blob/master/app/src/test/java/com/wa2c/android/prefsapp/PrefsJavaUnitTest.java)

## Licence

[MIT](https://github.com/wa2c/prefs/blob/master/LICENSE.txt)

## Author

[wa2c](https://github.com/wa2c)
