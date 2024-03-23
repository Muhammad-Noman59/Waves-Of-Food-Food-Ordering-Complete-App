package com.example.wavesoffood.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wavesoffood.adapters.MenuBottomAdapter
import com.example.wavesoffood.databinding.FragmentSearchBinding
import com.example.wavesoffood.model.MenuItems
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: MenuBottomAdapter
    private lateinit var database: FirebaseDatabase
    private val originalMenuItem = mutableListOf<MenuItems>()



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
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        // Retrieve menu item from database
        retrieveMenuItem()

        // Setup Search View
        setupSearchView()




        return binding.root
    }

    private fun retrieveMenuItem() {

        // get database reference
         database = FirebaseDatabase.getInstance()

        // Reference ton the menu node
        val foodReference : DatabaseReference = database.reference.child("Menu")
        foodReference.addListenerForSingleValueEvent(object  : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(foodSnapshot in snapshot.children){

                    val menuItem = foodSnapshot.getValue(MenuItems::class.java)
                    menuItem?.let {
                        originalMenuItem.add(it)
                    }
                }
                showAllMenu()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    private fun showAllMenu() {

        val filterMenuItems =ArrayList(originalMenuItem)
        setAdapter(filterMenuItems)
    }

    private fun setAdapter(filterMenuItem: List<MenuItems>) {
        adapter = MenuBottomAdapter(filterMenuItem,requireContext())
        binding.searchRv.layoutManager = LinearLayoutManager(requireContext())
        binding.searchRv.adapter = adapter
    }


    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {

                filterMenuItems(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterMenuItems(newText)
                return true
            }
        })

    }

    private fun filterMenuItems(query: String) {

        val filterMenuItem = originalMenuItem.filter {
            it.foodName?.contains(query,ignoreCase = true) == true
        }

     setAdapter(filterMenuItem)

    }




companion object {

}
}


