package com.tomasznajda.ktx.rxjava2

import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.exceptions.OnErrorNotImplementedException
import io.reactivex.plugins.RxJavaPlugins

private val onNextStub: (Any) -> Unit = {}
private val onErrorStub: (Throwable) -> Unit = {
    RxJavaPlugins.onError(OnErrorNotImplementedException(it))
}
private val onCompleteStub: () -> Unit = {}

fun <T : Any> Flowable<T>.retrySubscribe(onNext: (T) -> Unit = onNextStub,
                                         onError: (Throwable) -> Unit = onErrorStub,
                                         onComplete: () -> Unit = onCompleteStub) = this
        .doOnNext(onNext)
        .doOnComplete(onComplete)
        .doOnError(onError)
        .retry()
        .subscribe()!!

fun <T : Any> Observable<T>.retrySubscribe(onNext: (T) -> Unit = onNextStub,
                                           onError: (Throwable) -> Unit = onErrorStub,
                                           onComplete: () -> Unit = onCompleteStub) = this
        .doOnNext(onNext)
        .doOnComplete(onComplete)
        .doOnError(onError)
        .retry()
        .subscribe()!!

fun <T : Any> Maybe<T>.retrySubscribe(onSuccess: (T) -> Unit = onNextStub,
                                      onError: (Throwable) -> Unit = onErrorStub,
                                      onComplete: () -> Unit = onCompleteStub) = this
        .doOnSuccess(onSuccess)
        .doOnComplete(onComplete)
        .doOnError(onError)
        .retry()
        .subscribe()!!

fun <T : Any> Single<T>.retrySubscribe(onSuccess: (T) -> Unit = onNextStub,
                                       onError: (Throwable) -> Unit = onErrorStub) = this
        .doOnSuccess(onSuccess)
        .doOnError(onError)
        .retry()
        .subscribe()!!