package com.leedsbeckett.todo_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the layout for the activity
        setContentView(R.layout.activity_main)

        // Set up the action bar with the navigation controller
        setupActionBarWithNavController(findNavController(R.id.fragment))
    }

    // Called when the user navigates up in the app
    override fun onSupportNavigateUp(): Boolean
    {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}