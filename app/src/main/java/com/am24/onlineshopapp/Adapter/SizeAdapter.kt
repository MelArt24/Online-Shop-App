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
import com.am24.onlineshopapp.databinding.ViewholderSizeBinding
import com.bumptech.glide.Glide

class SizeAdapter (val items: MutableList<String>)
    : RecyclerView.Adapter<SizeAdapter.Viewholder>() {

    private var selectedPosition = 1
    private var lastSelectedPosition = -1
    private lateinit var context: Context

    class Viewholder(val binding: ViewholderSizeBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SizeAdapter.Viewholder {
        context = p0.context
        val binding = ViewholderSizeBinding.inflate(LayoutInflater.from(context), p0, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(p0: SizeAdapter.Viewholder, @SuppressLint("RecyclerView") p1: Int) {

        p0.binding.sizeTxt.text = items[p1]

        p0.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = p1
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }

        if(selectedPosition == p1) {
            p0.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg_selected)
            p0.binding.sizeTxt.setTextColor(context.resources.getColor(R.color.purple))
        } else {
            p0.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg)
            p0.binding.sizeTxt.setTextColor(context.resources.getColor(R.color.black))
        }
    }

    override fun getItemCount(): Int = items.size


}