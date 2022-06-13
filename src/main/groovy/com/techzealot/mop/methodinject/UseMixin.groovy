package com.techzealot.mop.methodinject

class Friend {
    def listen() {
        "$name is listening as a friend"
    }
}

class Teacher {
    def teach() {
        println("teaching")
    }
}

/**
 * Mixin注解方式只能由类的作者提供
 */
@Mixin([Friend, Teacher])
class PersonA {
    String firstName
    String lastName

    String getName() {
        "$firstName $lastName"
    }
}

john = new PersonA(firstName: "John", lastName: "Smith")
println(john.listen())
john.teach()

//使用mixin方法非常灵活
class Dog {
    String name
}
//为类的所有实例混入
Dog.mixin(Friend)

buddy = new Dog(name: "Buddy")

println(buddy.listen())

//为类的部分实例混入
class Cat {
    String name
}

socks = new Cat(name: "Socks")
socks.metaClass.mixin(Friend)
println(socks.listen())
