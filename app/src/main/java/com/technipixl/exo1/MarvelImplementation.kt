package com.technipixl.exo1

import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Date
import java.util.concurrent.TimeUnit

class MarvelImplementation {

    val timeStamp = Date().time
    val privateKey = "be674084e4ff6e6e2c7c78b070db649b5b9439fd"
    val publicKey = "adb753eb95ee9db0f7816ba872b244f9"
    val hashExample = HashGenerator.generateHash(timeStamp, privateKey, publicKey)
    val limit: String = "100"

    fun getRetrofit(): Retrofit {
        val okBuilder = OkHttpClient().newBuilder().apply {
            connectTimeout(15, TimeUnit.SECONDS)
            callTimeout(15, TimeUnit.SECONDS)
            readTimeout (15, TimeUnit.SECONDS)
            writeTimeout(15, TimeUnit.SECONDS)
        }
        //specifie l'url de l'api sur laquelle on va chercher les information
        return Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com/v1/public/")

            // converti json->kotlin
            .addConverterFactory(GsonConverterFactory.create())
            .client(okBuilder.build()) // specifie le client avec les délais spécifiés au-dessus
            .build() // finalise la construction de l'objet et retourne une instance prête à être utilisée pour effectuer des appels réseau
    }

    suspend fun getMarvel(): Response<MarvelResponse> =
        getRetrofit().create(MarvelService::class.java).getMarvel(publicKey, timeStamp.toString(), hashExample.toString(), limit  )


}
