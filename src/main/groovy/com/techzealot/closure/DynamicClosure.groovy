package com.techzealot.closure

/**
 * 判断closure是否定义 未定义使用默认逻辑
 * @param closure
 */
def doSomething(closure) {
    //groovy对于普通对象类型null视为false
    if (closure) {
        closure()
    } else {
        println("do default")
    }
}

doSomething()
doSomething {
    println("do in closure")
}

/**
 * 获取闭包参数个数和类型
 */
def examine(Closure closure) {
    println("param size:$closure.maximumNumberOfParameters")
    println("param list: $closure.parameterTypes")
}

examine {}
examine { it }
examine { -> }
examine { int a, String b -> }
examine { val -> }

