import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

const val API_KEY: String = "01146fdd46a844f7a6793903230206"
class Weather {
    fun getWeather(city: String): WeatherResponse {
        val okHttpClient = OkHttpClient.Builder()
            .callTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        val service = retrofit.create(WeatherAPI::class.java)

        var response: WeatherResponse
        runBlocking {
            response = service.getWeather(API_KEY, city, "no")
        }
        return response
    }
}

interface WeatherAPI {
    @GET("current.json?")
    suspend fun getWeather(@Query("key") key: String, @Query("q") q: String, @Query("aqi") aqi: String): WeatherResponse
}


class WeatherResponse {
    var location: Location? = null
    var current: Current? = null
}

class Location {
    var name: String? = null
    var region: String? = null
    var country: String? = null
    var lat = 0.0
    var lon = 0.0
    var tz_id: String? = null
    var localtime_epoch: Long = 0
    var localtime: String? = null
}

class Current {
    var last_updated_epoch: Long = 0
    var last_updated: String? = null
    var temp_c = 0.0
    var temp_f = 0.0
    var is_day = 0
    var condition: Condition? = null
    var wind_mph = 0.0
    var wind_kph = 0.0
    var wind_degree = 0
    var wind_dir: String? = null
    var pressure_mb = 0.0
    var pressure_in = 0.0
    var precip_mm = 0.0
    var precip_in = 0.0
    var humidity = 0
    var cloud = 0
    var feelslike_c = 0.0
    var feelslike_f = 0.0
    var vis_km = 0.0
    var vis_miles = 0.0
    var uv = 0.0
    var gust_mph = 0.0
    var gust_kph = 0.0
}

class Condition {
    var text: String? = null
    var icon: String? = null
    var code = 0
}