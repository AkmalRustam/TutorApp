package com.akmaldev.tutorapp.util.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment

/**
 * This function is responsible for the color of the status bar
 */
fun AppCompatActivity.setStatusBarColor(statusBarColor: Int) {
    window.statusBarColor = getColor(statusBarColor)
}


/**
 * This function is responsible for the color of the navigation bar
 */
fun AppCompatActivity.setNavigationBarColor(navigationBarColor: Int) {
    window.navigationBarColor = getColor(navigationBarColor)
}

/**
 * If state true, the navigation bar icons is dark.
 * If state false, the navigation bar icons is light
 */
fun AppCompatActivity.setLightNavigationBar(state: Boolean) {
    WindowInsetsControllerCompat(
        window,
        window.decorView
    ).isAppearanceLightNavigationBars = state
}

/**
 * If state true, the status bar icons is dark.
 * If state false, the status bar icons is light
 */
fun AppCompatActivity.setLightStatusBar(state: Boolean) {
    WindowInsetsControllerCompat(
        window,
        window.decorView
    ).isAppearanceLightStatusBars = state
}

/**
 * This function is responsible for the color of the status bar
 */
fun Fragment.setStatusBarColor(statusBarColor: Int) {
    requireActivity().window.statusBarColor = requireContext().getColor(statusBarColor)
}


/**
 * This function is responsible for the color of the navigation bar
 */
fun Fragment.setNavigationBarColor(navigationBarColor: Int) {
    requireActivity().window.navigationBarColor = requireContext().getColor(navigationBarColor)
}

/**
 * If state true, the navigation bar icons is dark.
 * If state false, the navigation bar icons is light
 */
fun Fragment.setLightNavigationBar(state: Boolean) {
    WindowInsetsControllerCompat(
        requireActivity().window,
        requireActivity().window.decorView
    ).isAppearanceLightNavigationBars = state
}

/**
 * If state true, the status bar icons is dark.
 * If state false, the status bar icons is light
 */
fun Fragment.setLightStatusBar(state: Boolean) {
    WindowInsetsControllerCompat(
        requireActivity().window,
        requireActivity().window.decorView
    ).isAppearanceLightStatusBars = state
}