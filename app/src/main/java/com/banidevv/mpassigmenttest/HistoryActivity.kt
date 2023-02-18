package com.banidevv.mpassigmenttest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.banidevv.mpassignmenttest.databinding.ActivityHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryActivity : BaseActivity() {

    private lateinit var binding: ActivityHistoryBinding
    private val viewModel by viewModels<HistoryViewModel>()

    private val listAdapter = HistoryListAdapter()

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, HistoryActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHistoryBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initialView()
        initialObserver()

        viewModel.getListImage()
    }

    private fun initialObserver() {
        viewModel.uiState.observe(this) {
            when(it) {
                is ViewState.Error -> {
                    hideLoading()
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                ViewState.Loading -> {
                    showLoading()
                }
                is ViewState.Success -> {
                    hideLoading()
                    listAdapter.setData(it.data)
                }
            }
        }
    }

    private fun initialView() {
        binding.rvList.adapter = listAdapter
    }
}