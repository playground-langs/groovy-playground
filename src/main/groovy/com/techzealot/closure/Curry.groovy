package com.techzealot.closure

/**
 * 科里化就是预设lambda参数 可以简化重复传参
 * @param closure
 * @return
 */
def two(closure) {
    def one = closure.curry("first")
    one("second1")
    one("second2")
}

two { p1, p2 ->
    println("$p1 $p2")
}



