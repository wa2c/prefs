Prefs - SharedPreferences wrapper library
=========================================

本プログラムは、AndroidのSharedPreferencesを操作するためのラッパーライブラリです。  
SharedPreferencesに対する各種操作を提供します。

## 機能

### 対応データ型

操作可能なデータ型を追加します。全ての型に、値を取得するgetメソッドと、値を保存するputメソッドがあります。データはSharedPreferencesの型に変換されて処理されます。

対応する型と、get/putメソッドおよび、実際に保存される際の型は次の通りとなります。

| データ型      | get             | put             | 実際の型      | 変換処理                    |
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
| ByteArray     | getBin          | putBin          | String        | Base64                      |
| Serializable  | getSerializable | putSerializable | String        | ObjectOutputStream + Base64 |
| Object        | getObject       | putObject       | String        | Gson                        |

SerializableおよびObjectについては、正しくget/putできるかは処理対象クラスの実装に依存します。また、proguard等により難読化された場合、正しく処理することができません。


### 初期化

以下のようにインスタンスを初期化します。名前を省略すると、デフォルトのSharedPreferencesが使用されます

~~~kotlin
// デフォルト初期化
var prefs1 = Prefs(context)
// 名前付き初期化
var prefs2 = Prefs(context, "name")
~~~

### putメソッド

putメソッドは次のように行います。edit()およびapply()は不要です。メソッドチェーンの記述に対応しています。

~~~kotlin
// 通常
prefs.putInt("pref_1", 123)
// メソッドチェーン
prefs
    .putString("pref_2", "abc")
    .putString("pref_3", "def")
~~~

複数の値を一度にputする場合、begin()およびend()を使用します。

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


### getメソッドのNon-null型, Nullable型対応

すべての型に、Non-null型を返すgetメソッドと、Nullable型を返すgetメソッドを使用できます。Nullable型を返すメソッドは、名前の末尾に"OrNull"が付きます。

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

### getメソッドのデフォルト値省略

Serializable、Objectを除き、Non-null型を返すgetメソッドはデフォルト値の省略が可能です。

~~~kotlin
// 省略記述
val v1 = prefs.getInt("pref_v1")
// 上と同じ
val v2 = prefs.getInt("pref_v2", 0)
// 省略記述
val v3 = prefs.getString("prefs_v3")
// 上と同じ
val v4 = prefs.getString("prefs_v4", "")
~~~

デフォルト値はプロパティとして以下のように定義されており、変更可能です。

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

getおよびputの対象キーに、文字列リソースIDを使用することができます。

~~~kotlin
// get - 文字列指定
val v1 = prefs.getIntOrNull("pref_v1") 
// get - リソースID指定
val v2 = prefs.getIntOrNull(R.string.pref_v2)
// put - 文字列指定
prefs.putInt("pref_v3", 123) 
// put - リソースID指定
prefs.putInt(R.string.pref_v4, 456)
~~~

Serializable、Object型を除き、Non-null型を返すgetメソッドのデフォルト値に、リソースIDを使用することができます。
第3引数がデフォルト値リソースIDの指定となります。リソースIDを指定した場合、第2引数のデフォルト値は無視されます。

~~~kotlin
// 第3引数指定 (第2引数は無視)
val v1 = prefs.getInt(R.string.pref_v1, -1, R.string.v1)
// 名前付き引数指定
val v2 = prefs.getInt(R.string.pref_v2, defRes = R.integer.v2) 
~~~

利用可能なリソースの型は、データ型で異なります。利用可能なリソースは次の通りです。

| データ型      | bool | integer | float | dimen | string | array-string | raw, etc. |
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
| ByteArray     |   -  |    -    |   -   |   -   |    -   |       -      |     *     |

### 型変換

SerializableおよびObjectのgetメソッドにおいて、型推論を利用することができます(明示的に型パラメータを与えることもできます)。Javaから利用することはできません。

~~~kotlin
// 型推論
var v1: SampleObject? = pref.getObjectOrNull("pref_v1")
// 型パラメータ
var v2 = prefs.getObjectOrNull<SampleObject?>("pref_v2")
~~~

Kotlin以外から利用する場合は、引数にClassまたはType(Objectのみ)を与えることで、戻り値の型変換を行うことができます。

~~~kotlin
// Class
var v3 = prefs.getObjectOrNull("pref_v3", ObjectTestData::class.java)
// Type
var v4 = prefs.getObjectOrNull("pref_v4", object: TypeToken<ObjectTestData>(){}.type)
~~~

### インデックス演算子 (Kotlinのみ)

インデックス演算子により、get/putメソッドを省略することができます。getはNullable型を返します。型推論により適用メソッドが自動的に決定されます。

~~~kotlin
val v1 = 123
prefs["pref_v1"] = v1 // putInt
val v1_ : Int? = prefs["pref_v1"] // getIntOrNull

val v2 = "abc"
prefs[R.string.pref_v2] = v2 // putString
val v2_ : String? = prefs[R.string.pref_v2] // getStringOrNull
~~~

## 動作環境

- Android API Level 14 以降

## インストール

次の記述をbuild.gradleファイルに追加してください。

```gradle
    repositories {
        maven { url 'https://github.com/wa2c/prefs/raw/master/repository/' }
    }

    dependencies {
        implementation 'com.wa2c.android:prefs:0.1.1'
    }
```

## サンプル

サンプルアプリおよびテストコードを参照してください。

* [サンプルアプリ](https://github.com/wa2c/prefs)
* [Kotlinテスト](https://github.com/wa2c/prefs/blob/master/app/src/test/java/com/wa2c/android/prefsapp/PrefsKotlinUnitTest.kt)
* [Javaテスト](https://github.com/wa2c/prefs/blob/master/app/src/test/java/com/wa2c/android/prefsapp/PrefsJavaUnitTest.java)

## ライセンス

[MIT](https://github.com/wa2c/prefs/blob/master/LICENSE)

## 作者

[wa2c](https://github.com/wa2c)
