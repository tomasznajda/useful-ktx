package com.tomasznajda.ktx._util

import org.hamcrest.CoreMatchers
import org.junit.Assert
import kotlin.reflect.KClass

fun assertEquals(expected: Any?, actual: Any?) = Assert.assertEquals(expected, actual)

fun <T> Any.assertIsInstanceOf(clazz: Class<T>) =
        Assert.assertThat(this, CoreMatchers.`is`(CoreMatchers.instanceOf(clazz)))

fun <T : Any> Any.assertIsInstanceOf(clazz: KClass<T>) = assertIsInstanceOf(clazz.java)