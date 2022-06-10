package com.techzealot.mop.aop

/**
 * 静态拦截 具有源码所有权 不常用
 */
class Car implements GroovyInterceptable {
    def drive() {
        System.out.println("drive")
    }

    def check() {
        System.out.println("check")
    }

    @Override
    Object invokeMethod(String name, Object args) {
        if (name == "check") {
            println("before check")
            metaClass.getMetaMethod("check").invoke(this, null)
            println("after check")
        } else {
            def method = metaClass.getMetaMethod(name, args)
            if (method != null) {
                method.invoke(this, args)
            } else {
                metaClass.invokeMethod(this, name, args)
            }
        }
    }
}

def car = new Car()
car.check()
car.drive()
car.notExist()

