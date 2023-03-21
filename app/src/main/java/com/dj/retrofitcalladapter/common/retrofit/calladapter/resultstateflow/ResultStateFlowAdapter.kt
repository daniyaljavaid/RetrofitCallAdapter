package com.dj.retrofitcalladapter.common.retrofit.calladapter.resultstateflow

import com.dj.retrofitcalladapter.common.ResultState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import kotlin.coroutines.resumeWithException

@ExperimentalCoroutinesApi
class ResultStateFlowAdapter<S : Any>(
    private val successType: Type,
) : CallAdapter<S, Flow<ResultState<S>>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<S>): Flow<ResultState<S>> {
        return flow {
            emit(
                suspendCancellableCoroutine { continuation ->
                    call.enqueue(object : Callback<S> {
                        override fun onFailure(call: Call<S>, t: Throwable) {
                            continuation.resumeWithException(t)
                        }

                        override fun onResponse(call: Call<S>, response: Response<S>) {
                            if (response.isSuccessful) {
                                if (response.body() != null) {
                                    continuation.resume(
                                        value = ResultState.Success(response.body()!!),
                                        null
                                    )
                                } else {
                                    continuation.resume(
                                        value = ResultState.Error(),
                                        null
                                    )
                                }
                            } else {
                                continuation.resume(
                                    value = ResultState.Error(),
                                    null
                                )
                            }
                        }
                    })
                    continuation.invokeOnCancellation { call.cancel() }
                }
            )
        }
    }
}
