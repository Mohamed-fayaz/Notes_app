package com.example.notes.timer

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TimerShowViewModel(private val timeRepository: TimeRepository) : ViewModel() {

    val allTime: LiveData<List<TimeUsed>> = timeRepository.alltimes


}


class TimerShowViewModelFactory(private val timeRepository: TimeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TimerShowViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TimerShowViewModel(timeRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}