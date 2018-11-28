package com.tomasznajda.ktx.android

import android.os.Build

fun fromAndroid(version: Int, block: () -> Unit) {
    if(Build.VERSION.SDK_INT >= version) block()
}

fun toAndroid(version: Int, block: () -> Unit) {
    if(Build.VERSION.SDK_INT <= version) block()
}

fun afterAndroid(version: Int, block: () -> Unit) {
    if(Build.VERSION.SDK_INT > version) block()
}

fun beforeAndroid(version: Int, block: () -> Unit) {
    if(Build.VERSION.SDK_INT < version) block()
}