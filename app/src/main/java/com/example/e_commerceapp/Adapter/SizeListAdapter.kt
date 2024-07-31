package com.example.e_commerceapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.VieeholderPicListBinding
import com.example.e_commerceapp.databinding.ViewholderSizeBinding

class SizeListAdapter(val items: MutableList<String>) :
    RecyclerView.Adapter<SizeListAdapter.ViewHolder>() {

        private var selectedPosition = -1
        private var lastSelectedPosition = -1
        private lateinit var context: Context


    inner class ViewHolder(val binding: ViewholderSizeBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeListAdapter.ViewHolder {
        context = parent.context
        val binding = ViewholderSizeBinding.inflate(LayoutInflater.from(context) , parent , false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SizeListAdapter.ViewHolder, position: Int) {

        holder.binding.sizeTxt.text = items[position]

        holder.binding.root.setOnClickListener{
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)


        }
        if (selectedPosition == position){
            holder.binding.sizeLayout.setBackgroundResource(R.drawable.green_bg3)
            holder.binding.sizeTxt.setTextColor(context.getColor(R.color.white))
        }else{
            holder.binding.sizeLayout.setBackgroundResource(R.drawable.grey_bg)
            holder.binding.sizeTxt.setTextColor(context.getColor(R.color.black))
        }

    }

    override fun getItemCount(): Int = items.size

}