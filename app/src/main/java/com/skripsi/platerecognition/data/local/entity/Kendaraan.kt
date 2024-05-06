package com.skripsi.platerecognition.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "kendaraan")
data class Kendaraan (
    @PrimaryKey
    var noPolisi: String = "",
    var merk: String? = null,
    var type: String? = null,
    var jenis: String? = null
) : Parcelable