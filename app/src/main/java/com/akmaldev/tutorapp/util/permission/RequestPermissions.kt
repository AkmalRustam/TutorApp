package com.akmaldev.tutorapp.util.permission

import android.Manifest
import android.app.Activity
import androidx.core.app.ActivityCompat

class RequestPermissions {
    fun requestReadExternalStoragePermission(activity: Activity, requestCode: Int) {
        val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        ActivityCompat.requestPermissions(activity, permissions, requestCode)
    }

    fun requestReadMediaImagesPermission(activity: Activity, requestCode: Int) {
        val permissions = arrayOf(Manifest.permission.READ_MEDIA_IMAGES)
        ActivityCompat.requestPermissions(activity, permissions, requestCode)
    }
}