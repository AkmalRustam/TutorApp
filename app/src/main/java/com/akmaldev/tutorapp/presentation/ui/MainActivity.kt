package com.akmaldev.tutorapp.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.akmaldev.tutorapp.util.extension.setLightNavigationBar
import com.akmaldev.tutorapp.util.extension.setLightStatusBar
import com.akmaldev.tutorapp.util.extension.setNavigationBarColor
import com.akmaldev.tutorapp.util.extension.setStatusBarColor
import com.akmaldev.tutorapp.R
import com.akmaldev.tutorapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        with(binding) {
            setValues()
            setListeners()
        }
    }

    private fun ActivityMainBinding.setListeners() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fragment_container_view)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            Log.d(TAG, "destination: ${destination.label}")
            when (destination.id) {
                R.id.splashFragment -> {
                    setStatusBarColor(R.color.white)
                    setLightStatusBar(true)
                    setNavigationBarColor(R.color.white)
                    setLightNavigationBar(true)
                    makeDefaultScreen()
                }

                R.id.userTypeFragment,
                R.id.authFragment,
                R.id.studentFragment,
                R.id.profileFragment,
                R.id.notificationFragment,
                R.id.capturedImageFragment,
                R.id.questionFragment,
                R.id.tutorFragment,
                R.id.tutorProfileFragment,
                R.id.tutorNotificationFragment -> {
                    setStatusBarColor(R.color.light_gray)
                    setLightStatusBar(true)
                    setNavigationBarColor(R.color.light_gray)
                    setLightNavigationBar(true)
                    makeDefaultScreen()
                }

                R.id.cameraFragment -> {
                    makeFullScreen()
                }
            }
        }
    }

    private fun makeDefaultScreen() {
        WindowCompat.getInsetsController(window, window.decorView).apply {
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
            show(WindowInsetsCompat.Type.navigationBars())
            show(WindowInsetsCompat.Type.statusBars())
        }
    }

    private fun makeFullScreen() {
        WindowCompat.getInsetsController(window, window.decorView).apply {
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            hide(WindowInsetsCompat.Type.navigationBars())
            hide(WindowInsetsCompat.Type.statusBars())
        }
    }

    private fun ActivityMainBinding.setValues() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController = navHostFragment.navController
    }

    companion object {
        private const val TAG = "MainActivityTag"
    }
}