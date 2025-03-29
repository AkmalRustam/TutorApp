package com.akmaldev.tutorapp.util.extension

import android.content.Context
import android.content.res.Resources
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun Fragment.toastMessage(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

}

fun AppCompatActivity.toastMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

}

fun EditText.hideInputEditText(activity: FragmentActivity) {
    this.clearFocus()
    val inputMethod =
        activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    inputMethod?.hideSoftInputFromWindow(this.windowToken, 0)
}

fun EditText.showInputEditText(activity: FragmentActivity) {
    this.requestFocus()
    val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun String.formatFullName(): String {
    val resultBuilder = StringBuilder()
    val splittedFullName = this.split(' ')
    splittedFullName.forEach {
        val result = "${it.first()}${it.drop(1).lowercase()}"
        resultBuilder.append(result)
        resultBuilder.append(" ")
    }
    return resultBuilder.toString().trim()
}

fun String.formatName(): String {
    return this.take(1).uppercase() + this.drop(1).lowercase()
}

fun Int.dpToPx(): Float {
    return this * Resources.getSystem().displayMetrics.density
}