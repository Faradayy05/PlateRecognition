package com.skripsi.platerecognition.ui.platedetail

import android.graphics.BitmapFactory
import android.graphics.Shader
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.skripsi.platerecognition.R
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

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.memphis_bg)
        val drawable = BitmapDrawable(resources, bitmap)
        drawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
        binding.detailPlateLyt.background = drawable
    }
}