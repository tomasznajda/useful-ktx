package com.tomasznajda.ktx.rxjava2

import io.reactivex.BackpressureStrategy
import io.reactivex.subjects.CompletableSubject
import io.reactivex.subjects.MaybeSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.SingleSubject
import org.junit.Test

class RxZipExtKtTest {

    @Test
    fun `zip emits pair of items when both flowables emits items`() {
        val firstSubject = PublishSubject.create<Int>()
        val secondSubject = PublishSubject.create<Int>()
        val observer = zip(firstSubject.toFlowable(BackpressureStrategy.BUFFER),
                           secondSubject.toFlowable(BackpressureStrategy.BUFFER)).test()
        firstSubject.onNext(1)
        secondSubject.onNext(3)
        observer.assertValue(1 to 3)
    }

    @Test
    fun `zip does not emit any item when only first flowable emits item`() {
        val firstSubject = PublishSubject.create<Int>()
        val secondSubject = PublishSubject.create<Int>()
        val observer = zip(firstSubject.toFlowable(BackpressureStrategy.BUFFER),
                           secondSubject.toFlowable(BackpressureStrategy.BUFFER)).test()
        firstSubject.onNext(1)
        observer.assertNoValues()
    }

    @Test
    fun `zip does not emit any item when only second flowable emits item`() {
        val firstSubject = PublishSubject.create<Int>()
        val secondSubject = PublishSubject.create<Int>()
        val observer = zip(firstSubject.toFlowable(BackpressureStrategy.BUFFER),
                           secondSubject.toFlowable(BackpressureStrategy.BUFFER)).test()
        secondSubject.onNext(3)
        observer.assertNoValues()
    }

    @Test
    fun `zip emits pair of items when both observables emits items`() {
        val firstSubject = PublishSubject.create<Int>()
        val secondSubject = PublishSubject.create<Int>()
        val observer = zip(firstSubject, secondSubject).test()
        firstSubject.onNext(1)
        secondSubject.onNext(3)
        observer.assertValue(1 to 3)
    }

    @Test
    fun `zip does not emit any item when only first observable emits item`() {
        val firstSubject = PublishSubject.create<Int>()
        val secondSubject = PublishSubject.create<Int>()
        val observer = zip(firstSubject, secondSubject).test()
        firstSubject.onNext(1)
        observer.assertNoValues()
    }

    @Test
    fun `zip does not emit any item when only second observable emits item`() {
        val firstSubject = PublishSubject.create<Int>()
        val secondSubject = PublishSubject.create<Int>()
        val observer = zip(firstSubject, secondSubject).test()
        secondSubject.onNext(3)
        observer.assertNoValues()
    }

    @Test
    fun `zip emits pair of items when both maybes emits items`() {
        val firstSubject = MaybeSubject.create<Int>()
        val secondSubject = MaybeSubject.create<Int>()
        val observer = zip(firstSubject, secondSubject).test()
        firstSubject.onSuccess(1)
        secondSubject.onSuccess(3)
        observer.assertValue(1 to 3)
    }

    @Test
    fun `zip does not emit any item when only first maybe emits item`() {
        val firstSubject = MaybeSubject.create<Int>()
        val secondSubject = MaybeSubject.create<Int>()
        val observer = zip(firstSubject, secondSubject).test()
        firstSubject.onSuccess(1)
        observer.assertNoValues()
    }

    @Test
    fun `zip does not emit any item when only second maybe emits item`() {
        val firstSubject = MaybeSubject.create<Int>()
        val secondSubject = MaybeSubject.create<Int>()
        val observer = zip(firstSubject, secondSubject).test()
        secondSubject.onSuccess(3)
        observer.assertNoValues()
    }

    @Test
    fun `zip emits pair of items when both singles emits items`() {
        val firstSubject = SingleSubject.create<Int>()
        val secondSubject = SingleSubject.create<Int>()
        val observer = zip(firstSubject, secondSubject).test()
        firstSubject.onSuccess(1)
        secondSubject.onSuccess(3)
        observer.assertValue(1 to 3)
    }

    @Test
    fun `zip does not emit any item when only first single emits item`() {
        val firstSubject = SingleSubject.create<Int>()
        val secondSubject = SingleSubject.create<Int>()
        val observer = zip(firstSubject, secondSubject).test()
        firstSubject.onSuccess(1)
        observer.assertNoValues()
    }

    @Test
    fun `zip does not emit any item when only second single emits item`() {
        val firstSubject = SingleSubject.create<Int>()
        val secondSubject = SingleSubject.create<Int>()
        val observer = zip(firstSubject, secondSubject).test()
        secondSubject.onSuccess(3)
        observer.assertNoValues()
    }

    @Test
    fun `zip completes when both completables completes`() {
        val firstSubject = CompletableSubject.create()
        val secondSubject = CompletableSubject.create()
        val observer = zip(firstSubject, secondSubject).test()
        firstSubject.onComplete()
        secondSubject.onComplete()
        observer.assertComplete()
    }

