package com.tomasznajda.ktx.kotlin

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class NullExtKtTest {

    @Test
    fun `isNotNull returns true when Any is not null`() {
        assertTrue(Unit.isNotNull())
    }

    @Test
    fun `isNotNull returns false when Any is null`() {
        assertFalse(null.isNotNull())
    }

    @Test
    fun `isNull returns true when Any is null`() {
        assertTrue(null.isNull())
    }

    @Test
    fun `isNull returns false when Any is not null`() {
        assertFalse(Unit.isNull())
    }
}