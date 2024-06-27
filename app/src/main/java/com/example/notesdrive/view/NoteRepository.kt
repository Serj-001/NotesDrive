package com.example.notesdrive.view

import androidx.lifecycle.LiveData
import com.example.notesdrive.data.Note
import com.example.notesdrive.data.NoteDao

class NoteRepository(private val noteDao: NoteDao) {

    val loadAllByDateAdded: LiveData<List<Note>> = noteDao.loadAllByDateAdded()
    val countRow: LiveData<Int> = noteDao.getCountFromTable()
    val sumCost: LiveData<Int> = noteDao.getSumCost()

    suspend fun addNote(note: Note) {
        noteDao.addNote(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }
}