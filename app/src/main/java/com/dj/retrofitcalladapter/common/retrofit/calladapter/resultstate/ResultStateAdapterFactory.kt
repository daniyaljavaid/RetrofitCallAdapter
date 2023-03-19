package com.dj.retrofitcalladapter.common.retrofit.calladapter.resultstate

import com.dj.retrofitcalladapter.common.ResultState
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ResultStateAdapterFactory : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        // return null if we can't handle any of the type and let retrofit handle itself

        // suspend functions wrap the response type in `Call`
        if (Call::class.java != getRawType(returnType)) {
            return null
        }

        // check first that the return type is `ParameterizedType`
        check(returnType is ParameterizedType) {
            "return type must be parameterized as Call<ResultState<<Foo>> or Call<ResultState<out Foo>>"
        }

        // get the response type inside the `Call` type
        val responseType = getParameterUpperBound(0, returnType)
        // if the response type is not ApiResponse then we can't handle this type, so we return null
        if (getRawType(responseType) != ResultState::class.java) {
            return null
        }

        // the response type is ApiResponse and should be parameterized
        check(responseType is ParameterizedType) { "Response must be parameterized as ResultState<Foo> or ResultState<out Foo>" }

        val successBodyType = getParameterUpperBound(0, responseType)

        return ResultStateAdapter<Any>(successBodyType)
    }
}
