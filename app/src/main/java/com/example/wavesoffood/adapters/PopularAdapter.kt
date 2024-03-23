package com.example.wavesoffood.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wavesoffood.DetailsActivity
import com.example.wavesoffood.databinding.PopularItemBinding

class PopularAdapter(
    private val item: List<String>,
    private val price: List<String>,
    private val image: List<Int>,
    private val requireContext: Context
) : RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {



    //        Made by Muhammad Noman
//
//        If you need my service or you have any question then you can contact with me.
//
//        WhatsApp number :  +923104881573
//
//        LinkedIn account :  https://www.linkedin.com/in/muhammad-noman59
//
//        Facebook account :  https://www.facebook.com/profile.php?id=100092720556743&mibextid=ZbWKwL


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {

        return PopularViewHolder(
            PopularItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {

        val item = item[position]
        val image = image[position]
        val price = price[position]
        holder.bind(item, price, image)
        holder.itemView.setOnClickListener {

            // set Click Listner to open food details

            val intent = Intent(requireContext, DetailsActivity::class.java)
            intent.putExtra("MenuItemName", item)
            intent.putExtra("MenuItemImages", image)
            requireContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return item.size

    }

    class PopularViewHolder(val binding: PopularItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val imageView = binding.foodImage

        fun bind(item: String, price: String, image: Int) {

            binding.foodName.text = item
            binding.foodPrice.text = price
            imageView.setImageResource(image)


        }


    }

}