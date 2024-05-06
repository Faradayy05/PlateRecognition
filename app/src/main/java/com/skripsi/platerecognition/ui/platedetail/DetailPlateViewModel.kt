package com.skripsi.platerecognition.ui.platedetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.skripsi.platerecognition.data.local.entity.Kendaraan
import com.skripsi.platerecognition.data.remote.response.DetailPlateResponse
import com.skripsi.platerecognition.repository.KendaraanRepository
import com.skripsi.platerecognition.repository.Result
import kotlinx.coroutines.launch

class DetailPlateViewModel(private val kendaraanRepository: KendaraanRepository) : ViewModel() {

    private val _detailPlate = MutableLiveData<Result<DetailPlateResponse>>()
    val detailPlate: MutableLiveData<Result<DetailPlateResponse>> = _detailPlate

    init {
        getDetailPlate()
    }

    private fun getDetailPlate() {
        viewModelScope.launch {
            val response = kendaraanRepository.getDetailPlate(noPolisi)
            response.asFlow().collect {
                _detailPlate.value = it
            }
        }
    }

    fun getKendaraanById(noPolisi: String) = kendaraanRepository.getKendaraanById(noPolisi)

    fun insertKendaraan(kendaraan: Kendaraan) {
        viewModelScope.launch {
            kendaraanRepository.insertKendaraan(kendaraan)
        }
    }

    fun deleteKendaraan(kendaraan: Kendaraan) {
        viewModelScope.launch {
            kendaraanRepository.deleteKendaraan(kendaraan)
        }
    }

    companion object {
        var noPolisi = ""
    }
}