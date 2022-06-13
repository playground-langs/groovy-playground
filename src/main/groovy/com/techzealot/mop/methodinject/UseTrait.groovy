package com.techzealot.mop.methodinject

//trait编译时生效 可在java中使用

trait Fly {
    def fly() {
        println("flying...")
    }
}

trait Swim {
    def swim() {
        println("swim...")
    }
}

trait Swimming {
    def swim() {
        println("swimming...")
    }
}
//若发生冲突 最后一个生效
class Duck implements Fly, Swim, Swimming {

}

duck = new Duck()
duck.swim()