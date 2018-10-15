package com.tomasznajda.ktx.rxjava2

import io.reactivex.*

fun <T> Flowable<T>.waitForCompletion(mapper: (T) -> Completable) =
        flatMapSingle { mapper(it).toSingleDefault(it) }!!

fun <T> Observable<T>.waitForCompletion(mapper: (T) -> Completable) =
        flatMapSingle { mapper(it).toSingleDefault(it) }!!

fun <T> Maybe<T>.waitForCompletion(mapper: (T) -> Completable) =
        flatMapSingleElement { mapper(it).toSingleDefault(it) }!!

fun <T> Single<T>.waitForCompletion(mapper: (T) -> Completable) =
        flatMap { mapper(it).toSingleDefault(it) }!!

fun <T> Flowable<T>.switchWaitForCompletion(mapper: (T) -> Completable) =
        switchMapSingle { mapper(it).toSingleDefault(it) }!!

fun <T> Observable<T>.switchWaitForCompletion(mapper: (T) -> Completable) =
        switchMapSingle { mapper(it).toSingleDefault(it) }

fun <T> Flowable<T>.concatWaitForCompletion(mapper: (T) -> Completable) =
        concatMapSingle { mapper(it).toSingleDefault(it) }!!

fun <T> Observable<T>.concatWaitForCompletion(mapper: (T) -> Completable) =
        concatMapSingle { mapper(it).toSingleDefault(it) }!!
