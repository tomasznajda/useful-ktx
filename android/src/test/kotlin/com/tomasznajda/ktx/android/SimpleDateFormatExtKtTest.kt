package com.tomasznajda.ktx.android

import com.tomasznajda.ktx.junit.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.util.*

@RunWith(RobolectricTestRunner::class)
class SimpleDateFormatExtKtTest {

    val millis = 1577891410000 //01.01.2020 16:10:10

    @Test
    fun `format returns formatted string `() {
        assertEquals(expected = "01.01.2020",
                     actual = Date(millis).format("dd.MM.yyyy"))
    }
}