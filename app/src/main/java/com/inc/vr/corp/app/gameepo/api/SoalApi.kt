package com.inc.vr.corp.app.gameepo.api

import com.inc.vr.corp.app.gameepo.model.SoalInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SoalApi {
    @Headers("Content-Type: application/json")
    @POST("food")
    fun loadBuku( @Body userData: SoalInfo): Call<List<SoalInfo>>
}