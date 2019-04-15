package com.tomasznajda.ktx.android

import android.content.Context
import android.preference.PreferenceManager
import androidx.test.core.app.ApplicationProvider
import com.tomasznajda.ktx.junit.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SharedPreferencesExtKtTest {

    val context = ApplicationProvider.getApplicationContext<Context>()
    var booleanPref by context.prefs("test").boolean("TEST_BOOLEAN", true)
    var floatPref by context.prefs("test").float("TEST_FLOAT", 15.1f)
    var intPref by context.prefs("test").int("TEST_INT", 23)
    var longPref by context.prefs("test").long("TEST_LONG", 923)
    var stringPref by context.prefs("test").string("TEST_STRING", "string")
    var stringSetPref by context.prefs("test").stringSet("TEST_STRING_SET", setOf("ab", "cd"))

    @Test
    fun `prefs returns shared preferences instance for given name when name is provided`() {
        assertEquals(expected = context.getSharedPreferences("abcd", Context.MODE_PRIVATE),
                     actual = context.prefs("abcd"))
    }

    @Test
    fun `prefs returns default shared preferences instance when name is not provided`() {
        assertEquals(expected = PreferenceManager.getDefaultSharedPreferences(context),
                     actual = context.prefs())
    }

    @Test
    fun `boolean property returns default value when no data is stored under given key`() {
        context.prefs("test").edit().remove("TEST_BOOLEAN").commit()
        assertEquals(expected = true,
                     actual = booleanPref)
    }

    @Test
    fun `boolean property returns stored value when data is stored under given key`() {
        context.prefs("test").edit().putBoolean("TEST_BOOLEAN", false).commit()
        assertEquals(expected = false,
                     actual = booleanPref)
    }

    @Test
    fun `boolean property saves value under given key`() {
        booleanPref = false
        assertEquals(expected = false,
                     actual = context.prefs("test").getBoolean("TEST_BOOLEAN", true))
    }

    @Test
    fun `float property returns default value when no data is stored under given key`() {
        context.prefs("test").edit().remove("TEST_FLOAT").commit()
        assertEquals(expected = 15.1f,
                     actual = floatPref)
    }

    @Test
    fun `float property returns stored value when data is stored under given key`() {
        context.prefs("test").edit().putFloat("TEST_FLOAT", 9.3f).commit()
        assertEquals(expected = 9.3f,
                     actual = floatPref)
    }

    @Test
    fun `float property saves value under given key`() {
        floatPref = 4.7f
        assertEquals(expected = 4.7f,
                     actual = context.prefs("test").getFloat("TEST_FLOAT", 0f))
    }

    @Test
    fun `int property returns default value when no data is stored under given key`() {
        context.prefs("test").edit().remove("TEST_INT").commit()
        assertEquals(expected = 23,
                     actual = intPref)
    }

    @Test
    fun `int property returns stored value when data is stored under given key`() {
        context.prefs("test").edit().putInt("TEST_INT", 19).commit()
        assertEquals(expected = 19,
                     actual = intPref)
    }

    @Test
    fun `int property saves value under given key`() {
        intPref = 13
        assertEquals(expected = 13,
                     actual = context.prefs("test").getInt("TEST_INT", 0))
    }

    @Test
    fun `long property returns default value when no data is stored under given key`() {
        context.prefs("test").edit().remove("TEST_LONG").commit()
        assertEquals(expected = 923L,
                     actual = longPref)
    }

    @Test
    fun `long property returns stored value when data is stored under given key`() {
        context.prefs("test").edit().putLong("TEST_LONG", 19).commit()
        assertEquals(expected = 19L,
                     actual = longPref)
    }

    @Test
    fun `long property saves value under given key`() {
        longPref = 13
        assertEquals(expected = 13L,
                     actual = context.prefs("test").getLong("TEST_LONG", 0))
    }

    @Test
    fun `string property returns default value when no data is stored under given key`() {
        context.prefs("test").edit().remove("TEST_STRING").commit()
        assertEquals(expected = "string",
                     actual = stringPref)
    }

    @Test
    fun `string property returns stored value when data is stored under given key`() {
        context.prefs("test").edit().putString("TEST_STRING", "another string").commit()
        assertEquals(expected = "another string",
                     actual = stringPref)
    }

    @Test
    fun `string property saves value under given key`() {
        stringPref = "another string"
        assertEquals(expected = "another string",
                     actual = context.prefs("test").getString("TEST_STRING", null))
    }

    @Test
    fun `stringSet property returns default value when no data is stored under given key`() {
        context.prefs("test").edit().remove("TEST_STRING_SET").commit()
        assertEquals(expected = setOf("ab", "cd"),
                     actual = stringSetPref)
    }

    @Test
    fun `stringSet property returns stored value when data is stored under given key`() {
        context.prefs("test").edit().putStringSet("TEST_STRING_SET", setOf("qw", "er")).commit()
        assertEquals(expected = setOf("qw", "er"),
                     actual = stringSetPref)
    }

    @Test
    fun `stringSet property saves value under given key`() {
        stringSetPref = setOf("12", "34")
        assertEquals(expected = setOf("12", "34"),
                     actual = context.prefs("test").getStringSet("TEST_STRING_SET", null))
    }
}