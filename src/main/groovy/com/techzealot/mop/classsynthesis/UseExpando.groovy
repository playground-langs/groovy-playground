package com.techzealot.mop.classsynthesis

//动态指定
carA = new Expando()
carA.year = 2021
carA.miles = 10
//使用map指定属性和方法
carB = new Expando(year: 2022, miles: 0)

println("carA: $carA")
println("carB: $carB")

//方法合成
car = new Expando(year: 2012, miles: 0, turn: { println("turning") })
car.drive = {
    miles += 10
    println("$miles miles driven")
}
car.drive()
car.turn()