package com.tomasznajda.ktx.android

import android.content.ClipData
import android.content.Context

fun String.copyToClipboard(context: Context, label: String) =
        ClipData.newPlainText(label, this).let { context.clipboardManager?.primaryClip = it }