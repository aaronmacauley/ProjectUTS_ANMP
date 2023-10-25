package com.example.waroengujang_sembarangwes.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.waroengujang_sembarangwes.R
import com.example.waroengujang_sembarangwes.model.CartItem
import com.example.waroengujang_sembarangwes.viewmodel.CartViewModel

class CartFragment : Fragment() {
    private lateinit var cartViewModel: CartViewModel
    private lateinit var cartAdapter: CartItemAdapter
    private lateinit var txtSubtotal: TextView
    private lateinit var txtTax: TextView
    private lateinit var txtTotal: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        // Initialize ViewModel
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)

        // Initialize RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewCart)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Initialize subtotal, tax, and total TextViews
        txtSubtotal = view.findViewById(R.id.txtSubtotal)
        txtTax = view.findViewById(R.id.txtTax)
        txtTotal = view.findViewById(R.id.txtTotal)

        // Initialize the CartItemAdapter
        cartAdapter = CartItemAdapter(ArrayList()) // Initialize with an empty list
        recyclerView.adapter = cartAdapter

        // Observe changes in the cart items
        cartViewModel.cartItems.observe(viewLifecycleOwner, Observer { cartItems ->
            // Update the adapter with the new cart items
            cartAdapter.updateCart(cartItems)

            // Calculate subtotal, tax, and total based on cartItems and update corresponding TextViews
            val subtotal = calculateSubtotal(cartItems)
            val tax = calculateTax(subtotal)
            val total = subtotal + tax
            txtSubtotal.text = "IDR $subtotal"
            txtTax.text = "IDR $tax"
            txtTotal.text = "IDR $total"
        })

        return view
    }
    
    private fun calculateSubtotal(cartItems: List<CartItem>): Double {
        return cartItems.sumOf { it.menuItem.harga!!.toDouble() * it.quantity }
    }

    private fun calculateTax(subtotal: Double): Double {
        return subtotal * 0.10
    }

}