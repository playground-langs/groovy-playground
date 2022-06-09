package com.techzealot.dsl

detailInfo=[:]

def methodMissing(String name,args){
    detailInfo[name]=args
}

def introduce(closure){
    closure.delegate=this
    closure()
    detailInfo.each{
        key,value->println("$key:$value")
    }
}

introduce{
    name "tom"
    age 18
}