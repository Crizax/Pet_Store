package com.example.e_commerceapp.Adapter

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.e_commerceapp.Activity.DetailAcitivity
import com.example.e_commerceapp.Model.ItemsModel
import com.example.e_commerceapp.databinding.ViewBestSellerBinding

class BestSellerAdapter(val items: MutableList<ItemsModel>) :
    RecyclerView.Adapter<BestSellerAdapter.ViewHolder>() {

    private var context: Context? = null

    class ViewHolder(val binding: ViewBestSellerBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding = ViewBestSellerBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.titleTxt.text = items[position].title
        holder.binding.priceTxt.text = buildString {
            append("$")
            append(items[position].price.toString())
        }
        holder.binding.ratingTxt.text = items[position].rating.toString()
        val requestOption = RequestOptions().transform(CenterCrop())
        Glide.with(holder.itemView.context)
            .load(items[position].picUrl[0])
            .apply(requestOption)
            .into(holder.binding.picBestSeller)

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context , DetailAcitivity::class.java)
            intent.putExtra("object" , items[position] as Parcelable)
            holder.itemView.context.startActivity(intent)
        }

    }
}