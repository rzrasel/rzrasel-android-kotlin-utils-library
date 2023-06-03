package com.rzrasel.rztutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.rzrasel.rztutorial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var photoAdapter: PhotoAdapter
    private var dataList = ArrayList<DataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onInitView()
    }

    private fun onInitView() {
        photoAdapter = PhotoAdapter(applicationContext, ArrayList())
        onLoadData()
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(applicationContext, 2)
            adapter = photoAdapter
        }
        onLoadData()
        photoAdapter.setDataList(dataList)
            .setOnClickListener(object : PhotoAdapter.SetOnItemClickListener {
                override fun setOItemClickListener(view: View, itemData: DataModel, position: Int) {
                    Toast.makeText(this@MainActivity, "Test ${itemData.title}", Toast.LENGTH_LONG).show()
                }
            })
    }

    private fun onLoadData() {
        dataList = PhotoModelDataSet.getData()
    }
    val listener = object: PhotoAdapter.SetOnItemClickListener {
        override fun setOItemClickListener(view: View, itemData: DataModel, position: Int) {
            //TODO("Not yet implemented")
        }
    }
}
/*
https://github.com/nameisjayant/Android-ViewModel-Factory-Example-In-Kotlin
https://pasindulaksara.medium.com/recyclerview-with-grid-layout-in-kotlin-414d780c47ae
https://medium.com/geekculture/managing-ui-with-kotlin-sealed-classes-1ee674f1836f

*/