package com.banidevv.mpassigmenttest

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.banidevv.domain.model.ImageResultUiModel
import com.banidevv.mpassignmenttest.databinding.ItemHistoryBinding

class HistoryListAdapter : RecyclerView.Adapter<HistoryListAdapter.HistoryViewHolder>() {

    private val list: MutableList<ImageResultUiModel> = mutableListOf()

    class HistoryViewHolder(
        private val binding: ItemHistoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ImageResultUiModel) = with(binding) {
            ivImage.setImageBitmap(item.image)
            tvTitle.text = item.name
            tvResult.text = item.result
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<ImageResultUiModel>) {
        this.list.clear()
        this.list.addAll(list)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(list[position])
    }

}
