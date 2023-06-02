package com.rzrasel.rztutorial.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rzrasel.rztutorial.repository.BaseRepository
import com.rzrasel.rztutorial.repository.DashboardMenuRepository
import com.rzrasel.rztutorial.repository.LessonDataRepository
import com.rzrasel.rztutorial.viewmodel.DashboardViewModel
import com.rzrasel.rztutorial.viewmodel.LessonViewModel

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