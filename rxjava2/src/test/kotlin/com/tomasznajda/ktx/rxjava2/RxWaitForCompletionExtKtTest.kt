package com.tomasznajda.ktx.rxjava2

import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.CompletableSubject
import org.junit.Test

class RxWaitForCompletionExtKtTest {

    @Test
    fun `Single#waitForCompletion emits item when completable completes`() {
        val completable = CompletableSubject.create()
        val observer = Single.just(1).waitForCompletion { completable }.test()
        completable.onComplete()
        observer.assertValue(1)
    }

    @Test
    fun `Single#waitForCompletion does not emit item when completable does not complete`() {
        val completable = CompletableSubject.create()
        val observer = Single.just(1).waitForCompletion { completable }.test()
        observer.assertNoValues()
    }

    @Test
    fun `Maybe#waitForCompletion emits item when completable completes`() {
        val completable = CompletableSubject.create()
        val observer = Maybe.just(1).waitForCompletion { completable }.test()
        completable.onComplete()
        observer.assertValue(1)
    }

    @Test
    fun `Maybe#waitForCompletion does not emit item when completable does not complete`() {
        val completable = CompletableSubject.create()
        val observer = Maybe.just(1).waitForCompletion { completable }.test()
        observer.assertNoValues()
    }

    @Test
    fun `Observable#waitForCompletion emits item when completable completes`() {
        val completable = CompletableSubject.create()
        val observer = Observable.just(1).waitForCompletion { completable }.test()
        completable.onComplete()
        observer.assertValue(1)
    }

    @Test
    fun `Observable#waitForCompletion does not emit item when completable does not complete`() {
        val completable = CompletableSubject.create()
        val observer = Observable.just(1).waitForCompletion { completable }.test()
        observer.assertNoValues()
    }

    @Test
    fun `Flowable#waitForCompletion emits item when completable completes`() {
        val completable = CompletableSubject.create()
        val observer = Flowable.just(1).waitForCompletion { completable }.test()
        completable.onComplete()
        observer.assertValue(1)
    }

    @Test
    fun `Flowable#waitForCompletion does not emit item when completable does not complete`() {
        val completable = CompletableSubject.create()
        val observer = Flowable.just(1).waitForCompletion { completable }.test()
        observer.assertNoValues()
    }

    @Test
    fun `Flowable#waitForCompletion does not keep the order`() {
        val completables = (0..2).map { CompletableSubject.create() }
        val observer = Flowable.fromIterable(0..2).waitForCompletion { completables[it] }.test()
        completables[1].onComplete()
        completables[2].onComplete()
        completables[0].onComplete()
        observer.assertValues(1, 2, 0)
    }

    @Test
    fun `Flowable#concatWaitForCompletion keeps the order`() {
        val completables = (0..2).map { CompletableSubject.create() }
        val observer = Flowable.fromIterable(0..2).concatWaitForCompletion { completables[it] }.test()
        completables[1].onComplete()
        completables[2].onComplete()
        completables[0].onComplete()
        observer.assertValues(0, 1, 2)
    }

    @Test
    fun `Flowable#switchWaitForCompletion skips uncompleted events`() {
        val completables = (0..2).map { CompletableSubject.create() }
        val observer = Flowable.fromIterable(0..2).switchWaitForCompletion { completables[it] }.test()
        completables[1].onComplete()
        completables[2].onComplete()
        completables[0].onComplete()
        observer.assertValues(2)
    }

    @Test
    fun `Observable#waitForCompletion does not keep the order`() {
        val completables = (0..2).map { CompletableSubject.create() }
        val observer = Observable.fromIterable(0..2).waitForCompletion { completables[it] }.test()
        completables[1].onComplete()
        completables[2].onComplete()
        completables[0].onComplete()
        observer.assertValues(1, 2, 0)
    }

    @Test
    fun `Observable#concatWaitForCompletion keeps the order`() {
        val completables = (0..2).map { CompletableSubject.create() }
        val observer = Observable.fromIterable(0..2).concatWaitForCompletion { completables[it] }.test()
        completables[1].onComplete()
        completables[2].onComplete()
        completables[0].onComplete()
        observer.assertValues(0, 1, 2)
    }

    @Test
    fun `Observable#switchWaitForCompletion skips uncompleted events`() {
        val completables = (0..2).map { CompletableSubject.create() }
        val observer = Observable.fromIterable(0..2).switchWaitForCompletion { completables[it] }.test()
        completables[1].onComplete()
        completables[2].onComplete()
        completables[0].onComplete()
        observer.assertValues(2)
    }
}