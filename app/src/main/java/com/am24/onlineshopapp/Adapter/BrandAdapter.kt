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
import com.bumptech.glide.Glide

class BrandAdapter (val items: MutableList<BrandModel>)
    : RecyclerView.Adapter<BrandAdapter.Viewholder>() {

    private var selectedPosition = 1
    private var lastSelectedPosition = -1
    private lateinit var context: Context

    class Viewholder(val binding: ViewholderBrandBinding)
        : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BrandAdapter.Viewholder {
        context = p0.context
        val binding = ViewholderBrandBinding.inflate(LayoutInflater.from(context), p0, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(p0: BrandAdapter.Viewholder, @SuppressLint("RecyclerView") p1: Int) {
        val item = items[p1]
        p0.binding.title.text = item.title

        Glide.with(p0.itemView.context)
            .load(item.picUrl)
            .into(p0.binding.pic)

        p0.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = p1
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }

        p0.binding.title.setTextColor(context.resources.getColor(R.color.white))

        if(selectedPosition == p1) {
            p0.binding.pic.setBackgroundResource(0)
            p0.binding.mainLayout.setBackgroundResource(R.drawable.purple_bg)
            ImageViewCompat.setImageTintList(p0.binding.pic, ColorStateList.valueOf(context.getColor(R.color.white)))
            p0.binding.title.visibility = View.VISIBLE
        } else {
            p0.binding.pic.setBackgroundResource(R.drawable.grey_bg)
            p0.binding.mainLayout.setBackgroundResource(0)
            ImageViewCompat.setImageTintList(p0.binding.pic, ColorStateList.valueOf(context.getColor(R.color.black)))
            p0.binding.title.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = items.size


}