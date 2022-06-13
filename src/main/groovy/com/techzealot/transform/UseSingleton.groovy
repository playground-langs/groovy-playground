package com.techzealot.transform

@Singleton(lazy = true, strict = false)
class TheUnique {
    private TheUnique() {
        println("instance created")
    }

    def hello() {
        println("hello")
    }
}

TheUnique.instance.hello()
TheUnique.instance.hello()
