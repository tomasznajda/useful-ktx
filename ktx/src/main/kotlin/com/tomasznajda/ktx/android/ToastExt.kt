package com.tomasznajda.ktx.android

import android.content.Context
import android.support.annotation.StringRes
import android.widget.Toast

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(this, message, duration).show()

fun Context.toast(@StringRes messageId: Int, duration: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(this, messageId, duration).show()