package com.dj.retrofitcalladapter.common.retrofit.calladapter.resultstate

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class ResultStateExceptionAdapter<S : Any> : CallAdapter<S, Call<Any>> {

    override fun responseType(): Type {
        throw IllegalStateException("Unsupported type")
    }

    override fun adapt(call: Call<S>): Call<Any> {
        throw IllegalStateException("Unsupported type")
    }
}
