package com.tomasznajda.ktx.rxjava2

import io.reactivex.BackpressureStrategy
import io.reactivex.subjects.MaybeSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.SingleSubject
import org.junit.Test

class RxMergeExtKtTest {

    @Test
    fun `merge emits event when any of two flowables emits event`() {
        val firstSubject = PublishSubject.create<Int>()
        val secondSubject = PublishSubject.create<Int>()
        val observer = merge(firstSubject.toFlowable(BackpressureStrategy.BUFFER),
                             secondSubject.toFlowable(BackpressureStrategy.BUFFER)).test()
        firstSubject.onNext(10)
        observer.assertValues(10)
        secondSubject.onNext(20)
        observer.assertValues(10, 20)
    }

    @Test
    fun `merge emits event when any of two observables emits event`() {
        val firstSubject = PublishSubject.create<Int>()
        val secondSubject = PublishSubject.create<Int>()
        val observer = merge(firstSubject, secondSubject).test()
        firstSubject.onNext(10)
        observer.assertValues(10)
        secondSubject.onNext(20)
        observer.assertValues(10, 20)
    }

    @Test
    fun `merge emits event when any of two maybes emits event`() {
        val firstSubject = MaybeSubject.create<Int>()
        val secondSubject = MaybeSubject.create<Int>()
        val observer = merge(firstSubject, secondSubject).test()
        firstSubject.onSuccess(10)
        observer.assertValues(10)
        secondSubject.onSuccess(20)
        observer.assertValues(10, 20)
    }

    @Test
    fun `merge emits event when any of two singles emits event`() {
        val firstSubject = SingleSubject.create<Int>()
        val secondSubject = SingleSubject.create<Int>()
        val observer = merge(firstSubject, secondSubject).test()
        firstSubject.onSuccess(10)
        observer.assertValues(10)
        secondSubject.onSuccess(20)
        observer.assertValues(10, 20)
    }

    @Test
    fun `merge emits event when any of three flowables emits event`() {
        val firstSubject = PublishSubject.create<Int>()
        val secondSubject = PublishSubject.create<Int>()
        val thirdSubject = PublishSubject.create<Int>()
        val observer = merge(firstSubject.toFlowable(BackpressureStrategy.BUFFER),
                             secondSubject.toFlowable(BackpressureStrategy.BUFFER),
                             thirdSubject.toFlowable(BackpressureStrategy.BUFFER)).test()
        firstSubject.onNext(10)
        observer.assertValues(10)
        secondSubject.onNext(20)
        observer.assertValues(10, 20)
        thirdSubject.onNext(30)
        observer.assertValues(10, 20, 30)
    }

    @Test
    fun `merge emits event when any of three observables emits event`() {
        val firstSubject = PublishSubject.create<Int>()
        val secondSubject = PublishSubject.create<Int>()
        val thirdSubject = PublishSubject.create<Int>()
        val observer = merge(firstSubject, secondSubject, thirdSubject).test()
        firstSubject.onNext(10)
        observer.assertValues(10)
        secondSubject.onNext(20)
        observer.assertValues(10, 20)
        thirdSubject.onNext(30)
        observer.assertValues(10, 20, 30)
    }

    @Test
    fun `merge emits event when any of three maybes emits event`() {
        val firstSubject = MaybeSubject.create<Int>()
        val secondSubject = MaybeSubject.create<Int>()
        val thirdSubject = MaybeSubject.create<Int>()
        val observer = merge(firstSubject, secondSubject, thirdSubject).test()
        firstSubject.onSuccess(10)
        observer.assertValues(10)
        secondSubject.onSuccess(20)
        observer.assertValues(10, 20)
        thirdSubject.onSuccess(30)
        observer.assertValues(10, 20, 30)
    }

    @Test
    fun `merge emits event when any of three singles emits event`() {
        val firstSubject = SingleSubject.create<Int>()
        val secondSubject = SingleSubject.create<Int>()
        val thirdSubject = SingleSubject.create<Int>()
        val observer = merge(firstSubject, secondSubject, thirdSubject).test()
        firstSubject.onSuccess(10)
        observer.assertValues(10)
        secondSubject.onSuccess(20)
        observer.assertValues(10, 20)
        thirdSubject.onSuccess(30)
        observer.assertValues(10, 20, 30)
    }
}