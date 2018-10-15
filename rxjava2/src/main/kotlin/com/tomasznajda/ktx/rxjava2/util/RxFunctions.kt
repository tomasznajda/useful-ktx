package com.tomasznajda.ktx.rxjava2.util

import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function3

class ToPair<T1, T2> : BiFunction<T1, T2, Pair<T1, T2>> {
    override fun apply(t1: T1, t2: T2) = Pair(t1, t2)
}

class ToTriple<T1, T2, T3> : Function3<T1, T2, T3, Triple<T1, T2, T3>> {
    override fun apply(t1: T1, t2: T2, t3: T3) = Triple(t1, t2, t3)
}