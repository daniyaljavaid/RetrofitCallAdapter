package com.dj.retrofitcalladapter.datasource.service

import com.dj.retrofitcalladapter.common.ResultState
import com.dj.retrofitcalladapter.dto.ResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("")
    suspend fun getData(@Query("name") name: String = "dj"): ResultState<ResponseDto>

}