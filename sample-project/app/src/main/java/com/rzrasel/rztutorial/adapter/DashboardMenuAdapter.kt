package com.rzrasel.rztutorial.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rzrasel.rztutorial.DataModel
import com.rzrasel.rztutorial.PhotoAdapter
import com.rzrasel.rztutorial.databinding.RecyclerLayoutItemBinding
import com.rzrasel.rztutorial.databinding.RecyclerviewItemDashboardMenuBinding
import com.rzrasel.rztutorial.model.DashboardMenuModel

class DashboardMenuAdapter(val context: Context, private var modelDataList: ArrayList<DashboardMenuModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var binding: RecyclerviewItemDashboardMenuBinding
    private var onItemClickListener: SetOnItemClickListener? = null

    internal fun setDataList(modelDataList: ArrayList<DashboardMenuModel>) {
        this.modelDataList = modelDataList
        notifyDataSetChanged()
        //return this
    }

    internal fun setOnClickListener(onItemClickListener: SetOnItemClickListener): DashboardMenuAdapter {
        this.onItemClickListener = onItemClickListener
        return this
    }

    //  total count of items in the list
    override fun getItemCount() = modelDataList.size

    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardMenuAdapter.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        binding = RecyclerviewItemDashboardMenuBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(modelDataList[position], position)
    }

    inner class ViewHolder(itemView: RecyclerviewItemDashboardMenuBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        fun bind(item: DashboardMenuModel, position: Int) {
            binding.apply {
                //image.setImageResource(item.image)
                sysTitle.text = item.title
                //desc.text = item.desc
            }
            if (onItemClickListener != null) {
                itemView.setOnClickListener {
                    onItemClickListener!!.setOItemClickListener(itemView, item, position)
                }
            }
        }
    }

    interface SetOnItemClickListener {
        fun setOItemClickListener(view: View, itemData: DashboardMenuModel, position: Int)
    }
}