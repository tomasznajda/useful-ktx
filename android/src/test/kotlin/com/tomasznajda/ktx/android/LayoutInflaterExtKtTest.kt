package com.tomasznajda.ktx.android

import android.app.Activity
import android.support.v4.app.Fragment
import android.view.ViewGroup
import com.tomasznajda.ktx.junit.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith

import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.support.v4.SupportFragmentController

@RunWith(RobolectricTestRunner::class)
class LayoutInflaterExtKtTest {

    val activity = Robolectric.setupActivity(Activity::class.java)
    val fragment = SupportFragmentController.setupFragment(Fragment())
    val root = activity.findViewById<ViewGroup>(android.R.id.content)

    @Test
    fun `ViewGroup#inflate inflates layout`() {
        val view = root.inflate(R.layout.test_layout)
        assertNotNull(view.findViewById(R.id.testView))
    }

    @Test
    fun `ViewGroup#inflate does not attach inflated layout to the view group by default`() {
        root.inflate(R.layout.test_layout)
        assertEquals(expected = 0, actual = root.childCount)
    }

    @Test
    fun `ViewGroup#inflate does not attach inflated layout to the view group when attach to root flag is set to false`() {
        root.inflate(R.layout.test_layout, attachToRoot = false)
        assertEquals(expected = 0, actual = root.childCount)
    }

    @Test
    fun `ViewGroup#inflate attaches inflated layout to the view group when attach to root flag is set to true`() {
        root.inflate(R.layout.test_layout, attachToRoot = true)
        assertEquals(expected = 1, actual = root.childCount)
    }

    @Test
    fun `Context#inflate inflates layout`() {
        val view = activity.inflate(R.layout.test_layout)
        assertNotNull(view.findViewById(R.id.testView))
    }

    @Test
    fun `Fragment#inflate inflates layout`() {
        val view = fragment.inflate(R.layout.test_layout)
        assertNotNull(view.findViewById(R.id.testView))
    }
}