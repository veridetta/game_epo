package com.inc.vr.corp.app.gameepo.adapter

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.graphics.Color
import android.media.MediaPlayer
import android.util.Log.v
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.inc.vr.corp.app.gameepo.R
import com.inc.vr.corp.app.gameepo.model.SoalInfo
import kotlinx.android.synthetic.main.rc_cat.view.*
import kotlinx.android.synthetic.main.rc_soal.view.*


private var context: Context? = null
var betul : Int = 0
class SoalAdapter(private val heroes: List<SoalInfo>) : RecyclerView.Adapter<SoalHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): SoalHolder {
        context = viewGroup.getContext();
        betul = 0
        return SoalHolder(
            LayoutInflater.from(viewGroup.context).inflate(
                R.layout.rc_soal,
                viewGroup,
                false
            )
        )
    }
    override fun getItemCount(): Int = heroes.size
    override fun onBindViewHolder(holder: SoalHolder, position: Int) {
        holder.bindOrder(heroes[position])
    }
}
class SoalHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val img_soal = view.img_soal
    private val img_opsi_a = view.img_opsi_a
    private val img_opsi_b = view.img_opsi_b
    private val img_opsi_c = view.img_opsi_c
    //private val img_opsi_d = view.img_opsi_d
    private val card = view.card
    private val soal = view.tanya_isi
    private val opsi_a = view.opsi_a
    private val opsi_b = view.opsi_b
    private val opsi_c = view.opsi_c
    //private val opsi_d = view.opsi_d
    private val txt_opsi_a = view.txt_opsi_a
    private val txt_opsi_b = view.txt_opsi_b
    private val txt_opsi_c = view.txt_opsi_c
    //private val txt_opsi_d = view.txt_opsi_d
    fun bindOrder(buku: SoalInfo) {
        var klik : Int=0
        val options: RequestOptions = RequestOptions()
                .fitCenter()
                .placeholder(R.drawable.logo)
                .error(R.drawable.logo)
        Glide.with(context).load(buku.gambar_soal).apply(options).into(img_soal)
        Glide.with(context).load(buku.a_gambar).apply(options).into(img_opsi_a)
        Glide.with(context).load(buku.b_gambar).apply(options).into(img_opsi_b)
        Glide.with(context).load(buku.c_gambar).apply(options).into(img_opsi_c)
        //Glide.with(context).load(buku.d_gambar).apply(options).into(img_opsi_d)
        soal.text = buku.soal
        txt_opsi_a.text = buku.a
        txt_opsi_b.text = buku.b
        txt_opsi_c.text = buku.c
        //txt_opsi_d.text = buku.d
        card.setOnClickListener {
            //val intent = Intent(context, MenuActivity::class.java)
            //intent.putExtra("id", buku.id.toString())
           // context?.startActivity(intent)
        }
        val editormyshared: SharedPreferences.Editor = context!!.getSharedPreferences(
            "Game_Epo",
            MODE_PRIVATE
        ).edit()
        editormyshared.remove("betul")
        editormyshared.apply()
        val benar : MediaPlayer = MediaPlayer.create(context, R.raw.benar)
        benar!!.isLooping = false
        val salah : MediaPlayer = MediaPlayer.create(context, R.raw.salah)
        salah!!.isLooping = false
        opsi_a.setOnClickListener(View.OnClickListener { v ->
            if (buku.kunci!!.equals(v.getTag())) {
                opsi_a.setBackgroundColor(Color.GREEN)
                betul++
                klik++
                benar.start()
                val editormyshared: SharedPreferences.Editor = context!!.getSharedPreferences(
                    "Game_Epo",
                    MODE_PRIVATE
                ).edit()
                editormyshared.putInt("betul", betul)
                editormyshared.apply()
            } else {
                opsi_a.setBackgroundColor(Color.RED)
                kunci(opsi_b, buku.kunci!!)
                kunci(opsi_c, buku.kunci!!)
          //      kunci(opsi_d, buku.kunci!!)
                salah.start()
            }
            opsi_a.isEnabled = false
            opsi_a.isClickable = false
            opsi_b.isEnabled = false
            opsi_b.isClickable = false
            opsi_c.isEnabled = false
            opsi_c.isClickable = false
            //opsi_d.isEnabled = false
            //opsi_d.isClickable = false
        })
        opsi_b.setOnClickListener(View.OnClickListener { v ->
            if (buku.kunci!!.equals(v.getTag())) {
                opsi_b.setBackgroundColor(Color.GREEN)
                betul++
                klik++
                benar.start()
                val editormyshared: SharedPreferences.Editor = context!!.getSharedPreferences(
                    "Game_Epo",
                    MODE_PRIVATE
                ).edit()
                editormyshared.putInt("betul", betul)
                editormyshared.apply()
            } else {
                opsi_b.setBackgroundColor(Color.RED)
                kunci(opsi_a, buku.kunci!!)
                kunci(opsi_c, buku.kunci!!)
              //  kunci(opsi_d, buku.kunci!!)
                salah.start()
            }
            opsi_a.isEnabled = false
            opsi_a.isClickable = false
            opsi_b.isEnabled = false
            opsi_b.isClickable = false
            opsi_c.isEnabled = false
            opsi_c.isClickable = false
            //opsi_d.isEnabled = false
            //opsi_d.isClickable = false
        })
        opsi_c.setOnClickListener(View.OnClickListener { v ->
            if (buku.kunci!!.equals(v.getTag())) {
                opsi_c.setBackgroundColor(Color.GREEN)
                betul++
                klik++
                benar.start()
                val editormyshared: SharedPreferences.Editor = context!!.getSharedPreferences(
                    "Game_Epo",
                    MODE_PRIVATE
                ).edit()
                editormyshared.putInt("betul", betul)
                editormyshared.apply()
            } else {
                opsi_c.setBackgroundColor(Color.RED)
                kunci(opsi_b, buku.kunci!!)
                kunci(opsi_a, buku.kunci!!)
              //  kunci(opsi_d, buku.kunci!!)
                salah.start()
            }
            opsi_a.isEnabled = false
            opsi_a.isClickable = false
            opsi_b.isEnabled = false
            opsi_b.isClickable = false
            opsi_c.isEnabled = false
            opsi_c.isClickable = false
            //opsi_d.isEnabled = false
            //opsi_d.isClickable = false
        })
        /*opsi_d.setOnClickListener(View.OnClickListener { v ->
            if (buku.kunci!!.equals(v.getTag())) {
                opsi_d.setBackgroundColor(Color.GREEN)
                betul++
                klik++
                benar.start()
                val editormyshared: SharedPreferences.Editor = context!!.getSharedPreferences(
                    "Game_Epo",
                    MODE_PRIVATE
                ).edit()
                editormyshared.putInt("betul", betul)
                editormyshared.apply()
            } else {
                opsi_a.setBackgroundColor(Color.RED)
                kunci(opsi_b, buku.kunci!!)
                kunci(opsi_c, buku.kunci!!)
                kunci(opsi_a, buku.kunci!!)
                salah.start()
            }
            opsi_a.isEnabled = false
            opsi_a.isClickable = false
            opsi_b.isEnabled = false
            opsi_b.isClickable = false
            opsi_c.isEnabled = false
            opsi_c.isClickable = false
            opsi_d.isEnabled = false
            opsi_d.isClickable = false
        })*/
    }
    fun kunci(opsi: LinearLayout, kunci_jawaban: String){
        if(opsi.getTag().equals(kunci_jawaban)){
            opsi.setBackgroundColor(Color.GREEN)
        }
    }
}