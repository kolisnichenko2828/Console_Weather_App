fun main() {
    println("Hello Weather!")
    val weather = Weather()
    var city = ""
    while(true) {
        print("введите город: ")
        city = readLine() as String
        if (city == "q") {
            break
        } else {
            try {
                println("получаю инфу ...")
                weather.getWeather(city)
            } catch (e: Exception) {
                print(e)
                continue
            }
        }
    }
}