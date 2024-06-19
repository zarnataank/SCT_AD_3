package com.example.stopwatchapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    lateinit var textview:TextView
    lateinit var start:MaterialButton
    lateinit var stop:MaterialButton
    lateinit var restart:MaterialButton
    private var isRunning=false
    private var timerseconds=0

    private val handler=Handler(Looper.getMainLooper())

    private val runnable=object :Runnable{
        override fun run() {
            timerseconds++
            val hours=timerseconds/36000
            val minutes=(timerseconds % 3600)/60
            val seconds=timerseconds % 60
            val time=String.format("%02d:%02d:%02d",hours,minutes,seconds)
            textview.text=time
            handler.postDelayed(this,1000)


        }
    }


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textview=findViewById(R.id.clock)
        start=findViewById(R.id.start)
        stop=findViewById(R.id.stop)
        restart=findViewById(R.id.restart)
        start.setOnClickListener{
            starttimer()
        }
        stop.setOnClickListener{
            stoptimer()
        }
        restart.setOnClickListener{
            restartimer()
        }


    }

    private fun restartimer() {
        stoptimer()
        timerseconds=0
        textview.text="00:00:00"
        start.isEnabled=true
        restart.isEnabled=false
        stop.isEnabled=true
    }

    private fun starttimer() {
        if(!isRunning)
        {
            handler.postDelayed(runnable,1000)
            isRunning=true
            restart.isEnabled=true
            stop.isEnabled=true
            start.isEnabled=false

        }
    }
    private fun stoptimer()
    {
        if(isRunning)
        {
            handler.removeCallbacks(runnable)
            isRunning=false
            start.isEnabled=true
            stop.isEnabled=false
            restart.isEnabled=true
        }
    }


}

