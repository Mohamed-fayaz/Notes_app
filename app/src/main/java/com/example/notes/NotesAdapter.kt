package com.example.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.example.notes.database.Notes
import com.example.notes.databinding.NotesRvLayoutBinding


class NotesAdapter() : ListAdapter<Notes, NotesAdapter.ViewHolder>(NoteDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentNote = getItem(position)
        holder.bind(currentNote)

    }


    class ViewHolder(val binding: NotesRvLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentNote: Notes) {
            binding.note = currentNote
            binding.executePendingBindings()
        }

    companion object {
        fun from(parent: ViewGroup): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = NotesRvLayoutBinding.inflate(layoutInflater, parent, false)

            return ViewHolder(binding)
        }
    }

}

}

class NoteDiffCallback :
    DiffUtil.ItemCallback<Notes>() {
    override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean {
   return oldItem.id  == newItem.id
    }

    override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean {
     return oldItem == newItem
    }
}