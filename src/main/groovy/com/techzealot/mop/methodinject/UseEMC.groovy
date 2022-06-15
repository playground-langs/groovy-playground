package com.techzealot.mop.methodinject

/**
 * 使用EMC动态合成方法 无需源码编辑权限
 */
class PersonB {
    def work() {
        "working"
    }
}

//拦截未定义方法
PersonB.metaClass.methodMissing = { String name, args ->
    def plays = ["Tennis", "VolleyBall", "BasketBall"]
    println "method missing called for $name"
    def methodInList = plays.find {
        name.contains(it)
    }
    //拦截
    if (methodInList) {
        def impl = { Object[] vargs ->
            "playing ${name.split("play")[1]}..."
        }
        //缓存
        PersonB.metaClass."$name" = impl
        //初次调用 后续调用走缓存
        impl(args)
    } else {
        throw new MissingMethodException(name, PersonB.class, args)
    }
}
//拦截所有方法 不存在方法转发至methodMissing
PersonB.metaClass.invokeMethod = { String name, args ->
    System.out.println("intercept for all method call $name")
    def method = PersonB.metaClass.getMetaMethod(name, args)
    //exist
    if (method) {
        method.invoke(delegate, args)
    }
    //missing
    else {
        delegate.metaClass.invokeMissingMethod(delegate, name, args)
    }
}

jack = new PersonB()
jack.work()
println jack.playTennis()
println jack.playTennis()

try {
    jack.playPolitics()
} catch (ex) {
    ex.printStackTrace()
}

tom = new PersonB()
//可以为实例合成方法
tom.metaClass.methodMissing = { String name, args ->
    System.out.println("instance method missing called for $name")
}
tom.sing()
tom.dance()

paul = new PersonB()

try {
    paul.sing()
    paul.dance()
} catch (ex) {
    ex.printStackTrace()
}