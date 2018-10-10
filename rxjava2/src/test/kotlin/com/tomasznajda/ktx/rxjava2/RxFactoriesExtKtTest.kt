package com.tomasznajda.ktx.rxjava2

import com.tomasznajda.ktx.junit.assertIsInstanceOf
import com.tomasznajda.ktx.rxjava2._util.ComparableException
import io.reactivex.*
import org.junit.Test

class RxFactoriesExtKtTest {

    @Test
    fun `asFlowable creates Flowable that emits given object once when object is not null`() {
        val flowable = Unit.asFlowable()
        flowable.assertIsInstanceOf(Flowable::class)
        val observer = flowable.test()
        observer.assertValues(Unit)
        observer.assertNoErrors()
    }

    @Test
    fun `asFlowable creates empty Flowable when given object is null`() {
        val flowable = null.asFlowable<Unit>()
        flowable.assertIsInstanceOf(Flowable::class)
        val observer = flowable.test()
        observer.assertNoValues()
        observer.assertNoErrors()
    }

    @Test
    fun `asObservable creates Observable that emits given object once when object is not null`() {
        val observable = Unit.asObservable()
        observable.assertIsInstanceOf(Observable::class)
        val observer = observable.test()
        observer.assertValues(Unit)
        observer.assertNoErrors()
    }

    @Test
    fun `asObservable creates empty Observable when given object is null`() {
        val observable = null.asObservable<Unit>()
        observable.assertIsInstanceOf(Observable::class)
        val observer = observable.test()
        observer.assertNoValues()
        observer.assertNoErrors()
    }

    @Test
    fun `asMaybe creates Maybe that emits given object once when object is not null`() {
        val maybe = Unit.asMaybe()
        maybe.assertIsInstanceOf(Maybe::class)
        val observer = maybe.test()
        observer.assertValues(Unit)
        observer.assertNoErrors()
    }

    @Test
    fun `asMaybe creates empty Maybe when given object is null`() {
        val maybe = null.asMaybe<Unit>()
        maybe.assertIsInstanceOf(Maybe::class)
        val observer = maybe.test()
        observer.assertNoValues()
        observer.assertNoErrors()
    }

    @Test
    fun `asSingle creates Single that emits given object once`() {
        val single = Unit.asSingle()
        single.assertIsInstanceOf(Single::class)
        val observer = single.test()
        observer.assertValues(Unit)
        observer.assertNoErrors()
    }

    @Test
    fun `asFlowableError creates Flowable that emits given error`() {
        val exception = ComparableException()
        val flowable = exception.asFlowableError<Unit, ComparableException>()
        flowable.assertIsInstanceOf(Flowable::class)
        val observer = flowable.test()
        observer.assertNoValues()
        observer.assertError(exception.copy())
    }

    @Test
    fun `asObservableError creates Observable that emits given error`() {
        val exception = ComparableException()
        val observable = exception.asObservableError<Unit, ComparableException>()
        observable.assertIsInstanceOf(Observable::class)
        val observer = observable.test()
        observer.assertNoValues()
        observer.assertError(exception.copy())
    }

    @Test
    fun `asSingleError creates Single that emits given error`() {
        val exception = ComparableException()
        val single = exception.asSingleError<Unit, ComparableException>()
        single.assertIsInstanceOf(Single::class)
        val observer = single.test()
        observer.assertNoValues()
        observer.assertError(exception.copy())
    }

    @Test
    fun `asMaybeError creates Maybe that emits given error`() {
        val exception = ComparableException()
        val maybe = exception.asMaybeError<Unit, ComparableException>()
        maybe.assertIsInstanceOf(Maybe::class)
        val observer = maybe.test()
        observer.assertNoValues()
        observer.assertError(exception.copy())
    }

    @Test
    fun `asCompletableError creates Completable that emits given error`() {
        val exception = ComparableException()
        val completable = exception.asCompletableError()
        completable.assertIsInstanceOf(Completable::class)
        val observer = completable.test()
        observer.assertNoValues()
        observer.assertError(exception.copy())
    }
}