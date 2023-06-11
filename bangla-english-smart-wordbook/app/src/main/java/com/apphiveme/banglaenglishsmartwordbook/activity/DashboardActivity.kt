package com.apphiveme.banglaenglishsmartwordbook.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.apphiveme.banglaenglishsmartwordbook.adapter.DashboardMenuAdapter
import com.apphiveme.banglaenglishsmartwordbook.databinding.ActivityDashboardBinding
import com.apphiveme.banglaenglishsmartwordbook.enumeration.EnumDashboardMenu
import com.apphiveme.banglaenglishsmartwordbook.factory.ViewModelFactory
import com.apphiveme.banglaenglishsmartwordbook.model.DashboardMenuData
import com.apphiveme.banglaenglishsmartwordbook.model.DashboardMenuModel
import com.apphiveme.banglaenglishsmartwordbook.model.IntentDataModel
import com.apphiveme.banglaenglishsmartwordbook.network.DataResource
import com.apphiveme.banglaenglishsmartwordbook.repository.DashboardMenuRepository
import com.apphiveme.banglaenglishsmartwordbook.viewmodel.DashboardViewModel
import com.rzrasel.kotlinutils.ShowToast
import java.io.Serializable

class DashboardActivity : AppCompatActivity() {
    private lateinit var activity: Activity
    private lateinit var context: Context
    private lateinit var binding: ActivityDashboardBinding

    //
    private lateinit var dashboardMenuAdapter: DashboardMenuAdapter
    private var adapterDataList = ArrayList<DashboardMenuModel>()
    private var gridItemWidth: Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        activity = this
        context = this
        //
        onInitListView()
        onInitGridView()
        observeModelView()
    }

    private fun onInitListView() {
        dashboardMenuAdapter = DashboardMenuAdapter(context, ArrayList())
            .setOnClickListener(itemClickListener)

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

        val columnCount: Int = 2

        binding.sysRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, columnCount)
            adapter = dashboardMenuAdapter
        }
        //RecyclerViewHelper.onGridColumnCount(context, binding.sysRecyclerView, gridItemWidth, 0)
    }

    private fun observeModelView() {
        val factory = ViewModelFactory(DashboardMenuRepository(DashboardMenuData))
        val viewModel =
            ViewModelProvider(this@DashboardActivity, factory)[DashboardViewModel::class.java]
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
                /*if (dashboardMenu == EnumDashboardMenu.DAYS_OF_THE_WEEK) {
                    intent = Intent(context, HorizontalLessonActivity::class.java)
                } else if (dashboardMenu == EnumDashboardMenu.MONTHS_OF_THE_YEAR) {
                    intent = Intent(context, HorizontalLessonActivity::class.java)
                }*/
                if(dashboardMenu.serial!! > 0) {
                    intent = Intent(context, HorizontalLessonActivity::class.java)
                }
                intent.let { letIntent ->
                    letIntent?.apply {
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
}