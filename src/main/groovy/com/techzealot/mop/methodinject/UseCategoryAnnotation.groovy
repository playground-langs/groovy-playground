package com.techzealot.mop.methodinject

/**
 * 使用注解简化 只能注入一种特定类型
 */
@Category(String)
class StringUtilAnnotated {
    def toSSN() {
        if (this.size() == 9) {
            return "${this[0..2]}-${this[3..4]}-${this[5..8]}"
        }
    }
}
//use可以接受多个参数
use(StringUtilAnnotated) {
    println "123456789".toSSN()
    // Object no such method
    println new Object().toSSN()
}

