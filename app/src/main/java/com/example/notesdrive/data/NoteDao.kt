package com.example.notesdrive.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM note_table ORDER BY dateAdded ASC")
    fun loadAllByDateAdded(): LiveData<List<Note>>

    @Query("SELECT COUNT(*) FROM note_table")
    fun getCountFromTable(): LiveData<Int>

    @Query("SELECT SUM(cost) FROM note_table")
    fun getSumCost(): LiveData<Int>
}