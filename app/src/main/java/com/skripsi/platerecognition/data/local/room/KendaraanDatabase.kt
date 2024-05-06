package com.skripsi.platerecognition.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.skripsi.platerecognition.data.local.entity.Kendaraan

@Database(entities = [Kendaraan::class], version = 1, exportSchema = false)
abstract class KendaraanDatabase : RoomDatabase() {
    abstract fun kendaraanDao(): KendaraanDAO

    companion object {
        @Volatile
        private var instance: KendaraanDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): KendaraanDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    KendaraanDatabase::class.java, "Kendaraan.db"
                ).build()
            }
    }
}