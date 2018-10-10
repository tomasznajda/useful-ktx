package com.tomasznajda.ktx.junit

import org.hamcrest.CoreMatchers
import org.junit.Assert
import kotlin.reflect.KClass

fun assertEquals(expected: Any?, actual: Any?) = Assert.assertEquals(expected, actual)

fun <T : Any> Any.assertIsInstanceOf(clazz: KClass<T>) =
        Assert.assertThat(this, CoreMatchers.`is`(CoreMatchers.instanceOf(clazz.java)))