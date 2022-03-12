package com.inc.vr.corp.app.gameepo

import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_result.*
import java.util.*

class ResultActivity : AppCompatActivity() {
    lateinit var myshared: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val actionBar = supportActionBar
        actionBar!!.hide()
        myshared = getSharedPreferences("Game_Epo", MODE_PRIVATE)
        var betul = myshared.getInt("betul", 0)
        var total = myshared.getInt("total", 0)
        var nilai = myshared.getInt("nilai", 90)
        donut_progress.max = 100
        val komplit : MediaPlayer = MediaPlayer.create(this, R.raw.complete)
        komplit!!.isLooping = false
        komplit.start()
        var timer: Timer
        timer = Timer()
        var c = 0
        timer.schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread { donut_progress.setProgress(donut_progress.getProgress() + 1) }
                c++;
                if (c >= persentasi(betul,total).toInt()) {
                    timer.cancel();
                    timer.purge();
                    return;
                }
            }
        }, 1000, 50)

        desc.text="Kamu benar "+betul+" dari "+total+" pertanyaan yang ada"
        score_tct.text = nilai.toString()
        val b : Float = betul.toFloat()
        val t : Float = total.toFloat()
        val rat=(b/t)*5
        rating.rating =rat
        //Toast.makeText(this,(persentasi(betul,total)*5).toString(),Toast.LENGTH_LONG)
        btn_back.setOnClickListener{
            val intent = Intent(this@ResultActivity, MenuActivity::class.java)
            startActivity(intent)
        }
    }
    fun persentasi(betul: Int, total: Int): Float {
        val persen : Float = (betul.toFloat()/total.toFloat())*100
        //val round : Int = (persen)
        return persen
    }
}

