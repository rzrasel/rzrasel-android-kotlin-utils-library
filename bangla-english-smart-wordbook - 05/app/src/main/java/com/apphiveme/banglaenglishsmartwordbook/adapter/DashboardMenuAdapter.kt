package com.apphiveme.banglaenglishsmartwordbook.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apphiveme.banglaenglishsmartwordbook.databinding.RecyclerviewItemDashboardMenuBinding
import com.apphiveme.banglaenglishsmartwordbook.model.DashboardMenuModel
import com.rzrasel.kotlinutils.AssetFileReader

class DashboardMenuAdapter(
    val context: Context,
    private var modelDataList: ArrayList<DashboardMenuModel>
) :
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
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DashboardMenuAdapter.ViewHolder {

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
            //var menuSlug: EnumDashboardMenu? = EnumDashboardMenu.NONE
            binding.apply {
                //image.setImageResource(item.image)
                /*sysTitle.text = item.title
                sysDescription.text = item.description*/
                sysTitle.text = "";
                sysDescription.text = "";
                println("DEBUG_LOG_PRINT: DashboardMenuAdapter image path ${item.bnImagePath}")
                val drawable: Drawable? = AssetFileReader.drawable(context, item.bnImagePath)
                if(drawable != null) {
                    sysImageView.setImageDrawable(drawable)
                } else {
                    println("DEBUG_LOG_PRINT: DashboardMenuAdapter image drawable is null")
                }
                /*val bitmap: Bitmap? = AssetFileReader.bitmap(context, item.enImagePath)
                bitmap?.let {
                    sysImageView.setImageBitmap(bitmap)
                } ?: run {
                    println("DEBUG_LOG_PRINT: DashboardMenuAdapter image bitmap is null")
                }*/
                /*println("DEBUG_LOG_PRINT: item.slug ${item.slug}")
                menuSlug = EnumDashboardMenu.find(item.slug)
                println("DEBUG_LOG_PRINT: menuSlug $menuSlug")*/

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