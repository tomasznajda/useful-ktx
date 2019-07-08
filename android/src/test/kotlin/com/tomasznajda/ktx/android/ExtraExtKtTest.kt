package com.tomasznajda.ktx.android

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.tomasznajda.ktx.junit.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import java.lang.ClassCastException

@RunWith(RobolectricTestRunner::class)
class ExtraExtKtTest {

    @Test
    fun `Activity#extra provides boolean from given key when extras contain this key and value is of boolean type`() {
        val intent = Intent().putExtra("TEST_BOOLEAN", true)
        val activity = Robolectric.buildActivity(TestActivity::class.java, intent).create().start().resume().get()
        assertEquals(expected = true,
                     actual = activity.booleanExtra)
    }

    @Test
    fun `Activity#extra provides default boolean from given key when extras do not contain this key`() {
        val activity = Robolectric.buildActivity(TestActivity::class.java).create().start().resume().get()
        assertEquals(expected = true,
                     actual = activity.booleanExtraWithDefaultValue)
    }

    @Test(expected = NullPointerException::class)
    fun `Activity#extra throws an exception when extras do not contain given key and default value is not specified`() {
        val activity = Robolectric.buildActivity(TestActivity::class.java).create().start().resume().get()
        activity.booleanExtra
    }

    @Test(expected = ClassCastException::class)
    fun `Activity#extra throws an exception when extras contain given key, but of a different type`() {
        val intent = Intent().putExtra("TEST_BOOLEAN", "boolean")
        val activity = Robolectric.buildActivity(TestActivity::class.java, intent).create().start().resume().get()
        activity.booleanExtra
    }

    @Test
    fun `Activity#extraOptional provides boolean from given key when extras contains this key and value is of boolean type`() {
        val intent = Intent().putExtra("TEST_BOOLEAN", true)
        val activity = Robolectric.buildActivity(TestActivity::class.java, intent).create().start().resume().get()
        assertEquals(expected = true,
                     actual = activity.booleanExtraOptional)
    }

    @Test
    fun `Activity#extraOptional provides default boolean from given key when extras do not contain this key`() {
        val activity = Robolectric.buildActivity(TestActivity::class.java).create().start().resume().get()
        assertEquals(expected = true,
                     actual = activity.booleanExtraOptionalWithDefaultValue)
    }

    @Test
    fun `Activity#extraOptional provides null when extras do not contain given key and default value is not specified`() {
        val activity = Robolectric.buildActivity(TestActivity::class.java).create().start().resume().get()
        assertEquals(expected = null,
                     actual = activity.booleanExtraOptional)
    }

    @Test(expected = ClassCastException::class)
    fun `Activity#extraOptional throws an exception when extras contain given key, but of a different type`() {
        val intent = Intent().putExtra("TEST_BOOLEAN", "boolean")
        val activity = Robolectric.buildActivity(TestActivity::class.java, intent).create().start().resume().get()
        activity.booleanExtraOptional
    }
}

private class TestActivity : Activity() {

    val booleanExtra by extra<Boolean>("TEST_BOOLEAN")
    val booleanExtraWithDefaultValue by extra("TEST_BOOLEAN", defaultValue = true)
    val booleanExtraOptional by extraOptional<Boolean>("TEST_BOOLEAN")
    val booleanExtraOptionalWithDefaultValue by extraOptional("TEST_BOOLEAN", defaultValue = true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_layout)
    }
}

private class TestFragment : Fragment() {

}