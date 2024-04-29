package com.skripsi.platerecognition.ui.authentication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.skripsi.platerecognition.R
import com.skripsi.platerecognition.databinding.ActivityAuthenticationBinding

class AuthenticationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthenticationBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        supportActionBar?.hide()

        val mFragmentManager = supportFragmentManager
        val mLoginFragment = LoginOptionsFragment()
        val fragment = mFragmentManager.findFragmentByTag(LoginOptionsFragment::class.java.simpleName)

        if (fragment !is LoginOptionsFragment) {
            mFragmentManager.commit {
                add(R.id.frame_container, mLoginFragment, LoginOptionsFragment::class.java.simpleName)
            }
        }
    }
}