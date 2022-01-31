package com.instaimagepicker

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.instaimagepicker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var dataBinding: ActivityMainBinding? = null;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        dataBinding?.imagePicker?.setOnClickListener {
            startForResultLostItemFound.launch(Intent(this@MainActivity, Picker::class.java))
        }
    }

    val startForResultLostItemFound =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                var value = result.data?.getStringExtra("image")
                Log.d("image", value.toString())
            }
        }


}