package com.rzrasel.rztutorial.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.rzrasel.rztutorial.ViewPagerAdapter
import com.rzrasel.rztutorial.databinding.ActivityViewpagerBinding

class ViewpagerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewpagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewpagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var textList = ArrayList<String>()
        textList.add("Slide 1")
        textList.add("Slide 2")
        textList.add("Slide 3")
        val viewPagerAdapter = ViewPagerAdapter(this, textList)

        /*binding.sysViewpager.apply {
            adapter = viewPagerAdapter
            pageMargin = 15
            setPadding(50, 0, 50, 0)
            clipToPadding = false
            addOnPageChangeListener(viewPagerPageChangeListener)
        }*/
        binding.sysViewpager.apply {
            adapter = viewPagerAdapter
            setPadding(50, 0, 50, 0)
            clipToPadding = false
            registerOnPageChangeCallback(onPageChangeCallback)
        }
    }

    private var onPageChangeCallback: ViewPager2.OnPageChangeCallback = object:
        ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            //
            println("DEBUG_LOG_PRINT: onPageSelected state $position")
            println("DEBUG_LOG_PRINT: onPageSelected currentItem ${binding.sysViewpager.currentItem}")
        }

        override fun onPageScrollStateChanged(state: Int) {
            super.onPageScrollStateChanged(state)
            println("DEBUG_LOG_PRINT: onPageScrollStateChanged state $state")
            //println("DEBUG_LOG_PRINT: onPageScrollStateChanged currentItem ${binding.sysViewpager.currentItem}")
        }

        override fun onPageScrolled(position: Int,
                                    positionOffset: Float,
                                    positionOffsetPixels: Int) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            println("DEBUG_LOG_PRINT: onPageScrolled position $position")
        }
    }

    var viewPagerPageChangeListener: ViewPager.OnPageChangeListener =
        object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                // your logic here
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                // your logic here
            }

            override fun onPageSelected(position: Int) {
                // your logic here
            }
        }
}

//https://prasanthvel.medium.com/create-custom-viewpager-in-android-android-kotlin-tutorial-b4c7eff76488
