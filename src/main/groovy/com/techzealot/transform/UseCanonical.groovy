package com.techzealot.transform

import groovy.transform.Canonical
import groovy.transform.ToString

@Canonical()
@ToString(excludes = "firstName,lastName")
class Person {
    String firstName
    String lastName
    int age
}

println(new Person(firstName: "Sara", lastName: "Walker", age: 49))
