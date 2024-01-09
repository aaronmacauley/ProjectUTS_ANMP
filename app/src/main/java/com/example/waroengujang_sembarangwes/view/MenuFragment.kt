package com.example.waroengujang_sembarangwes.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.waroengujang_sembarangwes.R
import com.example.waroengujang_sembarangwes.databinding.FragmentHomeBinding
import com.example.waroengujang_sembarangwes.databinding.FragmentMenuBinding
import com.example.waroengujang_sembarangwes.model.Menu
import com.example.waroengujang_sembarangwes.viewmodel.MenuDetailViewModel
import com.example.waroengujang_sembarangwes.viewmodel.MenuViewModel
import com.example.waroengujang_sembarangwes.viewmodel.SharedViewModel

class MenuFragment : Fragment() {
    private lateinit var viewModel: MenuViewModel
    private lateinit var menuDetailViewModel: MenuDetailViewModel
    private lateinit var menuAdapter : MenuAdapter
    private lateinit var binding:FragmentMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        menuDetailViewModel = ViewModelProvider(requireActivity()).get(MenuDetailViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_menu, container, false)
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MenuViewModel::class.java)
        menuAdapter = MenuAdapter(arrayListOf(), menuDetailViewModel)
        viewModel.refresh()
        val recViewMenu = view.findViewById<RecyclerView>(R.id.recViewMenu)
        recViewMenu.layoutManager = LinearLayoutManager(context)
        recViewMenu.adapter = menuAdapter
        observeViewModel()

        val editSearch = view.findViewById<EditText>(R.id.editSearch)
        val btnSearch = view.findViewById<Button>(R.id.btnSearch)
        val txtChange = view.findViewById<TextView>(R.id.txtChange)
        val txtTable = view.findViewById<TextView>(R.id.txtTable)

        //default table number in case staff hasnt inserted table number in home
        var tableNumber = 1
        txtTable.text = "Table $tableNumber"
        //change to table number based on home
        val sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        sharedViewModel.tableNumber.observe(viewLifecycleOwner, { tableNumber ->
            txtTable.text = "Table $tableNumber"
        })

        txtChange.setOnClickListener{
            val action = MenuFragmentDirections.actionItemHome()
            Navigation.findNavController(it).navigate(action)
        }

        btnSearch.setOnClickListener {
            val searchQuery = editSearch.text?.toString()?.trim()

            val menus = viewModel.menuLd.value
            val filteredMenu = menus?.filter { menu ->
                searchQuery?.let { it1 -> menu?.nama?.contains(it1, ignoreCase = true) } == true
            }

            menuAdapter.updateMenu(filteredMenu as ArrayList<Menu>)
        }
    }

    fun observeViewModel() {
        viewModel.menuLd.observe(viewLifecycleOwner, Observer { menuList ->
            val menuArrayList = menuList?.toCollection(ArrayList()) ?: ArrayList()
            menuAdapter.updateMenu(menuArrayList)
        })
    }

}