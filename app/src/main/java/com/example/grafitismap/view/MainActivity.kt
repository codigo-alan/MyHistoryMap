package com.example.grafitismap.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.grafitismap.R
import com.example.grafitismap.databinding.ActivityMainBinding




class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    //Navigation Drawer
    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Navigation Drawer
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val drawerLayout = binding.drawerLayout
        binding.navigationView.setupWithNavController(navController)
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.splashFragment,
                R.id.loginFragment,
                R.id.signUpFragment,
                R.id.mapFragment,
                R.id.addMarkerFragment,
                R.id.markersListFragment,
                R.id.profileFragment
            ), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)

        //To lock the collapsed side menu
        val currentDestinationId = navController.currentDestination?.id

        if (currentDestinationId == R.id.loginFragment || currentDestinationId == R.id.signUpFragment){
            binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        }else {
            binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        }

    }

    //Navigation Drawer
    override fun onSupportNavigateUp(): Boolean {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}