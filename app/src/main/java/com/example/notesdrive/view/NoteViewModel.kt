package com.example.notesdrive.view

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notesdrive.data.Note
import com.example.notesdrive.data.NoteDatabase
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {

    val loadAllByDateAdded: LiveData<List<Note>>
    private val repository: NoteRepository
    var noteDescription by mutableStateOf("")
    var noteCost by mutableStateOf(0)
    var noteCostType by  mutableStateOf("")

    init {
        val noteDao = NoteDatabase.getDatabase(application).noteDao()
        repository = NoteRepository(noteDao)
        loadAllByDateAdded = repository.loadAllByDateAdded
    }

    fun changeDescription(value: String) {
        noteDescription = value
    }

    fun changeCost(value: String) {
        noteCost = value.toIntOrNull()?:noteCost
    }

    fun addNote(note: Note) {
        viewModelScope.launch {
            repository.addNote(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repository.deleteNote(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            repository.updateNote(note)
        }
    }
}