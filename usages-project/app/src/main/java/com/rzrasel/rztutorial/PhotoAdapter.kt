package com.rzrasel.rztutorial

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rzrasel.rztutorial.databinding.RecyclerLayoutItemBinding

class PhotoAdapter(val context: Context, private var modelDataList: ArrayList<DataModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var binding: RecyclerLayoutItemBinding
    private lateinit var onItemClickListener: SetOnItemClickListener

    internal fun setDataList(modelDataList: ArrayList<DataModel>): PhotoAdapter {
        this.modelDataList = modelDataList
        notifyDataSetChanged()
        return this
    }

    internal fun setOnClickListener(onItemClickListener: SetOnItemClickListener): PhotoAdapter {
        this.onItemClickListener = onItemClickListener
        return this
    }

    //  total count of items in the list
    override fun getItemCount() = modelDataList.size

    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoAdapter.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        binding = RecyclerLayoutItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(modelDataList[position], position)
    }

    inner class ViewHolder(itemView: RecyclerLayoutItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        fun bind(item: DataModel, position: Int) {
            binding.apply {
                image.setImageResource(item.image)
                title.text = item.title
                desc.text = item.desc
            }
            if (onItemClickListener != null) {
                itemView.setOnClickListener {
                    onItemClickListener.setOItemClickListener(itemView, item, position)
                }
            }
        }
    }

    interface SetOnItemClickListener {
        fun setOItemClickListener(view: View, itemData: DataModel, position: Int)
    }
}