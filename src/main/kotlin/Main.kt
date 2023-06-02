fun main() {
    println("Hello Weather!")
    val weather = Weather()
    var city: String
    while(true) {
        print("введите город: ")
        city = readLine() as String
        if (city == "q") {
            break
        } else {
            try {
                println("получаю инфу ...")
                val response: WeatherResponse = weather.getWeather(city)
                println("температура: ${response.current?.temp_c}")
            } catch (e: Exception) {
                print(e)
                continue
            }
        }
    }
}