package com.techzealot.transform

class Heavy {
    def size = 10

    Heavy() {
        println("creating heavy with $size")
    }
}

class AsNeed {
    def value
    @Lazy
    Heavy heavy1 = new Heavy()
    @Lazy
    Heavy heavy2 = { new Heavy(size: value) }()

}


def asNeed = new AsNeed(value: 1000)

println asNeed.heavy1.size
println asNeed.heavy1.size
println asNeed.heavy2.size
