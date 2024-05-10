package com.skripsi.platerecognition.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "kendaraan")
data class User (
    @PrimaryKey
    var email: String = "",
    var password: String = "",
    var name: String = "",
) : Parcelable