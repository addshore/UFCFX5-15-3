var data = [
    "Numbers": ["1","2","3","4","5"],
    "Letters": ["A", "C", "C", "D"],
    "Spaces": [" ", "     ", "        "],
    "Symbols": ["£", "^", "#"],
    "Words": ["Cheese", "Keyboard"],
    ]

for (key, value) in data {
    var outputLine: String
    outputLine = "\(key) include: ( "
    for (innerValue) in value {
        outputLine += "'" + innerValue + "', "
    }
    // Remove the last 2 chars ', '
    outputLine.removeAtIndex(outputLine.endIndex.predecessor())
    outputLine.removeAtIndex(outputLine.endIndex.predecessor())
    outputLine += " )"
    println(outputLine)
}