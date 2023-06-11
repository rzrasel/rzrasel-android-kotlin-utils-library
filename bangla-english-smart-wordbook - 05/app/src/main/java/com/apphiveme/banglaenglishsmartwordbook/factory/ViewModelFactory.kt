package com.apphiveme.banglaenglishsmartwordbook.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.apphiveme.banglaenglishsmartwordbook.repository.BaseRepository
import com.apphiveme.banglaenglishsmartwordbook.repository.DashboardMenuRepository
import com.apphiveme.banglaenglishsmartwordbook.repository.LessonDataRepository
import com.apphiveme.banglaenglishsmartwordbook.viewmodel.DashboardViewModel
import com.apphiveme.banglaenglishsmartwordbook.viewmodel.LessonViewModel

class ViewModelFactory(private val repository: BaseRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DashboardViewModel::class.java) -> DashboardViewModel(
                repository as DashboardMenuRepository
            ) as T
            modelClass.isAssignableFrom(LessonViewModel::class.java) -> LessonViewModel(
                repository as LessonDataRepository
            ) as T

            else -> throw IllegalArgumentException("ViewModel class not found")
        }
    }
}