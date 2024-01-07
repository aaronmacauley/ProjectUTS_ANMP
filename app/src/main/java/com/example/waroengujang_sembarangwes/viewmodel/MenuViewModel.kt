package com.example.waroengujang_sembarangwes.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.waroengujang_sembarangwes.model.Menu
import com.example.waroengujang_sembarangwes.model.WaroengUjangDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class MenuViewModel(application: Application): AndroidViewModel(application) {

    val menuLd = MutableLiveData<List<Menu>>()

    private var job = Job()
    override val coroutineContext: CoroutineContext get() = job + Dispatchers.IO

    fun refresh() {

        menuLd.value = true

        launch {
            val db = Room.databaseBuilder(
                getApplication(),
                WaroengUjangDatabase::class.java, "WaroengUjang"
            ).build()

            menuLd.postValue(db.WaroengUjangDao().selectAllTodo())
        }
    }
    fun clearTask(menu: Menu) {
        launch {
            val db = Room.databaseBuilder(
                getApplication(),
                WaroengUjangDatabase::class.java, "WaroengUjang").build()
            db.WaroengUjangDao().deleteTodo(menu)

            menuLd.postValue(db.WaroengUjangDao().selectAllTodo())
        }
    }

}

//    val menusLD = MutableLiveData<ArrayList<Menu>>()
//    val TAG = "volleyTagMenu"
//    private var queue: RequestQueue? = null
//
//    fun refresh() {
//        queue = Volley.newRequestQueue(getApplication())
//        val url = "http://10.0.2.2/anmp/menu.json"
//
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            {
//                val sType = object: TypeToken<List<Menu>>(){}.type
//                val result = Gson().fromJson<List<Menu>>(it, sType)
//                menusLD.value = result as ArrayList<Menu>?
//                Log.d("showvoley", it)
//            },
//            {
//                Log.d("showvoley", it.toString())
//            })
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
//
//    }
//}
