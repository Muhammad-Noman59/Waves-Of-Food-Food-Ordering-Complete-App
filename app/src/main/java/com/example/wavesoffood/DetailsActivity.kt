package com.example.wavesoffood

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.wavesoffood.databinding.ActivityDetailsBinding
import com.example.wavesoffood.model.CartItems
import com.example.wavesoffood.model.MenuItems
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var foodImages : String
    private lateinit var foodIngredients : String
    private lateinit var foodDescriptions : String
    private lateinit var foodPrices : String
    private lateinit var foodNames:String


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
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

         foodNames = intent.getStringExtra("MenuItem").toString()
        foodImages = intent.getStringExtra("MenuImage").toString()
       foodPrices = intent.getStringExtra("MenuPrice").toString()
        foodDescriptions = intent.getStringExtra("MenuDescription").toString()
       foodIngredients = intent.getStringExtra("MenuIngredients").toString()

        binding.ingredientsTextView.text = foodIngredients
        binding.descriptionTextView.text = foodDescriptions
        Glide.with(this).load(Uri.parse(foodImages)).into(binding.imageView9)
        binding.detailsFoodName.text = foodNames




        binding.detailsBackBtn.setOnClickListener {
            finish()
        }
        binding.detailsAddCartBtn.setOnClickListener {
            addItemToCart()
        }

    }

    private fun addItemToCart() {


        val database = FirebaseDatabase.getInstance().reference
        val userUid = auth.currentUser?.uid ?: ""

        // Create a cartItem object
        val cartItems = CartItems(
            foodNames,
            foodPrices,
            foodDescriptions,
            foodImages,
            1
        )

        // Save data to cart item to firebase database
        database.child("Users").child(userUid).child("Cart Items").push().setValue(cartItems)
            .addOnSuccessListener {

                Toast.makeText(this, "Item added onto cart successfully", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
            Toast.makeText(this, "Item not added", Toast.LENGTH_SHORT).show()
        }
    }
}