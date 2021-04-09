package com.example.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.RealmResults

class NotesAdapter (private val context :Context?, private val notesList : RealmResults<Notes>)
    :RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       val v = LayoutInflater.from(parent.context).inflate(R.layout.notes_rv_layout,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int){
        val titleTV = holder.itemView.findViewById<TextView>(R.id.titleTV)
        val description = holder.itemView.findViewById<TextView>(R.id.descTV)
        titleTV.text = notesList[position]!!.title
        description.text = notesList[position]!!.description
    }

    override fun getItemCount(): Int {
      return notesList.size
    }
class ViewHolder(v:View?):RecyclerView.ViewHolder(v!!){

val title = itemView.findViewById<TextView>(R.id.titleTV)
    val description = itemView.findViewById<TextView>(R.id.descTV)
}

}