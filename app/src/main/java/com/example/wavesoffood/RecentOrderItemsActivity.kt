package com.example.wavesoffood

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wavesoffood.adapters.RecentBuyAdapter
import com.example.wavesoffood.databinding.ActivityRecentOrderItemsBinding
import com.example.wavesoffood.databinding.RecentButItemBinding
import com.example.wavesoffood.model.OrderDetails

class RecentOrderItemsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecentOrderItemsBinding
    private lateinit var allFoodNames: ArrayList<String>
    private lateinit var allFoodImages: ArrayList<String>
    private lateinit var allFoodPrices: ArrayList<String>
    private lateinit var allFoodQuantities: ArrayList<Int>


    //        Made by Muhammad Noman
//
//        If you need my service or you have any question then you can contact with me.
//
//        WhatsApp number :  +923104881573
//
//        LinkedIn account :  https://www.linkedin.com/in/muhammad-noman59
//
//        Facebook account :  https://www.facebook.com/profile.php?id=100092720556743&mibextid=ZbWKwL



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecentOrderItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val recentBuyOrderItem =
            intent.getSerializableExtra("recentBuyOrderItem") as ArrayList<OrderDetails>
        recentBuyOrderItem?.let { orderDetails ->
            if (orderDetails.isNotEmpty()) {
                val recentOrderDetails = orderDetails[0]

                allFoodNames = recentOrderDetails.foodItemName as ArrayList<String>
                allFoodImages = recentOrderDetails.foodItemImage as ArrayList<String>
                allFoodPrices = recentOrderDetails.foodItemPrice as ArrayList<String>
                allFoodQuantities = recentOrderDetails.foodItemQuantities as ArrayList<Int>
            }
        }

        setAdapter()
    }

    private fun setAdapter() {
        val rv = binding.rv
        rv.layoutManager = LinearLayoutManager(this)
        val adapter = RecentBuyAdapter(this,allFoodNames,allFoodImages,allFoodPrices,allFoodQuantities)

        rv.adapter = adapter
    }
}