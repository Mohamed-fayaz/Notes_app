package com.example.notes.timer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.TimervBinding
import java.util.*

class TimeAdapter() : ListAdapter<TimeUsed, TimeAdapter.ViewHolder>(TimeDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentTime = getItem(position)
        holder.bind(currentTime)

    }


    class ViewHolder(val binding: TimervBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentTime: TimeUsed) {
            binding.time = currentTime
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TimervBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }

}

class TimeDiffCallback :
    DiffUtil.ItemCallback<TimeUsed>() {
    override fun areItemsTheSame(oldItem: TimeUsed, newItem: TimeUsed): Boolean {
        return oldItem.usedId  == newItem.usedId
    }

    override fun areContentsTheSame(oldItem: TimeUsed, newItem: TimeUsed): Boolean {
        return oldItem == newItem
    }
}