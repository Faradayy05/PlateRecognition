package com.skripsi.platerecognition.ui.history

import androidx.lifecycle.ViewModel
import com.skripsi.platerecognition.repository.KendaraanRepository

class HistoryPlateViewModel(private val kendaraanRepository: KendaraanRepository) : ViewModel() {
    fun getAllKendaraan() = kendaraanRepository.getAllKendaraan()
}