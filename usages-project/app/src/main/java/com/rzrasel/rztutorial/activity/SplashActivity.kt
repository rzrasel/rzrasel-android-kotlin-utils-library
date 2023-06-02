package com.rzrasel.rztutorial.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.window.SplashScreen
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.rzrasel.rztutorial.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        //val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setupSplashScreen(splashScreen)
        context = this@SplashActivity
        //
        /*val intent = Intent(context, DashboardActivity::class.java)
        context.startActivity(intent)
        finishAffinity()*/
        /*context.startActivity(Intent(context, DashboardActivity::class.java).apply {
            putExtra("key", "value")
        })*/
        Intent(context, DashboardActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(this)
        }
        finishAffinity()
    }
}