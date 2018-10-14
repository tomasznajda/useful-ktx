package com.tomasznajda.ktx.android

import android.util.Log

fun logwtf(tag: String, msg: String = "", e: Throwable? = null) =
        e?.let { Log.wtf(tag, msg, e) } ?: Log.wtf(tag, msg)

fun loge(tag: String, msg: String = "", e: Throwable? = null) =
        e?.let { Log.e(tag, msg, e) } ?: Log.e(tag, msg)

fun logw(tag: String, msg: String = "", e: Throwable? = null) =
        e?.let { Log.w(tag, msg, e) } ?: Log.w(tag, msg)

fun logi(tag: String, msg: String = "", e: Throwable? = null) =
        e?.let { Log.i(tag, msg, e) } ?: Log.i(tag, msg)

fun logd(tag: String, msg: String = "", e: Throwable? = null) =
        e?.let { Log.d(tag, msg, e) } ?: Log.d(tag, msg)

fun logv(tag: String, msg: String = "", e: Throwable? = null) =
        e?.let { Log.v(tag, msg, e) } ?: Log.v(tag, msg)

fun Any.loge(tag: String, format: (String) -> String = { it }) = Log.e(tag, format.invoke(toString()))

fun Any.logw(tag: String, format: (String) -> String = { it }) = Log.w(tag, format.invoke(toString()))

fun Any.logi(tag: String, format: (String) -> String = { it }) = Log.i(tag, format.invoke(toString()))

fun Any.logd(tag: String, format: (String) -> String = { it }) = Log.d(tag, format.invoke(toString()))

fun Any.logv(tag: String, format: (String) -> String = { it }) = Log.v(tag, format.invoke(toString()))

fun Any.println(format: (String) -> String = { it }) = println(format.invoke(toString()))

fun Throwable.logwtf(tag: String, msg: String = "") = logwtf(tag, msg, this)

fun Throwable.loge(tag: String, msg: String = "") = loge(tag, msg, this)

fun Throwable.logw(tag: String, msg: String = "") = logw(tag, msg, this)

fun Throwable.logi(tag: String, msg: String = "") = logi(tag, msg, this)

fun Throwable.logd(tag: String, msg: String = "") = logd(tag, msg, this)

fun Throwable.logv(tag: String, msg: String = "") = logv(tag, msg, this)

