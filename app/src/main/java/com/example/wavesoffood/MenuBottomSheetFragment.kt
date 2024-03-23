package com.example.wavesoffood

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wavesoffood.adapters.MenuBottomAdapter
import com.example.wavesoffood.databinding.FragmentMenuBottomSheetBinding
import com.example.wavesoffood.model.MenuItems
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MenuBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var database: FirebaseDatabase
    private lateinit var menuItems: MutableList<MenuItems>
    private lateinit var binding: FragmentMenuBottomSheetBinding
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
        binding = FragmentMenuBottomSheetBinding.inflate(inflater, container, false)

        binding.backBtnMenu.setOnClickListener {
            dismiss()
        }
        retrieveMenuItem()


        return binding.root
    }

    private fun retrieveMenuItem(){
        database = FirebaseDatabase.getInstance()
        val foodRef = database.reference.child("Menu")
        menuItems = mutableListOf()
        foodRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (foodSnapshot in snapshot.children) {
                    val menuItem = foodSnapshot.getValue(MenuItems::class.java)
                    menuItem?.let { menuItems.add(it) }
                }
                // ones data receive , set to adapter
                setAdapter()
            }

            private fun setAdapter() {
                val adapter = MenuBottomAdapter(menuItems, requireContext())
                binding.mRv.layoutManager = LinearLayoutManager(requireContext())
                binding.mRv.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
            }


        })
    }

    companion object {

    }

}