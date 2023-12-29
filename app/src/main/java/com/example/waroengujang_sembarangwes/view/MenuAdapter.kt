package com.example.waroengujang_sembarangwes.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.waroengujang_sembarangwes.R
import com.example.waroengujang_sembarangwes.databinding.CartItemBinding
import com.example.waroengujang_sembarangwes.databinding.MenuItemBinding
import com.example.waroengujang_sembarangwes.model.Menu
import com.example.waroengujang_sembarangwes.viewmodel.MenuDetailViewModel
import com.squareup.picasso.Picasso


class MenuAdapter(private val menu: ArrayList<Menu>, private val menuDetailViewModel: MenuDetailViewModel)
    : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>(){
    class MenuViewHolder(v: View): RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.menu_item, parent, false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        var binding: MenuItemBinding

        var txtNama = holder.itemView.findViewById<TextView>(R.id.textView)
        var txtHarga = holder.itemView.findViewById<TextView>(R.id.txtTableOrder)
        var txtKategori = holder.itemView.findViewById<TextView>(R.id.txtHargaOrder)
        var imgFoto = holder.itemView.findViewById<ImageView>(R.id.imgFoto)
        var btnDetail = holder.itemView.findViewById<Button>(R.id.btnDetail)

        var nama = menu[position].nama
        var harga = menu[position].harga
        var kategori = menu[position].kategori
        var foto = menu[position].foto

        txtNama.text = "$nama"
        txtHarga.text = "Rp. $harga"
        txtKategori.text = "$kategori"
        Picasso.get().load(foto).into(imgFoto)

        btnDetail.setOnClickListener(){
            val selectedItem = menu[position]

            menuDetailViewModel.setSelectedMenu(selectedItem)

            val action = MenuFragmentDirections.actionMenuDetailFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updateMenu(newMenu: ArrayList<Menu>){
        menu.clear()
        menu.addAll(newMenu)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return menu.size
    }


}