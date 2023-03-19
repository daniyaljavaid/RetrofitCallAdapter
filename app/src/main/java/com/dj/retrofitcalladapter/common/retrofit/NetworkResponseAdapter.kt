package com.dj.retrofitcalladapter.common.retrofit

import com.dj.retrofitcalladapter.common.ResultState
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class NetworkResponseAdapter<S : Any>(
    private val successType: Type,
) : CallAdapter<S, Call<ResultState<S>>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<S>): Call<ResultState<S>> {
        return NetworkResponseCall(call)
    }
}
