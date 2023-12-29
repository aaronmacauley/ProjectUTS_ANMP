package com.example.waroengujang_sembarangwes.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.waroengujang_sembarangwes.R
import com.example.waroengujang_sembarangwes.viewmodel.OrderDetailViewModel
import androidx.navigation.fragment.findNavController
import com.example.waroengujang_sembarangwes.databinding.FragmentOrderDetailBinding


class OrderDetailFragment : Fragment() {

    private lateinit var viewModel: OrderDetailViewModel
    private lateinit var binding:FragmentOrderDetailBinding

    private lateinit var txtTableOd: TextView
    private lateinit var txtDurationOd: TextView
    private lateinit var txtHargaOd: TextView
    private lateinit var txtOrderOd: TextView
    private lateinit var btnOrderMore: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_order_detail, container, false)

        txtTableOd = view.findViewById(R.id.txtTableOd)
        txtDurationOd = view.findViewById(R.id.txtDurationOD)
        txtHargaOd = view.findViewById(R.id.txtHargaOd)
        txtOrderOd = view.findViewById(R.id.txtOrderOD)
        btnOrderMore=view.findViewById(R.id.btnOrderMore)

        viewModel = ViewModelProvider(this).get(OrderDetailViewModel::class.java)

        viewModel.orderLD.observe(viewLifecycleOwner, Observer { orderDetails ->
            orderDetails?.let {
                val orderDetail = orderDetails[0]
                txtTableOd.text = orderDetail.table.toString()
                txtDurationOd.text = orderDetail.duration
                txtHargaOd.text = orderDetail.total_harga.toString()
                txtOrderOd.text = orderDetail.menu
            }
        })

        btnOrderMore.setOnClickListener {
            val action = OrderDetailFragmentDirections.ActionItemMenu()
            Navigation.findNavController(it).navigate(action)
        }




        viewModel.refresh()

        return view
    }

}