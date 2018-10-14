package com.tomasznajda.ktx.kotlin

import kotlin.reflect.KClass

fun <T : Any> Any.cast(clazz: Class<T>) = clazz.cast(this)!!

fun <T : Any> Any.cast(clazz: KClass<T>) = clazz.java.cast(this)!!