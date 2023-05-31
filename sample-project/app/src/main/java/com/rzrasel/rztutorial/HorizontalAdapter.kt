package com.rzrasel.rztutorial

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rzrasel.rztutorial.databinding.RecyclerviewItemHorizontalBinding

class HorizontalAdapter(val context: Context, private var modelDataList: ArrayList<DataModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var binding: RecyclerviewItemHorizontalBinding
    private lateinit var onItemClickListener: SetOnItemClickListener

    internal fun setDataList(modelDataList: ArrayList<DataModel>): HorizontalAdapter {
        this.modelDataList = modelDataList
        notifyDataSetChanged()
        return this
    }

    internal fun setOnClickListener(onItemClickListener: SetOnItemClickListener): HorizontalAdapter {
        this.onItemClickListener = onItemClickListener
        return this
    }

    //  total count of items in the list
    override fun getItemCount() = modelDataList.size

    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalAdapter.ViewHolder {

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
/*

Zaynax লিমিটেড অ্যান্ড্রয়েড এবং আইওএস-এ বিভিন্ন ধরনের অ্যাপ ডেভেলপমেন্টের জন্য সিনিয়র এবং টিম লিড হিসেবে আমাকে হায়ার করে। এখানে অ্যান্ড্রয়েড এবং আইওএস উভয় প্ল্যাটফর্মের জন্য zDrop ই-কমার্স অ্যাপ এবং Zaynax Health স্বাস্থ্য সম্পর্কিত অ্যাপ উল্লেখযোগ্য।




*/