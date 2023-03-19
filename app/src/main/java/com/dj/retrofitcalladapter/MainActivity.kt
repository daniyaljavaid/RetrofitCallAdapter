package com.dj.retrofitcalladapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dj.retrofitcalladapter.common.ResultState
import com.dj.retrofitcalladapter.datasource.service.ServiceObject
import com.dj.retrofitcalladapter.datasource.service.ServiceObject.getServiceObject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val apiService by lazy {
        getServiceObject()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        callApi()
        callApiWithResultState()
        callApiWithResultStateFlow()
    }

    private fun callApi() {
        GlobalScope.launch {
            try {
                val response = apiService.getData()
                Log.d("callApi----->", response.name)
            } catch (exception: Exception) {
                Log.e("callApi----->", exception.toString())
            }

        }
    }

    private fun callApiWithResultState() {
        GlobalScope.launch {
            try {
                when (val response = apiService.getDataWithResultState()) {
                    is ResultState.Success -> Log.d(
                        "callApiWithResultState----->",
                        response.data.name
                    )
                    is ResultState.Error -> Log.d("callApiWithResultState----->", "Network Error")
                }
            } catch (exception: Exception) {
                Log.e("callApiWithResultState----->", exception.toString())
            }
        }
    }

    private fun callApiWithResultStateFlow() {
        GlobalScope.launch {
            try { // to catch exception if flow is not allowed in factory
                apiService.getDataWithResultStateFlow()
                    .catch { exception ->
                        Log.e("callApiWithResultStateFlow----->", exception.toString())
                    }
                    .collect {
                        when (it) {
                            is ResultState.Success -> Log.d(
                                "callApiWithResultStateFlow----->",
                                it.data.name
                            )
                            is ResultState.Error -> Log.d(
                                "callApiWithResultStateFlow----->",
                                "Network Error"
                            )
                        }
                    }
            } catch (exception: Exception) {
                Log.e("callApiWithResultStateFlow----->", exception.toString())
            }
        }
    }
}