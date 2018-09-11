# useful-ktx
Useful Kotlin extensions to speed up Android app development

## [android](https://github.com/tomasznajda/useful-ktx/tree/master/ktx/src/main/kotlin/com/tomasznajda/ktx/android)
```kotlin
Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT)
Context.toast(@StringRes messageId: Int, duration: Int = Toast.LENGTH_SHORT)
```

## [kotlin](https://github.com/tomasznajda/useful-ktx/tree/master/ktx/src/main/kotlin/com/tomasznajda/ktx/kotlin)
```kotlin
T?.isNotNull()
T?.isNull()
```

## [rxjava2](https://github.com/tomasznajda/useful-ktx/tree/master/ktx/src/main/kotlin/com/tomasznajda/ktx/rxjava2)
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
```

## [gson](https://github.com/tomasznajda/useful-ktx/tree/master/ktx/src/main/kotlin/com/tomasznajda/ktx/gson)
```kotlin
Any.toJson()
String.fromJson(clazz: Class<T>)
String.fromJson(clazz: KClass<T>)
```
