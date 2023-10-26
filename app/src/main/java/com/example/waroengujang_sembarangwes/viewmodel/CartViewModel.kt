package com.example.waroengujang_sembarangwes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.waroengujang_sembarangwes.model.CartItem
import com.example.waroengujang_sembarangwes.model.Menu


class CartViewModel (application: Application) : AndroidViewModel(application) {
    val cartItems = MutableLiveData<ArrayList<CartItem>>()

    fun addToCart(cartItem: CartItem) {
        val currentCart = cartItems.value.orEmpty()
        val currentCartArrayList = ArrayList(currentCart) // Convert to ArrayList
        val existingItem = currentCartArrayList.find { it.menuItem.nama == cartItem.menuItem.nama }

        if (existingItem != null) {
            existingItem.quantity += cartItem.quantity
        } else {
            currentCartArrayList.add(cartItem)
        }

        cartItems.value = currentCartArrayList

    }

    fun processToKitchen() {
        cartItems.value?.clear()
    }


}