package com.rzrasel.rztutorial.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rzrasel.kotlinutils.AppScreen
import com.rzrasel.kotlinutils.ShowToast
import com.rzrasel.rztutorial.R
import com.rzrasel.rztutorial.adapter.DashboardMenuAdapter
import com.rzrasel.rztutorial.databinding.ActivityDashboardBinding
import com.rzrasel.rztutorial.enumeration.EnumDashboardMenu
import com.rzrasel.rztutorial.factory.ViewModelFactory
import com.rzrasel.rztutorial.helper.RecyclerViewHelper
import com.rzrasel.rztutorial.model.DashboardMenuData
import com.rzrasel.rztutorial.model.DashboardMenuModel
import com.rzrasel.rztutorial.model.IntentDataModel
import com.rzrasel.rztutorial.network.DataResource
import com.rzrasel.rztutorial.repository.DashboardMenuRepository
import com.rzrasel.rztutorial.viewmodel.DashboardViewModel
import java.io.Serializable
import kotlin.math.floor
import kotlin.math.round


class DashboardActivity : AppCompatActivity() {
    private lateinit var activity: Activity
    private lateinit var context: Context
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var dashboardMenuAdapter: DashboardMenuAdapter
    private var adapterDataList = ArrayList<DashboardMenuModel>()
    private var gridItemWidth: Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        activity = this@DashboardActivity
        context = this@DashboardActivity
        gridItemWidth = resources.getDimension(R.dimen.grid_item_width)
        onInitListView()
        onInitGridView()
        modelViewObserver()
    }

    private fun onInitListView() {
        dashboardMenuAdapter = DashboardMenuAdapter(context, ArrayList())
            .setOnClickListener(itemClickListener)

        val columnCount: Int = 4

        binding.sysRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = dashboardMenuAdapter
        }
        //RecyclerViewHelper.onGridColumnCount(context, binding.sysRecyclerView, gridItemWidth, 0)
    }
    private fun onInitGridView() {
        dashboardMenuAdapter = DashboardMenuAdapter(context, ArrayList())
            .setOnClickListener(itemClickListener)

        val columnCount: Int = 1

        binding.sysRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, columnCount)
            adapter = dashboardMenuAdapter
        }
        //RecyclerViewHelper.onGridColumnCount(context, binding.sysRecyclerView, gridItemWidth, 0)
    }

    private fun modelViewObserver() {
        val factory = ViewModelFactory(DashboardMenuRepository(DashboardMenuData))
        val viewModel = ViewModelProvider(this@DashboardActivity, factory)[DashboardViewModel::class.java]
        viewModel.getData(context)
        viewModel.response.observe(this@DashboardActivity) {
            //Log.d("RESPONSE", it.toString())
            when (it) {
                is DataResource.Success -> {
                    adapterDataList = it.value
                    //Log.d("RESPONSE", "DEBUG_LOG_PRINT: viewModel.response.observe $menuDataList")
                    //Log.d("RESPONSE", "DEBUG_LOG_PRINT: viewModel.response.observe ${menuDataList.size}")
                    //Log.d("RESPONSE", "DEBUG_LOG_PRINT: viewModel.response.observe ${menuDataList[0].title}")
                    //RecyclerViewHelper.onGridColumnCount(context, binding.sysRecyclerView, gridItemWidth, menuDataList.size)
                    dashboardMenuAdapter.setDataList(adapterDataList)
                }

                is DataResource.Failed -> {}
            }
        }
    }

    private val itemClickListener = object : DashboardMenuAdapter.SetOnItemClickListener {
        override fun setOItemClickListener(
            view: View,
            itemData: DashboardMenuModel,
            position: Int
        ) {
            //Toast.makeText(this@DashboardActivity, "Item clicked: ${itemData.title}", Toast.LENGTH_LONG).show()
            val dashboardMenu: EnumDashboardMenu? = EnumDashboardMenu.findSlug(itemData.slug)
            if (dashboardMenu != null) {
                var intent: Intent? = null
                if (dashboardMenu == EnumDashboardMenu.DAYS_OF_THE_WEEK) {
                    intent = Intent(context, HorizontalRecyclerActivity::class.java)
                }
                intent.let { intent1 ->
                    intent1?.apply {
                        val intentDataModel: IntentDataModel = IntentDataModel(
                            itemData
                        )
                        val bundle = Bundle()
                        bundle.putSerializable(
                            "dashboard_menu_model",
                            intentDataModel as Serializable
                        )
                        putExtras(bundle)
                        context.startActivity(this)
                    }
                } ?: {
                    ShowToast.show(context, "${itemData.bnTitle} menu not found")
                }
            } else {
                ShowToast.show(context, "${itemData.bnTitle} not found")
            }
        }
    }

    /*private fun onCreateOld01(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /-*binding.sysImageView.setBackgroundResource(
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
        )*-/

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
    }*/
}

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
