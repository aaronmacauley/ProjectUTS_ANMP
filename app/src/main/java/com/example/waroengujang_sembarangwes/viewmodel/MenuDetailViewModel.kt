package com.example.waroengujang_sembarangwes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.waroengujang_sembarangwes.model.Menu

class MenuDetailViewModel(application: Application): AndroidViewModel(application) {
    val menuLD = MutableLiveData<Menu>()

    fun setSelectedMenu(menu: Menu) {
        menuLD.value = menu
    }
}