package com.techzealot.mop.classsynthesis

class Worker {
    def simpleWork1() {
        println("simple work1")
    }

    def simpleWork2() {
        println("simple work2")
    }
}

class Expert {
    def advanceWork1() {
        println("advance work1")
    }

    def advanceWork2() {
        println("advance work2")
    }
}

class Manager {
    {
        delegateCallsTo Worker, Expert, GregorianCalendar
    }

    def schedule() {
        println("schedule...")
    }
}

Object.metaClass.delegateCallsTo = { Class... klassOfDelegates ->
    def objectOfDelegates = klassOfDelegates.collect { it.getDeclaredConstructor().newInstance() }
    delegate.metaClass.methodMissing = { String name, args ->
        def delegateTo = objectOfDelegates.find { it.metaClass.respondsTo(it, name, args) }
        if (delegateTo) {
            delegate.metaClass."$name" = { Object[] vargs ->
                delegateTo.invokeMethod(name, vargs)
            }
            delegateTo.invokeMethod(name, args)
        } else {
            throw new MissingMethodException(name, delegate.getClass(), args)
        }
    }
}

peter = new Manager()

peter.schedule()
peter.simpleWork1()
peter.simpleWork2()
peter.advanceWork1()
peter.advanceWork2()

