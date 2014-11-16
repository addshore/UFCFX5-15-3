var data = [
    "Brit Pop": ["Oasis", "Blur", "Pulp"],
    "New Romantics": ["ABC", "Spandau Ballet", "Duran Duran"],
    "Glam Rock": ["Slade", "T. Rex", "Roxy Music"],
    "Punk Rock": ["The Undertones", "The Buzzcocks", "Blondie"],
    "Foo": ["Bar", "Baz"],
    ]

for (key, value) in data {
    var outputLine: String
    outputLine = "\(key): "
    for (innerValue) in value {
        outputLine += innerValue + ", "
    }
    // Remove the last 2 chars ', '
    outputLine.removeAtIndex(outputLine.endIndex.predecessor())
    outputLine.removeAtIndex(outputLine.endIndex.predecessor())
    println(outputLine)
}