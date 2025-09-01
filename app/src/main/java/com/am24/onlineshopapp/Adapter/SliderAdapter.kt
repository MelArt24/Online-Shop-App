package com.am24.onlineshopapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.am24.onlineshopapp.Model.SliderModel
import com.am24.onlineshopapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions

class SliderAdapter(private var sliderItems: List<SliderModel>, private val viewPager2: ViewPager2)
    : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    private lateinit var context: Context
    private val runnable = Runnable {
        sliderItems = sliderItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SliderViewHolder {
        context = p0.context
        val view = LayoutInflater.from(p0.context)
            .inflate(R.layout.slider_item_container, p0, false)
        return SliderViewHolder(view)
    }

    override fun onBindViewHolder(p0: SliderViewHolder, p1: Int) {
        p0.setImage(sliderItems[p1], context)
        if(p1==sliderItems.lastIndex-1){
            viewPager2.post(runnable)
        }
    }

    override fun getItemCount(): Int = sliderItems.size

    class SliderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageSlide)

        fun setImage(sliderItems: SliderModel, context: Context) {
            val requestOptions = RequestOptions().transform(CenterInside())

            Glide.with(context).load(sliderItems.url).apply(requestOptions).into(imageView)
        }
    }
}