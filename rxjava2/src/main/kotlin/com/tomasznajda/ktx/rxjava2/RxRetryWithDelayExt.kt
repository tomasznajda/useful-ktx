package com.tomasznajda.ktx.rxjava2

import io.reactivex.*
import io.reactivex.functions.Function
import java.util.concurrent.TimeUnit

fun <T> Flowable<T>.retryWithDelay(maxRetries: Int, delayInMillis: Long) =
        retryWhen(RetryWithDelayFlowableFunction(maxRetries, delayInMillis))!!

fun <T> Observable<T>.retryWithDelay(maxRetries: Int, delayInMillis: Long) =
        retryWhen(RetryWithDelayObservableFunction(maxRetries, delayInMillis))!!

fun <T> Maybe<T>.retryWithDelay(maxRetries: Int, delayInMillis: Long) =
        retryWhen(RetryWithDelayFlowableFunction(maxRetries, delayInMillis))!!

fun <T> Single<T>.retryWithDelay(maxRetries: Int, delayInMillis: Long) =
        retryWhen(RetryWithDelayFlowableFunction(maxRetries, delayInMillis))!!

fun Completable.retryWithDelay(maxRetries: Int, delayInMillis: Long) =
        retryWhen(RetryWithDelayFlowableFunction(maxRetries, delayInMillis))!!

private class RetryWithDelayObservableFunction(private val maxRetries: Int,
                                               private val delayInMillis: Long)
    : Function<Observable<out Throwable>, Observable<*>> {

    private var retryCount = 0

    override fun apply(attempts: Observable<out Throwable>) =
            attempts.flatMap { throwable ->
                if (retryCount++ < maxRetries) Observable.timer(delayInMillis, TimeUnit.MILLISECONDS)
                else Observable.error(throwable)
            }!!
}

private class RetryWithDelayFlowableFunction(private val maxRetries: Int,
                                             private val delayInMillis: Long)
    : Function<Flowable<out Throwable>, Flowable<*>> {

    private var retryCount = 0

    override fun apply(attempts: Flowable<out Throwable>) =
            attempts.flatMap { throwable ->
                if (retryCount++ < maxRetries) Flowable.timer(delayInMillis, TimeUnit.MILLISECONDS)
                else Flowable.error(throwable)
            }!!
}