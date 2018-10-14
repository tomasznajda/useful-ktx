package com.tomasznajda.ktx.android

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup

fun ViewGroup.inflate(@LayoutRes layoutId: Int, attachToRoot: Boolean = false) =
        LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)!!

fun Context.inflate(@LayoutRes layoutId: Int) =
        LayoutInflater.from(this).inflate(layoutId, null)!!

fun Fragment.inflate(@LayoutRes layoutId: Int) =
        LayoutInflater.from(context).inflate(layoutId, null)!!
