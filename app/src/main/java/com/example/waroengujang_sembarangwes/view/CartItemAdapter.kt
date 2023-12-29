package com.example.waroengujang_sembarangwes.view

import android.annotation.SuppressLint
import com.example.waroengujang_sembarangwes.model.CartItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.waroengujang_sembarangwes.R
import com.example.waroengujang_sembarangwes.databinding.CartItemBinding
import com.example.waroengujang_sembarangwes.databinding.FragmentCartBinding
import com.example.waroengujang_sembarangwes.model.Menu
import com.example.waroengujang_sembarangwes.viewmodel.SharedViewModel
import com.squareup.picasso.Picasso

class CartItemAdapter(private val cartItems: ArrayList<CartItem>,
                      private val sharedViewModel: SharedViewModel
)
    : RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder>() {

    class CartItemViewHolder(v: View): RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.cart_item, parent, false)
        return CartItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        var binding: CartItemBinding
        var txtNama = holder.itemView.findViewById<TextView>(R.id.txtNamaItem)
        var txtHarga = holder.itemView.findViewById<TextView>(R.id.txtHargaItemCart)
        var txtQty = holder.itemView.findViewById<TextView>(R.id.txtItemCartQty)
        var imgFoto = holder.itemView.findViewById<ImageView>(R.id.imgFotoCart)

        val cartItem = cartItems[position]

        val imageButtonAdd = holder.itemView.findViewById<ImageButton>(R.id.imageButtonAdd)
        val imageButtonMin = holder.itemView.findViewById<ImageButton>(R.id.imageButtonMin)

        // Set the initial quantity
        txtQty.text = cartItem.quantity.toString()

        imageButtonAdd.setOnClickListener {
            cartItem.quantity++
            txtQty.text = cartItem.quantity.toString()
            sharedViewModel.cartItems.value = ArrayList(cartItems)
        }

        imageButtonMin.setOnClickListener {
            if (cartItem.quantity > 1) {
                cartItem.quantity--
                txtQty.text = cartItem.quantity.toString()
            }
            sharedViewModel.cartItems.value = ArrayList(cartItems)

        }


        var nama = cartItem.menuItem.nama
        var harga = cartItem.menuItem.harga
        var qty = cartItem.quantity
        var foto = cartItem.menuItem.foto

        txtNama.text = "$nama"
        txtHarga.text = "Rp. $harga"
        txtQty.text = "$qty"
        Picasso.get().load(foto).into(imgFoto)

    }

    fun updateCart(newCart: List<CartItem>){
        cartItems.clear()
        cartItems.addAll(newCart)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }
}