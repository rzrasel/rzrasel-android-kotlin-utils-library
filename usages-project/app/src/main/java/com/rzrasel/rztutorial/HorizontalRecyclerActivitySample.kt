package com.rzrasel.rztutorial

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rzrasel.kotlinutils.DelayBitPattern
import com.rzrasel.kotlinutils.RandomValue
import com.rzrasel.rztutorial.adapter.HorizontalAdapter
import com.rzrasel.rztutorial.adapter.HorizontalAdapterOld
import com.rzrasel.rztutorial.databinding.ActivityHorizontalRecyclerBinding
import com.rzrasel.rztutorial.databinding.ActivityHorizontalRecyclerMainBinding
import com.rzrasel.rztutorial.utils.ProPrefDataStore
import kotlinx.coroutines.launch

class HorizontalRecyclerActivitySample : AppCompatActivity() {
    private lateinit var binding: ActivityHorizontalRecyclerMainBinding
    private lateinit var horizontalAdapter: HorizontalAdapterOld
    private var dataList = ArrayList<DataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHorizontalRecyclerMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        println("DEBUG_LOG_PRINT: random value - ${RandomValue.intRange(1, 10)}")
        println("DEBUG_LOG_PRINT: random value - ${(1..10).random()}")
        println("DEBUG_LOG_PRINT: currentMillis() - ${DelayBitPattern.currentMillis()}")
        println("DEBUG_LOG_PRINT: getMillis() - ${DelayBitPattern.getMillis(hour = 1, second = 0)}")
        println(
            "DEBUG_LOG_PRINT: currentMillis() - ${
                DelayBitPattern.isPassed(
                    DelayBitPattern.currentMillis() - 1000,
                    DelayBitPattern.currentMillis(),
                    2000
                )
            }"
        )
        val prefDataStore: ProPrefDataStore = ProPrefDataStore(this@HorizontalRecyclerActivitySample)
        lifecycleScope.launch {
            prefDataStore.writeString("test_key", "test data in data store")
        }
        //
        lifecycleScope.launch {
            /*prefDataStore.readString("test_key").collect {
                println("DEBUG_LOG_PRINT: prefDataStore profData $it")
            }*/
            prefDataStore.read(ProPrefDataStore.DataType.STRING_KEY, "test_key").collect {
                println("DEBUG_LOG_PRINT: prefDataStore read profData $it")
            }
        }
        //
        //prefDataStore = ProPrefDataStore(this@HorizontalRecyclerActivity)
        lifecycleScope.launch {
            prefDataStore.write(
                ProPrefDataStore.DataType.STRING_KEY,
                "test_key_two",
                "test data in data store two"
            )
        }
        lifecycleScope.launch {
            /*prefDataStore.readString("test_key").collect {
                println("DEBUG_LOG_PRINT: prefDataStore profData $it")
            }*/
            prefDataStore.read(ProPrefDataStore.DataType.STRING_KEY, "test_key_two").collect {
                println("DEBUG_LOG_PRINT: prefDataStore read profData test_key_two $it")
            }
        }
        //
        //prefDataStore = ProPrefDataStore(this@HorizontalRecyclerActivity)
        lifecycleScope.launch {
            prefDataStore.writeString("test_key_three", "test data in data store")
        }
        lifecycleScope.launch {
            /*prefDataStore.readString("test_key").collect {
                println("DEBUG_LOG_PRINT: prefDataStore profData $it")
            }*/
            prefDataStore.read(ProPrefDataStore.DataType.STRING_KEY, "test_key_three").collect {
                println("DEBUG_LOG_PRINT: prefDataStore read profData test_key_three $it")
            }
        }
        //onInitView()
    }

    private fun onInitView() {
        horizontalAdapter = HorizontalAdapterOld(applicationContext, ArrayList())
        onLoadData()
        binding.sysRecyclerView.apply {
            setHasFixedSize(true)
            //layoutManager = GridLayoutManager(applicationContext, 2)
            layoutManager = LinearLayoutManager(applicationContext, RecyclerView.HORIZONTAL, false)
            adapter = horizontalAdapter
        }
        onLoadData()
        horizontalAdapter.setDataList(dataList)
            .setOnClickListener(object : HorizontalAdapterOld.SetOnItemClickListener {
                override fun setOItemClickListener(view: View, itemData: DataModel, position: Int) {
                    Toast.makeText(
                        this@HorizontalRecyclerActivitySample,
                        "Test ${itemData.title}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
    }

    private fun onLoadData() {
        dataList = PhotoModelDataSet.getData()
    }

    val listener = object : HorizontalAdapterOld.SetOnItemClickListener {
        override fun setOItemClickListener(view: View, itemData: DataModel, position: Int) {
            //TODO("Not yet implemented")
        }
    }
}