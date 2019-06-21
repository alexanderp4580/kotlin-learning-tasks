package learning.tasks

import learning.tasks.data.Pet
import learning.tasks.data.User
import learning.tasks.view.InvisibleView
import learning.tasks.view.OnClickLister
import learning.tasks.view.View
import learning.tasks.view.Visibility

fun main() {
    usersWithPets()
    fooStuff()
    views()
    intsToString()
    simpleDecorator {
        println("My custom function")
    }
    anonymousClickListener()
    runLoggingFilter()
    runInFix()
    reifiedExample(
            listOf(
                    View(),
                    InvisibleView(),
                    InvisibleView(),
                    View(),
                    InvisibleView()
            )
    )
}

fun reifiedExample(views: List<View>) {
    // Can also partition by Type using this method.
    views.countViewOfType<InvisibleView>().also { println("Number of invisible views : $it") }
}

inline fun <reified T : View> Iterable<View>.countViewOfType(): Int = this.count { it is T }

fun runInFix() {
    for (i in 0..8) println(2.0 pow i)
}

infix fun <T : Number> T.pow(power: Int): Double {
    val value = this.toDouble()
    var result = value
    for (i in 1..power) result *= value
    return result
}

fun runLoggingFilter() {
    listOf(1, 3, -5, 10).loggingFilter { it > 0 }.also { println("Total sum of positives: ${it.sum()}") }
}

inline fun <T> Iterable<T>.loggingFilter(predicate: (T) -> Boolean): List<T> {
    println("Starting filtering")
    val filtered = this.filter(predicate)
    println("Finished filtering")
    return filtered
}

fun anonymousClickListener() {
    val view = View().apply {
        onClickLister = object : OnClickLister {
            override fun onClick() {
                println("onClick, clicked!")
            }
        }
    }

    view.click()
}

fun simpleDecorator(anotherFun: () -> Any) {
    println("Executing received function")
    anotherFun()
    println("Finished Execution")
}

fun intsToString() {
    listOf(1000, 220, 30).run {
        // Convert to strings
        val strings = map { it.toString() }
        strings.forEach { stringNumber -> println("$stringNumber - len: " + stringNumber.length) }
    }
}

fun views() {
    val views = arrayOf(View(), InvisibleView())
    printViews(views.asIterable())
    setAllViewsGone(*views)
    printViews(views.asIterable())
}

fun setAllViewsGone(vararg views: View) {
    views.map { it.apply { visibility = Visibility.GONE } }
}

fun printViews(views: Iterable<View>) {
    views.forEachIndexed { i, view -> println("view index: $i - isVisible: ${view.visibility}") }
}

fun callPet(pet: Pet) {
    val name = pet.name
    println("Hey $name, come here!")
}

fun fooStuff() {
    foo(i = 21)
}

fun foo(s: String = "Default string", i: Int = 42) {
    println("$s : $i")
}

fun usersWithPets() {
    val userWithPet = User(Pet("Sparky"))
    val userWithoutPet = User() // Apply could be called here to set the values.
    val users = listOf(userWithPet, userWithoutPet)

    with(userWithoutPet) {
        // Apply is more appropriate for setting values
        address = "142 Curtain road, London, GB"
        phone = "+4439103023"
    }

    users.map { user -> user.pet?.let { pet -> callPet(pet) } }
}
