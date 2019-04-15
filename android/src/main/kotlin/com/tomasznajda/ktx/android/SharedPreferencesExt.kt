package com.tomasznajda.ktx.android

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun Context.prefs(name: String? = null) =
        name
                ?.let { getSharedPreferences(it, Context.MODE_PRIVATE) }
                ?: PreferenceManager.getDefaultSharedPreferences(this)

fun SharedPreferences.boolean(key: String, defaultValue: Boolean = false) =
        delegate(key,
                 defaultValue,
                 SharedPreferences::getBoolean,
                 SharedPreferences.Editor::putBoolean)

fun SharedPreferences.float(key: String, defaultValue: Float = 0f) =
        delegate(key,
                 defaultValue,
                 SharedPreferences::getFloat,
                 SharedPreferences.Editor::putFloat)

fun SharedPreferences.int(key: String, defaultValue: Int = 0) =
        delegate(key,
                 defaultValue,
                 SharedPreferences::getInt,
                 SharedPreferences.Editor::putInt)

fun SharedPreferences.long(key: String, defaultValue: Long = 0) =
        delegate(key,
                 defaultValue,
                 SharedPreferences::getLong,
                 SharedPreferences.Editor::putLong)

fun SharedPreferences.string(key: String, defaultValue: String = "") =
        delegate(key,
                 defaultValue,
                 SharedPreferences::getString,
                 SharedPreferences.Editor::putString)

fun SharedPreferences.stringSet(key: String, defaultValue: Set<String> = emptySet()) =
        delegate(key,
                 defaultValue,
                 SharedPreferences::getStringSet,
                 SharedPreferences.Editor::putStringSet)

fun <T> SharedPreferences.delegate(
        key: String,
        defaultValue: T,
        getter: SharedPreferences.(String, T) -> T,
        setter: SharedPreferences.Editor.(String, T) -> SharedPreferences.Editor) =
        object : ReadWriteProperty<Any, T> {
            override fun getValue(thisRef: Any, property: KProperty<*>) = getter(key, defaultValue)
            override fun setValue(thisRef: Any, property: KProperty<*>, value: T) = edit().setter(key, value).apply()
        }