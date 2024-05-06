package com.skripsi.platerecognition.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.skripsi.platerecognition.data.local.entity.Kendaraan

@Dao
interface KendaraanDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(kendaraan: Kendaraan)

    @Delete
    suspend fun delete(kendaraan: Kendaraan)

    @Query("SELECT * FROM Kendaraan")
    fun getAllKendaraan(): LiveData<List<Kendaraan>>

    @Query("SELECT * FROM Kendaraan WHERE noPolisi = :id")
    fun getKendaraanById(id: String): LiveData<Kendaraan>
}