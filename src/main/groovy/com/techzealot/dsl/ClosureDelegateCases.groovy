package com.techzealot.dsl

import org.codehaus.groovy.runtime.DefaultGroovyMethods

class Data{
    String name
    int age

    void name(String name){
        this.name=name
    }

    void age(int age){
        this.age=age
    }
}

String user(@DelegatesTo(Data.class)Closure<Data> closure){
    Data data=new Data()
    DefaultGroovyMethods.with(data,closure)
    println("${data.name} ${data.age}")
}

user{
    name "jack"
    age 18
}