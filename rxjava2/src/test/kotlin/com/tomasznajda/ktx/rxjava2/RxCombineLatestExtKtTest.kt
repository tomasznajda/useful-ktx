package com.tomasznajda.ktx.rxjava2

import io.reactivex.BackpressureStrategy
import io.reactivex.subjects.PublishSubject
import org.junit.Test

class RxCombineLatestExtKtTest {

    @Test
    fun `combineLatest emits pair of latest flowable events`() {
        val firstSubject = PublishSubject.create<Int>()
        val secondSubject = PublishSubject.create<Int>()
        val observer = combineLatest(firstSubject.toFlowable(BackpressureStrategy.BUFFER),
                                     secondSubject.toFlowable(BackpressureStrategy.BUFFER)).test()
        firstSubject.onNext(10)
        secondSubject.onNext(20)
        observer.assertValues(10 to 20)
        firstSubject.onNext(11)
        observer.assertValues(10 to 20, 11 to 20)
        secondSubject.onNext(21)
        observer.assertValues(10 to 20, 11 to 20, 11 to 21)
    }

    @Test
    fun `combineLatest emits triple of latest flowable events`() {
        val firstSubject = PublishSubject.create<Int>()
        val secondSubject = PublishSubject.create<Int>()
        val thirdSubject = PublishSubject.create<Int>()
        val observer = combineLatest(firstSubject.toFlowable(BackpressureStrategy.BUFFER),
                                     secondSubject.toFlowable(BackpressureStrategy.BUFFER),
                                     thirdSubject.toFlowable(BackpressureStrategy.BUFFER)).test()
        firstSubject.onNext(10)
        secondSubject.onNext(20)
        thirdSubject.onNext(30)
        observer.assertValues(Triple(10, 20, 30))
        firstSubject.onNext(11)
        observer.assertValues(Triple(10, 20, 30), Triple(11, 20, 30))
        secondSubject.onNext(21)
        observer.assertValues(Triple(10, 20, 30), Triple(11, 20, 30), Triple(11, 21, 30))
        thirdSubject.onNext(31)
        observer.assertValues(Triple(10, 20, 30), Triple(11, 20, 30), Triple(11, 21, 30), Triple(11, 21, 31))
    }

    @Test
    fun `combineLatest emits pair of latest observable events`() {
        val firstSubject = PublishSubject.create<Int>()
        val secondSubject = PublishSubject.create<Int>()
        val observer = combineLatest(firstSubject, secondSubject).test()
        firstSubject.onNext(10)
        secondSubject.onNext(20)
        observer.assertValues(10 to 20)
        firstSubject.onNext(11)
        observer.assertValues(10 to 20, 11 to 20)
        secondSubject.onNext(21)
        observer.assertValues(10 to 20, 11 to 20, 11 to 21)
    }

    @Test
    fun `combineLatest emits triple of latest observable events`() {
        val firstSubject = PublishSubject.create<Int>()
        val secondSubject = PublishSubject.create<Int>()
        val thirdSubject = PublishSubject.create<Int>()
        val observer = combineLatest(firstSubject, secondSubject, thirdSubject).test()
        firstSubject.onNext(10)
        secondSubject.onNext(20)
        thirdSubject.onNext(30)
        observer.assertValues(Triple(10, 20, 30))
        firstSubject.onNext(11)
        observer.assertValues(Triple(10, 20, 30), Triple(11, 20, 30))
        secondSubject.onNext(21)
        observer.assertValues(Triple(10, 20, 30), Triple(11, 20, 30), Triple(11, 21, 30))
        thirdSubject.onNext(31)
        observer.assertValues(Triple(10, 20, 30), Triple(11, 20, 30), Triple(11, 21, 30), Triple(11, 21, 31))
    }
}