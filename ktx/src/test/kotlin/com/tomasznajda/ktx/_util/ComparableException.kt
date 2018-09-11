package com.tomasznajda.ktx._util

import java.util.*

data class ComparableException(val hash: Int = Random().nextInt()) : Exception()