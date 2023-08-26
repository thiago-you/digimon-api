package you.thiago.restapi.datasource

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Service {

    private fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://digimon-api.vercel.app/")
            .addConverterFactory(GsonConverterFactory.create(getGsonAdapter()))
            .build()
    }

    private fun getGsonAdapter(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    fun getDigimons(): List<Digimon> {
        val service = getInstance().create(DigimonApi::class.java)

        val response = service.getDigimons().execute()

        return response.body() ?: listOf()
    }
}