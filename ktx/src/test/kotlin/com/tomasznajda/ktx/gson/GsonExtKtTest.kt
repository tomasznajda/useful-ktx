package com.tomasznajda.ktx.gson

import com.tomasznajda.ktx._util.assertEquals
import org.junit.Test

class GsonExtKtTest {

    data class TestDataClass(val name: String, val value: Int)

    @Test
    fun `toJson converts any object to json`() {
        assertEquals(
                expected = "{\"name\":\"name\",\"value\":123}",
                actual = TestDataClass("name", 123).toJson())
    }

    @Test
    fun `fromJson converts json to object of given class`() {
        assertEquals(
                expected = TestDataClass("name", 123),
                actual = "{\"name\":\"name\",\"value\":123}".fromJson(TestDataClass::class.java))
    }
}