# useful-ktx
Useful Kotlin extensions to speed up Android app development

## [android](https://github.com/tomasznajda/useful-ktx/tree/master/android/src/main/kotlin/com/tomasznajda/ktx/android)
```kotlin
Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT)
Context.toast(@StringRes messageId: Int, duration: Int = Toast.LENGTH_SHORT)
```

## [kotlin](https://github.com/tomasznajda/useful-ktx/tree/master/kotlin/src/main/kotlin/com/tomasznajda/ktx/kotlin)
```kotlin
T?.isNotNull()
T?.isNull()
```

## [rxjava2](https://github.com/tomasznajda/useful-ktx/tree/master/rxjava2/src/main/kotlin/com/tomasznajda/ktx/rxjava2)
```kotlin
T?.asFlowable()
T?.asObservable()
T?.asMaybe()
T.asSingle()
R.asFlowableError()
R.asObservableError()
R.asSingleError()
R.asMaybeError()
R.asCompletableError()
zip(source1: Flowable<T1>, source2: Flowable<T2>)
zip(source1: Observable<T1>, source2: Observable<T2>)
zip(source1: Single<T1>, source2: Single<T2>)
zip(source1: Maybe<T1>, source2: Maybe<T2>)
zip(source1: Completable, source2: Completable)
zipWith(source: Flowable<T2>)
zipWith(source: Observable<T2>)
zipWith(source: Single<T2>)
zipWith(source: Maybe<T2>)
```

## [gson](https://github.com/tomasznajda/useful-ktx/tree/master/gson/src/main/kotlin/com/tomasznajda/ktx/gson)
```kotlin
Any.toJson()
String.fromJson(clazz: Class<T>)
String.fromJson(clazz: KClass<T>)
```

## [junit](https://github.com/tomasznajda/useful-ktx/tree/master/junit/src/main/kotlin/com/tomasznajda/ktx/junit)
```kotlin
assertEquals(expected: Any?, actual: Any?)
Any.assertIsInstanceOf(clazz: KClass<T>)
```
