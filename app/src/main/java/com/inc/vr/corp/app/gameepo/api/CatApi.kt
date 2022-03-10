package com.inc.vr.corp.app.gameepo.api

import com.inc.vr.corp.app.gameepo.CatInfo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

public interface CatApi {
    @Headers("Content-Type: application/json")
    @POST("cat")
    fun loadBuku( @Body userData: CatInfo): Call<List<CatInfo>>
}