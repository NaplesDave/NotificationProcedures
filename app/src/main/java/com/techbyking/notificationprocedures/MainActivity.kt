package com.techbyking.notificationprocedures

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.techbyking.notificationprocedures.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val CHANNEL_ID = "1"
    lateinit var mainBinding : ActivityMainBinding
    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("click", "In onCreate")
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        mainBinding.sendNotification.setOnClickListener {
            // sendNotification is Button id
            counter++
            Log.d("click", "Button Clicked")
            mainBinding.sendNotification.text = counter.toString()

            if (counter == 5) {  // button was clicked 5 times
                startNotification()
            }
        }// end buttononClickListener

        setContentView(R.layout.activity_main)
    }


    fun startNotification(){

        val builder = NotificationCompat.Builder(this@MainActivity,CHANNEL_ID)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            val channel = NotificationChannel(CHANNEL_ID,"1",
                NotificationManager.IMPORTANCE_DEFAULT)

            val manager : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            manager.createNotificationChannel(channel)

            builder.setSmallIcon(R.drawable.small_icon)
                .setContentTitle("Title")
                .setContentText("Notification Text")

        } else {

            builder.setSmallIcon(R.drawable.small_icon)
                .setContentTitle("Title")
                .setContentText("Notification Text")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        } // end if statement

        val notificationManagerCompat = NotificationManagerCompat.from(this@MainActivity)
        notificationManagerCompat.notify(1,builder.build())


    }// end startNotification function


}// end MainActivity class