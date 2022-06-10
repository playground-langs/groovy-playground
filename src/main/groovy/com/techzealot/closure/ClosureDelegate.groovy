package com.techzealot.closure
/**
 * 闭包委托 闭包内的this
 */

def examineClosure(closure) {
    closure()
}
/**
 * closure有三大属性 方法解析优先级:this->owner->delegate
 */
examineClosure() {
    println("in first closure:")
    println("class is : ${this.class.name}")
    println("this is : ${this},super is :${this.class.superclass.name}")
    println("owner is : ${owner},super is :${owner.class.superclass.name}")
    println("delegate is : ${delegate},super is :${delegate.class.superclass.name}")
}
//闭包委托
/**
 * 可能会有副作用
 * @param closure
 * @return
 */
def test1(closure) {
    closure.delegate = new Delegate()
    closure()
}
/**
 * 可以clone闭包避免副作用
 * @param closure
 * @return
 */
def test2(closure) {
    c = closure.clone()
    c.delegate = new Delegate()
    c()
}
/**
 * 简化形式
 */
def test3(closure) {
    new Delegate().with(closure)
    //等价于
    //DefaultGroovyMethods.with(new Delegate(),closure)
}


class Delegate {
    void foo(String arg) {
        println("foo $arg")
    }
}

test1 {
    foo "closure delegate"
}

test2 {
    foo "closure delegate"
}

test3 {
    foo "closure delegate"
}

