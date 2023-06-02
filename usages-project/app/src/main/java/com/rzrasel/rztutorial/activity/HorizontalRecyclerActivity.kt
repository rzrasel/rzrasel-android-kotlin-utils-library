package com.rzrasel.rztutorial.activity

import android.app.Activity
import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rzrasel.kotlinutils.AssetAudio
import com.rzrasel.kotlinutils.SerializableHelper
import com.rzrasel.rztutorial.adapter.HorizontalAdapter
import com.rzrasel.rztutorial.databinding.ActivityHorizontalRecyclerBinding
import com.rzrasel.rztutorial.enumeration.EnumDashboardMenu
import com.rzrasel.rztutorial.factory.ViewModelFactory
import com.rzrasel.rztutorial.model.DashboardMenuModel
import com.rzrasel.rztutorial.model.IntentDataModel
import com.rzrasel.rztutorial.model.LessonData
import com.rzrasel.rztutorial.model.TutorialDataModel
import com.rzrasel.rztutorial.network.DataResource
import com.rzrasel.rztutorial.repository.LessonDataRepository
import com.rzrasel.rztutorial.utils.ProAudioPlayer
import com.rzrasel.rztutorial.viewmodel.LessonViewModel
import java.io.IOException


class HorizontalRecyclerActivity : AppCompatActivity() {
    private lateinit var activity: Activity
    private lateinit var context: Context
    private lateinit var binding: ActivityHorizontalRecyclerBinding
    private lateinit var intentDataModel: IntentDataModel
    private lateinit var dashboardMenuModel: DashboardMenuModel
    private var enumDashboardMenu: EnumDashboardMenu? = EnumDashboardMenu.NONE
    //
    private lateinit var horizontalAdapter: HorizontalAdapter
    private var adapterDataList = ArrayList<TutorialDataModel>()

    //
    private lateinit var audioPlayer: ProAudioPlayer
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHorizontalRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        activity = this@HorizontalRecyclerActivity
        context = this@HorizontalRecyclerActivity

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
        //ShowToast.show(context, "Debug data dashboardMenuModel ${dashboardMenuModel.title}")
        audioPlayer = ProAudioPlayer.Builder().with(activity).build(context)
        //audioPlayer.onPlayAssetAudio("audio/alpha_bn_consonant_1_type_1.mp3")
        modelViewObserver()
    }

    private fun onInitView() {
        horizontalAdapter = HorizontalAdapter(applicationContext, ArrayList())

        val columnCount: Int = 4

        binding.sysRecyclerView.apply {
            setHasFixedSize(true)
            //layoutManager = GridLayoutManager(context, columnCount)
            layoutManager = LinearLayoutManager(applicationContext, RecyclerView.HORIZONTAL, false)
            adapter = horizontalAdapter
        }
        horizontalAdapter.setDataList(adapterDataList)
    }

    private fun modelViewObserver() {
        val factory = ViewModelFactory(LessonDataRepository(LessonData))
        val viewModel = ViewModelProvider(this@HorizontalRecyclerActivity, factory)[LessonViewModel::class.java]
        viewModel.getData(context, enumDashboardMenu)
        viewModel.response.observe(this@HorizontalRecyclerActivity) {
            when(it) {
                is DataResource.Success -> {
                    adapterDataList = it.value as ArrayList<TutorialDataModel>
                    //println("DEBUG_LOG_PRINT: responseData.sevenDay.size ${adapterDataList.size}")
                    horizontalAdapter.setDataList(adapterDataList)
                }
                is DataResource.Failed -> {}
                else -> {}
            }
        }
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

    private fun audioPlayerOld() {
        mediaPlayer = MediaPlayer()
        try {
            //mediaPlayer!!.setDataSource(getContext(), Uri.parse(musicUrl))
            AssetAudio.onLoadMedia(context, mediaPlayer!!, "audio/alpha_bn_consonant_1_type_1.mp3")
            mediaPlayer!!.setOnPreparedListener(MediaPreparedListener())
            mediaPlayer!!.prepareAsync()
            //mediaPlayer?.start()
        } catch (e: IOException) {
            println("DEBUG_LOG_PRINT: mediaPlayer")
            e.printStackTrace()
        }
    }

    inner class MediaPreparedListener : MediaPlayer.OnPreparedListener {
        override fun onPrepared(argMediaPlayer: MediaPlayer) {
            mediaPlayer = argMediaPlayer
            mediaPlayer!!.start()
        }
    }
}