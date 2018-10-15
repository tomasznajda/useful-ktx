package com.tomasznajda.ktx.android

import java.text.SimpleDateFormat
import java.util.*

fun Date.format(pattern: String, locale: Locale = Locale.getDefault()) =
        SimpleDateFormat(pattern, locale).format(this)!!