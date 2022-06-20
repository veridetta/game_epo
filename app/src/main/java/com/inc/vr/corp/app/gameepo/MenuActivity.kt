package com.inc.vr.corp.app.gameepo

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.inc.vr.corp.app.gameepo.adapter.CatAdapter
import com.inc.vr.corp.app.gameepo.api.CatApi
import com.inc.vr.corp.app.gameepo.api.RestApiService
import com.inc.vr.corp.app.gameepo.api.ServiceBuilder
import kotlinx.android.synthetic.main.activity_menu.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MenuActivity : AppCompatActivity() {
    lateinit var myshared: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val actionBar = supportActionBar
        actionBar!!.hide()
        myshared  = getSharedPreferences("Game_Epo", MODE_PRIVATE)
        val namae  = myshared.getString("nama", "Envy Epo")
        nama.text = namae
        val ss = getIntent().getStringExtra("name").toString()
        val resourceId = resources.getIdentifier(
            "down",
            "drawable",
            "com.inc.vr.corp.app.gameepo"
        )
        Glide.with(this)
            .load(resourceId)
            .into(down1)
        getCat(ss, "name")
        Glide.with(this)
            .load(resourceId)
            .into(down2)
        getCat(ss, "name")
        Glide.with(this)
            .load(resourceId)
            .into(down3)
        getCat(ss, "name")
    }
    fun getCat(title: String, author: String) {
        //val apiService= RestApiService()
        val bukuInfo = CatInfo(
            id = null,
            createdAt = null,
            updatedAt = null,
            cover = null,
            name = null
        )
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@MenuActivity)
        alertDialog.setTitle("Processing...")
        alertDialog.setMessage("Data sedang diproses")
        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
        val retrofit = ServiceBuilder.buildService(CatApi::class.java)
        retrofit.loadBuku(bukuInfo).enqueue(
            object : Callback<List<CatInfo>> {
                override fun onFailure(call: Call<List<CatInfo>>, t: Throwable) {
                    alert.dismiss()
                }

                override fun onResponse(
                    call: Call<List<CatInfo>>,
                    response: Response<List<CatInfo>>
                ) {
                    val addedUser = response.body()
                    if (addedUser !== null) {
                        //Timber.d(addedUser.toString())
                        val heroesAdapter = CatAdapter(addedUser)
                        rc_home.apply {
                            layoutManager = GridLayoutManager(this@MenuActivity, 1)
                            adapter = heroesAdapter
                        }
                    }
                    alert.dismiss()
                }
            }
        )
    }
}