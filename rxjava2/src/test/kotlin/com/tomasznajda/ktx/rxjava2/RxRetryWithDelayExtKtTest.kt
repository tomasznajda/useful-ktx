package com.tomasznajda.ktx.rxjava2

import com.tomasznajda.ktx.junit.assertEquals
import com.tomasznajda.ktx.rxjava2._util.ComparableException
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

class RxRetryWithDelayExtKtTest {

    val testScheduler = TestScheduler()

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler { testScheduler }
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
    }

    @Test
    fun `Flowable#retryWithDelay does nothing when no exception occurs`() {
        var invocations = 0
        val observer = Unit.asFlowable()
                .doOnNext { invocations++ }
                .retryWithDelay(maxRetries = 2, delayInMillis = 3000)
                .test()
        assertEquals(1, invocations)
        observer.assertValue(Unit).assertComplete().assertNoErrors()
        testScheduler.advanceTimeBy(3000, TimeUnit.MILLISECONDS)
        assertEquals(1, invocations)
        observer.assertValue(Unit).assertComplete().assertNoErrors()
    }

    @Test
    fun `Flowable#retryWithDelay retries observable after given delay when exception occurs`() {
        var invocations = 0
        Unit.asFlowable()
                .doOnNext { invocations++ }
                .flatMap { Exception().asFlowableError<Unit, Exception>() }
                .retryWithDelay(maxRetries = 2, delayInMillis = 3000)
                .test()
        assertEquals(1, invocations)
        testScheduler.advanceTimeBy(2900, TimeUnit.MILLISECONDS)
        assertEquals(1, invocations)
        testScheduler.advanceTimeBy(200, TimeUnit.MILLISECONDS)
        assertEquals(2, invocations)
    }

    @Test
    fun `Flowable#retryWithDelay emits error after exceeding given max retries`() {
        var invocations = 0
        val observer = Unit.asFlowable()
                .doOnNext { invocations++ }
                .flatMap { ComparableException(997).asFlowableError<Unit, Exception>() }
                .retryWithDelay(maxRetries = 2, delayInMillis = 3000)
                .test()
        assertEquals(1, invocations)
        observer.assertNotComplete().assertNoErrors()
        testScheduler.advanceTimeBy(3100, TimeUnit.MILLISECONDS)
        assertEquals(2, invocations)
        observer.assertNotComplete().assertNoErrors()
        testScheduler.advanceTimeBy(3100, TimeUnit.MILLISECONDS)
        assertEquals(3, invocations)
        observer.assertError(ComparableException(997))
        testScheduler.advanceTimeBy(3100, TimeUnit.MILLISECONDS)
        assertEquals(3, invocations)
        observer.assertError(ComparableException(997))
    }

    @Test
    fun `Observable#retryWithDelay does nothing when no exception occurs`() {
        var invocations = 0
        val observer = Unit.asObservable()
                .doOnNext { invocations++ }
                .retryWithDelay(maxRetries = 2, delayInMillis = 3000)
                .test()
        assertEquals(1, invocations)
        observer.assertValue(Unit).assertComplete().assertNoErrors()
        testScheduler.advanceTimeBy(3000, TimeUnit.MILLISECONDS)
        assertEquals(1, invocations)
        observer.assertValue(Unit).assertComplete().assertNoErrors()
    }

    @Test
    fun `Observable#retryWithDelay retries observable after given delay when exception occurs`() {
        var invocations = 0
        Unit.asObservable()
                .doOnNext { invocations++ }
                .flatMap { Exception().asObservableError<Unit, Exception>() }
                .retryWithDelay(maxRetries = 2, delayInMillis = 3000)
                .test()
        assertEquals(1, invocations)
        testScheduler.advanceTimeBy(2900, TimeUnit.MILLISECONDS)
        assertEquals(1, invocations)
        testScheduler.advanceTimeBy(200, TimeUnit.MILLISECONDS)
        assertEquals(2, invocations)
    }

    @Test
    fun `Observable#retryWithDelay emits error after exceeding given max retries`() {
        var invocations = 0
        val observer = Unit.asObservable()
                .doOnNext { invocations++ }
                .flatMap { ComparableException(997).asObservableError<Unit, Exception>() }
                .retryWithDelay(maxRetries = 2, delayInMillis = 3000)
                .test()
        assertEquals(1, invocations)
        observer.assertNotComplete().assertNoErrors()
        testScheduler.advanceTimeBy(3100, TimeUnit.MILLISECONDS)
        assertEquals(2, invocations)
        observer.assertNotComplete().assertNoErrors()
        testScheduler.advanceTimeBy(3100, TimeUnit.MILLISECONDS)
        assertEquals(3, invocations)
        observer.assertError(ComparableException(997))
        testScheduler.advanceTimeBy(3100, TimeUnit.MILLISECONDS)
        assertEquals(3, invocations)
        observer.assertError(ComparableException(997))
    }

    @Test
    fun `Maybe#retryWithDelay does nothing when no exception occurs`() {
        var invocations = 0
        val observer = Unit.asMaybe()
                .doOnSuccess { invocations++ }
                .retryWithDelay(maxRetries = 2, delayInMillis = 3000)
                .test()
        assertEquals(1, invocations)
        observer.assertValue(Unit).assertComplete().assertNoErrors()
        testScheduler.advanceTimeBy(3000, TimeUnit.MILLISECONDS)
        assertEquals(1, invocations)
        observer.assertValue(Unit).assertComplete().assertNoErrors()
    }

    @Test
    fun `Maybe#retryWithDelay retries maybe after given delay when exception occurs`() {
        var invocations = 0
        Unit.asMaybe()
                .doOnSuccess { invocations++ }
                .flatMap { Exception().asMaybeError<Unit, Exception>() }
                .retryWithDelay(maxRetries = 2, delayInMillis = 3000)
                .test()
        assertEquals(1, invocations)
        testScheduler.advanceTimeBy(2900, TimeUnit.MILLISECONDS)
        assertEquals(1, invocations)
        testScheduler.advanceTimeBy(200, TimeUnit.MILLISECONDS)
        assertEquals(2, invocations)
    }

    @Test
    fun `Maybe#retryWithDelay emits error after exceeding given max retries`() {
        var invocations = 0
        val observer = Unit.asMaybe()
                .doOnSuccess { invocations++ }
                .flatMap { ComparableException(997).asMaybeError<Unit, Exception>() }
                .retryWithDelay(maxRetries = 2, delayInMillis = 3000)
                .test()
        assertEquals(1, invocations)
        observer.assertNotComplete().assertNoErrors()
        testScheduler.advanceTimeBy(3100, TimeUnit.MILLISECONDS)
        assertEquals(2, invocations)
        observer.assertNotComplete().assertNoErrors()
        testScheduler.advanceTimeBy(3100, TimeUnit.MILLISECONDS)
        assertEquals(3, invocations)
        observer.assertError(ComparableException(997))
        testScheduler.advanceTimeBy(3100, TimeUnit.MILLISECONDS)
        assertEquals(3, invocations)
        observer.assertError(ComparableException(997))
    }

    @Test
    fun `Single#retryWithDelay does nothing when no exception occurs`() {
        var invocations = 0
        val observer = Unit.asSingle()
                .doOnSuccess { invocations++ }
                .retryWithDelay(maxRetries = 2, delayInMillis = 3000)
                .test()
        assertEquals(1, invocations)
        observer.assertValue(Unit).assertComplete().assertNoErrors()
        testScheduler.advanceTimeBy(3000, TimeUnit.MILLISECONDS)
        assertEquals(1, invocations)
        observer.assertValue(Unit).assertComplete().assertNoErrors()
    }

    @Test
    fun `Single#retryWithDelay retries single after given delay when exception occurs`() {
        var invocations = 0
        Unit.asSingle()
                .doOnSuccess { invocations++ }
                .flatMap { Exception().asSingleError<Unit, Exception>() }
                .retryWithDelay(maxRetries = 2, delayInMillis = 3000)
                .test()
        assertEquals(1, invocations)
        testScheduler.advanceTimeBy(2900, TimeUnit.MILLISECONDS)
        assertEquals(1, invocations)
        testScheduler.advanceTimeBy(200, TimeUnit.MILLISECONDS)
        assertEquals(2, invocations)
    }

    @Test
    fun `Single#retryWithDelay emits error after exceeding given max retries`() {
        var invocations = 0
        val observer = Unit.asSingle()
                .doOnSuccess { invocations++ }
                .flatMap { ComparableException(997).asSingleError<Unit, Exception>() }
                .retryWithDelay(maxRetries = 2, delayInMillis = 3000)
                .test()
        assertEquals(1, invocations)
        observer.assertNotComplete().assertNoErrors()
        testScheduler.advanceTimeBy(3100, TimeUnit.MILLISECONDS)
        assertEquals(2, invocations)
        observer.assertNotComplete().assertNoErrors()
        testScheduler.advanceTimeBy(3100, TimeUnit.MILLISECONDS)
        assertEquals(3, invocations)
        observer.assertError(ComparableException(997))
        testScheduler.advanceTimeBy(3100, TimeUnit.MILLISECONDS)
        assertEquals(3, invocations)
        observer.assertError(ComparableException(997))
    }

    @Test
    fun `Completable#retryWithDelay does nothing when no exception occurs`() {
        var invocations = 0
        val observer = Unit.asObservable()
                .doOnNext { invocations++ }
                .ignoreElements()
                .retryWithDelay(maxRetries = 2, delayInMillis = 3000)
                .test()
        assertEquals(1, invocations)
        observer.assertComplete().assertNoErrors()
        testScheduler.advanceTimeBy(3000, TimeUnit.MILLISECONDS)
        assertEquals(1, invocations)
        observer.assertComplete().assertNoErrors()
    }

    @Test
    fun `Completable#retryWithDelay retries completable after given delay when exception occurs`() {
        var invocations = 0
        Unit.asObservable()
                .doOnNext { invocations++ }
                .flatMap { Exception().asObservableError<Unit, Exception>() }
                .ignoreElements()
                .retryWithDelay(maxRetries = 2, delayInMillis = 3000)
                .test()
        assertEquals(1, invocations)
        testScheduler.advanceTimeBy(2900, TimeUnit.MILLISECONDS)
        assertEquals(1, invocations)
        testScheduler.advanceTimeBy(200, TimeUnit.MILLISECONDS)
        assertEquals(2, invocations)
    }

    @Test
    fun `Completable#retryWithDelay emits error after exceeding given max retries`() {
        var invocations = 0
        val observer = Unit.asObservable()
                .doOnNext { invocations++ }
                .flatMap { ComparableException(997).asObservableError<Unit, Exception>() }
                .ignoreElements()
                .retryWithDelay(maxRetries = 2, delayInMillis = 3000)
                .test()
        assertEquals(1, invocations)
        observer.assertNotComplete().assertNoErrors()
        testScheduler.advanceTimeBy(3100, TimeUnit.MILLISECONDS)
        assertEquals(2, invocations)
        observer.assertNotComplete().assertNoErrors()
        testScheduler.advanceTimeBy(3100, TimeUnit.MILLISECONDS)
        assertEquals(3, invocations)
        observer.assertError(ComparableException(997))
        testScheduler.advanceTimeBy(3100, TimeUnit.MILLISECONDS)
        assertEquals(3, invocations)
        observer.assertError(ComparableException(997))
    }
}