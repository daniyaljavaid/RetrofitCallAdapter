package com.dj.retrofitcalladapter.common.retrofit

import com.dj.retrofitcalladapter.common.retrofit.calladapter.restrict.RestrictionAdapterFactory
import com.dj.retrofitcalladapter.common.retrofit.calladapter.resultstate.ResultStateAdapterFactory
import com.dj.retrofitcalladapter.common.retrofit.calladapter.resultstateflow.ResultStateFlowAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitObject {
    private val moshi = Moshi.Builder().build()
    private val okHttpClient = OkHttpClient.Builder().build()
    private const val baseUrl = "https://api.agify.io/"

    fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RestrictionAdapterFactory())
            .addCallAdapterFactory(ResultStateAdapterFactory())
            .addCallAdapterFactory(ResultStateFlowAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }
}