package com.banidevv.mpassignmenttest

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.banidevv.mpassigmenttest.BaseActivity
import com.banidevv.mpassigmenttest.MainViewModel
import com.banidevv.mpassigmenttest.MainViewState
import com.banidevv.mpassignmenttest.databinding.ActivityMainBinding
import com.banidevv.ocr.ModelExecutionResult
import com.bumptech.glide.Glide
import com.github.dhaval2404.imagepicker.ImagePicker
import com.karumi.dexter.Dexter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var startImageResult: ActivityResultLauncher<Intent>
    private lateinit var binding: ActivityMainBinding

    private var result: String? = null
    private var uri: Uri? = null

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel.init(this)

        initImagePicker()
        initView()
        initObserver()

    }

    private fun setResult() {
        binding.ivResult.isVisible = true
        binding.tvResult.isVisible = true
        binding.tvTakePickture.isVisible = false

        binding.btnReset.isEnabled = true

        Glide.with(baseContext).load(uri).override(250, 250)
            .fitCenter().into(binding.ivResult)

        binding.tvResult.text = result
    }

    private fun initObserver() {
        viewModel.result.observe(this) {
            when (it) {
                MainViewState.Error -> {
                    hideLoading()

                    Toast.makeText(this, "Something wrong", Toast.LENGTH_SHORT)
                        .show()
                }
                MainViewState.Loading -> {
                    showLoading()
                }
                is MainViewState.Success -> {
                    hideLoading()
                    result = it.data

                    setResult()
                }
            }
        }
    }

    private fun initView() = with(binding) {
        tvTakePickture.setOnClickListener {
            ImagePicker.with(this@MainActivity)
                .galleryOnly()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .createIntent {
                    startImageResult.launch(it)
                }
        }

        btnReset.setOnClickListener {
            clear()
        }
    }

    private fun initImagePicker() {
        startImageResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                val resultCode = result.resultCode
                val data = result.data

                when (resultCode) {
                    Activity.RESULT_OK -> {
                        //Image Uri will not be null for RESULT_OK
                        val fileUri = data?.data

                        if (fileUri != null) {
                            uri = fileUri
                            viewModel.processPhoto(this, fileUri)
                        } else {
                            Toast.makeText(this, "Image not found", Toast.LENGTH_SHORT).show()
                        }
                    }
                    ImagePicker.RESULT_ERROR -> {
                        Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

    private fun clear() = with(binding) {
        tvResult.isVisible = false
        ivResult.isVisible = false
        tvTakePickture.isVisible = true

        btnReset.isEnabled = false
        result = null
    }
}