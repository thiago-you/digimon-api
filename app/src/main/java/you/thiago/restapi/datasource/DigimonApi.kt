package you.thiago.restapi.datasource

import retrofit2.Call
import retrofit2.http.GET

interface DigimonApi {
    @GET("api/digimon")
    fun getDigimons(): Call<List<Digimon>>
}