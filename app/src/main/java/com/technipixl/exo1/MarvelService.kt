package com.technipixl.exo1

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {
        @GET("characters")
        suspend fun getMarvel(
            @Query("apikey", encoded = false) apiKey: String,
            @Query("ts", encoded = true) ts: String,
            @Query("hash", encoded = true) hash: String,
            @Query("limit", encoded = true) limit: String
        ): Response<MarvelResponse>

    }