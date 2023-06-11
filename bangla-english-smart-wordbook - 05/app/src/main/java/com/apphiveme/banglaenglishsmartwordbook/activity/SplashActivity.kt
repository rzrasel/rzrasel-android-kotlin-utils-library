package com.apphiveme.banglaenglishsmartwordbook.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apphiveme.banglaenglishsmartwordbook.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var activity: Activity
    private lateinit var context: Context
    private lateinit var binging: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binging = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binging.root)
        activity = this
        context = this
        //

        Intent(context, DashboardActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(this)
        }
        finishAffinity()
    }
}