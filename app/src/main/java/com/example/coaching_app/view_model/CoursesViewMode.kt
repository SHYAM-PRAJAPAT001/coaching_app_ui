package com.example.coaching_app.view_model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CoursesViewMode : ViewModel() {

    private val _courses_state = MutableStateFlow(CoursesState())
    val courses_state : StateFlow<CoursesState> = _courses_state

    fun onCount(){
        _courses_state.value = _courses_state.value.copy(
            count = _courses_state.value.count + 1
        )
    }

    fun onLoading(){
        _courses_state.value = _courses_state.value.copy(
            isLoading = !_courses_state.value.isLoading
        )
    }
}

data class CoursesState(
    val count : Int = 0,
    val isLoading : Boolean = false
)