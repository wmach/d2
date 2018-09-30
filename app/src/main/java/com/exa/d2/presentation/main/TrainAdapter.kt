package com.exa.d2.presentation.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.exa.d2.data.db.entity.TrainEntity
import com.exa.d2.data.model.Train
import com.exa.d2.databinding.ListItemBinding

class TrainAdapter(var dataList: List<TrainEntity>) : RecyclerView.Adapter<TrainAdapter.BindingHolder>() {
    lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        setOnItemClickListener(listener)
        val layoutInflater = LayoutInflater.from(parent!!.context)
        val binding = ListItemBinding.inflate(layoutInflater, parent, false)
        return BindingHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        val data = dataList[position]
        holder.binding.setData(data)
        holder.binding.listItemLinearlayout.setOnClickListener({
            listener.onClick(it, data)
        })
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    interface OnItemClickListener {
        fun onClick(view: View, data: TrainEntity)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    class BindingHolder(var binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)
}