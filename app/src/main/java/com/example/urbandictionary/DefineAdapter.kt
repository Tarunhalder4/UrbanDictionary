package com.example.urbandictionary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.urbandictionary.data.Data
import com.example.urbandictionary.databinding.DefineBinding

class DefineAdapter constructor(private val list: List<Data>) :
    RecyclerView.Adapter<DefineAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: DefineBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Data) {
            binding.data = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DefineBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

}