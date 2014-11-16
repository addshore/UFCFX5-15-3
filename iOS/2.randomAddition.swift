var numberOne:Int
var numberTwo:Int
var guessCounter:Int

numberOne = Int(arc4random_uniform(10))
numberTwo = Int(arc4random_uniform(10))

var total:Int

total = numberOne + numberTwo

println("The sum of \(numberOne) + \(numberTwo) is:\(total)")