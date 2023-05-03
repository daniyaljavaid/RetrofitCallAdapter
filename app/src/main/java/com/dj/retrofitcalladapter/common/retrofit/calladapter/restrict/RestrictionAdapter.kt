package com.dj.retrofitcalladapter.common.retrofit.calladapter.restrict

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class RestrictionAdapter<S : Any> : CallAdapter<S, Call<Any>> {

    override fun responseType(): Type {
        throw IllegalStateException("Restricted type")
    }

    override fun adapt(call: Call<S>): Call<Any> {
        throw IllegalStateException("Restricted type")
    }
}
