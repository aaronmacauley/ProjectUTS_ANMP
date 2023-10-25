package com.example.waroengujang_sembarangwes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.waroengujang_sembarangwes.model.CartItem
import com.example.waroengujang_sembarangwes.view.CartItemAdapter

class SharedViewModel : ViewModel() {
    val tableNumber = MutableLiveData<String>()
    val cartItems = MutableLiveData<List<CartItem>>()
    val cartAdapter = MutableLiveData<CartItemAdapter>()
    val subtotal = MutableLiveData<Double>()
    val tax = MutableLiveData<Double>()
    val total = MutableLiveData<Double>()
}