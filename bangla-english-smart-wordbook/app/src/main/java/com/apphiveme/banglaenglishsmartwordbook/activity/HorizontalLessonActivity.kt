package com.apphiveme.banglaenglishsmartwordbook.activity

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apphiveme.banglaenglishsmartwordbook.adapter.HorizontalLessonAdapter
import com.apphiveme.banglaenglishsmartwordbook.databinding.ActivityHorizontalLessonBinding
import com.apphiveme.banglaenglishsmartwordbook.enumeration.EnumDashboardMenu
import com.apphiveme.banglaenglishsmartwordbook.factory.ViewModelFactory
import com.apphiveme.banglaenglishsmartwordbook.model.DashboardMenuModel
import com.apphiveme.banglaenglishsmartwordbook.model.IntentDataModel
import com.apphiveme.banglaenglishsmartwordbook.model.LessonData
import com.apphiveme.banglaenglishsmartwordbook.model.LessonDataModel
import com.apphiveme.banglaenglishsmartwordbook.network.DataResource
import com.apphiveme.banglaenglishsmartwordbook.repository.LessonDataRepository
import com.apphiveme.banglaenglishsmartwordbook.viewmodel.LessonViewModel
import com.rzrasel.kotlinmediaplayer.ProAudioPlayer
import com.rzrasel.kotlinutils.AssetFileReader
import com.rzrasel.kotlinutils.SerializableHelper

class HorizontalLessonActivity : AppCompatActivity() {
    private lateinit var activity: Activity
    private lateinit var context: Context
    private lateinit var binding: ActivityHorizontalLessonBinding

    //
    private lateinit var intentDataModel: IntentDataModel
    private lateinit var dashboardMenuModel: DashboardMenuModel
    private var enumDashboardMenu: EnumDashboardMenu? = EnumDashboardMenu.NONE

    //
    private lateinit var horizontalLessonAdapter: HorizontalLessonAdapter
    private var adapterDataList = ArrayList<LessonDataModel>()

    //
    private lateinit var audioPlayer: ProAudioPlayer
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHorizontalLessonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        activity = this
        context = this
        //
        val bundle: Bundle? = intent.extras
        bundle?.let {
            bundle.apply {
                intentDataModel = SerializableHelper.getSerializable(
                    activity,
                    "dashboard_menu_model",
                    IntentDataModel::class.java
                )
                dashboardMenuModel = intentDataModel.dataModel as DashboardMenuModel
                enumDashboardMenu = EnumDashboardMenu.findSlug(dashboardMenuModel.slug)
            }
        }
        onInitView()
        observerModelView()
        audioPlayer = ProAudioPlayer.Builder().with(activity).build(context)
        //audioPlayer.onPlayAssetAudio("audio/alpha_bn_consonant_1_type_1.mp3")
    }

    private fun onInitView() {
        horizontalLessonAdapter = HorizontalLessonAdapter(applicationContext, ArrayList())
            .setOnClickListener(object : HorizontalLessonAdapter.SetOnItemClickListener {
                override fun setOItemClickListener(
                    view: View,
                    itemData: LessonDataModel,
                    position: Int
                ) {
                    //val item: TutorialDataModel = adapterDataList[position]
                    println("DEBUG_LOG_PRINT: Item clicked position $position -  ${itemData.bigImagePath}")
                    playLesson(itemData, position)
                }
            })

        val columnCount: Int = 4

        binding.sysRecyclerView.apply {
            setHasFixedSize(true)
            //layoutManager = GridLayoutManager(context, columnCount)
            layoutManager = LinearLayoutManager(applicationContext, RecyclerView.HORIZONTAL, false)
            adapter = horizontalLessonAdapter
        }
        horizontalLessonAdapter.setDataList(adapterDataList)
    }

    private fun observerModelView() {
        val factory = ViewModelFactory(LessonDataRepository(LessonData))
        val viewModel =
            ViewModelProvider(this@HorizontalLessonActivity, factory)[LessonViewModel::class.java]
        viewModel.getData(context, enumDashboardMenu)
        viewModel.response.observe(this@HorizontalLessonActivity) {
            when (it) {
                is DataResource.Success -> {
                    adapterDataList = it.value as ArrayList<LessonDataModel>
                    //println("DEBUG_LOG_PRINT: responseData.sevenDay.size ${adapterDataList.size}")
                    horizontalLessonAdapter.setDataList(adapterDataList)
                    playLesson(adapterDataList[0], 0)
                }

                is DataResource.Failed -> {}
                else -> {}
            }
        }
    }

    private fun playLesson(itemData: LessonDataModel, position: Int) {
        val drawable: Drawable? =
            AssetFileReader.drawable(context, itemData.bigImagePath)
        val sdk: Int = android.os.Build.VERSION.SDK_INT
        /*drawable?.let {
            binding.sysLinearLayoutImage.apply {
                if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    setBackgroundDrawable(drawable)
                } else {
                    background = drawable
                }
            }
        }*/
        drawable.let {
            binding.sysImageView.setImageDrawable(drawable)
        }
        audioPlayer.onPlayAssetAudio(itemData.audioPath)
    }

    override fun onPause() {
        audioPlayer.onDestroy()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        audioPlayer.onResume()
    }

    override fun onDestroy() {
        audioPlayer.onDestroy()
        super.onDestroy()
    }

    inner class MediaPreparedListener : MediaPlayer.OnPreparedListener {
        override fun onPrepared(argMediaPlayer: MediaPlayer) {
            mediaPlayer = argMediaPlayer
            mediaPlayer!!.start()
        }
    }
}