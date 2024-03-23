package com.example.wavesoffood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.wavesoffood.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    val binding by lazy {
        ActivitySplashScreenBinding.inflate(layoutInflater)
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
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this,StartActivity::class.java))
            finish()
        },3000)

    }
}