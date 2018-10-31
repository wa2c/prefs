Medoly Library
==============

本プログラムは、AndroidのSharedPreferencesを操作するためのラッパークラスです。  
SharedPreferencesへのデータ入出力を行う各種メソッドを提供します。

## 機能

### 呼び出しの簡略化

~~~kotlin
// デフォルト
var prefs1 = Prefs(context)
// 名前付き
var prefs2 = Prefs(context, "name")
~~~


### putの簡略化

~~~kotlin
prefs.putInt("pref_1", 123)
prefs.putString("pref_2", "abced")
~~~

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

### データ型の追加


- Boolean (getBoolean/putBoolean)
- Byte (getByte/putByte)
- Short (getShort/putShort)
- Int (getInt/putInt)
- Long (getLong/putLong)
- Float (getFloat/putFloat)
- Double (getDouble/putDouble)
- BigInteger (getBigInteger/putBigInteger)
- BigDecimal (getBigDecimal/putBigDecimal)
- Char (getChar/putChar)
- String (getString/putString)
- Set<String> (getStringSet/putStringSet)
- byte\[\] (getBin/putBin)
- Serializable (getSerializable/putSerializable) [ObjectStreamを使用]
- Object (getObject/putObject) [Gsonを使用]

### Non-null型, Nullable型対応

~~~kotlin
val v1 = prefs.getIntOrNull("pref_v1") // Int?型
val v2 = prefs.getInt("pref_v2", 0) // Int型
val v3 = prefs.getStringOrNull("prefs_v3") // String?型
val v4 = prefs.getString("prefs_v4", "") // String型
~~~

### 初期値の省略

~~~kotlin
val v1 = prefs.getInt("pref_v1") // 初期値省略
val v2 = prefs.getInt("pref_v2", 0) // 上と同じ
val v3 = prefs.getString("prefs_v3") // 初期値省略
val v4 = prefs.getString("prefs_v4", "") // 上と同じ
~~~

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

### リソースIDの使用

~~~kotlin
// get
val v1 = prefs.getIntOrNull("pref_v1") // 文字列指定
val v2 = prefs.getIntOrNull(R.string.pref_v2) // リソースID指定
// put
prefs.putInt("pref_v3", 123) // 文字列指定
prefs.putInt(R.string.pref_v4, 456) // リソースID指定
~~~

~~~kotlin
val v1 = prefs.getInt(R.string.pref_v1, defRes = R.string.v1)
val v2 = prefs.getInt(R.string.pref_v2, defRes = R.integer.v2)
val v3 = prefs.getString(R.string.pref_v3, defRes = R.string.v3)
val v4 = prefs.getString(R.string.pref_v4, defRes = R.integer.v4)
~~~

### 型推論 (Kotlinのみ)

~~~kotlin
var v1: SampleObject? = pref.getObjectOrNull("pref_v1")
var v2 = prefs.getObjectOrNull<SampleObject?>("pref_v2")
var v3 = prefs.getObjectOrNull("pref_v3", ObjectTestData::class.java)
var v4 = prefs.getObjectOrNull("pref_v4", object: TypeToken<ObjectTestData>(){}.type)
~~~



## 動作環境

- Androi API Level 14 以降

## インストール

次の記述をbuild.gradleファイルに追加してください。

```gradle
    repositories {
        maven { url 'https://github.com/wa2c/prefs/raw/master/repository/' }
    }

    dependencies {
        implementation 'com.wa2c.android:prefs:0.0.1'
    }
```

## サンプル

テストコードを参照してください。

* [Kotlinサンプル](https://github.com/wa2c/prefs/blob/master/app/src/test/java/com/wa2c/android/prefsapp/PrefsKotlinUnitTest.kt)
* [Javaサンプル](https://github.com/wa2c/prefs/blob/master/app/src/test/java/com/wa2c/android/prefsapp/PrefsJavaUnitTest.java)

## ライセンス

[MIT](hhttps://github.com/wa2c/prefs/blob/master/LICENSE.txt)

## 作者

[wa2c](https://github.com/wa2c)
