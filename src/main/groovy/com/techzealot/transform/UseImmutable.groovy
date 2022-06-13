package com.techzealot.transform

import groovy.transform.Immutable

/**
 * 产生不可变对象 生成final字段及toString hashcode等方法
 */
@Immutable
class CreditCard {
    String cardNumber
    int creditLimit
}

def card = new CreditCard("4000-1111-2222-3333", 1000)

println(card)
