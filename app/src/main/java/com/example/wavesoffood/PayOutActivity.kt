package com.example.wavesoffood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.bumptech.glide.disklrucache.DiskLruCache.Value
import com.example.wavesoffood.databinding.ActivityPayOutBinding
import com.example.wavesoffood.fragments.CartFragment
import com.example.wavesoffood.fragments.HomeFragment
import com.example.wavesoffood.model.OrderDetails
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PayOutActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPayOutBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var userId : String
    private lateinit var databaseReference : DatabaseReference
    private lateinit var names : String
    private lateinit var address : String
    private lateinit var numbers : String
    private lateinit var totalAmounts : String
    private lateinit var foodItemName : ArrayList<String>
    private lateinit var foodItemPrice : ArrayList<String>
    private lateinit var foodItemDescription : ArrayList<String>
    private lateinit var foodItemImage : ArrayList<String>
    private lateinit var foodItemIngredient : ArrayList<String>
    private lateinit var foodItemQuantities : ArrayList<Int>


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
        binding=ActivityPayOutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize
        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference()

        // Set user data
        setUserData()

        // Get user details from firebase
        val intent = intent
        foodItemName = intent.getStringArrayListExtra("FoodItemName") as ArrayList<String>
        foodItemPrice = intent.getStringArrayListExtra("FoodItemPrice") as ArrayList<String>
        foodItemImage = intent.getStringArrayListExtra("FoodItemImage") as ArrayList<String>
        foodItemDescription = intent.getStringArrayListExtra("FoodItemDescription") as ArrayList<String>
        foodItemIngredient = intent.getStringArrayListExtra("FoodItemIngredient") as ArrayList<String>
        foodItemQuantities = intent.getIntegerArrayListExtra("FoodItemQuantities") as ArrayList<Int>

        totalAmounts = calculateTotalAmount().toString()+"$"

//        binding.totalAmount.isEnabled= false

        binding.totalAmount.setText(totalAmounts)

        binding.imageView7.setOnClickListener {
        finish()
        }
        binding.placeOrder.setOnClickListener {

            // get data from textView
            names = binding.name.text.toString()
            address = binding.address.text.toString()
            numbers = binding.number.text.toString().trim()

            if (names.isBlank() && address.isBlank() && numbers.isBlank()){

                Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show()
            }else{
                placeOrder()
            }

        }

    }

    private fun placeOrder() {

        userId = auth.currentUser?.uid?:""
        val time = System.currentTimeMillis()
        val itemPushKey = databaseReference.child("Order Details").push().key
        val orderDetails = OrderDetails(userId,names,foodItemName,foodItemImage,foodItemPrice,foodItemQuantities,address,numbers,time,itemPushKey,false,false,totalAmounts)
        val orderReference = databaseReference.child("Order Details").child(itemPushKey!!)
        orderReference.setValue(orderDetails).addOnSuccessListener {
            val bottomSheetDialog = CongratsBottomSheet()
            bottomSheetDialog.show(supportFragmentManager,"Test")
            removeItemFromCart()
            addItemToHistory(orderDetails)
        }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to order", Toast.LENGTH_SHORT).show()
            }
    }

    private fun addItemToHistory(orderDetails: OrderDetails){
        databaseReference.child("Users").child(userId).child("Buy History")
            .child(orderDetails.itemPushKey!!)
            .setValue(orderDetails).addOnSuccessListener {
                
            }
    }

    private fun removeItemFromCart() {
        val cartItemReference = databaseReference.child("Users").child(userId).child("Cart Items")
        cartItemReference.removeValue()
    }

    private fun calculateTotalAmount(): Int {
        var totalAmount = 0
        for (i in 0 until foodItemPrice.size){
            var price = foodItemPrice[i]
            val lastChar = price.last()
            val priceIntValue = price.filter { it.isDigit() }.takeIf { it.isNotEmpty() }?.toIntOrNull() ?: 0
//            val priceIntValue = if (lastChar == '$'){
//                price.dropLast(1).toInt()
//            }else{
//                price.toInt()
//            }

            val quantity = foodItemQuantities[i]
            totalAmount += priceIntValue *quantity
        }
        return totalAmount
    }

    private fun setUserData() {

        val user = auth.currentUser

        if (user != null){
            var userid = user.uid
            val userReference = databaseReference.child("Users").child(userid)

            userReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()){
                        val names = snapshot.child("name").getValue(String::class.java)?:""
                        val addresses = snapshot.child("address").getValue(String::class.java)?:""
                        val numbers = snapshot.child("number").getValue(String::class.java)?:""

                        // set text
                        binding.apply {
                            name.setText(names)
                            address.setText(addresses)
                            number.setText(numbers)
                        }

                    }


                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
        }
    }
}