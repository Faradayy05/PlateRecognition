package com.skripsi.platerecognition.ui.landingpage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.skripsi.platerecognition.MainActivity
import com.skripsi.platerecognition.databinding.ActivityLandingPageBinding
import com.skripsi.platerecognition.ui.authentication.AuthenticationActivity

class LandingPage : AppCompatActivity() {

    private lateinit var binding: ActivityLandingPageBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLandingPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.proceedButton.setOnClickListener {
            goToMainActivity()
        }
    }

    private fun goToMainActivity() {
        startActivity(Intent(this@LandingPage, AuthenticationActivity::class.java))
        finish()
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null){
            startActivity(Intent(this@LandingPage, MainActivity::class.java))
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }
}