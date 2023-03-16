package com.viniychuk.bebetter

import android.os.Bundle
import android.view.Menu
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.viniychuk.bebetter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())


//--------------------------------------------------------------------------------------------------

        binding.bNav.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.item1 -> replaceFragment(HomeFragment())
                R.id.item2 -> replaceFragment(ChatFragment())
                R.id.item3 -> replaceFragment(ProfileFragment())

                else -> {
                }
            }
            true
        }
//-------------------------------------нижнее меню -------------------------------------------------
//--------------------------------------------------------------------------------------------------
        setSupportActionBar(binding.appBarMain.toolbar)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.photo,
                R.id.nav_home,
                R.id.nav_gallery,
                R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
//------------------------------Боковое меню ↑ -----------------------------------------------------
//--------------------------------------------------------------------------------------------------

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

//-----------------------------Кнопка настройки ↑ --------------------------------------------------
//--------------------------------------------------------------------------------------------------

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

//-----------------------------кнопка зайти в боковое меню ↑ ---------------------------------------
//--------------------------------------------------------------------------------------------------

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host, fragment)
        fragmentTransaction.commit()

    }
}

