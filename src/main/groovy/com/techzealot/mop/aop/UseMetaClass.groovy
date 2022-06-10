package com.techzealot.mop.aop

/**
 * 动态拦截 推荐
 */
class CarA {
    def drive() {
        //println为Object扩展方法 也会被拦截
        //println("drive")
        System.out.println("drive")
    }

    def check() {
        System.out.println("check")
    }
}
/**
 * 需要使用delegate获取对象
 * CarA内所有方法调用都会被拦截包括 println扩展方法
 */
CarA.metaClass.invokeMethod = { String name, args ->
    println("this:$this deleagte:$delegate owner:$owner")
    if (name == "check") {
        println("before check")
        CarA.metaClass.getMetaMethod("check").invoke(delegate, null)
        println("after check")
    } else {
        def method = CarA.metaClass.getMetaMethod(name, args)
        if (method != null) {
            method.invoke(delegate, args)
        } else {
            CarA.metaClass.invokeMissingMethod(delegate, name, args)
        }
    }
}

def car = new CarA()
car.check()
car.drive()
car.notExist()