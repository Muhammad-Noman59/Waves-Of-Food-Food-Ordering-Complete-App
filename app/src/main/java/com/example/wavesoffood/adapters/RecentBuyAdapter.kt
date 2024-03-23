package com.example.wavesoffood.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wavesoffood.databinding.RecentButItemBinding

class RecentBuyAdapter(
    private var context: Context,
    private var foodNameList: ArrayList<String>,
    private var foodImageList: ArrayList<String>,
    private var foodPriceList: ArrayList<String>,
    private var foodQuantityList: ArrayList<Int>
) : RecyclerView.Adapter<RecentBuyAdapter.RecentViewHolder>() {



    //        Made by Muhammad Noman
//
//        If you need my service or you have any question then you can contact with me.
//
//        WhatsApp number :  +923104881573
//
//        LinkedIn account :  https://www.linkedin.com/in/muhammad-noman59
//
//        Facebook account :  https://www.facebook.com/profile.php?id=100092720556743&mibextid=ZbWKwL




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentViewHolder {
        val binding =
            RecentButItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecentViewHolder(binding)
    }


    override fun onBindViewHolder(holder: RecentViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int =foodNameList.size


    inner class RecentViewHolder(private val binding: RecentButItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply{
                foodName.text = foodNameList[position]
                foodPrice.text = foodPriceList[position]
                recentBuyItemQuantity.text = foodQuantityList[position].toString()

                val uriString = foodImageList[position]
                val uri = Uri.parse(uriString)
                Glide.with(context).load(uri).into(foodImage)
            }

        }

    }

}