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
import com.example.waroengujang_sembarangwes.viewmodel.SharedViewModel

class CartFragment : Fragment() {
    private lateinit var sharedViewModel: SharedViewModel
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
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewCart)
        recyclerView.layoutManager = LinearLayoutManager(context)
//        cartAdapter = sharedViewModel.cartAdapter.value ?: CartItemAdapter(ArrayList())
        cartAdapter = CartItemAdapter(ArrayList(), sharedViewModel)

        recyclerView.adapter = cartAdapter

        txtSubtotal = view.findViewById(R.id.txtSubtotal)
        txtTax = view.findViewById(R.id.txtTax)
        txtTotal = view.findViewById(R.id.txtTotal)

        sharedViewModel.cartItems.observe(viewLifecycleOwner) { cartItems ->

            cartAdapter.updateCart(cartItems)
            val subtotal = calculateSubtotal(cartItems)
            val tax = calculateTax(subtotal)
            val total = subtotal + tax

            sharedViewModel.subtotal.value = subtotal
            sharedViewModel.tax.value = tax
            sharedViewModel.total.value = total

            txtSubtotal.text = "Subtotal: IDR $subtotal"
            txtTax.text = "Tax (10%): IDR $tax"
            txtTotal.text = "Total: IDR $total"
        }

        return view
    }

    private fun calculateSubtotal(cartItems: List<CartItem>): Double {
        return cartItems.sumOf { it.menuItem.harga!!.toDouble() * it.quantity }
    }

    private fun calculateTax(subtotal: Double): Double {
        return subtotal * 0.10
    }

}