package com.example.waroengujang_sembarangwes.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.waroengujang_sembarangwes.model.Order
import com.example.waroengujang_sembarangwes.R


class RvAdapterOrder(private val orderList: List<Order>, private val navController: NavController)
    : RecyclerView.Adapter<RvAdapterOrder.OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.order_layout, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val currentOrder = orderList[position]
        if (currentOrder.status == 0) {
            holder.tableNo.text = currentOrder.no_table.toString()
            holder.totalPrice.text ="IDR "+ currentOrder.harga_total.toString()
            holder.duration.text = currentOrder.duration
            holder.status.text = currentOrder.status.toString()
            holder.itemView.visibility = View.VISIBLE
        } else {
            holder.itemView.visibility = View.GONE
        }
        holder.btnDetail.setOnClickListener {
            val action = OrderFragmentDirections.ActionOrderDetail()
            holder.itemView.findNavController().navigate(action)
        }


    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    inner class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tableNo: TextView = itemView.findViewById(R.id.txtTableOrder)
        val totalPrice: TextView = itemView.findViewById(R.id.txtHargaOrder)
        val duration: TextView = itemView.findViewById(R.id.txtDurationOrder)
        val status: TextView = itemView.findViewById(R.id.txtStatusOrder)
        val btnDetail: Button = itemView.findViewById(R.id.btnOrderDetail)
    }
}
