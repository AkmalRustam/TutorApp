package com.akmaldev.tutorapp.util.permission

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class CheckPermissions (private val context: Context) {
    fun hasReadExternalStoragePermission(): Boolean {
        val permission = Manifest.permission.READ_EXTERNAL_STORAGE
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
    }
    fun hasReadMediaImagesPermission(): Boolean {
        val permission = Manifest.permission.READ_MEDIA_IMAGES
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
    }
}