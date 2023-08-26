package you.thiago.restapi.datasource

import com.google.gson.annotations.SerializedName

data class Digimon(
    @SerializedName("name")
    val name: String,
    @SerializedName("img")
    val img: String,
    @SerializedName("level")
    val level: String
)