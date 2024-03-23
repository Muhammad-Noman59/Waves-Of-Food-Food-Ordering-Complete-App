package com.example.wavesoffood

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wavesoffood.adapters.NotificationAdapter
import com.example.wavesoffood.databinding.FragmentNotificationBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class NotificationFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentNotificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }


    //        Made by Muhammad Noman
//
//        If you need my service or you have any question then you can contact with me.
//
//        WhatsApp number :  +923104881573
//
//        LinkedIn account :  https://www.linkedin.com/in/muhammad-noman59
//
//        Facebook account :  https://www.facebook.com/profile.php?id=100092720556743&mibextid=ZbWKwL


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentNotificationBinding.inflate(inflater, container, false)
        binding.imageView6.setOnClickListener {
            dismiss()
        }
        val names= listOf("Your order has been Canceled Successfully","Order has been taken by the driver","Congrats Your Order Placed")
        val images = listOf(R.drawable.sad_emoji,R.drawable.delivery_green,R.drawable.success_illu)
        val adapter = NotificationAdapter(ArrayList(names), ArrayList(images))
        binding.NRv.layoutManager=LinearLayoutManager(requireContext())
        binding.NRv.adapter=adapter
       return binding.root
    }

    companion object {

    }
}