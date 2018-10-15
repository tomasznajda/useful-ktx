package com.tomasznajda.ktx.rxjava2

import com.tomasznajda.ktx.junit.assertEquals
import com.tomasznajda.ktx.rxjava2._util.ComparableException
import io.reactivex.BackpressureStrategy
import io.reactivex.exceptions.OnErrorNotImplementedException
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.subjects.PublishSubject
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class RxRetrySubscribeExtKtTest {

    class TestConsumer<T>(private val action: (T) -> Unit = {}) {
        val events = mutableListOf<T>()
        val consumer: (T) -> Unit = { events.add(it); action(it) }
    }
    val defaultErrorConsumer = TestConsumer<Throwable>()

    @Before
    fun setUp() {
        RxJavaPlugins.setErrorHandler { defaultErrorConsumer.consumer(it) }
    }

    @Test
    fun `Flowable#retrySubscribe resubscribes when an error occurs in the stream`() {
        val subject = PublishSubject.create<Int>()
        val eventConsumer = TestConsumer<Int>()
        subject
                .toFlowable(BackpressureStrategy.BUFFER)
                .doOnNext { if (it > 100) throw ComparableException(it) }
                .retrySubscribe(onNext = eventConsumer.consumer)

        subject.onNext(1)
        assertEquals(expected = listOf(1),
                     actual = eventConsumer.events)
        subject.onNext(666)
        subject.onNext(2)
        assertEquals(expected = listOf(1, 2),
                     actual = eventConsumer.events)
        subject.onNext(3)
        assertEquals(expected = listOf(1, 2, 3),
                     actual = eventConsumer.events)
    }

    @Test
    fun `Flowable#retrySubscribe invokes onError consumer when an error occurs in the stream`() {
        val subject = PublishSubject.create<Int>()
        val errorConsumer = TestConsumer<Throwable>()
        subject
                .toFlowable(BackpressureStrategy.BUFFER)
                .doOnNext { if (it > 100) throw ComparableException(it) }
                .retrySubscribe(onError = errorConsumer.consumer)

        subject.onNext(1)
        assertEquals(expected = emptyList<Int>(),
                     actual = errorConsumer.events)
        subject.onNext(666)
        assertEquals(expected = listOf(ComparableException(666)),
                     actual = errorConsumer.events)
        subject.onNext(2)
        assertEquals(expected = listOf(ComparableException(666)),
                     actual = errorConsumer.events)
        subject.onNext(999)
        assertEquals(expected = listOf(ComparableException(666), ComparableException(999)),
                     actual = errorConsumer.events)
    }

    @Test
    fun `Flowable#retrySubscribe resubscribes when an error occurs in the onNext consumer`() {
        val subject = PublishSubject.create<Int>()
        val eventConsumer = TestConsumer<Int> { if (it > 100) throw ComparableException(it) }
        subject
                .toFlowable(BackpressureStrategy.BUFFER)
                .retrySubscribe(onNext = eventConsumer.consumer)
        subject.onNext(1)
        assertEquals(expected = listOf(1),
                     actual = eventConsumer.events)
        subject.onNext(997)
        assertEquals(expected = listOf(1, 997),
                     actual = eventConsumer.events)
        subject.onNext(2)
        assertEquals(expected = listOf(1, 997, 2),
                     actual = eventConsumer.events)
        subject.onNext(3)
        assertEquals(expected = listOf(1, 997, 2, 3),
                     actual = eventConsumer.events)
    }

    @Test
    fun `Flowable#retrySubscribe invokes onError consumer when an error occurs in the onNext consumer`() {
        val subject = PublishSubject.create<Int>()
        val errorConsumer = TestConsumer<Throwable>()
        subject
                .toFlowable(BackpressureStrategy.BUFFER)
                .retrySubscribe(
                        onNext = TestConsumer<Int> { if (it > 100) throw ComparableException(it) }.consumer,
                        onError = errorConsumer.consumer)

        subject.onNext(1)
        assertEquals(expected = emptyList<Int>(),
                     actual = errorConsumer.events)
        subject.onNext(997)
        assertEquals(expected = listOf(ComparableException(997)),
                     actual = errorConsumer.events)
        subject.onNext(1)
        assertEquals(expected = listOf(ComparableException(997)),
                     actual = errorConsumer.events)
        subject.onNext(998)
        assertEquals(expected = listOf(ComparableException(997), ComparableException(998)),
                     actual = errorConsumer.events)
    }

    @Test
    fun `Flowable#retrySubscribe invokes onError consumer when an error occurs in the onComplete consumer`() {
        var shouldThrow = true
        val subject = PublishSubject.create<Int>()
        val errorConsumer = TestConsumer<Throwable>()
        val completeConsumer = TestConsumer<Any> {
            if (shouldThrow) {
                shouldThrow = false
                throw ComparableException(997)
            }
        }
        subject
                .toFlowable(BackpressureStrategy.BUFFER)
                .retrySubscribe(
                        onError = errorConsumer.consumer,
                        onComplete = { completeConsumer.consumer(Unit) })

        subject.onNext(997)
        assertEquals(expected = emptyList<Int>(),
                     actual = errorConsumer.events)
        subject.onComplete()
        assertEquals(expected = listOf(ComparableException(997)),
                     actual = errorConsumer.events)
    }

    @Test
    fun `Flowable#retrySubscribe throws on error not implemented exception when onError consumer is not overrode`() {
        val subject = PublishSubject.create<Int>()
        subject
                .toFlowable(BackpressureStrategy.BUFFER)
                .doOnNext { if (it > 100) throw ComparableException(it) }
                .retrySubscribe()
        subject.onNext(997)
        assertTrue(defaultErrorConsumer.events[0] is OnErrorNotImplementedException)
    }

    @Test
    fun `Observable#retrySubscribe resubscribes when an error occurs in the stream`() {
        val subject = PublishSubject.create<Int>()
        val eventConsumer = TestConsumer<Int>()
        subject
                .doOnNext { if (it > 100) throw ComparableException(it) }
                .retrySubscribe(onNext = eventConsumer.consumer)

        subject.onNext(1)
        assertEquals(expected = listOf(1),
                     actual = eventConsumer.events)
        subject.onNext(666)
        subject.onNext(2)
        assertEquals(expected = listOf(1, 2),
                     actual = eventConsumer.events)
        subject.onNext(3)
        assertEquals(expected = listOf(1, 2, 3),
                     actual = eventConsumer.events)
    }

    @Test
    fun `Observable#retrySubscribe invokes onError consumer when an error occurs in the stream`() {
        val subject = PublishSubject.create<Int>()
        val errorConsumer = TestConsumer<Throwable>()
        subject
                .doOnNext { if (it > 100) throw ComparableException(it) }
                .retrySubscribe(onError = errorConsumer.consumer)

        subject.onNext(1)
        assertEquals(expected = emptyList<Int>(),
                     actual = errorConsumer.events)
        subject.onNext(666)
        assertEquals(expected = listOf(ComparableException(666)),
                     actual = errorConsumer.events)
        subject.onNext(2)
        assertEquals(expected = listOf(ComparableException(666)),
                     actual = errorConsumer.events)
        subject.onNext(999)
        assertEquals(expected = listOf(ComparableException(666), ComparableException(999)),
                     actual = errorConsumer.events)
    }

    @Test
    fun `Observable#retrySubscribe resubscribes when an error occurs in the onNext consumer`() {
        val subject = PublishSubject.create<Int>()
        val eventConsumer = TestConsumer<Int> { if (it > 100) throw ComparableException(it) }
        subject
                .retrySubscribe(onNext = eventConsumer.consumer)

        subject.onNext(1)
        assertEquals(expected = listOf(1),
                     actual = eventConsumer.events)
        subject.onNext(997)
        assertEquals(expected = listOf(1, 997),
                     actual = eventConsumer.events)
        subject.onNext(2)
        assertEquals(expected = listOf(1, 997, 2),
                     actual = eventConsumer.events)
        subject.onNext(3)
        assertEquals(expected = listOf(1, 997, 2, 3),
                     actual = eventConsumer.events)
    }

    @Test
    fun `Observable#retrySubscribe invokes onError consumer when an error occurs in the onNext consumer`() {
        val subject = PublishSubject.create<Int>()
        val errorConsumer = TestConsumer<Throwable>()
        subject
                .retrySubscribe(
                        onNext = TestConsumer<Int> { if (it > 100) throw ComparableException(it) }.consumer,
                        onError = errorConsumer.consumer)

        subject.onNext(1)
        assertEquals(expected = emptyList<Int>(),
                     actual = errorConsumer.events)
        subject.onNext(997)
        assertEquals(expected = listOf(ComparableException(997)),
                     actual = errorConsumer.events)
        subject.onNext(1)
        assertEquals(expected = listOf(ComparableException(997)),
                     actual = errorConsumer.events)
        subject.onNext(998)
        assertEquals(expected = listOf(ComparableException(997), ComparableException(998)),
                     actual = errorConsumer.events)
    }

    @Test
    fun `Observable#retrySubscribe invokes onError consumer when an error occurs in the onComplete consumer`() {
        var shouldThrow = true
        val subject = PublishSubject.create<Int>()
        val errorConsumer = TestConsumer<Throwable>()
        val completeConsumer = TestConsumer<Any> {
            if (shouldThrow) {
                shouldThrow = false
                throw ComparableException(997)
            }
        }
        subject
                .retrySubscribe(
                        onError = errorConsumer.consumer,
                        onComplete = { completeConsumer.consumer(Unit) })

        subject.onNext(997)
        assertEquals(expected = emptyList<Int>(),
                     actual = errorConsumer.events)
        subject.onComplete()
        assertEquals(expected = listOf(ComparableException(997)),
                     actual = errorConsumer.events)
    }

    @Test
    fun `Observable#retrySubscribe throws on error not implemented exception when onError consumer is not overrode`() {
        val subject = PublishSubject.create<Int>()
        subject
                .doOnNext { if (it > 100) throw ComparableException(it) }
                .retrySubscribe()
        subject.onNext(997)
        assertTrue(defaultErrorConsumer.events[0] is OnErrorNotImplementedException)
    }

    @Test
    fun `Maybe#retrySubscribe resubscribes when an error occurs in the stream`() {
        val subject = PublishSubject.create<Int>()
        val eventConsumer = TestConsumer<Int>()
        subject
                .firstElement()
                .doOnSuccess { if (it > 100) throw ComparableException(it) }
                .retrySubscribe(onSuccess = eventConsumer.consumer)

        subject.onNext(666)
        subject.onNext(1)
        assertEquals(expected = listOf(1),
                     actual = eventConsumer.events)
    }

    @Test
    fun `Maybe#retrySubscribe invokes onError consumer when an error occurs in the stream`() {
        val subject = PublishSubject.create<Int>()
        val errorConsumer = TestConsumer<Throwable>()
        subject
                .firstElement()
                .doOnSuccess { if (it > 100) throw ComparableException(it) }
                .retrySubscribe(onError = errorConsumer.consumer)

        subject.onNext(666)
        assertEquals(expected = listOf(ComparableException(666)),
                     actual = errorConsumer.events)
        subject.onNext(1)
        assertEquals(expected = listOf(ComparableException(666)),
                     actual = errorConsumer.events)
    }

    @Test
    fun `Maybe#retrySubscribe resubscribes when an error occurs in the onSuccess consumer`() {
        val subject = PublishSubject.create<Int>()
        val eventConsumer = TestConsumer<Int> { if (it > 100) throw ComparableException(it) }
        subject
                .firstElement()
                .retrySubscribe(onSuccess = eventConsumer.consumer)
        subject.onNext(997)
        assertEquals(expected = listOf(997),
                     actual = eventConsumer.events)
        subject.onNext(1)
        assertEquals(expected = listOf(997, 1),
                     actual = eventConsumer.events)
    }

    @Test
    fun `Maybe#retrySubscribe invokes onError consumer when an error occurs in the onSuccess consumer`() {
        val subject = PublishSubject.create<Int>()
        val errorConsumer = TestConsumer<Throwable>()
        subject
                .firstElement()
                .retrySubscribe(
                        onSuccess = TestConsumer<Int> { if (it > 100) throw ComparableException(it) }.consumer,
                        onError = errorConsumer.consumer)

        subject.onNext(997)
        assertEquals(expected = listOf(ComparableException(997)),
                     actual = errorConsumer.events)
        subject.onNext(1)
        assertEquals(expected = listOf(ComparableException(997)),
                     actual = errorConsumer.events)
    }

    @Test
    fun `Maybe#retrySubscribe invokes onError consumer when an error occurs in the onComplete consumer`() {
        var shouldThrow = true
        val subject = PublishSubject.create<Int>()
        val errorConsumer = TestConsumer<Throwable>()
        val completeConsumer = TestConsumer<Any> {
            if (shouldThrow) {
                shouldThrow = false
                throw ComparableException(997)
            }
        }
        subject
                .firstElement()
                .retrySubscribe(
                        onError = errorConsumer.consumer,
                        onComplete = { completeConsumer.consumer(Unit) })

        subject.onComplete()
        assertEquals(expected = listOf(ComparableException(997)),
                     actual = errorConsumer.events)
    }

    @Test
    fun `Maybe#retrySubscribe throws on error not implemented exception when onError consumer is not overrode`() {
        val subject = PublishSubject.create<Int>()
        subject
                .firstElement()
                .doOnSuccess { if (it > 100) throw ComparableException(it) }
                .retrySubscribe()
        subject.onNext(997)
        assertTrue(defaultErrorConsumer.events[0] is OnErrorNotImplementedException)
    }

    @Test
    fun `Single#retrySubscribe resubscribes when an error occurs in the stream`() {
        val subject = PublishSubject.create<Int>()
        val eventConsumer = TestConsumer<Int>()
        subject
                .firstOrError()
                .doOnSuccess { if (it > 100) throw ComparableException(it) }
                .retrySubscribe(onSuccess = eventConsumer.consumer)

        subject.onNext(666)
        subject.onNext(1)
        assertEquals(expected = listOf(1),
                     actual = eventConsumer.events)
    }

    @Test
    fun `Single#retrySubscribe invokes onError consumer when an error occurs in the stream`() {
        val subject = PublishSubject.create<Int>()
        val errorConsumer = TestConsumer<Throwable>()
        subject
                .firstOrError()
                .doOnSuccess { if (it > 100) throw ComparableException(it) }
                .retrySubscribe(onError = errorConsumer.consumer)

        subject.onNext(666)
        assertEquals(expected = listOf(ComparableException(666)),
                     actual = errorConsumer.events)
        subject.onNext(1)
        assertEquals(expected = listOf(ComparableException(666)),
                     actual = errorConsumer.events)
    }

    @Test
    fun `Single#retrySubscribe resubscribes when an error occurs in the onSuccess consumer`() {
        val subject = PublishSubject.create<Int>()
        val eventConsumer = TestConsumer<Int> { if (it > 100) throw ComparableException(it) }
        subject
                .firstOrError()
                .retrySubscribe(onSuccess = eventConsumer.consumer)
        subject.onNext(997)
        assertEquals(expected = listOf(997),
                     actual = eventConsumer.events)
        subject.onNext(1)
        assertEquals(expected = listOf(997, 1),
                     actual = eventConsumer.events)
    }

    @Test
    fun `Single#retrySubscribe invokes onError consumer when an error occurs in the onSuccess consumer`() {
        val subject = PublishSubject.create<Int>()
        val errorConsumer = TestConsumer<Throwable>()
        subject
                .firstOrError()
                .retrySubscribe(
                        onSuccess = TestConsumer<Int> { if (it > 100) throw ComparableException(it) }.consumer,
                        onError = errorConsumer.consumer)

        subject.onNext(997)
        assertEquals(expected = listOf(ComparableException(997)),
                     actual = errorConsumer.events)
        subject.onNext(1)
        assertEquals(expected = listOf(ComparableException(997)),
                     actual = errorConsumer.events)
    }

    @Test
    fun `Single#retrySubscribe throws on error not implemented exception when onError consumer is not overrode`() {
        val subject = PublishSubject.create<Int>()
        subject
                .firstOrError()
                .doOnSuccess { if (it > 100) throw ComparableException(it) }
                .retrySubscribe()
        subject.onNext(997)
        assertTrue(defaultErrorConsumer.events[0] is OnErrorNotImplementedException)
    }
}