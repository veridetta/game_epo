package com.inc.vr.corp.app.gameepo.model

import com.google.gson.annotations.SerializedName

data class SoalInfo (
    @SerializedName("id") var id: Int?,
    @SerializedName("created_at") var createdAt: String?,
    @SerializedName("updated_at") var updatedAt: String?,
    @SerializedName("soal") var soal: String?,
    @SerializedName("gambar_soal") var gambar_soal: String?,
    @SerializedName("kunci") var kunci: String?,
    @SerializedName("a") var a: String?,
    @SerializedName("b") var b: String?,
    @SerializedName("c") var c: String?,
    @SerializedName("d") var d: String?,
    @SerializedName("a_gambar") var a_gambar: String?,
    @SerializedName("b_gambar") var b_gambar: String?,
    @SerializedName("c_gambar") var c_gambar: String?,
    @SerializedName("d_gambar") var d_gambar: String?,
    @SerializedName("cat_id") var catId: Int?
)