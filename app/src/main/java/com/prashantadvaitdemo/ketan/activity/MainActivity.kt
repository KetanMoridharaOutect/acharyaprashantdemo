package com.prashantadvaitdemo.ketan.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.prashantadvaitdemo.ketan.R
import com.prashantadvaitdemo.ketan.adapter.ImageAdapter
import com.prashantadvaitdemo.ketan.databinding.ActivityMainBinding
import com.prashantadvaitdemo.ketan.view_model.ImageViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ImageViewModel
    private lateinit var adapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(ImageViewModel::class.java)
        adapter = ImageAdapter()

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        val layoutManager = GridLayoutManager(this, 3)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.imageList.observe(this, { images ->
            adapter.setImageList(images)
        })
    }
}