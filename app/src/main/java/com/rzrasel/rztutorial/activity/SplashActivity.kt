package com.rzrasel.rztutorial.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rzrasel.rztutorial.HorizontalAdapter
import com.rzrasel.rztutorial.HorizontalRecyclerActivity
import com.rzrasel.rztutorial.R
import com.rzrasel.rztutorial.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //
        /*val intent = Intent(this@SplashActivity, DashboardActivity::class.java)
        startActivity(intent)
        finishAffinity()*/
        /*val intent = Intent(this@SplashActivity, HorizontalRecyclerActivity::class.java)
        startActivity(intent)
        finishAffinity()*/
        val intent = Intent(this@SplashActivity, ViewpagerActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }
}