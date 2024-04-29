package com.skripsi.platerecognition.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.skripsi.platerecognition.R
import com.skripsi.platerecognition.ui.landingpage.LandingPage

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        setupAction()
    }

    private fun setupAction() {
        supportActionBar?.hide()
        Handler(mainLooper).postDelayed({
            startActivity(Intent(this@SplashScreen, LandingPage::class.java))
            finish()
        }, DELAY)
    }

    companion object {
        const val DELAY = 2000L
    }
}