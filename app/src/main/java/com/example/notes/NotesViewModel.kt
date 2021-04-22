package com.example.notes

import androidx.lifecycle.*
import com.example.notes.database.Notes
import com.example.notes.database.NotesRepository
import com.example.notes.timer.TimeRepository
import kotlinx.coroutines.launch

class NotesViewModel(private val repository : NotesRepository, private val timeRepository: TimeRepository):ViewModel() {
    val allWords: LiveData<List<Notes>> = repository.allWords



    fun insert(notes: Notes) = viewModelScope.launch {

        repository.insert(notes)
    }

    fun deleteall()=viewModelScope.launch {
        repository.deleteall()
        timeRepository.clear()
    }



}




class NotesViewModelFactory(private val repository: NotesRepository, private val timeRepository: TimeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NotesViewModel(repository,timeRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}