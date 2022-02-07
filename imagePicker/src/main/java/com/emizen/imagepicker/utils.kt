package com.emizen.imagepicker

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


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

    fun  loadImage(context: Context,url: String,imageView: ImageView){
        Glide.with(context)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.drawable.placeholder)
            .into(imageView)
    }


    fun makeToast(context: Context,msg: String){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
    }
}