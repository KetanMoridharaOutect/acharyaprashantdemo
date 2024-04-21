package com.prashantadvaitdemo.ketan.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prashantadvaitdemo.ketan.model.MediaCoverage
import com.prashantadvaitdemo.ketan.utils.ApiService
import com.prashantadvaitdemo.ketan.utils.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageViewModel : ViewModel() {

    private val _imageList = MutableLiveData<List<String>>()
    val imageList: LiveData<List<String>> = _imageList

    private val apiService = RetrofitClient.retrofit.create(ApiService::class.java)

    init {
        fetchImages()
    }

    private fun fetchImages() {
        val limit = 100 // Number of images to fetch
        apiService.getMediaCoverages(limit).enqueue(object : Callback<List<MediaCoverage>> {
            override fun onResponse(
                call: Call<List<MediaCoverage>>,
                response: Response<List<MediaCoverage>>
            ) {
                if (response.isSuccessful) {
                    val images = mutableListOf<String>()
                    response.body()?.forEach { coverage ->
                        images.add(coverage.thumbnail.domain + "/" + coverage.thumbnail.basePath + "/"+coverage.thumbnail.qualities[coverage.thumbnail.qualities.size-3]+ "/" + coverage.thumbnail.key)
                    }
                    _imageList.value = images
                } else {
                    // Handle error
                }
            }

            override fun onFailure(call: Call<List<MediaCoverage>>, t: Throwable) {
                // Handle failure
                t.printStackTrace()
            }
        })
    }
}