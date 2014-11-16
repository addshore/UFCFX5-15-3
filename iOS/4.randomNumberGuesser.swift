var numberToGuess:Int
var userGuess:Int
var guessCounter:Int

guessCounter = 0

func getRandomNumber() -> Int {
    var randomNumber:Int
    randomNumber = Int(arc4random_uniform(10))
    if( randomNumber != 0 ) {
        return randomNumber
    } else {
        return getRandomNumber()
    }
}

do {
    numberToGuess = getRandomNumber()
    userGuess = getRandomNumber()
    
    guessCounter = guessCounter + 1
    
    println("The number to guess is: \(numberToGuess)")
    println("The user guess is: \(userGuess)")
    
    if( userGuess > numberToGuess ) {
        println( "Guess too large" )
    }
    if( userGuess < numberToGuess ) {
        println( "Guess too small" )
    }
    
} while userGuess != numberToGuess

println("Well Done! you guessed my number \(numberToGuess) in \(guessCounter) attempt(s)")

