package com.dj.retrofitcalladapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dj.retrofitcalladapter.common.ResultState
import com.dj.retrofitcalladapter.datasource.service.ServiceObject
import com.dj.retrofitcalladapter.datasource.service.ServiceObject.getServiceObject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        callApi()
    }

    private fun callApi() {
        GlobalScope.launch {
            try {
                when (val response = getServiceObject().getData()) {
                    is ResultState.Success -> Log.d("----->", response.data.name)
                    is ResultState.Error -> Log.d("----->", "Network Error")
                }
            } catch (exception: Exception) {
                Log.e("----->", exception.toString())
            }

        }
    }
}