# Kotlin Questions and Tasks

Difference between apply, run, also and let?
-------
**apply**: Lets you set values within the apply block directly on the object.

**run**: Useful for setting values and creating an object in a single block.

**also**: Useful for additional misc. operations that are not introducing side effects. eg. logging.

**let**: Executes the code block with `it` as this context. Useful for executing code only on non null elements 
with obj?.let{}

Do primitives are available for user in Kotlin?
-------
Only classes are available for users, `Int`, `String` etc. During runtime they can be represented by primitives.

Difference between val and var?
-------

val cannot be changed - immutable.
var is a standard variable.


How to create static method in Class?
-------
```kotlin
class UtilClass {
    companion object {
        fun debug(debugMessage : String) {
            println("[DEBUG] $debugMessage")
        }
    }
}
```

`@JvmStatic` is useful to avoid adding `companion` to call the utility.


What class is used for primitives array? Create an array of Strings.
-------
**Array** is used for storing primitive in a basic way.

```kotlin
val stringArray: Array<String> = arrayOf("a", "b", "cde")
```

What is data class?
-------
Special class with built in functions for comparing, copying and deconstructing.

Used for holding data and decoupling it from logic.

Difference between data class and ordinary class?
-------
Data class is used as a container with simple getters and setters.
An ordinary class can be used as a data class but it won't have any of the benefits and could cause confusion.

What is file?
-------
A file is a type of data storage within a file system.
Kotlin File is a neat way of handling a file IO. Widely used for reading, writing and editing.

What is block init?
-------
`init` is the constructor block that is used to create the object. Its the first code executed when creating an instance.

What for out and in are used? Example: Presenter<V in BaseView>.
-------
`out` modifier defines interfaces that can produce. It means you can define returns of that type.

```kotlin
interface Source<out T> {
    fun nextT(): T
}
```
Notice: `out T` and `next(): T`.

`in` modifier defines interfaces that can consume. It can use the `in` type in its functions.

```kotlin
interface Comparable<in T> {
    operator fun compareTo(other: T): Int
}
```
Notice: `in T` and `other: T`.

What annotation should be used to make a class available for extending?
-------
`open` otherwise the class is final.

What is object declaration in Kotlin?
-------
`object` is a declaration for a singleton. It does not have a constructor and can be called directly.

What is lazy in Kotlin? How to use it?
-------
lazy delegation is a simple way to load the value only when necessary.

Example:

```kotlin
private val dateFormat by lazy { SimpleDateFormat("MMM d, yyyy", Locale.US) }
```

What is lateinit?
------
All variables have to be initialized and sometimes its not convenient to do so immediately. With `lateinit` its possible
to initialize the value at a later time. 

Can we check if lateinit property has already been initialized?
-------
Yes, but only if its lexically accessible.

```kotlin
class FooClass {
    lateinit var barProp: String
    
    fun isBarInitialized() = ::barProp::isInitialized
}
```

Visibility modifiers in Kotlin
-------
private, protected, internal(restricted to module) and public (default).

What is extension? Create an extension function that will print variable’s value in log once it is called
-------

```kotlin
class App

fun App.log(message: String) {
    println("[DEBUG] $message")
}
```

Create a fun with default arguments
-------
```kotlin
fun App.log(message: String = "Logging Event") {
    println("[DEBUG] $message")
}
```

How to call a function fun foo(arg1 : Int = 0, arg2 : Int = 1) passing only second argument?
-------

```kotlin
foo(arg2 = 4)
```

What is Unit in Kotlin?
-------
Corresponds to a void in Java.

There is a class @Nullable User, that has a parameter - @Nullable class Dog. class Dog has a parameter @Nullable name : String. How to invoke fun callDog(@Notnull name : String){} in one line?
-------
```kotlin
user?.let { user -> user.dog?.let { dog -> dog.name?.let { user.callDog(dog.name)}}
```

What is coroutine?
-------
A way to invoke a function in an asynchronous way.

```kotlin
launch{ asyncCode() }
```

What is Unit, Nothing, Any in Kotlin?
-------
**Unit** is void.
**Nothing** is used to define a function that will never return. eg. throw an exception.
**Any** Root of hierarchy, every class inherits from Any.

What is inline, noinline, crossinline?
-------
**inline** Makes the compiler inline code instead of creating an object to run it.
**noinline** No inline functions can be stored as objects, while inline can not.
**crossinline** Forbids non local returns.

*** What is spread operator?
-------
```kotlin
arrayOf(0, 1, *arrayOf(2, 3), 4) == arrayOf(0, 1, 2, 3, 4)
```
