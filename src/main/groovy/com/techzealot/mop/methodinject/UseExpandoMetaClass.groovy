package com.techzealot.mop.methodinject


import java.time.LocalDateTime

/**
 * 注入实例方法
 */
def daysFromNow = { ->
    LocalDateTime.now().plusDays(delegate)
}

Integer.metaClass.getDaysFromNow = daysFromNow
Long.metaClass.getDaysFromNow = daysFromNow
//子类可以继承
Number.metaClass.someMethod = { args ->
    println("$delegate $args")
}

println 5.daysFromNow
println 10L.daysFromNow
5.someMethod(6)
5L.someMethod(6)

/**
 * 注入静态方法
 */
Integer.metaClass.'static'.isEven = { val -> val % 2 == 0 }
println Integer.isEven(2)
println Integer.isEven(3)

/**
 * 注入构造器 <<不能覆盖现有构造器
 */
Integer.metaClass.constructor << { LocalDateTime date ->
    Integer.valueOf(date.getDayOfYear())
}

println new Integer(LocalDateTime.now())

/**
 * 使用 =覆盖构造器
 */
Integer.metaClass.constructor = { int val ->
    println "before call"
    def constructor = Integer.class.getConstructor(Integer.TYPE)
    constructor.newInstance(val)
}

println new Integer(10)