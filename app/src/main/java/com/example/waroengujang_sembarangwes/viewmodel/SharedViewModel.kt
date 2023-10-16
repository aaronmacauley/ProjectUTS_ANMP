package com.example.waroengujang_sembarangwes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val tableNumber = MutableLiveData<String>()
}