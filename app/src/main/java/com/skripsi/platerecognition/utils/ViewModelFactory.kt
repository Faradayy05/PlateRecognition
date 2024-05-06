package com.skripsi.platerecognition.utils

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.skripsi.platerecognition.di.Injection
import com.skripsi.platerecognition.repository.KendaraanRepository
import com.skripsi.platerecognition.ui.history.HistoryPlateViewModel
import com.skripsi.platerecognition.ui.platedetail.DetailPlateViewModel

class ViewModelFactory private constructor(private val kendaraanRepository: KendaraanRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailPlateViewModel::class.java)) {
            return DetailPlateViewModel(kendaraanRepository) as T
        }
        if (modelClass.isAssignableFrom(HistoryPlateViewModel::class.java)) {
            return HistoryPlateViewModel(kendaraanRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }
}