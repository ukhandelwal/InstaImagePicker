package com.instaimagepicker

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var button: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.imagePicker)
        button?.setOnClickListener {
            startForResultLostItemFound.launch(Intent(this@MainActivity, Picker::class.java))
        }
    }

    private val startForResultLostItemFound =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                var value = result.data?.getStringExtra("image")
                Log.d("image", value.toString())
            }
        }

    companion object {
        fun onClick(context: Context) {
            Toast.makeText(context, "hello", Toast.LENGTH_LONG).show()
        }
    }

}