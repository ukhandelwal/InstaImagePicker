package com.emizen.imagepicker

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.MediaColumns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.emizen.imagepicker.databinding.ActivityPickerBinding


open class Picker : AppCompatActivity(), PickerAdapter.OnItemclickListener {
    var adapter: PickerAdapter? = null
    var model: ArrayList<PickerModel>? = ArrayList()
    var dataBinding: ActivityPickerBinding? = null
    var position: Int? = -0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_picker)
        init()
    }


    private fun init() {
        dataBinding?.next?.setOnClickListener {
            val intent = Intent()
            intent.putExtra("image", model!![position!!].imagePath.toString())
            setResult(Activity.RESULT_OK, intent)
            super.onBackPressed()
        }
        dataBinding?.back?.setOnClickListener {
            onBackPressed()
        }

        dataBinding?.photos?.setOnClickListener {
            getAllShownImagesPath()
        }
        dataBinding?.videos?.setOnClickListener {
            getAllShownVideosPath()
        }
        adapter = PickerAdapter(this@Picker, model!!)
        dataBinding?.adapter = adapter
        adapter!!.setOnItemCliclListener(this)
    }

    private fun getAllShownImagesPath() {
        model?.clear()
        val uri: Uri
        val cursor: Cursor?
        val column_index_data: Int
        val column_index_folder_name: Int
        var absolutePathOfImage: String? = null
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaColumns.DATA,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME
        )
        cursor = this@Picker.contentResolver.query(
            uri, projection, null,
            null, null
        )
        column_index_data = cursor!!.getColumnIndexOrThrow(MediaColumns.DATA)
        column_index_folder_name =
            cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data)
            val listOfAllImages = PickerModel(absolutePathOfImage)
            model?.add(listOfAllImages)
        }
        adapter?.notifyDataSetChanged()
        position = 0
        dataBinding?.imageShow!!.visibility = View.VISIBLE
        dataBinding?.videoView?.visibility = View.GONE
        Glide.with(this@Picker)
            .load(model!![0].imagePath)
            .centerCrop()
//            .placeholder(R.drawable.ic_launcher_background)
            .into(dataBinding?.imageShow!!)
    }


    private fun getAllShownVideosPath() {
        model?.clear()
        val uri: Uri
        val cursor: Cursor?
        val column_index_data: Int
        val column_index_folder_name: Int
        var absolutePathOfImage: String? = null
        uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaColumns.DATA,
            MediaStore.Video.Media.BUCKET_DISPLAY_NAME
        )
        cursor = this@Picker.contentResolver.query(
            uri, projection, null,
            null, null
        )
        column_index_data = cursor!!.getColumnIndexOrThrow(MediaColumns.DATA)
        column_index_folder_name =
            cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME)
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data)
            val listOfAllImages = PickerModel(absolutePathOfImage)
//            Log.e("getAllShownVideosPath: ","absolutePathOfImage -"+ absolutePathOfImage)
            model?.add(listOfAllImages)
        }
        adapter?.notifyDataSetChanged()

        position = 0
        dataBinding?.imageShow!!.visibility = View.GONE
        dataBinding?.videoView?.visibility = View.VISIBLE
        dataBinding?.videoView?.setVideoURI(Uri.parse(model!![position!!].imagePath))
        dataBinding?.videoView?.setOnPreparedListener(MediaPlayer.OnPreparedListener { mp ->
            mp.isLooping = true
            dataBinding?.videoView?.start()
        })
        dataBinding?.videoView?.setOnCompletionListener { mp ->
            mp.release()
        }
    }

    override fun onItemClick(position: Int) {
        if (model!![position].imagePath.split(".").toString() == ".mp4") {
            dataBinding?.imageShow!!.visibility = View.GONE
            dataBinding?.videoView?.visibility = View.VISIBLE
            dataBinding?.videoView?.setVideoURI(Uri.parse(model!![position].imagePath))
            dataBinding?.videoView?.setOnPreparedListener(MediaPlayer.OnPreparedListener { mp ->
                mp.isLooping = true
                dataBinding?.videoView?.start()
            })
            dataBinding?.videoView?.setOnCompletionListener { mp ->
                mp.release()
            }
        } else {
            dataBinding?.videoView?.visibility = View.GONE
            dataBinding?.imageShow!!.visibility = View.VISIBLE
            Glide.with(this@Picker)
                .load(model!![position].imagePath)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(dataBinding?.imageShow!!)
        }
    }


}