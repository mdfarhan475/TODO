package com.example.todo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.todo.databinding.ActivityWelcomeBinding

class welcomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityWelcomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        enableEdgeToEdge()


        setContentView(binding.root)

        val alphaAnimation= AnimationUtils.loadAnimation(applicationContext ,R.anim.splace_anim)
        binding.textViewwLcome.startAnimation(alphaAnimation)
        val handler =   Handler(Looper.getMainLooper())
        handler.postDelayed(object : Runnable{
            override fun run() {
                val intent = Intent(this@welcomeActivity,MainActivity::class.java)
                startActivity(intent)
            }

        },5000)



    }
}