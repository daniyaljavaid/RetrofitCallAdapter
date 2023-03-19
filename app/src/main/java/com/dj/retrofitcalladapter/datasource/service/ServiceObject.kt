package com.dj.retrofitcalladapter.datasource.service

import com.dj.retrofitcalladapter.datasource.RetrofitObject.createRetrofit
import retrofit2.create

object ServiceObject {
    fun getServiceObject() = createRetrofit().create<ApiService>()
}