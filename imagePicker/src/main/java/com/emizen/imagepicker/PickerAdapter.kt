package com.emizen.imagepicker

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.emizen.imagepicker.databinding.ListRowBinding
import com.emizen.imagepicker.utils.loadImage


class PickerAdapter(val context: Context, val model: ArrayList<PickerModel>) :
    RecyclerView.Adapter<PickerAdapter.ViewHolder>() {
    private var mListener: OnItemclickListener? = null


    interface OnItemclickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemCliclListener(listener: OnItemclickListener?) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ListRowBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_row,
            parent,
            false
        )
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.binding.image.let {
            loadImage(context,model[position].imagePath,it)
        }
        viewHolder.binding.image.setOnClickListener {
            if (mListener != null) {
                if (position != RecyclerView.NO_POSITION)
                    mListener!!.onItemClick(position)
            }
        }
    }


    override fun getItemCount(): Int {
        return model.size
    }


    inner class ViewHolder(itemView: ListRowBinding) : RecyclerView.ViewHolder(itemView.root) {
        var binding: ListRowBinding = itemView
    }
}