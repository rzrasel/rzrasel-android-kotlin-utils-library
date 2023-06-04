package com.apphiveme.banglaenglishsmartwordbook.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.apphiveme.banglaenglishsmartwordbook.R
import com.apphiveme.banglaenglishsmartwordbook.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binging: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binging = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binging.root)
    }
}