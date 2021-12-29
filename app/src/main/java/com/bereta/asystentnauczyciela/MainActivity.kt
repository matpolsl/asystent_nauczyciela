package com.bereta.asystentnauczyciela


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        navController = Navigation.findNavController(this,R.id.myNavHostFragment)
        val bottom_nav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottom_nav.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.subjectsList || destination.id == R.id.studentsList || destination.id == R.id.settings) {
                bottom_nav.visibility = View.VISIBLE
            } else {
                bottom_nav.visibility = View.GONE
            }
        }
        //NavigationUI.setupActionBarWithNavController(this, navController)
    }
}