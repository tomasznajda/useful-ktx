package com.tomasznajda.ktx.rxjava2

import io.reactivex.*
import io.reactivex.functions.BiFunction

fun <T1, T2> zip(source1: Flowable<T1>, source2: Flowable<T2>) =
        Flowable.zip(source1, source2, ZipToPair())!!

fun <T1, T2> zip(source1: Observable<T1>, source2: Observable<T2>) =
        Observable.zip(source1, source2, ZipToPair())!!

fun <T1, T2> zip(source1: Single<T1>, source2: Single<T2>) =
        Single.zip(source1, source2, ZipToPair())!!

fun <T1, T2> zip(source1: Maybe<T1>, source2: Maybe<T2>) =
        Maybe.zip(source1, source2, ZipToPair())!!

fun zip(source1: Completable, source2: Completable) =
        zip(source1.toSingleDefault(Unit),
            source2.toSingleDefault(Unit)).ignoreElement()!!

fun <T1, T2> Flowable<T1>.zipWith(source: Flowable<T2>) =
        zipWith(source, ZipToPair())!!

fun <T1, T2> Observable<T1>.zipWith(source: Observable<T2>) =
        zipWith(source, ZipToPair())!!

fun <T1, T2> Single<T1>.zipWith(source: Single<T2>) =
        zipWith(source, ZipToPair())!!

fun <T1, T2> Maybe<T1>.zipWith(source: Maybe<T2>) =
        zipWith(source, ZipToPair())!!

class ZipToPair<T1, T2> : BiFunction<T1, T2, Pair<T1, T2>> {

    override fun apply(t1: T1, t2: T2) = Pair(t1, t2)
}