package com.tomasznajda.ktx.rxjava2

import com.tomasznajda.ktx.rxjava2.util.ToPair
import com.tomasznajda.ktx.rxjava2.util.ToTriple
import io.reactivex.*

fun <T1, T2> zip(source1: Flowable<T1>, source2: Flowable<T2>) =
        Flowable.zip(source1, source2, ToPair())!!

fun <T1, T2> zip(source1: Observable<T1>, source2: Observable<T2>) =
        Observable.zip(source1, source2, ToPair())!!

fun <T1, T2> zip(source1: Single<T1>, source2: Single<T2>) =
        Single.zip(source1, source2, ToPair())!!

fun <T1, T2> zip(source1: Maybe<T1>, source2: Maybe<T2>) =
        Maybe.zip(source1, source2, ToPair())!!

fun zip(source1: Completable, source2: Completable) =
        zip(source1.toSingleDefault(Unit),
            source2.toSingleDefault(Unit)).ignoreElement()!!

fun <T1, T2, T3> zip(source1: Flowable<T1>, source2: Flowable<T2>, source3: Flowable<T3>) =
        Flowable.zip(source1, source2, source3, ToTriple())!!

fun <T1, T2, T3> zip(source1: Observable<T1>, source2: Observable<T2>, source3: Observable<T3>) =
        Observable.zip(source1, source2, source3, ToTriple())!!

fun <T1, T2, T3> zip(source1: Single<T1>, source2: Single<T2>, source3: Single<T3>) =
        Single.zip(source1, source2, source3, ToTriple())!!

fun <T1, T2, T3> zip(source1: Maybe<T1>, source2: Maybe<T2>, source3: Maybe<T3>) =
        Maybe.zip(source1, source2, source3, ToTriple())!!

fun zip(source1: Completable, source2: Completable, source3: Completable) =
        zip(source1.toSingleDefault(Unit),
            source2.toSingleDefault(Unit),
            source3.toSingleDefault(Unit)).ignoreElement()!!

fun <T1, T2> Flowable<T1>.zipWith(source: Flowable<T2>) =
        zipWith(source, ToPair())!!

fun <T1, T2> Observable<T1>.zipWith(source: Observable<T2>) =
        zipWith(source, ToPair())!!

fun <T1, T2> Single<T1>.zipWith(source: Single<T2>) =
        zipWith(source, ToPair())!!

fun <T1, T2> Maybe<T1>.zipWith(source: Maybe<T2>) =
        zipWith(source, ToPair())!!