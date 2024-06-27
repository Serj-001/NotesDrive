package com.example.notesdrive.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notesdrive.data.Note
import com.example.notesdrive.data.NoteDatabase
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {

    val loadAllByDateAdded: LiveData<List<Note>>
    val countRow: LiveData<Int>
    val sumCost: LiveData<Int>
    private val repository: NoteRepository

    init {
        val noteDao = NoteDatabase.getDatabase(application).noteDao()
        repository = NoteRepository(noteDao)
        loadAllByDateAdded = repository.loadAllByDateAdded
        countRow = repository.countRow
        sumCost = repository.sumCost
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

}