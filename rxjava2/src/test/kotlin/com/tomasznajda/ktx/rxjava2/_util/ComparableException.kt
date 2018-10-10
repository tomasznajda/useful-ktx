package com.tomasznajda.ktx.rxjava2._util

import java.util.*

data class ComparableException(val hash: Int = Random().nextInt()) : Exception()