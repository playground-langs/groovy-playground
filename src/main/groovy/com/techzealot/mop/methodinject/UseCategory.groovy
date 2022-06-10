package com.techzealot.mop.methodinject

class StringCategory {
    //未定义类型代表为Object注入该方法 可以限制类型
    def static toSSN(self) {
        if (self.metaClass.respondsTo(self, "size") && self.size() == 9) {
            return "${self[0..2]}-${self[3..4]}-${self[5..8]}"
        }
    }
}
//作用域内可用
use(StringCategory) {
    println "123456789".toSSN()
    println new StringBuilder("123456789").toSSN()
    // Object is ok
    println new Object().toSSN()
}

