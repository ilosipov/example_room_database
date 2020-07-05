package com.ilosipov.exampleroomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

/**
 * Класс MainActivity
 * @author Ilya Osipov (mailto:il.osipov.gm@gmail.com)
 * @since 05.07.2020
 * @version $Id$
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBarWithNavController(findNavController(R.id.fragment))
    }

    override fun onSupportNavigateUp() : Boolean {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}