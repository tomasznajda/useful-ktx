package com.tomasznajda.ktx.rxjava2

import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

fun <T> merge(source1: Flowable<T>, source2: Flowable<T>) = Flowable.merge(source1, source2)!!

fun <T> merge(source1: Observable<T>, source2: Observable<T>) = Observable.merge(source1, source2)!!

fun <T> merge(source1: Single<T>, source2: Single<T>) = Single.merge(source1, source2)!!

fun <T> merge(source1: Maybe<T>, source2: Maybe<T>) = Maybe.merge(source1, source2)!!

fun <T> merge(source1: Flowable<T>, source2: Flowable<T>, source3: Flowable<T>) =
        Flowable.merge(source1, source2, source3)!!

fun <T> merge(source1: Observable<T>, source2: Observable<T>, source3: Observable<T>) =
        Observable.merge(source1, source2, source3)!!

fun <T> merge(source1: Single<T>, source2: Single<T>, source3: Single<T>) =
        Single.merge(source1, source2, source3)!!

fun <T> merge(source1: Maybe<T>, source2: Maybe<T>, source3: Maybe<T>) =
        Maybe.merge(source1, source2, source3)!!