package com.inc.vr.corp.app.gameepo

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

lateinit var intent: Intent
lateinit var myshared: SharedPreferences
var status : String? = null
class LaunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        val actionBar = supportActionBar
        actionBar!!.hide()
        myshared = getSharedPreferences("Game_Epo", MODE_PRIVATE)
         status = myshared.getString("status","logout")
        val handler = Handler()
        handler.postDelayed({
            if (status == "login") {
                intent = Intent(applicationContext, MenuActivity::class.java)
                finish()
                startActivity(intent)
            } else {
                intent = Intent(applicationContext, LoginActivity::class.java)
                finish()
                startActivity(intent)
            }
        }, 2000)
    }
}