package com.apphiveme.banglaenglishsmartwordbook.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apphiveme.banglaenglishsmartwordbook.databinding.RecyclerviewItemHorizontalLessonBinding
import com.apphiveme.banglaenglishsmartwordbook.model.LessonDataModel
import com.rzrasel.kotlinutils.AssetFileReader

class HorizontalLessonAdapter(
    val context: Context,
    private var modelDataList: ArrayList<LessonDataModel>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var binding: RecyclerviewItemHorizontalLessonBinding
    private lateinit var onItemClickListener: SetOnItemClickListener

    internal fun setDataList(modelDataList: ArrayList<LessonDataModel>): HorizontalLessonAdapter {
        this.modelDataList = modelDataList
        notifyDataSetChanged()
        return this
    }

    internal fun setOnClickListener(onItemClickListener: SetOnItemClickListener): HorizontalLessonAdapter {
        this.onItemClickListener = onItemClickListener
        return this
    }

    //  total count of items in the list
    //override fun getItemCount() = modelDataList.size
    override fun getItemCount(): Int {
        return modelDataList.size
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HorizontalLessonAdapter.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        binding = RecyclerviewItemHorizontalLessonBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(modelDataList[position], position)
    }

    inner class ViewHolder(itemView: RecyclerviewItemHorizontalLessonBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        fun bind(item: LessonDataModel, position: Int) {
            if(item.id < 0) {
                //
            }
            binding.apply {
                itemView.tag = position + 1
                val drawable: Drawable? = AssetFileReader.drawable(context, item.smallImagePath)
                println("DEBUG_LOG_PRINT: small image path " + item.smallImagePath)
                drawable?.let {
                    sysImageView.setImageDrawable(drawable)
                } ?: {
                    println("DEBUG_LOG_PRINT: DashboardMenuAdapter image drawable is null")
                }
                //sysTextViewTitle.text = item.bengali
                //sysTextViewDescription.text = item.desc
                sysTextViewTitle.visibility = View.GONE
                sysTextViewDescription.visibility = View.GONE
            }
            onItemClickListener.let {
                itemView.setOnClickListener {
                    onItemClickListener.setOItemClickListener(itemView, item, position)
                }
            }
        }
    }

    interface SetOnItemClickListener {
        fun setOItemClickListener(view: View, itemData: LessonDataModel, position: Int)
    }
}