package com.techzealot.mop.methodinject

class Person {
    def play() {
        println("playing...")
    }
}

def emc = new ExpandoMetaClass(Person)
emc.sing = {
    println("oh baby baby...")
}
emc.initialize()
def jack = new Person()
def paul = new Person()

jack.metaClass = emc

jack.sing()

try {
    paul.sing()
} catch (Exception e) {
    e.printStackTrace()
}

jack.metaClass = null

try {
    jack.play()
    jack.sing()
} catch (e) {
    e.printStackTrace()
}

jack.metaClass.play = {
    println("start play")
}

jack.metaClass {
    sing = {
        println("start sing")
    }
    dance = {
        println("start dance")
    }
}

jack.play()
jack.sing()
jack.dance()
