package com.pdp.imperative.network

import com.pdp.imperative.model.TVShowDetails
import com.pdp.imperative.model.TVShowPopular
import retrofit2.Response
import retrofit2.http.*

interface TVShowService {

    @GET("api/most-popular")
    suspend fun apiTVShowPopular(@Query("page") page: Int): Response<TVShowPopular>

    @GET("api/show-details")
    suspend fun apiTVShowDetails(@Query("q") q: Int): Response<TVShowDetails>

}