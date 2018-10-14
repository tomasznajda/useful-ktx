package com.tomasznajda.ktx.android

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import com.tomasznajda.ktx.junit.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ViewVisibilityExtKtTest {

    val view by lazy {
        Robolectric
                .setupActivity(Activity::class.java)
                .findViewById<ViewGroup>(android.R.id.content)
                .inflate(R.layout.test_layout, attachToRoot = true)
                .findViewById<View>(R.id.testView)
    }

    @Test
    fun `visible sets visibility to VISIBLE`() {
        view.visibility = View.GONE
        view.visible()
        assertEquals(expected = View.VISIBLE, actual = view.visibility)
    }

    @Test
    fun `invisible sets visibility to INVISIBLE`() {
        view.visibility = View.VISIBLE
        view.invisible()
        assertEquals(expected = View.INVISIBLE, actual = view.visibility)
    }

    @Test
    fun `gone sets visibility to GONE`() {
        view.visibility = View.VISIBLE
        view.gone()
        assertEquals(expected = View.GONE, actual = view.visibility)
    }

    @Test
    fun `isVisible returns true when visibility is VISIBLE`() {
        view.visibility = View.VISIBLE
        assertEquals(expected = true, actual = view.isVisible)
    }

    @Test
    fun `isVisible returns false when visibility is INVISIBLE`() {
        view.visibility = View.INVISIBLE
        assertEquals(expected = false, actual = view.isVisible)
    }

    @Test
    fun `isVisible returns false when visibility is GONE`() {
        view.visibility = View.GONE
        assertEquals(expected = false, actual = view.isVisible)
    }

    @Test
    fun `isInvisible returns true when visibility is INVISIBLE`() {
        view.visibility = View.INVISIBLE
        assertEquals(expected = true, actual = view.isInvisible)
    }

    @Test
    fun `isInvisible returns false when visibility is VISIBLE`() {
        view.visibility = View.VISIBLE
        assertEquals(expected = false, actual = view.isInvisible)
    }

    @Test
    fun `isInvisible returns false when visibility is GONE`() {
        view.visibility = View.GONE
        assertEquals(expected = false, actual = view.isInvisible)
    }

    @Test
    fun `isGone returns true when visibility is GONE`() {
        view.visibility = View.GONE
        assertEquals(expected = true, actual = view.isGone)
    }

    @Test
    fun `isGone returns false when visibility is VISIBLE`() {
        view.visibility = View.VISIBLE
        assertEquals(expected = false, actual = view.isGone)
    }

    @Test
    fun `isGone returns false when visibility is INVISIBLE`() {
        view.visibility = View.INVISIBLE
        assertEquals(expected = false, actual = view.isGone)
    }

    @Test
    fun `isVisible sets visibility to VISIBLE when true`() {
        view.visibility = View.GONE
        view.isVisible = true
        assertEquals(expected = View.VISIBLE, actual = view.visibility)
    }

    @Test
    fun `isVisible sets visibility to GONE when false`() {
        view.visibility = View.VISIBLE
        view.isVisible = false
        assertEquals(expected = View.GONE, actual = view.visibility)
    }

    @Test
    fun `isInvisible sets visibility to INVISIBLE when true`() {
        view.visibility = View.VISIBLE
        view.isInvisible = true
        assertEquals(expected = View.INVISIBLE, actual = view.visibility)
    }

    @Test
    fun `isInvisible sets visibility to VISIBLE when false`() {
        view.visibility = View.INVISIBLE
        view.isInvisible = false
        assertEquals(expected = View.VISIBLE, actual = view.visibility)
    }

    @Test
    fun `isGone sets visibility to GONE when true`() {
        view.visibility = View.VISIBLE
        view.isGone = true
        assertEquals(expected = View.GONE, actual = view.visibility)
    }

    @Test
    fun `isGone sets visibility to VISIBLE when false`() {
        view.visibility = View.GONE
        view.isGone = false
        assertEquals(expected = View.VISIBLE, actual = view.visibility)
    }
}