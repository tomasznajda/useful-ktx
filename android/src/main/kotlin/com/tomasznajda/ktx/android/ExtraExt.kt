package com.tomasznajda.ktx.android

import android.app.Activity
import android.support.v4.app.Fragment
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

@Suppress("UNCHECKED_CAST")
fun <T> Activity.extr1a(name: String, defaultValue: T? = null) =
        Lazy<Activity, T> { activity, desc -> (intent?.extras?.get(name) ?: defaultValue) as T }

fun <T> Activity.extra(name: String, defaultValue: T? = null) = object : ReadOnlyProperty<Any, T> {
    override fun getValue(thisRef: Any, property: KProperty<*>) =
            (intent?.extras?.get(name) ?: defaultValue) as T
}

@Suppress("UNCHECKED_CAST")
fun <T> Activity.extraOptional(name: String, defaultValue: T? = null) = object : ReadOnlyProperty<Any, T?> {
    override fun getValue(thisRef: Any, property: KProperty<*>) =
            (intent?.extras?.get(name) ?: defaultValue) as? T
}

@Suppress("UNCHECKED_CAST")
fun <T> Fragment.extra(name: String, defaultValue: T? = null) = object : ReadOnlyProperty<Any, T> {
    override fun getValue(thisRef: Any, property: KProperty<*>) =
            (arguments?.get(name) ?: defaultValue) as T
}

@Suppress("UNCHECKED_CAST")
fun <T> Fragment.extraOptional(name: String, defaultValue: T? = null) = object : ReadOnlyProperty<Any, T?> {
    override fun getValue(thisRef: Any, property: KProperty<*>) =
            (arguments?.get(name) ?: defaultValue) as? T
}

class Lazy<T, V>(private val initializer: (T, KProperty<*>) -> V) : ReadOnlyProperty<T, V> {
    private object EMPTY
    private var value: Any? = EMPTY

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: T, property: KProperty<*>): V {
        if (value == EMPTY) value = initializer(thisRef, property)
        return value as V
    }
}
