package com.example.wavesoffood.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wavesoffood.databinding.BuyAgainItemBinding

class BuyAgainAdapter(
    val fName: MutableList<String>,
    val fPrice: MutableList<String>,
    val fImage: MutableList<String>,
    private var requireContext: Context
) : RecyclerView.Adapter<BuyAgainAdapter.BuyAgainViewHolder>() {




    //        Made by Muhammad Noman
//
//        If you need my service or you have any question then you can contact with me.
//
//        WhatsApp number :  +923104881573
//
//        LinkedIn account :  https://www.linkedin.com/in/muhammad-noman59
//
//        Facebook account :  https://www.facebook.com/profile.php?id=100092720556743&mibextid=ZbWKwL



    inner class BuyAgainViewHolder(val binding: BuyAgainItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(foodName: String, foodPrice: String, foodImage: String) {
            binding.apply {
                fAName.text = foodName
                fAPrice.text = foodPrice
                val uriString = foodImage
                val uri = Uri.parse(uriString)

                Glide.with(requireContext).load(uri).into(fAImage)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyAgainViewHolder {

        val binding =
            BuyAgainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BuyAgainViewHolder(binding)

    }

    override fun getItemCount(): Int = fName.size

    override fun onBindViewHolder(holder: BuyAgainViewHolder, position: Int) {
        holder.bind(fName[position],
            fPrice[position],
            fImage[position])
    }

}