package com.dj.retrofitcalladapter.common.retrofit.calladapter.resultstateflow

import com.dj.retrofitcalladapter.common.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import okhttp3.Request
import retrofit2.Response
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import java.io.IOException

internal class ResultStateFlowCall<S : Any>(
    private val delegate: Call<S>
) : Call<Flow<ResultState<S>>> {

    override fun enqueue(callback: Callback<Flow<ResultState<S>>>) {
        return delegate.enqueue(object : Callback<S> {
            override fun onResponse(call: Call<S>, response: Response<S>) {
                val body = response.body()
                val code = response.code()
                val error = response.errorBody()

                if (response.isSuccessful) {
                    if (body != null) {
                        callback.onResponse(
                            this@ResultStateFlowCall,
                            Response.success(flowOf(ResultState.Success(body)))
                        )
                    } else {
                        // Response is successful but the body is null
                        callback.onResponse(
                            this@ResultStateFlowCall,
                            Response.success(flowOf(ResultState.Error(null)))
                        )
                    }
                } else {
                    callback.onResponse(
                        this@ResultStateFlowCall,
                        Response.success(flowOf(ResultState.Error(null)))
                    )
                }
            }

            override fun onFailure(call: Call<S>, throwable: Throwable) {
                val networkResponse = when (throwable) {
                    is IOException -> ResultState.Error(throwable)
                    else -> ResultState.Error(throwable)
                }
                callback.onResponse(
                    this@ResultStateFlowCall,
                    Response.success(flowOf(networkResponse))
                )
            }
        })
    }

    override fun isExecuted() = delegate.isExecuted

    override fun clone() = ResultStateFlowCall(delegate.clone())

    override fun isCanceled() = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute(): Response<Flow<ResultState<S>>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()
}
