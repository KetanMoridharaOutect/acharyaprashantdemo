package com.prashantadvaitdemo.ketan.utils

import com.prashantadvaitdemo.ketan.model.MediaCoverage
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("content/misc/media-coverages")
    fun getMediaCoverages(@Query("limit") limit: Int): Call<List<MediaCoverage>>
}