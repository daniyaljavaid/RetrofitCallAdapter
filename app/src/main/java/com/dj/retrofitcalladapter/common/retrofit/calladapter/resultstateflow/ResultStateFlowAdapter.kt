package com.dj.retrofitcalladapter.common.retrofit.calladapter.resultstateflow

import com.dj.retrofitcalladapter.common.ResultState
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class ResultStateFlowAdapter<S : Any>(
    private val successType: Type,
) : CallAdapter<S, Call<Flow<ResultState<S>>>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<S>): Call<Flow<ResultState<S>>> {
        return ResultStateFlowCall(call)
    }
}
