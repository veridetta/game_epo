package com.inc.vr.corp.app.gameepo

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

lateinit var btn_simpan : CardView
lateinit var nama : TextView
lateinit var sekolah : TextView
lateinit var kelas : TextView
lateinit var editormyshared: SharedPreferences.Editor
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val actionBar = supportActionBar
        actionBar!!.hide()

        btn_simpan.setOnClickListener { view ->
            if ((nama.text.length > 0)  and (sekolah.text.length > 0) and (
                        kelas.text.length > 0)
            ) {
                editormyshared = getSharedPreferences(
                    "Krisis-Energi",
                    MODE_PRIVATE
                ).edit()
                editormyshared.putString("nama", nama.text.toString())
                editormyshared.putString("sekolah", sekolah.text.toString())
                editormyshared.putString("kelas", kelas.text.toString())
                editormyshared.putString("status", "login")
                editormyshared.apply()
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                intent.putExtra("jenis", "pg")
                startActivity(intent)
            } else {
                Toast.makeText(view.context, "Masih ada yang belum diisi", Toast.LENGTH_LONG).show()
            }
        }
    }
}