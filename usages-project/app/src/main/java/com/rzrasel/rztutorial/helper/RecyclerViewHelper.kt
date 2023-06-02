package com.rzrasel.rztutorial.helper

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.round

object RecyclerViewHelper {
    fun onGridColumnCount(context: Context, recyclerView: RecyclerView, gridItemWidth: Float, modelSize: Int) {
        //val gridItemWidth: Float = resources.getDimension(R.dimen.grid_item_width)
        //val gridContentPadding = resources.getDimension(R.dimen.grid_content_padding)
        recyclerView.post {
            val viewWidth: Int = recyclerView.width
            val itemCount: Float = viewWidth / gridItemWidth
            var roundItemCount: Int = round(itemCount).toInt()
            if(roundItemCount > modelSize) {
                roundItemCount = modelSize
            }
            if(roundItemCount <= 0) {
                roundItemCount = 1
            }
            //val itemSpacing: Float = roundItemCount * gridContentPadding
            //val itemWidth: Float = (viewWidth - itemSpacing) / roundItemCount.toFloat()
            var itemWidth: Float = viewWidth / roundItemCount.toFloat()
            if(itemWidth > gridItemWidth * 1.5) {
                itemWidth = (gridItemWidth * 1.15).toFloat()
            }
            /*println("DEBUG_LOG_PRINT: gridItemWidth $gridItemWidth")
            println("DEBUG_LOG_PRINT: viewWidth $viewWidth")
            println("DEBUG_LOG_PRINT: itemRound $itemCount")
            println("DEBUG_LOG_PRINT: roundItemCount $roundItemCount")
            println("DEBUG_LOG_PRINT: itemFloor $itemWidth")*/
            val layoutManager = GridLayoutManager(context, roundItemCount)
            recyclerView.layoutManager = layoutManager
        }
    }
}