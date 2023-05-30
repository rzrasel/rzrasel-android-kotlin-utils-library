package com.rzrasel.rztutorial.activity

import android.content.Intent
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.rzrasel.kotlinutils.ReadJsonFile
import com.rzrasel.rztutorial.adapter.DashboardMenuAdapter
import com.rzrasel.rztutorial.databinding.ActivityDashboardBinding
import com.rzrasel.rztutorial.factory.ViewModelFactory
import com.rzrasel.rztutorial.helper.DashboardMenu
import com.rzrasel.rztutorial.helper.ProGson
import com.rzrasel.rztutorial.model.DashboardMenuData
import com.rzrasel.rztutorial.model.DashboardMenuModel
import com.rzrasel.rztutorial.model.TestData
import com.rzrasel.rztutorial.network.DataResource
import com.rzrasel.rztutorial.repository.DashboardMenuRepository
import com.rzrasel.rztutorial.viewmodel.DashboardViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var dashboardMenuAdapter: DashboardMenuAdapter
    private var menuDataList = ArrayList<DashboardMenuModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*val size = AppScreen.size(this, this)
        val width: Int = size.width
        val height: Int = size.height
        println("DEBUG_LOG_PRINT: Screen Size Width: $width Height: $height")
        println("DEBUG_LOG_PRINT: Screen Size Px Width: ${width.px} Height: ${height.px}")
        println("DEBUG_LOG_PRINT: Screen Size Dp Width: ${width.dp} Height: ${height.dp}")*/
        onInitView()
        observeMenu()
        //
        //intent.putExtra("intent_key", user)
        //intent.putExtras(bundle)
        var testData: TestData
        /*intent.let {
            testData = intent.extras!!.getParcelable("data_key")!! as TestData
        }*/
        val bundle: Bundle? = intent.extras
        /*bundle.let {
            testData = it.getParcelable("intent_key", TestData::class.java)!!
        }*/
        testData = intent.getParcelableExtra<TestData>("test")!!
        bundle?.let {
            testData = it.getParcelable<TestData>("data_key")!!
            testData = it.parcelable<TestData>("data_key")!!
        }

        /*val userData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("DATA", TestData::class.java)
        } else {
            intent.getParcelableExtra<TestData>("DATA")
        }*/
    }

    private fun onInitView() {
        dashboardMenuAdapter = DashboardMenuAdapter(applicationContext, ArrayList())
        binding.sysRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(applicationContext, 2)
            adapter = dashboardMenuAdapter
        }
    }

    private fun observeMenu() {
        val factory = ViewModelFactory(DashboardMenuRepository(DashboardMenuData))
        val viewModel = ViewModelProvider(this, factory)[DashboardViewModel::class.java]
        viewModel.getData(this@DashboardActivity)
        viewModel.response.observe(this, Observer {
            //Log.d("RESPONSE", it.toString())
            when (it) {
                is DataResource.Success -> {
                    menuDataList = it.value as ArrayList<DashboardMenuModel>
                    Log.d(
                        "RESPONSE",
                        "DEBUG_LOG_PRINT: viewModel.response.observe $menuDataList"
                    )
                    Log.d(
                        "RESPONSE",
                        "DEBUG_LOG_PRINT: viewModel.response.observe ${menuDataList.size}"
                    )
                    Log.d(
                        "RESPONSE",
                        "DEBUG_LOG_PRINT: viewModel.response.observe ${menuDataList[0].title}"
                    )
                    dashboardMenuAdapter.setDataList(menuDataList)
                }

                is DataResource.Failed -> {}
            }
        })
    }

    private fun onCreateOld01(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*binding.sysImageView.setBackgroundResource(
            ProResources.getDrawableId(
                this@DashboardActivity,
                "book_icon"
            )
        )
        binding.sysImageView.setImageResource(
            ProResources.getDrawableId(
                this@DashboardActivity,
                "book_icon"
            )
        )*/

        GlobalScope.launch(Dispatchers.Default) {
            val jsonData = ReadJsonFile.fromJson(this@DashboardActivity, "test.json")
            //Log.d("DEBUG_LOG", "DEBUG_LOG_PRINT: $jsonData")
            val listData: ArrayList<DashboardMenuModel> = ProGson.fromJson(jsonData)
            Log.d("DEBUG_LOG", "DEBUG_LOG_PRINT: length ${listData.size}")
        }

        //val dashboardEnum = DashboardMenu.fromValue("test")
        val dashboardEnum = DashboardMenu.find("Sword")
        Log.d("LOG", "DEBUG_LOG_PRINT: ${dashboardEnum?.name}")

        val factory = ViewModelFactory(DashboardMenuRepository(DashboardMenuData))
        val viewModel = ViewModelProvider(this, factory)[DashboardViewModel::class.java]
        viewModel.getData(this@DashboardActivity)
        viewModel.response.observe(this, Observer {
            //Log.d("RESPONSE", it.toString())
            when (it) {
                is DataResource.Success -> {
                    val itemList = it.value as ArrayList<DashboardMenuModel>
                    Log.d(
                        "RESPONSE",
                        "DEBUG_LOG_PRINT: viewModel.response.observe ${itemList.size}"
                    )
                }

                is DataResource.Failed -> {}
            }
        })
    }
}

inline fun <reified T : Parcelable> Bundle.parcelableArrayList(key: String): ArrayList<T>? = when {
    SDK_INT >= 33 -> getParcelableArrayList(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelableArrayList(key)
}

inline fun <reified T : Parcelable> Intent.parcelableArrayList(key: String): ArrayList<T>? = when {
    SDK_INT >= 33 -> getParcelableArrayListExtra(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelableArrayListExtra(key)
}

inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
}

inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}

//https://stackoverflow.com/questions/73019160/android-getparcelableextra-deprecated

/*

<?php
$arr = array(
    array(
        "image" => "test_image",
        "title" => "Title Label 1",
        "description" => "Description Label 1",
        "slug" => "test_top_1",
    ),
    array(
        "image" => "test_image",
        "title" => "Title Label 1",
        "description" => "Description Label 1",
        "slug" => "test_top_2",
    ),
);


echo json_encode($arr);
?>


*/
