package com.example.weatherapp.Util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.weatherapp.locationPermissionCode


fun allPermissionsGranted(grantResults: IntArray): Boolean {
    grantResults.forEach {
        if (it == PackageManager.PERMISSION_DENIED) return false
    }
    return true
}

fun Fragment.checkPermissions(context: Context, permissionsGranted: () -> Unit) {
    val permissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    if (allPermissionsGranted(permissions.map { ContextCompat.checkSelfPermission(context, it) }.toIntArray())) {
        permissionsGranted()
    } else {
        requestPermissions(permissions, locationPermissionCode)
    }
}