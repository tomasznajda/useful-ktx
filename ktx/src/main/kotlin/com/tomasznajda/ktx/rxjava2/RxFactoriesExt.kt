package com.tomasznajda.ktx.rxjava2

import io.reactivex.*

fun <T : Any> T?.asFlowable() = this?.let { Flowable.just(this) } ?: Flowable.empty()

fun <T : Any> T?.asObservable() = this?.let { Observable.just(this) } ?: Observable.empty()

fun <T : Any> T?.asMaybe() = this?.let { Maybe.just(it) } ?: Maybe.empty()

fun <T : Any> T.asSingle() = Single.just(this)!!

fun <T : Any, R : Throwable> R.asFlowableError() = Flowable.error<T>(this)!!

fun <T : Any, R : Throwable> R.asObservableError() = Observable.error<T>(this)!!

fun <T : Any, R : Throwable> R.asSingleError() = Single.error<T>(this)!!

fun <T : Any, R : Throwable> R.asMaybeError() = Maybe.error<T>(this)!!

fun <R : Throwable> R.asCompletableError() = Completable.error(this)!!