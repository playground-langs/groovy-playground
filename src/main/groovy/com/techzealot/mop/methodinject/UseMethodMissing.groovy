package com.techzealot.mop.methodinject

/**
 * 运行时合成需要的方法 适用于具有源码权限
 */
class Player {
    def work() {
        "working..."
    }
    def plays = ["Tennis", "VolleyBall", "BasketBall"]

    //拦截->缓存->调用
    def methodMissing(String name, def args) {
        println "method missing called for $name"
        def methodInList = plays.find {
            it == name.split("play")[1]
        }
        //拦截
        if (methodInList) {
            def impl = { Object[] vargs ->
                "playing ${name.split("play")[1]}..."
            }
            Player instance = this
            //缓存
            instance.metaClass."$name" = impl
            //初次调用 后续调用走缓存
            impl(args)
        } else {
            throw new MissingMethodException(name, Player.class, args)
        }
    }
}

jack = new Player()
println jack.playTennis()
println jack.playTennis()
