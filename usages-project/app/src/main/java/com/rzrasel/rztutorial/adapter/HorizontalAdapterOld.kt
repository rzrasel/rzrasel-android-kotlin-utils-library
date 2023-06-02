package com.rzrasel.rztutorial.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rzrasel.rztutorial.DataModel
import com.rzrasel.rztutorial.databinding.RecyclerviewItemHorizontalBinding

class HorizontalAdapterOld(val context: Context, private var modelDataList: ArrayList<DataModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var binding: RecyclerviewItemHorizontalBinding
    private lateinit var onItemClickListener: SetOnItemClickListener

    internal fun setDataList(modelDataList: ArrayList<DataModel>): HorizontalAdapterOld {
        this.modelDataList = modelDataList
        notifyDataSetChanged()
        return this
    }
    internal fun setOnClickListener(onItemClickListener: SetOnItemClickListener): HorizontalAdapterOld {
        this.onItemClickListener = onItemClickListener
        return this
    }
    //  total count of items in the list
    override fun getItemCount() = modelDataList.size

    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalAdapterOld.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        binding = RecyclerviewItemHorizontalBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(modelDataList[position], position)
    }

    inner class ViewHolder(itemView: RecyclerviewItemHorizontalBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        fun bind(item: DataModel, position: Int) {
            binding.apply {
                sysImageView.setImageResource(item.image)
                sysTextViewTitle.text = item.title
                sysTextViewDescription.text = item.desc
            }
            /*if (onItemClickListener != null) {
                itemView.setOnClickListener {
                    onItemClickListener.setOItemClickListener(itemView, item, position)
                }
            }*/
        }
    }

    interface SetOnItemClickListener {
        fun setOItemClickListener(view: View, itemData: DataModel, position: Int)
    }
}