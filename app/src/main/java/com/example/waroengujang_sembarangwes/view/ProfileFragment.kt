package com.example.waroengujang_sembarangwes.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.waroengujang_sembarangwes.R
import com.example.waroengujang_sembarangwes.viewmodel.WaiterViewModel
import com.squareup.picasso.Picasso
class ProfileFragment : Fragment() {

    private lateinit var waiterViewModel: WaiterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageView = view.findViewById<ImageView>(R.id.imageViewProfile)
        val txtNamaProfile = view.findViewById<TextView>(R.id.txtNamaProfile)
        val txtWorkSince = view.findViewById<TextView>(R.id.txtWorkSince)

        waiterViewModel = ViewModelProvider(this).get(WaiterViewModel::class.java)
        waiterViewModel.orderLD.observe(viewLifecycleOwner, { waiters ->
            waiters?.let {
                val firstOrder = it.first()
                txtNamaProfile.text = firstOrder.name
                txtWorkSince.text = "Work since: " + firstOrder.work_since
                Picasso.get().load(firstOrder.img_url).into(imageView)
            }
        })
        waiterViewModel.refresh()
         waiterViewModel.refresh()
    }
}
