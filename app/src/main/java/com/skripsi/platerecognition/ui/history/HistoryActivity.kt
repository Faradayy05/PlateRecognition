package com.skripsi.platerecognition.ui.history

import android.graphics.BitmapFactory
import android.graphics.Shader
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.skripsi.platerecognition.R
import com.skripsi.platerecognition.adapter.KendaraanAdapter
import com.skripsi.platerecognition.data.local.entity.Kendaraan
import com.skripsi.platerecognition.databinding.ActivityHistoryBinding
import com.skripsi.platerecognition.utils.ViewModelFactory

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding
    private val historyPlateViewModel by viewModels<HistoryPlateViewModel> {
        ViewModelFactory.getInstance(application)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        supportActionBar?.hide()
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.memphis_bg)
        val drawable = BitmapDrawable(resources, bitmap)
        drawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
        binding.historyLyt.background = drawable

        val layoutManager = LinearLayoutManager(this)
        binding.rvKendaraan.layoutManager = layoutManager
        binding.rvKendaraan.setHasFixedSize(true)

        historyPlateViewModel.getAllKendaraan().observe(this) { kendaraan ->
            val data = arrayListOf<Kendaraan>()
            kendaraan.map {
                val item = Kendaraan(noPolisi = it.noPolisi, merk = it.merk, type = it.type, jenis = it.jenis)
                data.add(item)
            }
            if (data.isEmpty()) {
                binding.errMsg.text = getString(R.string.no_history)
                binding.errMsg.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
                binding.errMsg.visibility = View.GONE
            }
            binding.rvKendaraan.adapter = KendaraanAdapter(data)
        }
    }
}