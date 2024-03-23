package com.example.wavesoffood.adapters

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wavesoffood.databinding.NotificatinItemBinding

class NotificationAdapter  (val nName : ArrayList<String>,val nImage : ArrayList<Int>) : RecyclerView.Adapter<NotificationAdapter.ViewHolder>(){


    //        Made by Muhammad Noman
//
//        If you need my service or you have any question then you can contact with me.
//
//        WhatsApp number :  +923104881573
//
//        LinkedIn account :  https://www.linkedin.com/in/muhammad-noman59
//
//        Facebook account :  https://www.facebook.com/profile.php?id=100092720556743&mibextid=ZbWKwL



    inner class ViewHolder (val binding : NotificatinItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
            binding.apply {
                notificationText.text=nName[position]
                notificationImage.setImageResource(nImage[position])
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val binding = NotificatinItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = nName.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)

    }
}