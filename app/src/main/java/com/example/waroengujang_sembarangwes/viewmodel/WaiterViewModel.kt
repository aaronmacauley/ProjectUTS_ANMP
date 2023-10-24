package com.example.waroengujang_sembarangwes.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.waroengujang_sembarangwes.model.Waiter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WaiterViewModel(application: Application): AndroidViewModel(application) {
    val orderLD = MutableLiveData<ArrayList<Waiter>>()
    val tag = "volleyMenu"
    private var queue: RequestQueue? = null

    fun refresh() {
        queue = Volley.newRequestQueue(getApplication() )
        val url = "http://10.0.2.2/anmp/waiter.json"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Waiter>>() { }.type
                val result = Gson().fromJson<List<Waiter>>(it, sType)
                orderLD.value = result as ArrayList<Waiter>?
                Log.d("waiter", result.toString())
            },
            {
                Log.d("waiter", it.toString())

            })
        stringRequest.tag = tag
        queue?.add(stringRequest)
    }
}