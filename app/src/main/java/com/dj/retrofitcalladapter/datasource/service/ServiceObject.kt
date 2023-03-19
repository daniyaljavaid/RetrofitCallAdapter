package com.dj.retrofitcalladapter.datasource.service

import com.dj.retrofitcalladapter.common.retrofit.RetrofitObject.createRetrofit
import retrofit2.create

object ServiceObject {
    fun getServiceObject() = createRetrofit().create<ApiService>()
}