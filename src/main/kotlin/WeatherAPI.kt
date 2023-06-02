import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.http.*
import java.util.concurrent.TimeUnit

const val API_KEY: String = "01146fdd46a844f7a6793903230206"
class Weather {
    fun getWeather(city: String) {
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

        var response: String
        runBlocking {
            response = service.getWeather(API_KEY, city, "no").toString()
        }
        println(response)
    }
}

interface WeatherAPI {
    @GET("current.json?")
    suspend fun getWeather(@Query("key") key: String, @Query("q") q: String, @Query("aqi") aqi: String): Any
}