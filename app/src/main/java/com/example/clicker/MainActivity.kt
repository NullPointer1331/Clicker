package com.example.clicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.clicker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ClickerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)

        val application = requireNotNull(this).application
        val database = ClickerDatabase.getInstance(application)
        val playerDao = database.playerDao
        val shopItemDao = database.shopItemDao
        val viewModelFactory = ClickerViewModelFactory(playerDao, shopItemDao)
        viewModel = ViewModelProvider(this, viewModelFactory)[ClickerViewModel::class.java]

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(object : Runnable {
            override fun run() {
                viewModel.passSecond()
                handler.postDelayed(this, 1000)
            }
        }, 1000)
    }
}