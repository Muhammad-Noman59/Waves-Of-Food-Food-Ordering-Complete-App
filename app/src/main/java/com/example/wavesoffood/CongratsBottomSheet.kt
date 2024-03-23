package com.example.wavesoffood

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wavesoffood.databinding.FragmentCongratsBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CongratsBottomSheet : BottomSheetDialogFragment() {
    private lateinit var binding : FragmentCongratsBottomSheetBinding
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
        binding=FragmentCongratsBottomSheetBinding.inflate(inflater, container, false)
        binding.button3.setOnClickListener {
            val intent = Intent(requireContext(),MainActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

    companion object {

    }
}