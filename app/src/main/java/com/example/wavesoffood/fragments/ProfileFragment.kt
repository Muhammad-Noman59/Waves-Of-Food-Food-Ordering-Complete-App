package com.example.wavesoffood.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.wavesoffood.R
import com.example.wavesoffood.databinding.FragmentProfileBinding
import com.example.wavesoffood.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProfileFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var binding: FragmentProfileBinding
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
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        // Initialize
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference()

        binding.apply {

            profileName.isEnabled =false
            profileEmail.isEnabled =false
            profileAddress.isEnabled =false
            profileNumber.isEnabled =false

            save.visibility = View.INVISIBLE

        }

        binding.editBtn.setOnClickListener {
            binding.apply {

                profileName.isEnabled =true
                profileEmail.isEnabled =true
                profileAddress.isEnabled =true
                profileNumber.isEnabled =true

                save.visibility = View.VISIBLE
            }
        }

        setUserData()

        binding.save.setOnClickListener {

            val name = binding.profileName.toString()
            val email = binding.profileEmail.toString()
            val address = binding.profileAddress.toString()
            val number = binding.profileNumber.toString()

            // Update user data
            updateUserData(name, email, address, number)

        }

        return binding.root
    }

    private fun updateUserData(name: String, email: String, address: String, number: String) {

        val userId = auth.currentUser?.uid
        if (userId != null) {
            val userReference = database.child("Users").child(userId)
            val userData : HashMap<String,String> = hashMapOf(
                "name" to name,
                "email" to email,
                "address" to address,
                "number" to number
            )
            userReference.setValue(userData).addOnSuccessListener {
                Toast.makeText(requireContext(), "Profile update successfully", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {

                Toast.makeText(requireContext(), "Profile update failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setUserData() {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val userReference = database.child("Users").child(userId)

            userReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val userProfile = snapshot.getValue(UserModel::class.java)
                        Log.d("profile", "onDataChange: $userProfile")
                        if (userProfile != null) {
                            binding.profileName.setText(userProfile.name)
                            binding.profileAddress.setText(userProfile.address)
                            binding.profileEmail.setText(userProfile.email)
                            binding.profileNumber.setText(userProfile.number)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
        }
    }
}