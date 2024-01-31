package com.composeapp.testScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TestScreenVm : ViewModel() {
    private val _textMessage = MutableLiveData<String>(null)
    val textMessage: LiveData<String> = _textMessage

    init {
        printLn("Hello Android")

        val exampleClassA = Example()
        val exampleClassB = Example()

        val derivedExampleA = Derived()
        val derivedExampleB = Derived(data = 44)

        printLn("$exampleClassA")
        printLn("$exampleClassB")
        printLn("$derivedExampleA")
        printLn("$derivedExampleB")

        printLn("\n hashCode method must consistently return the same integer, provided no information used in equals comparisons on the object is modified.\n")

        val exA = Derived(45)
        printLn(exA.toString())
        printLn(exA.apply { changeData(57) }.toString())

        printLn("Derived class initialization order")

    }

    fun foo() {
        printLn("Push completed")
    }

    private fun printLn(str: String) {
        if (_textMessage.value.isNullOrEmpty()) _textMessage.value = ""
        _textMessage.value = _textMessage.value.plus(str).plus("\n")
    }
}

open class Example(private var someData: Int = 0) {
    fun changeData(newData: Int) {
        someData = newData
    }

    override fun toString(): String {
        return "${this::class.simpleName} data = [$someData] hash = [${hashCode()}]"
    }
}

class Derived() : Example() {
    constructor(data: Int) : this() {
        changeData(newData = data)
    }
}

open class Vehicle(protected val wheelsNum: Int = 0) {
    open var color: String = "White".also {
        Log.e("LOG", "Color $it")
    }

    init {
        Log.e("LOG", "Initialise Vehicle ${this::class.simpleName} param = $wheelsNum $color")
    }
}

class BatMobile() : Vehicle(wheelsNum = 4) {
    init {
        Log.e(
            "LOG",
            "Initialise BatMobile ${this::class.simpleName} param = ${super.wheelsNum} $color"
        )
        super.color = "Purpur"
    }
}