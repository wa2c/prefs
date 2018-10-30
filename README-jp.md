Medoly Library
==============

本プログラムは、AndroidのSharedPreferencesを操作するためのラッパークラスです。  
SharedPreferencesへのデータ入出力を行う各種メソッドを提供します。

## 説明

### 記述の簡素化

### 保存データ型の追加

SharedPreferencesに以下の値を

### Non-null型, Nullable型


### 初期値の設定






## 動作環境



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

# 使い方








## サンプル

テストコードを参照してください。

* [Kotlinサンプル](https://github.com/wa2c/prefs/blob/master/app/src/test/java/com/wa2c/android/prefsapp/PrefsKotlinUnitTest.kt)
* [Javaサンプル](https://github.com/wa2c/prefs/blob/master/app/src/test/java/com/wa2c/android/prefsapp/PrefsJavaUnitTest.java)

## ライセンス

[MIT](hhttps://github.com/wa2c/prefs/blob/master/LICENSE.txt)

## 作者

[wa2c](https://github.com/wa2c)
