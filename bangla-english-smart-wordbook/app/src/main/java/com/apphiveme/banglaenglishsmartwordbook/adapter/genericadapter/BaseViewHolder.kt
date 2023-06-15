package com.apphiveme.banglaenglishsmartwordbook.adapter.genericadapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class BaseViewHolder<T> internal constructor(
    private val binding: ViewBinding,
    private val expression: (T, ViewBinding) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: T) {
        expression(item, binding)
    }
}