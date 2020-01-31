package com.example.demochatfirebase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val messList: MutableList<ChatData>,context :Context) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var list :MutableList<ChatData> = messList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.item_chat,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mess = list[position]
        holder.bindData(mess)
    }

    inner class ViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.nameUserTx)
        val content = itemView.findViewById<TextView>(R.id.contentMessageTx)
        fun bindData(item: ChatData){
            name.text = item.name
            content.text = item.mess
        }
    }
}