package com.tomasznajda.ktx.kotlin

fun <T : Any?> T?.isNotNull() = this != null

fun <T : Any?> T?.isNull() = this == null