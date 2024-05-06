package com.skripsi.platerecognition.data.remote.retrofit

import com.skripsi.platerecognition.data.remote.response.DetailPlateResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("api/kendaraan/no-polisi/{noPolisi}")
    suspend fun getDetailPlate(
        @Path("noPolisi") noPolisi: String
    ) : DetailPlateResponse
}