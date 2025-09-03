package com.am24.onlineshopapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.am24.onlineshopapp.Model.ItemsModel
import com.am24.onlineshopapp.activity.DetailActivity
import com.am24.onlineshopapp.databinding.ViewholderRecommendedBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions

class PopularAdapter(val items: MutableList<ItemsModel>)
    : RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

    private var context: Context? = null

    class ViewHolder (val binding: ViewholderRecommendedBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PopularAdapter.ViewHolder {
        context = p0.context
        val binding = ViewholderRecommendedBinding.inflate(LayoutInflater.from(context), p0, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(p0: PopularAdapter.ViewHolder, p1: Int) {
        p0.binding.titleTxt.text = items[p1].title
        p0.binding.priceTxt.text = "$"+items[p1].price.toString()
        p0.binding.ratingTxt.text = items[p1].rating.toString()

        val requestOptions = RequestOptions().transform(CenterCrop())
        Glide.with(p0.itemView.context).load(items[p1].picUrl[0]).apply(requestOptions).into(p0.binding.pic)

        p0.itemView.setOnClickListener {
            val intent = Intent(p0.itemView.context, DetailActivity::class.java)
            intent.putExtra("object", items[p1])
            p0.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size

}