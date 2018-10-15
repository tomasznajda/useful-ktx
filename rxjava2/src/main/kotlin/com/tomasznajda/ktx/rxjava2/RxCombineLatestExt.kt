package com.tomasznajda.ktx.rxjava2

import com.tomasznajda.ktx.rxjava2.util.ToPair
import com.tomasznajda.ktx.rxjava2.util.ToTriple
import io.reactivex.Flowable
import io.reactivex.Observable

fun <T1, T2> combineLatest(source1: Flowable<T1>, source2: Flowable<T2>) =
        Flowable.combineLatest(source1, source2, ToPair())!!

fun <T1, T2> combineLatest(source1: Observable<T1>, source2: Observable<T2>) =
        Observable.combineLatest(source1, source2, ToPair())!!

fun <T1, T2, T3> combineLatest(source1: Flowable<T1>, source2: Flowable<T2>, source3: Flowable<T3>) =
        Flowable.combineLatest(source1, source2, source3, ToTriple())!!

fun <T1, T2, T3> combineLatest(source1: Observable<T1>, source2: Observable<T2>, source3: Observable<T3>) =
        Observable.combineLatest(source1, source2, source3, ToTriple())!!