var numberToGuess:Int
var userGuess:Int
var guessCounter:Int

guessCounter = 0

do {
    numberToGuess = Int(arc4random_uniform(10))
    userGuess = Int(arc4random_uniform(10))
    
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