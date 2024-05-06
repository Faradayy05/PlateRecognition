package com.skripsi.platerecognition.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.skripsi.platerecognition.data.local.room.KendaraanDatabase
import com.skripsi.platerecognition.data.remote.retrofit.ApiConfig
import com.skripsi.platerecognition.repository.KendaraanRepository


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

object Injection {
    fun provideRepository(context: Context) : KendaraanRepository {
        val apiService = ApiConfig.getApiService()
        val database = KendaraanDatabase.getInstance(context)
        val dao = database.kendaraanDao()
        return KendaraanRepository.getInstance(apiService, dao)
    }

}