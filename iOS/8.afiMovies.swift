var filmList = [
    "Citizen Kane",
    "Casablanca",
    "The Godfather",
    "Gone with the Wind",
    "Lawrence of Arabia",
    "The Wizard of Oz",
    "The Graduate",
    "On the Waterfront",
    "Schindler's List",
    "Singing' in the Rain",
    "It's a Wonderful Life",
    "Sunset Boulevard",
    "The Bridge on the River Kwai",
    "Some Like It Hot",
    "Star Wars",
    "All About Eve",
    "The African Queen",
    "Psycho",
    "Chinatown",
    "One Flew Over the Cuckoo's Nest",
]

var randomIndex = Int(arc4random_uniform(UInt32(filmList.count)))

println("Your AFI film for the evening is \(filmList[randomIndex])")