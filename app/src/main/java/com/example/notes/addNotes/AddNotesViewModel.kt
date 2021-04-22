package com.example.notes

import androidx.lifecycle.*
import com.example.notes.database.Notes
import com.example.notes.database.NotesRepository
import com.example.notes.timer.TimeRepository
import com.example.notes.timer.TimeUsed
import kotlinx.coroutines.launch

class AddNotesViewModel(private val repository : NotesRepository, private val timeRepository: TimeRepository):ViewModel() {
    val allWords: LiveData<List<Notes>> = repository.allWords
    val time = TimeUsed()
    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(notes: Notes) = viewModelScope.launch {

        repository.insert(notes)
    }
    fun onStartTracking(){

       time.startTimeMilli = System.currentTimeMillis()
    }

    fun onStopTracking()= viewModelScope.launch {

      time.endTimeMilli = System.currentTimeMillis()

        timeRepository.insert(time)


    }

}


class AddNotesViewModelFactory(private val repository: NotesRepository, private val timeRepository : TimeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddNotesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddNotesViewModel(repository,timeRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}