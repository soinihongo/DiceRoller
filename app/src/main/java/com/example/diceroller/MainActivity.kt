package com.example.diceroller

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var diceAnimation : AnimationDrawable? = null
    private var setTime: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonStart: Button = findViewById(R.id.button)
        buttonStart.setOnClickListener{
            onPlay()
        }
    }

    private fun onPlay(){
        var randomTime =(1000..3000).random().toLong()
        setTime = object :CountDownTimer(randomTime,1000)
        {
            override fun onTick(p0: Long) {
                val imageView : ImageView = findViewById(R.id.imageView)
                imageView.setBackgroundResource(R.drawable.animate_dice)
                diceAnimation = imageView.background as AnimationDrawable
                diceAnimation?.start()
            }

            override fun onFinish() {
                diceAnimation?.stop()
                Toast.makeText(this@MainActivity,"Dice Roller", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (setTime!=null)
        {
            setTime?.cancel()
        }
    }
}