package com.am24.onlineshopapp.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.am24.onlineshopapp.Model.BrandModel
import com.am24.onlineshopapp.R
import com.am24.onlineshopapp.databinding.ViewholderBrandBinding
import com.am24.onlineshopapp.databinding.ViewholderColorBinding
import com.bumptech.glide.Glide

class ColorAdapter (val items: MutableList<String>)
    : RecyclerView.Adapter<ColorAdapter.Viewholder>() {

    private var selectedPosition = 1
    private var lastSelectedPosition = -1
    private lateinit var context: Context

    class Viewholder(val binding: ViewholderColorBinding)
        : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ColorAdapter.Viewholder {
        context = p0.context
        val binding = ViewholderColorBinding.inflate(LayoutInflater.from(context), p0, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(p0: ColorAdapter.Viewholder, @SuppressLint("RecyclerView") p1: Int) {

        Glide.with(p0.itemView.context)
            .load(items[p1])
            .into(p0.binding.pic)

        p0.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = p1
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }

        if(selectedPosition == p1) {
            p0.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg_selected)
        } else {
            p0.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg)
        }
    }

    override fun getItemCount(): Int = items.size


}