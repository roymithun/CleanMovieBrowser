package com.tista.cleanmoviebrowser.app.presentation

import android.os.Bundle
import com.tista.cleanmoviebrowser.R
import com.tista.cleanmoviebrowser.base.extension.navigateSafe
import com.tista.cleanmoviebrowser.base.presentation.activity.BaseActivity
import com.tista.cleanmoviebrowser.base.presentation.navigation.NavManager
import com.tista.cleanmoviebrowser.databinding.ActivityNavHostBinding
import org.koin.android.ext.android.inject

class NavHostActivity : BaseActivity() {

    private lateinit var binding: ActivityNavHostBinding

    private val navManager: NavManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavHostBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        navController = findNavController(binding.navHostFragment)
        initNavManager()
    }

    private fun initNavManager() {
        navManager.setOnNavEvent {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
            val currentFragment = navHostFragment?.childFragmentManager?.fragments?.get(0)

            currentFragment?.navigateSafe(it)
        }
    }
}