    @Test
    fun `zip does not complete when only first completable completes`() {
        val firstSubject = CompletableSubject.create()
        val secondSubject = CompletableSubject.create()
        val observer = zip(firstSubject, secondSubject).test()
        firstSubject.onComplete()
        observer.assertNotComplete()
    }

    @Test
    fun `zip does not complete when only second completable completes`() {
        val firstSubject = CompletableSubject.create()
        val secondSubject = CompletableSubject.create()
        val observer = zip(firstSubject, secondSubject).test()
        secondSubject.onComplete()
        observer.assertNotComplete()
    }

    @Test
    fun `zipWith emits pair of items when both flowables emits items`() {
        val firstSubject = PublishSubject.create<Int>()
        val secondSubject = PublishSubject.create<Int>()
        val observer = firstSubject.toFlowable(BackpressureStrategy.BUFFER)
                .zipWith(secondSubject.toFlowable(BackpressureStrategy.BUFFER)).test()
        firstSubject.onNext(1)
        secondSubject.onNext(3)
        observer.assertValue(1 to 3)
    }

    @Test
    fun `zipWith does not emit any item when only first flowable emits item`() {
        val firstSubject = PublishSubject.create<Int>()
        val secondSubject = PublishSubject.create<Int>()
        val observer = firstSubject.toFlowable(BackpressureStrategy.BUFFER)
                .zipWith(secondSubject.toFlowable(BackpressureStrategy.BUFFER)).test()
        firstSubject.onNext(1)
        observer.assertNoValues()
    }

    @Test
    fun `zipWith does not emit any item when only second flowable emits item`() {
        val firstSubject = PublishSubject.create<Int>()
        val secondSubject = PublishSubject.create<Int>()
        val observer = firstSubject.toFlowable(BackpressureStrategy.BUFFER)
                .zipWith(secondSubject.toFlowable(BackpressureStrategy.BUFFER)).test()
        secondSubject.onNext(3)
        observer.assertNoValues()
    }

    @Test
    fun `zipWith emits pair of items when both observables emits items`() {
        val firstSubject = PublishSubject.create<Int>()
        val secondSubject = PublishSubject.create<Int>()
        val observer = firstSubject.zipWith(secondSubject).test()
        firstSubject.onNext(1)
        secondSubject.onNext(3)
        observer.assertValue(1 to 3)
    }

    @Test
    fun `zipWith does not emit any item when only first observable emits item`() {
        val firstSubject = PublishSubject.create<Int>()
        val secondSubject = PublishSubject.create<Int>()
        val observer = firstSubject.zipWith(secondSubject).test()
        firstSubject.onNext(1)
        observer.assertNoValues()
    }

    @Test
    fun `zipWith does not emit any item when only second observable emits item`() {
        val firstSubject = PublishSubject.create<Int>()
        val secondSubject = PublishSubject.create<Int>()
        val observer = firstSubject.zipWith(secondSubject).test()
        secondSubject.onNext(3)
        observer.assertNoValues()
    }

    @Test
    fun `zipWith emits pair of items when both maybes emits items`() {
        val firstSubject = MaybeSubject.create<Int>()
        val secondSubject = MaybeSubject.create<Int>()
        val observer = firstSubject.zipWith(secondSubject).test()
        firstSubject.onSuccess(1)
        secondSubject.onSuccess(3)
        observer.assertValue(1 to 3)
    }

    @Test
    fun `zipWith does not emit any item when only first maybe emits item`() {
        val firstSubject = MaybeSubject.create<Int>()
        val secondSubject = MaybeSubject.create<Int>()
        val observer = firstSubject.zipWith(secondSubject).test()
        firstSubject.onSuccess(1)
        observer.assertNoValues()
    }

    @Test
    fun `zipWith does not emit any item when only second maybe emits item`() {
        val firstSubject = MaybeSubject.create<Int>()
        val secondSubject = MaybeSubject.create<Int>()
        val observer = firstSubject.zipWith(secondSubject).test()
        secondSubject.onSuccess(3)
        observer.assertNoValues()
    }

    @Test
    fun `zipWith emits pair of items when both singles emits items`() {
        val firstSubject = SingleSubject.create<Int>()
        val secondSubject = SingleSubject.create<Int>()
        val observer = firstSubject.zipWith(secondSubject).test()
        firstSubject.onSuccess(1)
        secondSubject.onSuccess(3)
        observer.assertValue(1 to 3)
    }

    @Test
    fun `zipWith does not emit any item when only first single emits item`() {
        val firstSubject = SingleSubject.create<Int>()
        val secondSubject = SingleSubject.create<Int>()
        val observer = firstSubject.zipWith(secondSubject).test()
        firstSubject.onSuccess(1)
        observer.assertNoValues()
    }

    @Test
    fun `zipWith does not emit any item when only second single emits item`() {
        val firstSubject = SingleSubject.create<Int>()
        val secondSubject = SingleSubject.create<Int>()
        val observer = firstSubject.zipWith(secondSubject).test()
        secondSubject.onSuccess(3)
        observer.assertNoValues()
    }
}