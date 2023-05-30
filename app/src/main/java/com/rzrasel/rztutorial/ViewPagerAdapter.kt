package com.rzrasel.rztutorial

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rzrasel.rztutorial.databinding.ViewpagerItemBinding


class ViewPagerAdapter(private val context: Context, private val arrayList: ArrayList<String>) :
    RecyclerView.Adapter<ViewPagerAdapter.MyViewHolder>() {
    private lateinit var binding: ViewpagerItemBinding
    /*private val arrayList = ArrayList<String>()

    init {
        this.arrayList = arrayList
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        /*val view: View = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(view)*/
        val inflater = LayoutInflater.from(parent.context)
        binding = ViewpagerItemBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //holder.tvName.text = arrayList[position]
        holder.bind(arrayList[position], position)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class MyViewHolder(itemView: ViewpagerItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        //var tvName: TextView

        /*init {
            //tvName = itemView.findViewById<TextView>(R.id.tvName)
        }*/
        fun bind(item: String, position: Int) {
            binding.sysTextView.text = item
        }
    }
}

/*
class ViewPagerAdapter(private val mContext: Context, private val itemList: ArrayList<String>) :
    PagerAdapter() {
    //private var layoutInflater: LayoutInflater? = null
    private lateinit var binding: ViewpagerItemBinding

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        */
/*layoutInflater = LayoutInflater.from(mContext)
        val view = layoutInflater!!.inflate(R.layout.item_viewpage_layout, container, false)*//*


        */
/*var textview: TextView = view.findViewById(R.id.slide_screen_tv)
        textview.text = itemList[position]

        container.addView(view, position)
        return view*//*

        val inflater = LayoutInflater.from(container.context)
        binding = ViewpagerItemBinding.inflate(inflater, container, false)

        binding.sysTextView.text = itemList[position]
        println("DEBUG_LOG_PRINT: itemList[position] ${itemList[position]}")

        return binding
    }

    override fun getCount(): Int {
        return itemList.size
    }

    fun getItemPosition(obj: Any?): Int {
        return POSITION_NONE
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        val view = obj as View
        container.removeView(view)
    }
}*/
