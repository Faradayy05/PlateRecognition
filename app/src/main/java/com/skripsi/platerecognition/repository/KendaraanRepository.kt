package com.skripsi.platerecognition.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.skripsi.platerecognition.data.local.entity.Kendaraan
import com.skripsi.platerecognition.data.local.room.KendaraanDAO
import com.skripsi.platerecognition.data.remote.response.DetailPlateResponse
import com.skripsi.platerecognition.data.remote.retrofit.ApiService

class KendaraanRepository private constructor(
    private val apiService: ApiService,
    private val kendaraanDAO: KendaraanDAO
) {
    fun getAllKendaraan() : LiveData<List<Kendaraan>> {
        return kendaraanDAO.getAllKendaraan()
    }

    fun getKendaraanById(noPolisi: String) : LiveData<Kendaraan> {
        return kendaraanDAO.getKendaraanById(noPolisi)
    }

    suspend fun insertKendaraan(kendaraan: Kendaraan) {
        kendaraanDAO.insert(kendaraan)
    }

    suspend fun deleteKendaraan(kendaraan: Kendaraan) {
        kendaraanDAO.delete(kendaraan)
    }

    fun getDetailPlate(noPolisi: String): LiveData<Result<DetailPlateResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getDetailPlate(noPolisi)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    companion object {
        @Volatile
        private var instance: KendaraanRepository? = null
        fun getInstance(
            apiService: ApiService,
            kendaraanDAO: KendaraanDAO
        ) : KendaraanRepository = instance?: synchronized(this) {
            instance ?: KendaraanRepository(apiService, kendaraanDAO)
        }.also { instance = it }
    }
}