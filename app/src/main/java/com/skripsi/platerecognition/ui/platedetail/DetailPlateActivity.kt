package com.skripsi.platerecognition.ui.platedetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.skripsi.platerecognition.databinding.ActivityDetailPlateBinding
import java.io.File

class DetailPlateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPlateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPlateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        supportActionBar?.hide()
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        val img = intent.getSerializableExtra("IMG_FILE") as File

        img.let {
            binding.imgKendaraan.setImageURI(img.toUri())
        }
    }
}