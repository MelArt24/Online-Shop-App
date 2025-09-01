package com.am24.onlineshopapp.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.am24.onlineshopapp.Adapter.BrandAdapter
import com.am24.onlineshopapp.Model.SliderModel
import com.am24.onlineshopapp.Adapter.SliderAdapter
import com.am24.onlineshopapp.ViewModel.MainViewModel
import com.am24.onlineshopapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private val viewmodel = MainViewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBanner()
        initBrand()
    }

    private fun initBanner() {
        binding.progressBarBanner.visibility = View.VISIBLE
        viewmodel.banners.observe(this, Observer { items ->
            banners(items)
            binding.progressBarBanner.visibility = View.GONE
        })
        viewmodel.loadBanners()
    }

    private fun banners(images: List<SliderModel>) {
        binding.viewpagerSlider.adapter = SliderAdapter(images, binding.viewpagerSlider)
        binding.viewpagerSlider.clipToPadding = false
        binding.viewpagerSlider.clipChildren = false
        binding.viewpagerSlider.offscreenPageLimit = 3
        binding.viewpagerSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }

        binding.viewpagerSlider.setPageTransformer(compositePageTransformer)

        if(images.size > 1) {
            binding.dotIndicator.visibility = View.VISIBLE
            binding.dotIndicator.attachTo(binding.viewpagerSlider)
        }
    }

    private fun initBrand() {
        binding.progressBarBrand.visibility = View.VISIBLE
        viewmodel.brands.observe(this, Observer {
            binding.viewBrand.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            binding.viewBrand.adapter = BrandAdapter(it)
            binding.progressBarBrand.visibility = View.GONE
        })

        viewmodel.loadBrand()
    }
}