package com.emizen.imagepicker

import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import java.time.Duration

object utils {
    /** Check if this device has a camera */
    fun checkCameraHardware(context: Context): Boolean {
        if (context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true
        } else {
            // no camera on this device
            return false
        }
    }


    fun makeToast(context: Context,msg: String){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
    }
}