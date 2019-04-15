package com.tomasznajda.ktx.android

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.tomasznajda.ktx.junit.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class ClipboardExtKtTest {

    val context = ApplicationProvider.getApplicationContext<Context>()

    @Test
    fun `copyToClipboard creates new primary clip with given string`() {
        "text to copy".copyToClipboard(context, "text label")
        assertEquals(expected = "text to copy",
                     actual = context.clipboardManager?.primaryClip?.getItemAt(0)?.text)
        assertEquals(expected = "text label",
                     actual = context.clipboardManager?.primaryClip?.description?.label)
    }
}