package com.dj.retrofitcalladapter.common.retrofit

import com.dj.retrofitcalladapter.common.ResultState
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import java.lang.reflect.Type

class NetworkResponseAdapter<S : Any, E : Any>(
    private val successType: Type,
    private val errorBodyConverter: Converter<ResponseBody, E>
) : CallAdapter<S, Call<ResultState<S>>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<S>): Call<ResultState<S>> {
        return NetworkResponseCall(call, errorBodyConverter)
    }
}
