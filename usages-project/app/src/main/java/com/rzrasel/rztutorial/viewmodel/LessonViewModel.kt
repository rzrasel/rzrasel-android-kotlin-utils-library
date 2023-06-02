package com.rzrasel.rztutorial.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rzrasel.rztutorial.enumeration.EnumDashboardMenu
import com.rzrasel.rztutorial.network.DataResource
import com.rzrasel.rztutorial.repository.LessonDataRepository
import kotlinx.coroutines.launch

class LessonViewModel(private val repository: LessonDataRepository) :
    ViewModel() {
    /*private val _response: MutableLiveData<DataResource<ArrayList<TutorialDataModel>>> =
        MutableLiveData()
    val response: LiveData<DataResource<ArrayList<TutorialDataModel>>>
        get() = _response*/
    private val _response: MutableLiveData<DataResource<Any?>> =
        MutableLiveData()
    val response: LiveData<DataResource<Any?>>
        get() = _response

    fun getData(context: Context, enumDashboardMenu: EnumDashboardMenu?) = viewModelScope.launch {
        //var apiData: DataResource<Any?> = repository.getTutorialData(context, enumDashboardMenu)
        _response.value = repository.getTutorialData(context, enumDashboardMenu)
    }
}