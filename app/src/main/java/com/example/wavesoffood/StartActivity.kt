package com.example.wavesoffood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wavesoffood.databinding.ActivityStartBinding
import com.google.firebase.auth.FirebaseAuth

class StartActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    val binding by lazy{
        ActivityStartBinding.inflate(layoutInflater)
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        binding.nextBtn.setOnClickListener {
            startActivity(Intent(this,LogInActivity::class.java))
            finish()
        }

    }

    override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        if (user != null){
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
}