package com.dj.retrofitcalladapter.datasource.service

import com.dj.retrofitcalladapter.common.ResultState
import com.dj.retrofitcalladapter.dto.ResponseDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("?name=dj")
    suspend fun getData(): ResponseDto

    @GET("?name=dj")
    suspend fun getDataAsResponse(): Response<ResponseDto>

    @GET("?name=dj")
    suspend fun getDataWithResultState(): ResultState<ResponseDto>

    @GET("?name=dj")
    fun getDataWithResultStateFlow(): Flow<ResultState<ResponseDto>>
}