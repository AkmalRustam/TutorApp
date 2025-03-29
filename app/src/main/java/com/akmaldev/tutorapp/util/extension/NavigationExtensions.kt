package com.akmaldev.tutorapp.util.extension

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

fun Fragment.navigateTo(direction: Int) {
    findNavController().navigate(direction)
}

fun Fragment.navigateTo(direction: NavDirections) {
    findNavController().navigate(direction)
}

fun Fragment.popBackStack(): Boolean {
    return findNavController().popBackStack()
}

fun Fragment.popBackStack(@IdRes destinationId: Int, inclusive: Boolean): Boolean {
    return findNavController().popBackStack(destinationId, inclusive)
}

fun Fragment.popBackStack(
    @IdRes destinationId: Int,
    inclusive: Boolean,
    saveState: Boolean
): Boolean {
    return findNavController().popBackStack(destinationId, inclusive, saveState)
}

fun Fragment.popBackStack(
    route: String,
    inclusive: Boolean,
    saveState: Boolean = false
): Boolean {
    return findNavController().popBackStack(route, inclusive, saveState)
}