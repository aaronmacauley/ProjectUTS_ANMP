package com.example.waroengujang_sembarangwes.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.waroengujang_sembarangwes.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout

    private var submitTableNumberListener: SubmitTableNumberListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawerLayout)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentHost) as NavHostFragment
        navController = navHostFragment.navController

        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        val navView = findViewById<NavigationView>(R.id.navView)
        NavigationUI.setupWithNavController(navView, navController)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.setupWithNavController(navController)
    }

    // Method buat set listener
    fun setSubmitTableNumberListener(listener: SubmitTableNumberListener) {
        this.submitTableNumberListener = listener
    }

    // Method pas view di klik
    fun onClickSubmitTableNumber(view: View) {
        submitTableNumberListener?.onSubmitTableNumberListener(view)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawerLayout)
                || super.onSupportNavigateUp()
    }
}