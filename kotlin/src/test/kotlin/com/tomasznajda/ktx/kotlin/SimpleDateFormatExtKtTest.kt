package com.tomasznajda.ktx.kotlin

import com.tomasznajda.ktx.junit.assertEquals
import org.junit.Test

import java.util.*

class SimpleDateFormatExtKtTest {

    val millis = 1577891410000 //01.01.2020 16:10:10

    @Test
    fun `format returns formatted string `() {
        assertEquals(expected = "01.01.2020",
                     actual = Date(millis).format("dd.MM.yyyy"))
        assertEquals(expected = "01.01.2020 16:10:10",
                     actual = Date(millis).format("dd.MM.yyyy HH:mm:ss"))
    }
}