package com.dj.retrofitcalladapter.common.retrofit.calladapter.resultstateflow

import com.dj.retrofitcalladapter.common.ResultState
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ResultStateFlowAdapterFactory : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        // return null if we can't handle any of the type and let retrofit handle itself

        if (Flow::class.java != getRawType(returnType)) {
            return null
        }

        // check first that the return type is `ParameterizedType`
        check(returnType is ParameterizedType) {
            "return type must be parameterized as Flow<ResultState<<Foo>>"
        }

        // get the response type inside the `Flow` type
        val responseType = getParameterUpperBound(0, returnType)
        // if the response type is not ResultState then we can't handle this type, so we return null
        if (getRawType(responseType) != ResultState::class.java) {
            return null
        }

        // the response type is ResultState and should be parameterized
        check(responseType is ParameterizedType) { "Response must be parameterized as ResultState<Foo> or ResultState<out Foo>" }

        val successBodyType = getParameterUpperBound(0, responseType)

        return ResultStateFlowAdapter<Any>(successBodyType)
    }
}
