package com.tomasznajda.ktx.gson

import com.google.gson.Gson
import kotlin.reflect.KClass

fun Any.toJson() = Gson().toJson(this)!!

fun <T> String.fromJson(clazz: Class<T>) = Gson().fromJson(this, clazz)!!

fun <T : Any> String.fromJson(clazz: KClass<T>) = fromJson(clazz.java)