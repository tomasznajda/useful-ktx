package com.tomasznajda.ktx.android

import android.os.Build
import com.tomasznajda.ktx.junit.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.M])
class SystemVersionVerificationExtKtTest {

    @Test
    fun `fromAndroid invokes block when android version is the same as given`() {
        var invoked = false
        fromAndroid(Build.VERSION_CODES.M) { invoked = true }
        assertEquals(expected = true, actual = invoked)
    }

    @Test
    fun `fromAndroid invokes block when android version is higher than given`() {
        var invoked = false
        fromAndroid(Build.VERSION_CODES.LOLLIPOP) { invoked = true }
        assertEquals(expected = true, actual = invoked)
    }

    @Test
    fun `fromAndroid does not invoke block when android version is lower than given`() {
        var invoked = false
        fromAndroid(Build.VERSION_CODES.N) { invoked = true }
        assertEquals(expected = false, actual = invoked)
    }

    @Test
    fun `toAndroid invokes block when android version is the same as given`() {
        var invoked = false
        toAndroid(Build.VERSION_CODES.M) { invoked = true }
        assertEquals(expected = true, actual = invoked)
    }

    @Test
    fun `toAndroid invokes block when android version is lower than given`() {
        var invoked = false
        toAndroid(Build.VERSION_CODES.N) { invoked = true }
        assertEquals(expected = true, actual = invoked)
    }

    @Test
    fun `toAndroid does not invoke block when android version is higher than given`() {
        var invoked = false
        toAndroid(Build.VERSION_CODES.LOLLIPOP) { invoked = true }
        assertEquals(expected = false, actual = invoked)
    }

    @Test
    fun `afterAndroid invokes block when android version is higher than given`() {
        var invoked = false
        afterAndroid(Build.VERSION_CODES.LOLLIPOP) { invoked = true }
        assertEquals(expected = true, actual = invoked)
    }

    @Test
    fun `afterAndroid does not invoke block when android version is the same as given`() {
        var invoked = false
        afterAndroid(Build.VERSION_CODES.M) { invoked = true }
        assertEquals(expected = false, actual = invoked)
    }

    @Test
    fun `afterAndroid does not invoke block when android version is lower than given`() {
        var invoked = false
        afterAndroid(Build.VERSION_CODES.N) { invoked = true }
        assertEquals(expected = false, actual = invoked)
    }

    @Test
    fun `beforeAndroid invokes block when android version is lower than given`() {
        var invoked = false
        beforeAndroid(Build.VERSION_CODES.N) { invoked = true }
        assertEquals(expected = true, actual = invoked)
    }

    @Test
    fun `beforeAndroid does not invoke block when android version is the same as given`() {
        var invoked = false
        beforeAndroid(Build.VERSION_CODES.M) { invoked = true }
        assertEquals(expected = false, actual = invoked)
    }

    @Test
    fun `beforeAndroid does not invoke block when android version is higher than given`() {
        var invoked = false
        beforeAndroid(Build.VERSION_CODES.LOLLIPOP) { invoked = true }
        assertEquals(expected = false, actual = invoked)
    }
}