package com.example.wavesoffood.fragments

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.wavesoffood.R
import com.example.wavesoffood.RecentOrderItemsActivity
import com.example.wavesoffood.adapters.BuyAgainAdapter
import com.example.wavesoffood.databinding.FragmentHistoryBinding
import com.example.wavesoffood.model.OrderDetails
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    private lateinit var userId: String
    private lateinit var adapter: BuyAgainAdapter
    private var listOfOrderItem: MutableList<OrderDetails> = mutableListOf()
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
        binding = FragmentHistoryBinding.inflate(inflater, container, false)

        // Initialize firebase auth
        auth = FirebaseAuth.getInstance()

        // Initialize firebase database
        database = FirebaseDatabase.getInstance()

        // Retrieve and display the user oder history
        retrieveBuyHistory()

        // Recent item on client and show all recent buy items
        binding.recentBuyItem.setOnClickListener {
            seeRecentBuyItem()
        }
        binding.receivedBtn.setOnClickListener {
            updateOrderStatus()
        }

        return binding.root
    }

    private fun updateOrderStatus() {
        val itemPushKey = listOfOrderItem[0].itemPushKey
        val completeOrderReference = database.reference.child("Completed Orders").child(itemPushKey!!)
        completeOrderReference.child("paymentReceived").setValue(true)

    }

    // function to see recent buy items
    private fun seeRecentBuyItem() {

        listOfOrderItem.firstOrNull()?.let { recentBuy ->


            val intent = Intent(requireContext(),RecentOrderItemsActivity ::class.java)
            intent.putExtra("recentBuyOrderItem", ArrayList(getListOfOrderItems()))
            startActivity(intent)

        }
    }
    // function to retrieve recent items buy history
    private fun retrieveBuyHistory() {
        binding.recentBuyItem.visibility = View.INVISIBLE

        userId = auth.currentUser?.uid ?: ""
        val buyItemReference = database.reference.child("Users").child(userId).child("Buy History")
        val shortingQuery = buyItemReference.orderByChild("currentTime")

        shortingQuery.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (buySnapshot: DataSnapshot in snapshot.children) {
                    val buyHistoryItem = buySnapshot.getValue(OrderDetails::class.java)
                    buyHistoryItem?.let {
                        listOfOrderItem.add(it)
                    }
                }
                listOfOrderItem.reverse()
                if (listOfOrderItem.isNotEmpty()) {

                    // Display the most recent order details
                    setDataInRecentBuyItem()
                    // setup the recycler View with previous order details
                    setPreviousItemRecyclerView()
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
    // Function to Display the most recent order details
    private fun setDataInRecentBuyItem() {
        binding.recentBuyItem.visibility = View.VISIBLE

        val recentOrderItem = listOfOrderItem.firstOrNull() // Access the last item after reversing
        recentOrderItem?.let {
            with(binding) {
                recentName.text = it.foodItemName?.firstOrNull() ?: ""
                recentPrice.text = it.foodItemPrice?.firstOrNull() ?: ""
                val image = it.foodItemImage?.firstOrNull() ?: ""
                val uri = Uri.parse(image)

                Glide.with(requireContext()).load(uri).into(recentImage)
                listOfOrderItem.reverse()

                val isOrderAccepted = listOfOrderItem[0].orderAccepted

                if (isOrderAccepted){
                    recentOrderStatus.background.setTint(Color.GREEN)
                    receivedBtn.visibility = View.VISIBLE
                }
            }
        }
    }
    // Function to setup the recycler View with previous order details
    private fun setPreviousItemRecyclerView() {
        val f2Name = mutableListOf<String>()
        val f2Price = mutableListOf<String>()
        val f2Image = mutableListOf<String>()
        for (i in 1 until listOfOrderItem.size) {
            listOfOrderItem[i].foodItemName?.firstOrNull()?.let {
                f2Name.add(it)
                listOfOrderItem[i].foodItemPrice?.firstOrNull()?.let {
                    f2Price.add(it)
                    listOfOrderItem[i].foodItemImage?.firstOrNull()?.let {
                        f2Image.add(it)
                    }
                }
                val rv = binding.buyAgainRv
                rv.layoutManager = LinearLayoutManager(requireContext())
                adapter = BuyAgainAdapter(f2Name, f2Price, f2Image, requireContext())
                rv.adapter = adapter

            }
        }
    }

    fun getListOfOrderItems(): MutableList<OrderDetails> {
        return listOfOrderItem
    }
}