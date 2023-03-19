package com.dj.retrofitcalladapter.datasource.service

import com.dj.retrofitcalladapter.datasource.RetrofitObject
import retrofit2.create

object ServiceObject {
    fun getServiceObject() = RetrofitObject.createRetrofit().create<ApiService>()
}