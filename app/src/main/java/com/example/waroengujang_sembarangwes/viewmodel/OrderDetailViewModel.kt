package com.example.waroengujang_sembarangwes.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.waroengujang_sembarangwes.model.Order
import com.example.waroengujang_sembarangwes.model.OrderDetail
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class OrderDetailViewModel(application: Application): AndroidViewModel(application) {
    val orderLD = MutableLiveData<ArrayList<OrderDetail>>()
    val tag = "volleyOrderDetail"
    private var queue: RequestQueue? = null

    fun refresh() {
        queue = Volley.newRequestQueue(getApplication() )
        val url = "http://10.0.2.2/anmp/orderDetail.json"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<OrderDetail>>() { }.type
                val result = Gson().fromJson<List<OrderDetail>>(it, sType)
                orderLD.value = result as ArrayList<OrderDetail>?
                Log.d("volleyOrderDetail", result.toString())
            },
            {
                Log.d("volleyOrderDetail", it.toString())

            })
        stringRequest.tag = tag
        queue?.add(stringRequest)
    }
}