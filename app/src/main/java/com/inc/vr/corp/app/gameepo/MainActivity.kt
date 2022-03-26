package com.inc.vr.corp.app.gameepo

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inc.vr.corp.app.gameepo.adapter.SoalAdapter
import com.inc.vr.corp.app.gameepo.api.ServiceBuilder
import com.inc.vr.corp.app.gameepo.api.SoalApi
import com.inc.vr.corp.app.gameepo.model.SoalInfo
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var editormyshared: SharedPreferences.Editor
    lateinit var myshared: SharedPreferences
    var total:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actionBar = supportActionBar
        actionBar!!.hide()
        val id = getIntent().getStringExtra("id")!!.toInt()
        getFood(id, "cat_id")
        fab_selesai.hide()
        fab_selesai.setOnClickListener {

            myshared = getSharedPreferences("Game_Epo", MODE_PRIVATE)
            val betul = myshared.getInt("betul",0)
            val nilai = (betul*10)/total
            editormyshared =getSharedPreferences(
                "Game_Epo",
                MODE_PRIVATE
            ).edit()
            editormyshared.remove("total")
            editormyshared.apply()
            editormyshared.putInt("total", total)
            editormyshared.putInt("nilai", nilai)
            editormyshared.apply()
            //Toast.makeText(this,"Betul "+betul+" dari total"+total,Toast.LENGTH_LONG).show()
            val intent = Intent(this@MainActivity, ResultActivity::class.java)
            startActivity(intent)
        }
    }
    private fun getFood(title: Int, author: String) {
        val bukuInfo = SoalInfo(
                id = title,
                soal = null,
                catId = title,
                kunci = null,
                createdAt = null,
                gambar_soal = null,
                a = null,
                b = null,
                c = null,
                d = author,
                a_gambar = null,
                b_gambar = null,
                c_gambar = null,
                d_gambar = null,
                updatedAt = null
        )
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
        alertDialog.setTitle("Processing...")
        alertDialog.setMessage("Data sedang diproses")
        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
        val retrofit = ServiceBuilder.buildService(SoalApi::class.java)
        retrofit.loadBuku(bukuInfo).enqueue(
                object : Callback<List<SoalInfo>> {
                    override fun onFailure(call: Call<List<SoalInfo>>, t: Throwable) {
                        alert.dismiss()
                    }

                    override fun onResponse(call: Call<List<SoalInfo>>, response: Response<List<SoalInfo>>) {
                        val addedUser = response.body()
                        if (addedUser !== null) {
                            val heroesAdapter = SoalAdapter(addedUser)
                            rc_soal.apply {
                                layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                                adapter = heroesAdapter
                            }
                            total = heroesAdapter.itemCount
                            rc_soal.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                                    super.onScrollStateChanged(recyclerView, newState)
                                    if (!recyclerView.canScrollHorizontally(1)) {
                                        //Toast.makeText(this@YourActivity, "Last", Toast.LENGTH_LONG).show()
                                        fab_selesai.show()
                                    }else{
                                        fab_selesai.hide()
                                    }
                                }
                            })
                        }
                        alert.dismiss()

                    }
                }
        )

        /*
        apiService.loadBuku(bukuInfo) {
            Timber.d(" hasil " + it.toString())
            if (it != null) {

            }
        }

         */
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}