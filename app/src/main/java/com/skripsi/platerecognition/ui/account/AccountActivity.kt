package com.skripsi.platerecognition.ui.account

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.skripsi.platerecognition.R
import com.skripsi.platerecognition.databinding.ActivityAccountBinding
import com.skripsi.platerecognition.utils.uriToFile
import java.io.File

class AccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountBinding
    private var getFile: File? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()

        binding.changePhoto.setOnClickListener {
            changePhoto()
        }

        binding.btnSaveAccount.setOnClickListener {
            Toast.makeText(this, "Feature Coming Soon", Toast.LENGTH_SHORT).show()
        }

        binding.btnSaveAccount.setOnClickListener {
            Toast.makeText(this, "Feature Coming Soon", Toast.LENGTH_SHORT).show()
        }

        binding.changePhoto.setOnClickListener {
            startGallery()
        }
    }

    private fun setupView() {
        supportActionBar?.hide()
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        auth = Firebase.auth
        val user = auth.currentUser

        binding.edName.text = Editable.Factory.getInstance().newEditable(user?.displayName)
        binding.edEmail.text = Editable.Factory.getInstance().newEditable(user?.email)
        Glide
            .with(this)
            .load(user?.photoUrl)
            .into(binding.imgFotoProfil)
    }

    private fun changePhoto() {
        Toast.makeText(this, "Feature Coming Soon", Toast.LENGTH_SHORT).show()
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            val selectedImg = result.data?.data as Uri
            selectedImg.let { uri ->
                val myFile = uriToFile(uri, this)
                getFile = myFile
                binding.imgFotoProfil.setImageURI(uri)
            }
        }
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, getString(R.string.choose_picture))
        launcherIntentGallery.launch(chooser)
    }
}