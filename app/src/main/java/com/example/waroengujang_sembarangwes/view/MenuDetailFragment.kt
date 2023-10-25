package com.example.waroengujang_sembarangwes.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.waroengujang_sembarangwes.R
import com.example.waroengujang_sembarangwes.model.CartItem
import com.example.waroengujang_sembarangwes.viewmodel.CartViewModel
import com.example.waroengujang_sembarangwes.viewmodel.MenuDetailViewModel
import com.squareup.picasso.Picasso

class MenuDetailFragment : Fragment() {
    private lateinit var viewModel: MenuDetailViewModel
    private lateinit var cartViewModel: CartViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Initialize the ViewModel
        viewModel = ViewModelProvider(requireActivity()).get(MenuDetailViewModel::class.java)
        cartViewModel = ViewModelProvider(requireActivity()).get(CartViewModel::class.java)

        // Observe the LiveData
        viewModel.menuLD.observe(viewLifecycleOwner, { menu ->
            val txtNamaDetail = view?.findViewById<TextView>(R.id.txtNamaDetail)
            val txtHargaDetail = view?.findViewById<TextView>(R.id.txtHargaDetail)
            val txtDeskripsiDetail = view?.findViewById<TextView>(R.id.txtDeskripsiDetail)
            val imgFotoDetail = view?.findViewById<ImageView>(R.id.imgFotoDetail)

            txtNamaDetail?.text = menu.nama
            txtHargaDetail?.text = menu.harga.toString()
            txtDeskripsiDetail?.text = menu.deskripsi
            Picasso.get().load(menu.foto).into(imgFotoDetail)
        })

        var jumlah = 1
        var txtJumlahDetail = view?.findViewById<TextView>(R.id.txtJumlahDetail)
        val btnAddDetail = view?.findViewById<Button>(R.id.btnAddDetail)
        val btnTambahDetail = view?.findViewById<Button>(R.id.btnTambahDetail)
        val btnKurangDetail = view?.findViewById<Button>(R.id.btnKurangDetail)

        btnTambahDetail?.setOnClickListener{
            jumlah++
            txtJumlahDetail?.text = jumlah.toString()
        }

        btnKurangDetail?.setOnClickListener{
            if (jumlah > 1) {
                jumlah--
                txtJumlahDetail?.text = jumlah.toString()
            }
        }

        // Add to cart disini.
        btnAddDetail?.setOnClickListener{
            val menu = viewModel.menuLD.value
            menu?.let { menuItem ->
                val existingCartItem = cartViewModel.cartItems.value?.find { it.menuItem.nama == menuItem.nama }

                if (existingCartItem != null) {
                    existingCartItem.quantity += txtJumlahDetail?.text.toString().toInt()
                } else {
                    val cartItem = CartItem(menuItem, txtJumlahDetail?.text.toString().toInt())
                    cartViewModel.addToCart(cartItem)
                }
            }

            Toast.makeText(requireContext(), "Item added to Cart", Toast.LENGTH_SHORT).show()

            Log.e("XXX", "asu: ${cartViewModel.cartItems.value}", )
        }
    }
}