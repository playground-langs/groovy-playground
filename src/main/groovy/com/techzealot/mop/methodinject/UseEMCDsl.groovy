package com.techzealot.mop.methodinject

import java.time.LocalDateTime

/**
 * 通过EMC注入的只能通过groovy直接调用，不能在java中直接调用
 */
Integer.metaClass {
    getDateFromNow = { ->
        LocalDateTime.now().plusDays(delegate)
    }
    'static' {
        isEven = { val -> val % 2 == 0 }
    }
    constructor = { LocalDateTime date ->
        new Integer(date.getDayOfYear())
    }
    constructor = { int val ->
        println("intercept constructor call")
        constructor = Integer.class.getConstructor(Integer.TYPE)
        constructor.newInstance(val)
    }
}

println new Integer(LocalDateTime.now())
println Integer.isEven(2)
