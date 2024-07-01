package com.example.notesdrive.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM note_table ORDER BY dateAdded DESC")
    fun loadAllByDateAdded(): LiveData<List<Note>>

    @Update
    suspend fun updateNote(note: Note)
//    @Query("SELECT COUNT(*) FROM note_table")
//    fun getCountFromTable(): LiveData<Int>

//    @Query("SELECT SUM(cost) FROM note_table")
//    fun getSumCost(): LiveData<Int>
}