package com.dj.retrofitcalladapter.common.retrofit.calladapter.restrict

import com.dj.retrofitcalladapter.common.ResultState
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class RestrictionAdapterFactory : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        // when using suspend modifier, it wraps returnType T to Call<T>
        if (Call::class.java != getRawType(returnType)) {
            return RestrictionAdapter<Any>()
        }

        check(returnType is ParameterizedType) {
            "return type must be parameterized as Call<ResultState<<T>>"
        }

        val responseType = getParameterUpperBound(0, returnType)

        if (getRawType(responseType) != ResultState::class.java) {
            return RestrictionAdapter<Any>()
        }

        return null
    }
}
